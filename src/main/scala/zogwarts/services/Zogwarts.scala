package zogwarts.services

import zio._
import zogwarts.models.app.Wizard

trait Zogwarts {
  
  def sortWizard(wizard: Wizard): Task[Unit]

  def summonWizard(name: String): Task[Wizard]
}
