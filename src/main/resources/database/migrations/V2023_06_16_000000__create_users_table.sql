CREATE TABLE users
(
    id                INTEGER PRIMARY KEY,
    user_name         VARCHAR(20) UNIQUE NOT NULL CHECK ( LENGTH(user_name) BETWEEN 4 AND 20 ),
    email             VARCHAR(32) UNIQUE NOT NULL CHECK ( email LIKE '_%@_%.__%'),
    email_verified_at TIMESTAMP,
    password          VARCHAR(60)        NOT NULL,
    role              VARCHAR(8)         NOT NULL CHECK ( role IN ('ADMIN', 'STAFF', 'USER', 'GUEST') ),
    created_at        TIMESTAMP          NOT NULL,
    updated_at        TIMESTAMP
)