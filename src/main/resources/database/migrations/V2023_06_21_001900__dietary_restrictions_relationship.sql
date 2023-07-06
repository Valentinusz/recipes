CREATE TABLE dietary_restrictions (
    id INTEGER PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES users ON DELETE CASCADE,
    attribute_id INTEGER NOT NULL REFERENCES ingredient_attibutes ON DELETE CASCADE
);

CREATE UNIQUE INDEX user_to_dietary_restriction_user_id_restriction_id_unique ON dietary_restrictions (user_id, attribute_id)



