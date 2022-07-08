package zogwarts.models.db

import zogwarts.models._

final case class SpellBook(id: Long, name: String) 

object SpellBook {
  def make(spellBook: app.SpellBook): ( db.SpellBook, List[db.Spell] ) = {
  
    val dbSpellBook = db.SpellBook(0, spellBook.name)
    val dbSpells = spellBook.spells.map(db.Spell.make)
    (dbSpellBook, dbSpells)
  }
}

