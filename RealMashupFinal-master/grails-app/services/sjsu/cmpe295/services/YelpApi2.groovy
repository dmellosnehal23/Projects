package sjsu.cmpe295.services

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

class YelpApi2 extends DefaultApi10a {

		@Override
		public String getAccessTokenEndpoint() {
			return null;
		}

		@Override
		public String getAuthorizationUrl(Token arg0) {
			return null;
		}

		@Override
		public String getRequestTokenEndpoint() {
			return null;
		}
	}
