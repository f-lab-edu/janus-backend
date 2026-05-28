package kr.ai.janus.user;

import kr.ai.janus.auth.oauth.OAuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByProviderAndProviderUserId(OAuthProvider provider, String providerUserId);
}
