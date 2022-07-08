package zogwarts

import io.getquill._

package object dao {
 object ZogwartsPostgresContext extends PostgresZioJdbcContext(SnakeCase) 
}
