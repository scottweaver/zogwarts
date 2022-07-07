package zogwarts.models

import zio.Chunk

final case class SpellBook(spells: Chunk[Spell])
