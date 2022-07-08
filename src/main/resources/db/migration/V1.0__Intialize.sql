CREATE SCHEMA IF NOT EXISTS zogwarts;
CREATE TABLE spell (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) UNIQUE NOT NULL,
  level INTEGER NOT NULL,
  description VARCHAR(255) NOT NULL,
  effect VARCHAR(255) NOT NULL
);
CREATE TABLE spell_book (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) UNIQUE NOT NULL
  );
CREATE TABLE scribed_spell (
  spell_book_id INTEGER NOT NULL REFERENCES spell_book(id),
  spell_id INTEGER NOT NULL REFERENCES spell(id),
  PRIMARY KEY (spell_book_id, spell_id)
);
CREATE TABLE wizard (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  age INTEGER NOT NULL,
  spell_book_pk INTEGER NOT NULL REFERENCES spell_book(id)
);