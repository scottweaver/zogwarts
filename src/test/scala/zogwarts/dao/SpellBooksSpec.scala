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

object SpellBooksSpec extends ZIOSpecDefault {
  import ZogwartsPostgresContext._
  def spec = suite("SpellBooksSpec")(
    test("simple query") {

      val spellBook = db.SpellBook(0, "Zionomicon")
      val insert    = SpellBooks.insertSpellBook(spellBook)
      val q         = SpellBooks.spellBookByName("Zionomicon")

      for {
        id         <- ZogwartsPostgresContext.run(insert)
        spellBooks <- ZogwartsPostgresContext.run(q)
      } yield assertTrue(
        spellBooks.head.name == spellBook.name
      )
    } @@ DbMigrationAspect.migrate()()
  ).provideShared(
    ZPostgreSQLContainer.Settings.default,
    ZPostgreSQLContainer.live,
    DataSourceFromJdbcInfo.live
  )

}
