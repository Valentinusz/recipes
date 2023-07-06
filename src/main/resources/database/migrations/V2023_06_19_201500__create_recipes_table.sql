CREATE TABLE recipes (
    id INTEGER PRIMARY KEY,
    name VARCHAR(64) CHECK ( LENGTH(name) BETWEEN 2 AND 64) NOT NULL,
    preparation_time INTEGER CHECK ( preparation_time >= 0 ) NOT NULL,
    cooking_time INTEGER CHECK ( cooking_time >= 0 ) NOT NULL,
    instructions TEXT NOT NULL,
    author_id INTEGER NOT NULL,
    thumbnail VARCHAR(64),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    FOREIGN KEY (author_id) REFERENCES users (id) ON DELETE CASCADE
)

