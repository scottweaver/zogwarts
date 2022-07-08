package zogwarts.models.db

import zogwarts.models.app
import io.getquill._

final case class Spell(id: Long, name: String, level: Int, description: String, effect: String)

object Spell {

  inline given InsertMeta[Spell] = insertMeta[Spell](_.id)

  def make(appSpell: app.Spell): Spell =
    Spell(0, appSpell.name, appSpell.level, appSpell.description, appSpell.effect)

}