package kr.co.Mua.Social;

public class GoogleOAuthRequest {
    
	public GoogleOAuthRequest(String redirectUri, String clientId, String clientSecret, String code,
			String responseType, String scope, String accessType, String grantType, String state,
			String includeGrantedScopes, String loginHint, String prompt) {
		super();
		this.redirectUri = redirectUri;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.code = code;
		this.responseType = responseType;
		this.scope = scope;
		this.accessType = accessType;
		this.grantType = grantType;
		this.state = state;
		this.includeGrantedScopes = includeGrantedScopes;
		this.loginHint = loginHint;
		this.prompt = prompt;
	}
	
	public GoogleOAuthRequest() {
		// TODO Auto-generated constructor stub
	}
	
    public static GoogleOAuthRequestBuilder builder() {
        return new GoogleOAuthRequestBuilder();
    }

    public static class GoogleOAuthRequestBuilder {
        private GoogleOAuthRequest instance = new GoogleOAuthRequest();

        public GoogleOAuthRequestBuilder redirectUri(String redirectUri) {
            instance.redirectUri = redirectUri;
            return this;
        }

        public GoogleOAuthRequestBuilder clientId(String clientId) {
            instance.clientId = clientId;
            return this;
        }
        public GoogleOAuthRequestBuilder clientSecret(String clientSecret) {
            instance.clientSecret = clientSecret;
            return this;
        }

        public GoogleOAuthRequestBuilder code(String code) {
            instance.code = code;
            return this;
        }

        public GoogleOAuthRequestBuilder responseType(String responseType) {
            instance.responseType = responseType;
            return this;
        }

        public GoogleOAuthRequestBuilder scope(String scope) {
            instance.scope = scope;
            return this;
        }

        public GoogleOAuthRequestBuilder accessType(String accessType) {
            instance.accessType = accessType;
            return this;
        }

        public GoogleOAuthRequestBuilder grantType(String grantType) {
            instance.grantType = grantType;
            return this;
        }

        public GoogleOAuthRequestBuilder state(String state) {
            instance.state = state;
            return this;
        }

        public GoogleOAuthRequestBuilder includeGrantedScopes(String includeGrantedScopes) {
            instance.includeGrantedScopes = includeGrantedScopes;
            return this;
        }

        public GoogleOAuthRequestBuilder loginHint(String loginHint) {
            instance.loginHint = loginHint;
            return this;
        }

        public GoogleOAuthRequestBuilder prompt(String prompt) {
            instance.prompt = prompt;
            return this;
        }

        public GoogleOAuthRequest build() {
            return instance;
        }
    }
    
}
