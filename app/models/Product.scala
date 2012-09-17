package models

import org.scalaquery.ql.basic.{ BasicTable => Table }
import org.scalaquery.ql.extended.H2Driver.Implicit._
import org.scalaquery.ql.TypeMapper._
import org.scalaquery.ql._
import org.scalaquery.session.Database
import play.api.db._
import play.api.Play.current
import org.scalaquery.session.Session
import play.api.Logger

case class Product(name: String, colour: String, size: String, companyId: Int)

object Product extends Table[Product]("PRODUCT") {

  lazy val database = Database.forDataSource(DB.getDataSource())

  def name = column[String]("NAME", O.NotNull, O.DBType("varchar(64)"))
  def colour = column[String]("COLOUR", O.DBType("varchar(24)"))
  def size = column[String]("SIZE", O.DBType("varchar(12)"))
  def companyId = column[Int]("COMPANY_ID")

  def * = name ~ colour ~ size ~ companyId <> (Product.apply _, Product.unapply _)

  def create(product: Product) {
    database withSession { implicit session: Session =>
      Product.insert(product);
    }
  }

  def all() = database.withSession { implicit session: Session =>
    (for (product <- Product) yield product).list
  }

  def products(companyId: Int) = database.withSession { implicit session: Session =>
    (for {
      product <- Product
      if product.companyId is companyId
    } yield product).list
  }

  def query(params: Product) = database.withSession { implicit session: Session =>
    Logger.debug("Params: " + params)
    (for {
      product <- Product
      if product.name is params.name
      if product.size is params.size
      if product.colour is params.colour
    } yield product).list
  }
}