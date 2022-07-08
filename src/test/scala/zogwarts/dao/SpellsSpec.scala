package zogwarts.dao

import zio.test._
import zio._
import io.getquill._
import io.getquill
import io.getquill.context.ZioJdbc.DataSourceLayer
import javax.sql.DataSource
import io.github.scottweaver.zio.testcontainers.postgres.ZPostgreSQLContainer
import zogwarts.DataSourceFromJdbcInfo
import zogwarts.models._
import io.github.scottweaver.zio.aspect.DbMigrationAspect
import zogwarts.dao.ZogwartsPostgresContext._

object SpellsSpec extends ZIOSpecDefault {
  def spec = suite("SpellDAOSpec")(
    test("simple query") {

      val spellRow = db.Spell.make(app.Spell.ThirdLevel.fireball)

      val insert = Spells.insertSpell(spellRow)
      val query  = Spells.spellByName(spellRow.name)

      for {
        _      <- ZogwartsPostgresContext.run(insert)
        spells <- ZogwartsPostgresContext.run(query)
        _      <- Console.printLine(spells)
        fireball = spells.head
      } yield assertTrue(
        spells.size == 1,
        fireball.id != spellRow.id,
        fireball == spellRow.copy(id = fireball.id)
      )
    } @@ DbMigrationAspect.migrate()()
  ).provideShared(
    ZPostgreSQLContainer.Settings.default,
    ZPostgreSQLContainer.live,
    DataSourceFromJdbcInfo.live
  )

}
