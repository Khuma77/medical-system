CREATE TABLE IF NOT EXISTS diseases (
    id BIGSERIAL PRIMARY KEY,
    created_by BIGINT,
    last_modified_by BIGINT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    recommended_pills VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS symptoms (
    id BIGSERIAL PRIMARY KEY,
    created_by BIGINT,
    last_modified_by BIGINT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS diseases_symptoms (
    id BIGSERIAL PRIMARY KEY,
    created_by BIGINT,
    last_modified_by BIGINT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    disease_id BIGINT NOT NULL REFERENCES diseases(id),
    symptom_id BIGINT NOT NULL REFERENCES symptoms(id)
);

CREATE INDEX IF NOT EXISTS idx_diseases_symptoms_disease ON diseases_symptoms(disease_id);
CREATE INDEX IF NOT EXISTS idx_diseases_symptoms_symptom ON diseases_symptoms(symptom_id);

CREATE TABLE IF NOT EXISTS diagnosis (
    id BIGSERIAL PRIMARY KEY,
    created_by BIGINT,
    last_modified_by BIGINT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    patient_id UUID NOT NULL,
    doctor_id UUID,
    description TEXT
);

CREATE TABLE IF NOT EXISTS diagnosis_diseases (
    diagnosis_id BIGINT NOT NULL REFERENCES diagnosis(id),
    disease_id BIGINT NOT NULL REFERENCES diseases(id),
    PRIMARY KEY (diagnosis_id, disease_id)
);

CREATE TABLE IF NOT EXISTS medical_history (
    id BIGSERIAL PRIMARY KEY,
    created_by BIGINT,
    last_modified_by BIGINT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    diagnosis_id BIGINT NOT NULL REFERENCES diagnosis(id),
    surgery_date TIMESTAMP,
    discharge_date TIMESTAMP,
    notes TEXT
);

CREATE INDEX IF NOT EXISTS idx_medical_history_diagnosis ON medical_history(diagnosis_id);
