package kr.ai.janus.user;

import jakarta.persistence.*;
import kr.ai.janus.auth.oauth.OAuthProvider;
import kr.ai.janus.common.entity.CreatedAtEntity;

import java.time.OffsetDateTime;

@Entity
@Table(name = "users")
public class User extends CreatedAtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private OAuthProvider provider;

    @Column(name = "provider_user_id", nullable = false, length = 64)
    private String providerUserId;

    @Column(name = "last_login_at")
    private OffsetDateTime lastLoginAt;

    protected User() {}

    public static User create(OAuthProvider provider, String providerUserId, String nickname) {
        User user = new User();
        user.provider = provider;
        user.providerUserId = providerUserId;
        user.nickname = nickname;
        user.lastLoginAt = OffsetDateTime.now();
        return user;
    }

    public void updateLastLogin() {
        this.lastLoginAt = OffsetDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public OAuthProvider getProvider() {
        return provider;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public OffsetDateTime getLastLoginAt() {
        return lastLoginAt;
    }
}
