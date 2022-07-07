package zogwarts.models

final case class Spell(id: Long, name: String, level: Int, description: String)

object Spell {

  object ThirdLevel {
    val fireball = Spell(0, "Fireball", 3, "A ball of fire.  Guaranteed to roast goblins and annoy your party members.")
  }

}
