CREATE TABLE users (
    id INTEGER PRIMARY KEY,
    user_name VARCHAR(20) CHECK ( LENGTH(user_name) BETWEEN 6 AND 20 ) NOT NULL,
    email VARCHAR(32) CHECK ( email LIKE '_%@_%.__%') UNIQUE NOT NULL,
    email_verified_at TIMESTAMP,
    password VARCHAR(20) NOT NULL,
    is_admin BIT DEFAULT 0 NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
)