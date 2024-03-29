/*
 * copyright 2012, gash
 * 
 * Gash licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package poke.server.routing;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import poke.server.queue.ChannelQueue;
import poke.server.queue.QueueFactory;
import poke.server.resources.ResourceFactory;
import poke.server.resources.Resource;

import poke.server.conf.ServerConf;


/**
 * As implemented, this server handler does not share queues or worker threads
 * between connections. A new instance of this class is created for each socket
 * connection.
 * 
 * This approach allows clients to have the potential of an immediate response
 * from the server (no backlog of items in the queue); within the limitations of
 * the VM's thread scheduling. This approach is best suited for a low/fixed
 * number of clients (e.g., infrastructure).
 * 
 * Limitations of this approach is the ability to support many connections. For
 * a design where many connections (short-lived) are needed a shared queue and
 * worker threads is advised (not shown).
 * 
 * @author gash
 * 
 */
public class ServerHandler extends SimpleChannelUpstreamHandler {
	protected static Logger logger = LoggerFactory.getLogger("server");

	private ChannelQueue queue;
	
	public ServerHandler() {
		//logger.info("** ServerHandler created **");
	}

	/**
	 * override this method to provide processing behavior
	 * 
	 * @param msg
	 */
	public void handleMessage(eye.Comm.Request req, Channel channel) {
		if (req == null) {
			logger.error("ERROR: Unexpected content - null");
			return;
		}
			
		Resource resource = ResourceFactory.getInstance().resourceInstance(req.getHeader().getRoutingId());
		ServerConf.ResourceConf rConf = ResourceFactory.getInstance().getResourceConf(req.getHeader().getRoutingId());
		
		if(resource == null || !rConf.isEnabled()) { 
			//SNE : If resource not available or enabled route the request to slave
			queueInstance(channel).routeRequest(req);
		}
		// processing is deferred to the worker threads
		else{
			logger.info("ENQ REQ");
			
			queueInstance(channel).enqueueRequest(req);
		}
	}

	/**
	 * find the queue. Note this cannot return null.
	 * 
	 * @param channel
	 * @return
	 */
	private ChannelQueue queueInstance(Channel channel) {
		// if a single queue is needed, this is where we would obtain a
		// handle to it.

		if (queue != null)
			return queue;
		else {
			queue = QueueFactory.getInstance(channel);

			//pass config to queue
			//queue.setConfig(this.cfg);
			
			// on close remove from queue
			channel.getCloseFuture().addListener(
					new ConnectionClosedListener(queue));
		}

		return queue;
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		handleMessage((eye.Comm.Request) e.getMessage(), e.getChannel());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		logger.error(
				"ServerHandler error, closing channel, reason: " + e.getCause(),
				e);
		e.getCause().printStackTrace();
		e.getChannel().close();
	}

	public static class ConnectionClosedListener implements
			ChannelFutureListener {
		private ChannelQueue sq;

		public ConnectionClosedListener(ChannelQueue sq) {
			this.sq = sq;
		}

		@Override
		public void operationComplete(ChannelFuture future) throws Exception {
			if (sq != null)
				sq.shutdown(true);
			sq = null;
		}

	}
}
