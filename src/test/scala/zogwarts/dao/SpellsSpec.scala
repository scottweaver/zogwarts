package zogwarts.dao

import zio.test._
import zio._
import io.getquill._
import io.getquill.context.ZioJdbc.DataSourceLayer
import javax.sql.DataSource
import io.github.scottweaver.zio.testcontainers.postgres.ZPostgreSQLContainer
import zogwarts.DataSourceFromJdbcInfo
import zogwarts.models._
import io.github.scottweaver.zio.aspect.DbMigrationAspect

object SpellDAOSpec extends ZIOSpecDefault {
  def spec = suite("SpellDAOSpec")(
    test("simple query") {

      val spellRow = db.Spell.make(app.Spell.ThirdLevel.fireball)

      for {
        _      <- Spells.insertSpell(spellRow)
        spells <- Spells.allSpells
        _ <- Console.printLine(spells)
      } yield assertTrue(
        spells.head == spellRow
      )
    } @@ DbMigrationAspect.migrate()()
  ).provideShared(
    ZPostgreSQLContainer.Settings.default,
    ZPostgreSQLContainer.live,
    DataSourceFromJdbcInfo.live,
    Spells.live
  )

}
