package zogwarts.services

import zio.test._
import zogwarts.models.app.Spell
import zio._
import io.github.scottweaver.zio.testcontainers.postgres.ZPostgreSQLContainer
import zogwarts.DataSourceFromJdbcInfo
import io.github.scottweaver.zio.aspect.DbMigrationAspect
import zio.test.TestAspect._
import javax.sql.DataSource

object SpellServiceSpec extends ZIOSpecDefault {

  val researchSpell = suite("#researchSpell")(
    test("Should insert a spell and not fail.") {

      val spell = Spell.ThirdLevel.fireball

      for {
        _ <- SpellService.researchSpell(spell)
      } yield assertTrue(true)
    }
  ) @@ PurgeSpells.aspect

  val recallSpell = suite("#recallSpell")(
    test("Should be able to recall a spell that has been previously researched.") {

      val spell = Spell.ThirdLevel.fireball

      for {
        // _ <- SpellService.researchSpell(spell)
        _           <- PurgeSpells.delete(spell.name)
        actualSpell <- SpellService.recallSpell(spell.name)
      } yield assertTrue(
        actualSpell == spell
      )
    }
  )

  def spec = (suite("SpellServiceSpec")(
    researchSpell,
    recallSpell
  ) @@ DbMigrationAspect.migrate("db/migration", "db-test")() @@ sequential)
    .provideShared(
      ZPostgreSQLContainer.Settings.default,
      ZPostgreSQLContainer.live,
      DataSourceFromJdbcInfo.live,
      SpellService.live
    )

}

object PurgeSpells {

  def delete(name: String) =
    ZIO.attempt {
      import zogwarts.dao._
      import io.getquill._
      import ZogwartsPostgresContext._
      val d = quote(Spells.spells.filter(s => s.name == lift(name)).delete)
      ZIO.serviceWithZIO[DataSource](ds => ZogwartsPostgresContext.run(d).provide(ZLayer.succeed(ds)).orDie.unit)
    }

  val aspect = after(
    ZIO.attempt {
      import zogwarts.dao._
      import io.getquill._
      import ZogwartsPostgresContext._
      val d = quote(Spells.spells.filter(s => s.id > 0).delete)
      ZIO.serviceWithZIO[DataSource](ds => ZogwartsPostgresContext.run(d).provide(ZLayer.succeed(ds)).orDie.unit)
    }
  )

}
