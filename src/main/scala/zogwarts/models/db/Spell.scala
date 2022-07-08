package zogwarts.models.db

import zogwarts.models._

final case class Spell(id: Long, name: String, level: Int, description: String, effect: String)

object Spell {
  def make(spell: app.Spell): db.Spell = 
    db.Spell(0, spell.name, spell.level, spell.description, spell.effect)
  
}

