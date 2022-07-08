package zogwarts

import zio._
import javax.sql.DataSource
import org.postgresql.ds._
import io.github.scottweaver.models.JdbcInfo
import io.getquill.context.ZioJdbc.DataSourceLayer
import io.getquill.JdbcContextConfig
import org.postgresql.jdbc.AutoSave

object DataSourceFromJdbcInfo {

  // val live = ZLayer(ZIO.serviceWith[JdbcInfo](info => JdbcContextConfig()))
  val live = ZLayer.fromZIO {
    ZIO.serviceWithZIO[JdbcInfo] { jdbcInfo =>
      ZIO.attempt {
        val ds = new PGSimpleDataSource()

        ds.setURL(jdbcInfo.jdbcUrl)
        ds.setUser(jdbcInfo.username)
        ds.setPassword(jdbcInfo.password)
        // ds.setAutosave(AutoSave.ALWAYS)

        ds
      }
    }

  }
}
