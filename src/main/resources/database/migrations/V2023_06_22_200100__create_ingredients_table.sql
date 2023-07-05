CREATE TABLE ingredients (
    id INTEGER PRIMARY KEY,
    name VARCHAR(64) CHECK ( LENGTH(name) BETWEEN 2 AND 64) NOT NULL,
    energy INTEGER CHECK ( energy > 0 ) NOT NULL,
    category VARCHAR(10) CHECK ( category IN ('FRUIT', 'VEGETABLE', 'GRAIN', 'MEAT', 'DAIRY', 'SPICE', 'MISCELLANEOUS')),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
)

