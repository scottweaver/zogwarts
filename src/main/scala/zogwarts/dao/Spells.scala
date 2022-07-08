package zogwarts.dao

import io.getquill._
import zio._
import zogwarts.models.db.Spell
import javax.sql.DataSource

// trait Spells {
//   def spells: Quoted[EntityQuery[Spell]]

//   def insertSpell(spell: Spell): Quoted[ActionReturning[Spell, Long]]

//   def spellByName(name: String): Quoted[EntityQuery[Spell]]
// }
case object Spells {
  import ZogwartsPostgresContext._

  inline def spells = quote(query[Spell])

  def insertSpell(spell: Spell) =
    quote(spells.insertValue(lift(spell)).returning(_.id))

  def spellByName(name: String) =
    quote(spells.filter(_.name == lift(name)))

}
