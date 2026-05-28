package kr.ai.janus.auth.service;

import kr.ai.janus.auth.dto.AuthResponse;
import kr.ai.janus.auth.jwt.JwtProvider;
import kr.ai.janus.auth.oauth.OAuthClient;
import kr.ai.janus.auth.oauth.OAuthProvider;
import kr.ai.janus.auth.oauth.OAuthUserInfo;
import kr.ai.janus.auth.oauth.UnsupportedProviderException;
import kr.ai.janus.user.User;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthService {

    private final Map<OAuthProvider, OAuthClient> clients;
    private final OAuthUserService oauthUserService;
    private final JwtProvider jwtProvider;

    public AuthService(List<OAuthClient> oauthClients,
                       OAuthUserService oauthUserService,
                       JwtProvider jwtProvider) {
        this.clients = new EnumMap<>(OAuthProvider.class);
        for (OAuthClient client : oauthClients) {
            this.clients.put(client.getProvider(), client);
        }
        this.oauthUserService = oauthUserService;
        this.jwtProvider = jwtProvider;
    }

    public AuthResponse login(OAuthProvider provider, String code) {
        OAuthClient client = clients.get(provider);
        if (client == null) {
            throw new UnsupportedProviderException(provider);
        }

        OAuthUserInfo userInfo = client.fetchUserInfo(code);
        User user = oauthUserService.findOrRegister(provider, userInfo);

        String token = jwtProvider.issue(user.getId());
        return new AuthResponse(token, user.getId(), user.getNickname());
    }
}
