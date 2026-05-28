CREATE TABLE sessions (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE REFERENCES users(id) ON DELETE CASCADE,
    status VARCHAR(16) NOT NULL CHECK (status IN ('SELF_DONE', 'WAITING_FRIENDS', 'COMPLETED')),
    share_token VARCHAR(32) NOT NULL UNIQUE,
    self_answers_json JSONB NOT NULL,
    temp_persona_card_id INT REFERENCES persona_cards(id),
    final_persona_card_id INT REFERENCES persona_cards(id),
    final_stats_json JSONB,
    intro TEXT,
    quote TEXT,
    analysis_comment TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);
