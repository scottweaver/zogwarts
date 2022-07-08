package zogwarts.dao

import zogwarts.models.db._
import zio._
import io.getquill._
import javax.sql.DataSource
import ZogwartsPostgresContext._

case object ScribedSpells {

  val scribedSpells = quote {
    query[ScribedSpell]
  }

  def insertScribedSpell(spell: ScribedSpell) =
    val q = quote {
      query[ScribedSpell].insertValue(lift(spell))
    }

  def eraseSpellFromSpellBook(spell: Spell, spellBook: SpellBook) =
    val q = quote {
      query[ScribedSpell].filter(ss => ss.spellBookId == lift(spellBook.id) && ss.spellId == lift(spell.id)).delete
    }

}
