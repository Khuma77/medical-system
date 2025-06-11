CREATE TABLE contacts (
    id BIGSERIAL PRIMARY KEY,
    created_by BIGINT,
    last_modified_by BIGINT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    primary_phone_number VARCHAR(255) NOT NULL,
    secondary_phone_number VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    about TEXT
);

CREATE INDEX idx_contacts_email ON contacts(email);

CREATE INDEX idx_contacts_primary_phone_number ON contacts(primary_phone_number);

CREATE INDEX idx_contacts_is_deleted ON contacts(is_deleted);
