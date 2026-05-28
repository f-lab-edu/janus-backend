CREATE TABLE friend_responses (
    id BIGSERIAL PRIMARY KEY,
    session_id BIGINT NOT NULL REFERENCES sessions(id) ON DELETE CASCADE,
    device_id VARCHAR(64) NOT NULL,
    nickname VARCHAR(32) NOT NULL,
    answers_json JSONB NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    UNIQUE (session_id, device_id)
);
