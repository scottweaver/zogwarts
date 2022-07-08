package zogwarts.models.app

final case class Spell(name: String, level: Int, description: String, effect: String)

object Spell {
  object ThirdLevel {
    val fireball = Spell("Fireball", 3, "A ball of fire", "🔥🔥🔥🔥BOOM🔥🔥🔥🔥" )
  }

}