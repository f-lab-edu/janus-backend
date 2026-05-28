CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    nickname VARCHAR(32) NOT NULL,
    provider VARCHAR(16) NOT NULL,
    provider_user_id VARCHAR(64) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    last_login_at TIMESTAMPTZ,
    UNIQUE (provider, provider_user_id)
);
