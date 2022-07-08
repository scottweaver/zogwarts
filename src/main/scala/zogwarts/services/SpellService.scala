package zogwarts.services

import zogwarts.models.app.Spell
import zogwarts.models.db
import zogwarts.dao.Spells
import zio._
import zogwarts.dao.ZogwartsPostgresContext._
import javax.sql.DataSource

trait SpellService {

  def researchSpell(spell: Spell): Task[Unit]

  def recallSpell(spellName: String): Task[Spell]

}

object SpellService {

  val live = ZLayer {
    ZIO.serviceWith[DataSource](SpellServiceLive.apply)
  }

  def researchSpell(spell: Spell) = ZIO.serviceWithZIO[SpellService](_.researchSpell(spell))

  def recallSpell(spellName: String) = ZIO.serviceWithZIO[SpellService](_.recallSpell(spellName))
}


final case class SpellServiceLive(ds: DataSource) extends SpellService {

  val env = ZLayer.succeed(ds)

  override def researchSpell(spell: Spell): Task[Unit] = {
    val row = db.Spell.make(spell)
    val q   = Spells.insertSpell(row)

    run(q).provide(env).unit
  }

  override def recallSpell(spellName: String): Task[Spell] = {
  
    val q = Spells.spellByName(spellName)
    run(q).provide(env)
    .map(_.headOption)
    .flatMap {
      case Some(spell) => ZIO.debug(s">>> Spell recalled ${spell}").as(Spell.make(spell))
      case None        => ZIO.fail(new Exception(s"'$spellName' has yet to be researched."))
    }
  }

}
