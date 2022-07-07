package zogwarts

import zio.Chunk

final case class SpellBook(spells: Chunk[Spell])
