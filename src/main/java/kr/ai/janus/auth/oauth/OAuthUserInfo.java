package kr.ai.janus.auth.oauth;

public record OAuthUserInfo(
        String providerUserId,
        String nickname
) {}
