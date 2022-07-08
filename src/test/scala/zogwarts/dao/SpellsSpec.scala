package zogwarts.dao

import zio.test._
import zogwarts.models.app
import zogwarts.models.db
import zogwarts.dao.ZogwartsPostgresContext._
import io.getquill._
import io.github.scottweaver.zio.testcontainers.postgres.ZPostgreSQLContainer
import io.github.scottweaver.zio.aspect.DbMigrationAspect
import zogwarts.DataSourceFromJdbcInfo

object SpellsSpec extends ZIOSpecDefault {

  val spec = (suite("Spells")(
    test("insertSpells") {
      val spell  = db.Spell.make(app.Spell.ThirdLevel.fireball)
      val insert = Spells.insertSpell(spell)
      val q      = Spells.spellByName("Fireball")
      val q2     = Spells.spellByName("Expelliarmus")

      for {
        id          <- ZogwartsPostgresContext.run(insert)
        spells      <- ZogwartsPostgresContext.run(q)
        otherSpells <- ZogwartsPostgresContext.run(q2)

      } yield assertTrue(
        spells.size == 1,
        spells.head.id != spell.id,
        spells.head.name == "Fireball",
        otherSpells.head.name == "Expelliarmus"
      )

    }
  ) @@ DbMigrationAspect.migrate("db/migration", "test-migration")()).provide(
    ZPostgreSQLContainer.Settings.default,
    ZPostgreSQLContainer.live,
    DataSourceFromJdbcInfo.live
  )

}
