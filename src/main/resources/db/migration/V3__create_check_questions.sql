CREATE TABLE check_questions (
    id BIGSERIAL PRIMARY KEY,
    question_type VARCHAR(8) NOT NULL CHECK (question_type IN ('SELF', 'FRIEND')),
    sort_order INT NOT NULL,
    text TEXT NOT NULL,
    primary_axis VARCHAR(4) NOT NULL,
    primary_score INT NOT NULL,
    secondary_axis VARCHAR(4),
    secondary_score INT,
    UNIQUE (question_type, sort_order),
    CHECK (
        (secondary_axis IS NULL AND secondary_score IS NULL) OR
        (secondary_axis IS NOT NULL AND secondary_score IS NOT NULL)
    )
);
