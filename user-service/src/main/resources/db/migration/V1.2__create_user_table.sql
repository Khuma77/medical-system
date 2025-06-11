CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    created_by BIGINT,
    last_modified_by BIGINT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    user_id UUID NOT NULL,
    birth_date TIMESTAMP NOT NULL,
    gender VARCHAR(255) NOT NULL,
    contact_id BIGINT,
    CONSTRAINT fk_users_contact FOREIGN KEY (contact_id) REFERENCES contacts(id)
);

CREATE INDEX idx_users_user_id ON users(user_id);

CREATE INDEX idx_users_gender ON users(gender);

CREATE INDEX idx_users_is_deleted ON users(is_deleted);
