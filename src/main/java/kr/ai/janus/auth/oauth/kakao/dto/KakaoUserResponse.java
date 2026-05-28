package kr.ai.janus.auth.oauth.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoUserResponse(
        Long id,
        @JsonProperty("properties") Properties properties
) {
    public String nickname() {
        if (properties == null) {
            return null;
        }
        return properties.nickname();
    }

    public record Properties(
            @JsonProperty("nickname") String nickname
    ) {}
}
