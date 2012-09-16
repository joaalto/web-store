package models

import org.scalaquery.ql.basic.{ BasicTable => Table }
//import org.scalaquery.ql.basic.BasicDriver.Implicit._
import org.scalaquery.ql.extended.H2Driver.Implicit._

import org.scalaquery.ql.TypeMapper._
import org.scalaquery.ql._
import org.scalaquery.session.Database
import play.api.db._
import play.api.Play.current
import org.scalaquery.session.Session

case class Product(id: Int, name: String, colour: String)

object Product extends Table[Product]("PRODUCT") {
  lazy val database = Database.forDataSource(DB.getDataSource())

  def id = column[Int]("ID", O.PrimaryKey) // O.AutoInc
  def name = column[String]("NAME", O.NotNull, O.DBType("varchar(64)"))
  def colour = column[String]("COLOUR", O.DBType("varchar(64)"))

  def * = id ~ name ~ colour <> (Product.apply _, Product.unapply _)

  def create(product: Product) {
    database withSession { implicit session: Session =>
      Product.insert(product);
    }
  }

  def all() = database.withSession { implicit session: Session =>
    (for (prod <- Product) yield prod).list
  }

}