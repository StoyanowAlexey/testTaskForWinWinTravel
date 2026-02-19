--liquibase formatted sql

--changeset admin:1
CREATE TABLE users (
    id UUID PRIMARY KEY,
    email TEXT NOT NULL UNIQUE,
    password_hash TEXT NOT NULL
);

--rollback DROP TABLE users;

--changeset admin:2
CREATE TABLE processing_log (
    id UUID PRIMARY KEY,
    user_id UUID,
    input_text TEXT,
    output_text TEXT,
    created_at TIMESTAMP
);

--rollback DROP TABLE processing_log;