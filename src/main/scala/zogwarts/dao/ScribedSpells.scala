package zogwarts.dao

import zogwarts.models.db.ScribedSpell

trait ScribedSpells {

  def insertScribedSpell(spell: ScribedSpell): DAOZIO[Unit]

  def scribedSpellBySpellBookName(spellBookName: String): DAOZIO[List[ScribedSpell]]

}

final case class ScribedSpellsLive() {

  

}
