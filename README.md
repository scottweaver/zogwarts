# Zymposium with Containers

We build a simple CRUD application and then build integration/functional tests for it using [Testcontainers For Zio](https://github.com/scottweaver/testcontainers-for-zio).

## [Weapons of Choice](https://youtu.be/wCDIYvFmgW8)

- Scala 3 (though all this can be done using Scala 2.x variants of T4Z)
- ZIO 2.0 (though all this can be done using ZIO 1.x variants of T4Z)
- Proto-quill for DAO needs.
- PostgreSQL (a MySQl container is also supported by T4Z)
- Wanted to use Chimney for domain "traversal", but it's not available for Scala 3. :disappointed:

## Outline

1. Build the Model: `Spell`, `SpellBook` and `Wizard`
2. Make a database schema
3. Build DAOs for our models
    1. Start with the `Spells` DAO
    2. Start with the trait and then live.
    3. Walk through the Quill process:
        1. Create the Zio context.
        2. implement the insert and select all methods.
        3. Refactor the return type of `ZIO[Any, SQLException, A]` to `DAOZIO[A]`.
<<<<<<< HEAD
8. Build CRUD services for our models
9. Build integration/functional tests for our services  using T4Z!
=======
4. Build CRUD services for our models
5. Build integration/functional tests for our services  using T4Z!
>>>>>>> Fully implement and test 'Spells' DAO.
