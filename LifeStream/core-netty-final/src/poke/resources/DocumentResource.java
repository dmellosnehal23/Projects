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
package poke.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import poke.server.resources.Resource;
import poke.server.resources.ResourceUtil;
import poke.server.storage.InMemoryStorage;
import poke.server.storage.Storage;
import eye.Comm.PayloadReply;
import eye.Comm.Request;
import eye.Comm.Response;
import eye.Comm.Header.ReplyStatus;

public class DocumentResource implements Resource {
	protected static Logger logger = LoggerFactory.getLogger("server");
	
	@Override
	public Response process(Request request) {
		//Storage dataStore = InMemoryStorage.getInstance();
		Response reply;
		
		// TODO Auto-generated method stub
		if(request.getHeader().getRoutingId() == eye.Comm.Header.Routing.DOCADD) {
			try {
				logger.info("DOC REC " + request.toString());
				//dataStore.createNameSpace(request.getBody().getSpace());
			} catch (RuntimeException e) {
				reply = ResourceUtil.buildError(request.getHeader(),
						ReplyStatus.FAILURE,
						e.getMessage());
			}
		}
		else if (request.getHeader().getRoutingId() == eye.Comm.Header.Routing.DOCREMOVE) {
			
		}
		else if (request.getHeader().getRoutingId() == eye.Comm.Header.Routing.DOCFIND) {
			
		}
		
		Response.Builder r = Response.newBuilder();
		r.setHeader(ResourceUtil.buildHeaderFrom(request.getHeader(),
				ReplyStatus.SUCCESS, null));
		eye.Comm.PayloadReply.Builder p = PayloadReply.newBuilder();
        r.setBody(p.build());
        reply = r.build();
		
        return reply;
	}

}
