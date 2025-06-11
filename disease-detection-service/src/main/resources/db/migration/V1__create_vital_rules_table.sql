CREATE TABLE IF NOT EXISTS vital_rules (
    id BIGSERIAL PRIMARY KEY,
    created_by BIGINT,
    last_modified_by BIGINT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    vital_record_id BIGINT NOT NULL,
    operator VARCHAR(10),
    threshold_max BIGINT NOT NULL,
    threshold_min BIGINT NOT NULL,
    disease_id BIGINT NOT NULL,
    description TEXT
);