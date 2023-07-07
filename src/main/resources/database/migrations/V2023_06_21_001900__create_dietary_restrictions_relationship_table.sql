CREATE TABLE dietary_restrictions
(
    id           INTEGER PRIMARY KEY,
    user_id      INTEGER NOT NULL,
    attribute_id INTEGER NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (attribute_id) REFERENCES ingredient_attributes (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX user_to_dietary_restriction_user_id_restriction_id_unique ON dietary_restrictions (user_id, attribute_id)



