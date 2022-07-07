package zio.zymposium.tfz

import zio.test._
import io.github.scottweaver.zio.testcontainers.postgres._
import io.github.scottweaver.zio.aspect.DbMigrationAspect

object SchemaValidationSpec extends ZIOSpecDefault {

  def spec =
    suite("SchemaValidationSpec")(
      test("validate schema") {

        assertTrue(true)
   
      } @@ DbMigrationAspect.migrate()()
    ).provideShared(
      ZPostgreSQLContainer.Settings.default,
      ZPostgreSQLContainer.live
    )
  
}
