package kr.ai.janus.auth.service;

import kr.ai.janus.auth.oauth.OAuthProvider;
import kr.ai.janus.auth.oauth.OAuthUserInfo;
import kr.ai.janus.user.User;
import kr.ai.janus.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OAuthUserService {

    private static final String DEFAULT_NICKNAME = "사용자";

    private final UserRepository userRepository;

    public OAuthUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User findOrRegister(OAuthProvider provider, OAuthUserInfo userInfo) {
        return userRepository
                .findByProviderAndProviderUserId(provider, userInfo.providerUserId())
                .map(this::loginExisting)
                .orElseGet(() -> register(provider, userInfo));
    }

    private User loginExisting(User user) {
        user.updateLastLogin();
        return user;
    }

    private User register(OAuthProvider provider, OAuthUserInfo userInfo) {
        String nickname = resolveNickname(userInfo.nickname());
        return userRepository.save(
                User.create(provider, userInfo.providerUserId(), nickname));
    }

    private String resolveNickname(String nickname) {
        if (nickname == null || nickname.isBlank()) {
            return DEFAULT_NICKNAME;
        }
        return nickname;
    }
}
