package zogwarts.models.app


final case class SpellBook(name: String, spells: List[Spell]) 
{

  def memorizeSpell(name: String): Option[Spell] =
    spells.find(_.name == name)
}
