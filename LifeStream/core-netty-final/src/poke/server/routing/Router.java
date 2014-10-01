package poke.server.routing;

import poke.monitor.*;

import java.lang.Thread.State;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Executors;
import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import poke.server.conf.ServerConf;
import poke.server.queue.ChannelQueue;
import poke.server.queue.PerChannelQueue;
import poke.server.resources.ResourceUtil;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;

import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import eye.Comm.Request;
import eye.Comm.Response;
import eye.Comm.Header.ReplyStatus;

//SNE : Wrote the Router class similar to PerChannelQueue InboundWorker Thread
public class Router {
	protected static Logger logger = LoggerFactory.getLogger("server");
	
	//SNE : queue to store incoming route msg
	private LinkedBlockingDeque<com.google.protobuf.GeneratedMessage> rbound;
	private RouteWorker rworker;
	private ChannelQueue sq;
	
	
	public Router(ThreadGroup tgroup, ChannelQueue sq) {
	
		this.sq = sq;
		
		//SNE : Initialize queue and worker thread
		rbound = new LinkedBlockingDeque<com.google.protobuf.GeneratedMessage>();
		
		rworker = new RouteWorker(tgroup, 1, sq);
		rworker.start();
	}
	
	private Request resolveConflict(Request req, final String activeSlaveNodeId) {
		
		//SNE : check if the slave server node id is not in list of originators e.g "client1:one"
		if(req.getHeader().getOriginator().contains(activeSlaveNodeId)) {
			logger.error("Request loop detected");
			Response reply = ResourceUtil.buildError(req.getHeader(),
					ReplyStatus.FAILURE,
					"Resource not available");
			
			//SEN : Send response back
			this.sq.enqueueResponse(reply);
			
			return null;
		}
		
		//logger.info("R1 : " + req.toString());
		
		//SNE : Add current server nod to list of originator
		//		Fetch the Header Builder from request header
		eye.Comm.Header.Builder h = req.getHeader().toBuilder();
		//SNE : Append server node id to the originator list e.g "client:one"
		String serverNodeId = ActivityMonitor.getInstance().getServerConf().getServer().getGeneral().get("node.id");
		h.setOriginator(req.getHeader().getOriginator() + ":" + serverNodeId);
		
		//SNE Fetch the Request Builder from the request
		eye.Comm.Request.Builder b = req.toBuilder();
		//SNE : Merge header back into request
		b.mergeHeader(h.build());
		
		req = b.build();
		
		//logger.info("R2 : " + req.toString());
		
		try{
		Thread.sleep(2000);
		}catch(Exception e) {}
		
		
		return req;
	}
	
	//SNE : RouterWorker to read request from queue and forward to appropriate server 
	protected class RouteWorker extends Thread {
		int workerId;
		private PerChannelQueue sq;
		boolean forever = true;
		private ClientBootstrap bootstrap;
		
		//SNE : Lookup to maintain port ID to channel mapping. Implemented to we can resue the connections.
		private Map<Integer, Channel> channelLookup = new HashMap<Integer, Channel>();
		
		
		public RouteWorker(ThreadGroup tgrp, int workerId, ChannelQueue sq) {
			super(tgrp, "rbound-" + workerId);
			this.workerId = workerId;
			this.sq = (PerChannelQueue)sq;
			
			if (rbound == null)
				throw new RuntimeException(
						"connection worker detected null queue");

				//SNE :associate decoder with bootstrap
				this.bootstrap = new ClientBootstrap(
									new NioClientSocketChannelFactory(
										Executors.newCachedThreadPool(),
										Executors.newCachedThreadPool()));

				bootstrap.setOption("connectTimeoutMillis", 10000);
				bootstrap.setOption("tcpNoDelay", true);
				bootstrap.setOption("keepAlive", true);

			//SNE : Set up the pipeline factory.
			this.bootstrap.setPipelineFactory(new RouteDecoderPipeline(this.sq));
		}

		//SNE : Method to create a Channel to a particular host and port
		private Channel connect(String host, int port) {
			
			ChannelFuture future = this.bootstrap.connect(new InetSocketAddress(host, port));

			//SNE : wait for the connection to establish
			future.awaitUninterruptibly();

			if (future.isDone() && future.isSuccess())
				return future.getChannel();
			else
				throw new RuntimeException(
						"Not able to establish connection to server");
	
		}
		
		@Override
		public void run() {

			while (true) {
				if (!forever && rbound.size() == 0)
					break;

				try {
					Channel ch = null;
					
					// block until a message is enqueued
					Request req = ((Request) rbound.take());
					//logger.info("ROUTE MSG - " + req.getHeader().toString());
							
					//SNE :request Monitor for active servers
					ServerConf.SlaveConf sConf = ActivityMonitor.getInstance().getActiveSlave();
					if(sConf == null) {
						logger.error("No Server Available");
						//SNE : Generate error response
						Response reply = ResourceUtil.buildError(req.getHeader(),
								ReplyStatus.FAILURE,
								"Resource not available");
						
						//SEN : Send response back
						this.sq.enqueueResponse(reply);
						
						continue;
					}
					
					Integer activeSlavePort = Integer.parseInt(sConf.getPort());
					String activeSlaveNodeId = sConf.getNodeId();
					
					//SNE :  If conflict, send error response and continue
					if((req = resolveConflict(req, activeSlaveNodeId)) == null) continue;

					
					//logger.info("AVAILABLE - " + activeSlavePort);
					if(this.channelLookup.containsKey(activeSlavePort)) {
						//SNE :check if channel exists
						ch = this.channelLookup.get(activeSlavePort);
					}else {
						//SNE :if not create one
						ch = this.connect("localhost", activeSlavePort);
						
						//SNE : Add Channel to lookup for future use
						if(ch != null && !this.channelLookup.containsKey(activeSlavePort)){
							this.channelLookup.put(activeSlavePort, ch);
						}
					}
					
					if ( ch != null && ch.isOpen() && ch.isWritable()) {
						
						//SNE : Route the message
						RouteHandler handler = ch.getPipeline().get(RouteHandler.class);

						logger.info("ROUTING to " + activeSlavePort);
						logger.info("ROUTE CHANNEL INFO " + ch.getLocalAddress().toString());
						//logger.info(req.toString());
						if (!handler.send(req))
							rbound.putFirst(req);
					}
					else {
						rbound.putFirst(req);
						
						//SNE: channel dead and in lookup. remove from lookup
						if(this.channelLookup.containsKey(activeSlavePort)) this.channelLookup.remove(activeSlavePort);
					}
				
				} catch (InterruptedException ie) {
					break;
				} catch (Exception e) {
					Router.logger.error(
							"Unexpected communcation failure", e);
					break;
				}
				
			}

			if (!forever) {
				Router.logger.info("connection queue closing");
			}
		}
	}
	
	//SNE : method to populate the queue with msg to be routed
	public void routeRequest(Request req) throws InterruptedException {			
		this.rbound.put(req);
	}

	public class CloseListener implements ChannelFutureListener {
		private ChannelQueue sq;

		public CloseListener(ChannelQueue sq) {
			this.sq = sq;
		}

		@Override
		public void operationComplete(ChannelFuture future) throws Exception {
			sq.shutdown(true);
		}
	}
	
	//SNE : method to close all connections in case of failure.
	public void shutdown(boolean hard) {
		rbound.clear();
		
		if (rworker != null) {
			rworker.forever = false;
			if (rworker.getState() == State.BLOCKED
					|| rworker.getState() == State.WAITING)
				rworker.interrupt();
			rworker = null;
		}
	}
	
}
