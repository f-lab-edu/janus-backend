package kr.ai.janus.auth.oauth.kakao;

import kr.ai.janus.auth.oauth.OAuthClient;
import kr.ai.janus.auth.oauth.OAuthProvider;
import kr.ai.janus.auth.oauth.OAuthUserInfo;
import kr.ai.janus.auth.oauth.kakao.dto.KakaoTokenResponse;
import kr.ai.janus.auth.oauth.kakao.dto.KakaoUserResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Component
public class KakaoOAuthClient implements OAuthClient {

    private static final String TOKEN_URI = "https://kauth.kakao.com/oauth/token";
    private static final String USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";

    private final KakaoProperties properties;
    private final RestClient restClient;

    public KakaoOAuthClient(KakaoProperties properties) {
        this.properties = properties;
        this.restClient = RestClient.create();
    }

    @Override
    public OAuthProvider getProvider() {
        return OAuthProvider.KAKAO;
    }

    @Override
    public OAuthUserInfo fetchUserInfo(String code) {
        String accessToken = requestAccessToken(code);
        return requestUserInfo(accessToken);
    }

    private String requestAccessToken(String code) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "authorization_code");
        form.add("client_id", properties.restApiKey());
        form.add("client_secret", properties.clientSecret());
        form.add("redirect_uri", properties.redirectUri());
        form.add("code", code);

        KakaoTokenResponse tokenResponse = restClient.post()
                .uri(TOKEN_URI)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(form)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (req, res) -> {
                    throw new KakaoAuthException("카카오 토큰 발급 실패");
                })
                .body(KakaoTokenResponse.class);

        if (tokenResponse == null || tokenResponse.accessToken() == null) {
            throw new KakaoAuthException("카카오 토큰 응답이 올바르지 않습니다");
        }
        return tokenResponse.accessToken();
    }

    private OAuthUserInfo requestUserInfo(String accessToken) {
        KakaoUserResponse userResponse = restClient.get()
                .uri(USER_INFO_URI)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (req, res) -> {
                    throw new KakaoAuthException("카카오 사용자 정보 조회 실패");
                })
                .body(KakaoUserResponse.class);

        if (userResponse == null || userResponse.id() == null) {
            throw new KakaoAuthException("카카오 사용자 정보 응답이 올바르지 않습니다");
        }
        return new OAuthUserInfo(String.valueOf(userResponse.id()), userResponse.nickname());
    }
}
