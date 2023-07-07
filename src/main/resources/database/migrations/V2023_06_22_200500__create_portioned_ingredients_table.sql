CREATE TABLE portioned_ingredients
(
    id            INTEGER PRIMARY KEY,
    recipe_id     INTEGER NOT NULL,
    ingredient_id INTEGER NOT NULL,
    serving_size  VARCHAR(10) CHECK (
            serving_size IN ('TEASPOON', 'TABLESPOON', 'MILLILITER', 'GRAM', 'UNIT', 'SLICE')
        ),
    amount        DECIMAL(8, 2) CHECK ( amount > 0 ),

    FOREIGN KEY (recipe_id) REFERENCES recipes (id) ON DELETE CASCADE,
    FOREIGN KEY (ingredient_id) REFERENCES ingredients (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX ingredient_recipe_recipe_id_ingredient_id_unique ON portioned_ingredients (recipe_id, ingredient_id)



