package models

import org.scalaquery.ql.basic.{ BasicTable => Table }
import org.scalaquery.ql.TypeMapper._
import org.scalaquery.ql._

object Products extends Table[(Int, String)]("products") {
  def id = column[Int]("id", O NotNull)
  def name = column[String]("name", O DBType "varchar()")

  def * = id ~ name

}