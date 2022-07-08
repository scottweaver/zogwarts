package zogwarts

import zio.test._
import io.github.scottweaver.zio.testcontainers.postgres._
import io.github.scottweaver.zio.aspect.DbMigrationAspect
import java.sql.Connection
import zio._

object SchemaValidationSpec extends ZIOSpecDefault {

  def spec =
    suite("SchemaValidationSpec")(
      test("validate schema") {

        def testSelect(conn: Connection) = ZIO.attempt {
          val stmt = conn.createStatement()
          val rs1  = stmt.executeQuery("SELECT * FROM wizard")
          val rs2  = stmt.executeQuery("SELECT * FROM spell")
          val rs3  = stmt.executeQuery("SELECT * FROM spell_book")
          val rs4  = stmt.executeQuery("SELECT * FROM scribed_spell")

          stmt.close()
        }

        for {
          conn <- ZIO.service[Connection]
          _    <- testSelect(conn)
        } yield assertTrue(true)

      } @@ DbMigrationAspect.migrate()()
    )
      .provideShared(
        ZPostgreSQLContainer.Settings.default,
        ZPostgreSQLContainer.live
      )

}
