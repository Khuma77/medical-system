CREATE TABLE IF NOT EXISTS vital_fields (
    id BIGSERIAL PRIMARY KEY,
    created_by BIGINT,
    last_modified_by BIGINT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    name VARCHAR(72) NOT NULL,
    measurement_id BIGINT NOT NULL REFERENCES measurement(id),
    field_type VARCHAR(32) NOT NULL,
    is_required BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS vital_records (
    id BIGSERIAL PRIMARY KEY,
    created_by BIGINT,
    last_modified_by BIGINT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    user_id UUID NOT NULL,
    recorded_at TIMESTAMP NOT NULL,
    notes VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS vital_values (
    id BIGSERIAL PRIMARY KEY,
    created_by BIGINT,
    last_modified_by BIGINT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    record_id BIGINT NOT NULL REFERENCES vital_records(id),
    field_id BIGINT NOT NULL REFERENCES vital_fields(id),
    value VARCHAR(255) NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_vital_fields_measurement ON vital_fields(measurement_id);
CREATE INDEX IF NOT EXISTS idx_vital_values_record ON vital_values(record_id);
CREATE INDEX IF NOT EXISTS idx_vital_values_field ON vital_values(field_id);
