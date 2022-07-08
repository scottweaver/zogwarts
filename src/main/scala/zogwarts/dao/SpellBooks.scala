package zogwarts.dao

import zogwarts.models.db.SpellBook
import javax.sql.DataSource
import io.getquill._
import zio._

trait SpellBooks {
  
  def insertSpellBook(spellBook: SpellBook): DAOZIO[Unit]

  def spellBookByName(name: String): DAOZIO[Option[SpellBook]]

}

object SpellBooks {
  val live = ZLayer.fromZIO {
    ZIO.serviceWith[DataSource](SpellBooksLive(_))
  }

  def insertSpellBook(spellBook: SpellBook) =
    ZIO.serviceWithZIO[SpellBooks](_.insertSpellBook(spellBook))

  def spellBookByName(name: String) = 
    ZIO.serviceWithZIO[SpellBooks](_.spellBookByName(name))
}

final case class SpellBooksLive(dataSource: DataSource) extends SpellBooks {
  import ZogwartsPostgresContext._

  val spellBooks = quote(query[SpellBook])
  val env = ZLayer.succeed(dataSource)

  def insertSpellBook(spellBook: SpellBook): DAOZIO[Unit] = {
    val q = quote(spellBooks.insertValue(lift(spellBook)))

    run(q).unit.provide(env)
  }

  def spellBookByName(name: String): DAOZIO[Option[SpellBook]] = {
    val q = quote(spellBooks.filter(_.name == lift(name)))
    run(q).provide(env).map(_.headOption)
  }

}
