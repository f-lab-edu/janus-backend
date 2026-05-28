package kr.ai.janus.auth.oauth;

public class UnsupportedProviderException extends RuntimeException {

    public UnsupportedProviderException(OAuthProvider provider) {
        super("지원하지 않는 provider: " + provider);
    }

    public UnsupportedProviderException(String provider, Throwable cause) {
        super("지원하지 않는 provider: " + provider, cause);
    }
}
