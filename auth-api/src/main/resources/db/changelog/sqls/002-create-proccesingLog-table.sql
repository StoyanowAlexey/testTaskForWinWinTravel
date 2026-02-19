--liquibase formatted sql

--changeset admin:2
CREATE TABLE processing_log (
                                id UUID PRIMARY KEY,
                                user_id UUID,
                                input_text TEXT,
                                output_text TEXT,
                                created_at TIMESTAMP
);