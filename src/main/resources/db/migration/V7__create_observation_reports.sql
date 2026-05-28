CREATE TABLE observation_reports (
    id BIGSERIAL PRIMARY KEY,
    session_id BIGINT NOT NULL UNIQUE REFERENCES sessions(id) ON DELETE CASCADE,

    ai_honest_quip TEXT NOT NULL,
    friends_quip TEXT NOT NULL,

    self_keywords TEXT[] NOT NULL,
    friends_keywords TEXT[] NOT NULL,

    self_phrases_json JSONB NOT NULL,
    top_phrases_json JSONB NOT NULL,
    axis_scores_json JSONB NOT NULL,

    main_persona_id INT NOT NULL REFERENCES persona_cards(id),
    shadow_persona_id INT NOT NULL REFERENCES persona_cards(id),
    persona_summary TEXT NOT NULL,

    contrast_analysis TEXT NOT NULL,
    pattern_analysis TEXT NOT NULL,
    relationship_analysis TEXT,
    consumption_analysis TEXT,
    final_log TEXT,

    generated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);
