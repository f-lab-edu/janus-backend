CREATE TABLE moderation_logs (
    id BIGSERIAL PRIMARY KEY,
    session_id BIGINT REFERENCES sessions(id) ON DELETE SET NULL,
    input_type VARCHAR(32) NOT NULL,
    content_snippet TEXT NOT NULL,
    rejected_reason VARCHAR(64) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_moderation_logs_session_id ON moderation_logs(session_id);
