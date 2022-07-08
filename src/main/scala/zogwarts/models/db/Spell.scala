package zogwarts.models.db

import zogwarts.models._
import io.getquill._

final case class Spell(id: Long, name: String, level: Int, description: String, effect: String)


object Spell {
  inline given InsertMeta[Spell] = insertMeta[Spell](_.id)

  def make(spell: app.Spell): db.Spell =
    db.Spell(-1, spell.name, spell.level, spell.description, spell.effect)

}
