package zogwarts.models.app

import zogwarts.models.db

final case class Spell(name: String, level: Int, description: String, effect: String)

object Spell {

  def make(spellRow: db.Spell): Spell = Spell(
    name = spellRow.name,
    level = spellRow.level,
    description = spellRow.description,
    effect = spellRow.effect
  )

  object ThirdLevel {
    val fireball = Spell(
      "Fireball",
      3,
      "A ball of fire.  Guaranteed to roast goblins and annoy your party members.",
      "🔥🔥🔥🔥BOOM🔥🔥🔥🔥"
    )
  }

}
