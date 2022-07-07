# Zymposium with Containers

We build a simple CRUD application and then build integration/functional tests for it using [Testcontainers For Zio](https://github.com/scottweaver/testcontainers-for-zio).

## [Weapons of Choice](https://youtu.be/wCDIYvFmgW8)

- Scala 3 (though all this can be done using Scala 2.x variants of T4Z)
- ZIO 2.0 (though all this can be done using ZIO 1.x variants of T4Z)
- Proto-quill for DAO needs.sned ta
- PostgreSQL (a MySQl container is also supported by T4Z)

## Outline

1. Build the Model
2. Make a database schema
3. Build DAOs for our models
4. Build CRUD services for our models
5. Build integration/functional tests for our services  using T4Z!