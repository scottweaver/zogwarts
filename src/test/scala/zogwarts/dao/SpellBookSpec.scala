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
  def spec = suite("SpellBooksSpec")(
    test("simple query") {


      val spellBook = db.SpellBook(0, "Zionomicon")

      for {
        _      <- SpellBooks.insertSpellBook(spellBook)
        actualSpellBook <- SpellBooks.spellBookByName(spellBook.name)
      } yield assertTrue(
        actualSpellBook.head.name == spellBook.name
      )
    } @@ DbMigrationAspect.migrate()()
  ).provideShared(
    ZPostgreSQLContainer.Settings.default,
    ZPostgreSQLContainer.live,
    DataSourceFromJdbcInfo.live,
    SpellBooks.live
  )

}
