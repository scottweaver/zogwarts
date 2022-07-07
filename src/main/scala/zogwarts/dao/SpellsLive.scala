package zogwarts.dao

import io.getquill._
import zio._
import zogwarts.models.Spell
import javax.sql.DataSource

trait Spells {
  def allSpells: DAOZIO[List[Spell]]

  def insertSpell(spell: Spell): DAOZIO[Unit]
}

object Spells {
  val live = ZLayer.fromZIO {
    ZIO.serviceWith[DataSource](SpellsLive(_))
  }

  val allSpells = ZIO.serviceWithZIO[Spells](_.allSpells)

  def insertSpell(spell: Spell) = ZIO.serviceWithZIO[Spells](_.insertSpell(spell))
}

final case class SpellsLive(datasource: DataSource) extends Spells {
  import ZogwartsPostgresContext._
  val env = ZLayer.succeed(datasource)

  inline def spells = quote {
    query[Spell]
  }

  def allSpells: DAOZIO[List[Spell]] = run(spells).provide(env)

  def insertSpell(spell: Spell): DAOZIO[Unit] = {
    val q = quote(spells.insertValue(lift(spell)))

    run(q).unit.provide(env)
  }

}
