CREATE TABLE dietary_restrictions (
    id INTEGER PRIMARY KEY,
    name VARCHAR(20) CHECK ( LENGTH(name) > 3 )
);

INSERT INTO dietary_restrictions VALUES (1, 'vegetarian'),
                                        (2, 'vegan'),
                                        (3, 'paleo'),
                                        (4, 'kosher'),
                                        (5, 'halal'),
                                        (6, 'low_carb')



