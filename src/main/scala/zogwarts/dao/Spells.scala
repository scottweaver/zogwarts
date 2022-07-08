package zogwarts.dao

import io.getquill._
import ZogwartsPostgresContext._
import zogwarts.models.db.Spell

object Spells {

  def insertSpell(spell: Spell) =
    quote(query[Spell].insertValue(lift(spell)).returning(_.id))

  def spellByName(name: String) =
    quote(query[Spell].filter(_.name == lift(name)))

}
