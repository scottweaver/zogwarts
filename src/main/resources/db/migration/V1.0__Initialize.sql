CREATE SCHEMA IF NOT EXISTS zogwarts;
CREATE TABLE spell (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) UNIQUE NOT NULL,
  level INTEGER NOT NULL,
  description VARCHAR(255) NOT NULL,
  effect VARCHAR(255) NOT NULL
);