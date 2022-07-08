package zogwarts.models.app

import zio._

final case class Wizard(name: String, age: Int, level: Int, spellBook: SpellBook) {

  def castSpell(spellName: String): Task[Unit]  = {
    spellBook.memorizeSpell(spellName) match {
      case Some(spell) if spell.level <= level =>
        Console.print(s"${spell.effect}")
      case Some(spell) =>  
        ZIO.fail(new IllegalArgumentException(s"${spell.name} fizzles. You are not powerful enough to cast it."))
      case None =>
        ZIO.fail(new Exception(s"Hmm, are you that's a real spell?"))
    }
    
  }
}
