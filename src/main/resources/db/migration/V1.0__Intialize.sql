CREATE SCHEMA IF NOT EXISTS zogwarts;
CREATE TABLE spell (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  level INTEGER NOT NULL,
  description VARCHAR(255) NOT NULL
);
CREATE TABLE spell_book (id SERIAL PRIMARY KEY);
CREATE TABLE spell_book_cross_reference (
  spellbook_id INTEGER NOT NULL,
  spell_id INTEGER NOT NULL,
  PRIMARY KEY (spellbook_id, spell_id)
);
CREATE TABLE wizard (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  age INTEGER NOT NULL,
  spell_book_pk INTEGER NOT NULL REFERENCES spell_book(id)
);