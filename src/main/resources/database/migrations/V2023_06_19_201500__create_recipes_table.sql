CREATE TABLE recipes
(
    id               INTEGER PRIMARY KEY,
    name             VARCHAR(64) NOT NULL CHECK ( LENGTH(name) BETWEEN 2 AND 64 ),
    preparation_time INTEGER     NOT NULL CHECK ( preparation_time >= 0 ),
    cooking_time     INTEGER     NOT NULL CHECK ( cooking_time >= 0 ),
    instructions     TEXT        NOT NULL,
    author_id        INTEGER     NOT NULL,
    thumbnail        VARCHAR(64) NOT NULL,
    created_at       TIMESTAMP   NOT NULL,
    updated_at       TIMESTAMP,
    
    FOREIGN KEY (author_id) REFERENCES users (id) ON DELETE CASCADE
)

