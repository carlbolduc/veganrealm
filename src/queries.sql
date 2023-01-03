DROP TABLE search_recipes;
CREATE VIRTUAL TABLE search_recipes USING fts5(recipe_id, author, url, name, photo, ingredients, tokenize = 'porter ascii');
INSERT INTO search_recipes SELECT recipe_id, author, url, name, photo, ingredients FROM recipe;