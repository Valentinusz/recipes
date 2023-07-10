CREATE TABLE ingredients
(
    id         INTEGER PRIMARY KEY,
    name       VARCHAR(64) UNIQUE NOT NULL CHECK ( LENGTH(name) BETWEEN 2 AND 64 ),
    energy     INTEGER            NOT NULL CHECK ( energy >= 0 ),
    category   VARCHAR(16)        NOT NULL CHECK (
            category IN ('FRUIT', 'VEGETABLE', 'GRAIN', 'MEAT', 'DAIRY', 'SPICE', 'MISCELLANEOUS')
        ),
    created_at TIMESTAMP          NOT NULL,
    updated_at TIMESTAMP
)

