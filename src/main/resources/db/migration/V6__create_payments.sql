CREATE TABLE payments (
    id BIGSERIAL PRIMARY KEY,
    session_id BIGINT NOT NULL REFERENCES sessions(id),
    product_type VARCHAR(32) NOT NULL CHECK (product_type IN ('EARLY_VIEW', 'OBSERVATION_REPORT')),
    amount INT NOT NULL,
    status VARCHAR(16) NOT NULL CHECK (status IN ('PENDING', 'COMPLETED', 'FAILED', 'REFUNDED')),
    toss_payment_key VARCHAR(64),
    toss_order_id VARCHAR(64) UNIQUE NOT NULL,
    paid_at TIMESTAMPTZ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_payments_session_status ON payments(session_id, status);

CREATE UNIQUE INDEX idx_payments_unique_completed
ON payments(session_id, product_type)
WHERE status = 'COMPLETED';
