CREATE TABLE portioned_ingredients (
    id INTEGER PRIMARY KEY,
    recipe_id INTEGER NOT NULL REFERENCES recipes ON DELETE CASCADE,
    ingredient_id INTEGER NOT NULL REFERENCES ingredients ON DELETE CASCADE,
    serving_size VARCHAR(10) CHECK ( serving_size IN  ('TSP', 'TBSP', 'ML', 'G', 'UNIT', 'SLICE')),
    amount DECIMAL(8, 2) CHECK ( amount > 0 )
);

CREATE UNIQUE INDEX ingredient_recipe_recipe_id_ingredient_id_unique ON portioned_ingredients (recipe_id, ingredient_id)



