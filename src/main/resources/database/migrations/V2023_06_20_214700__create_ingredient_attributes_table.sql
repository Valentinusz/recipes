CREATE TABLE ingredient_attributes
(
    id   INTEGER PRIMARY KEY,
    name VARCHAR(20) NOT NULL CHECK ( LENGTH(name) > 3 )
);

INSERT INTO ingredient_attributes
VALUES (1, 'vegetarian'),
       (2, 'vegan'),
       (3, 'paleo'),
       (4, 'kosher'),
       (5, 'halal'),
       (6, 'low_carb')



