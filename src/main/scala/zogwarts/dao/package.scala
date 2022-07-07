package zogwarts

import io.getquill._
import zio.ZIO
import java.sql.SQLException

package object dao {
  
  object ZogwartsPostgresContext extends PostgresZioJdbcContext(SnakeCase)

  type DAOZIO[A] = ZIO[Any, SQLException, A] 
  
}
