CREATE TABLE favourites
(
    id        INTEGER PRIMARY KEY,
    user_id   INTEGER NOT NULL,
    recipe_id INTEGER NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (recipe_id) REFERENCES recipes (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX no_duplicate ON favourites (user_id, recipe_id)