package zogwarts.dao

import zogwarts.models.db.SpellBook
import javax.sql.DataSource
import io.getquill._
import zio._
import ZogwartsPostgresContext._

case object SpellBooks {

  val spellBooks = quote(query[SpellBook])

  def insertSpellBook(spellBook: SpellBook) = quote(spellBooks.insertValue(lift(spellBook)).returning(_.id))

  def spellBookByName(name: String) = quote(spellBooks.filter(_.name == lift(name)))
}
