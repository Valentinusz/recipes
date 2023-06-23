CREATE TABLE ingredient_recipe (
    id INTEGER PRIMARY KEY,
    recipe_id INTEGER NOT NULL REFERENCES recipes ON DELETE CASCADE,
    ingredient_id INTEGER NOT NULL REFERENCES ingredients ON DELETE CASCADE,
    serving_size VARCHAR(10) CHECK ( serving_size IN  ('tsp', 'tbsp', 'ml', 'g', 'unit', 'slice')),
    amount INTEGER CHECK ( amount > 0 )
);

CREATE UNIQUE INDEX ingredient_recipe_recipe_id_ingredient_id_unique ON ingredient_recipe (recipe_id, ingredient_id)



