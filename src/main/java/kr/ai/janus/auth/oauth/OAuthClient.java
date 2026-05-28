package kr.ai.janus.auth.oauth;

public interface OAuthClient {
    OAuthProvider getProvider();
    OAuthUserInfo fetchUserInfo(String code);
}
