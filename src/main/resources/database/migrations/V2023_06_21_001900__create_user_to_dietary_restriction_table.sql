CREATE TABLE user_to_dietary_restriction (
    id INTEGER PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES users ON DELETE CASCADE,
    restriction_id INTEGER NOT NULL REFERENCES dietary_restrictions ON DELETE CASCADE
);

CREATE UNIQUE INDEX user_to_dietary_restriction_user_id_restriction_id_unique ON user_to_dietary_restriction (user_id, restriction_id)



