package models

import org.scalaquery.ql.basic.{ BasicTable => Table }
import org.scalaquery.ql.extended.H2Driver.Implicit._
import org.scalaquery.session.Database
import org.scalaquery.session.Session
import org.scalaquery.ql.TypeMapper._
import org.scalaquery.ql._

import play.api.db.DB
import play.api.Play.current

case class Company(id: Int, name: String)

object Company extends Table[Company]("COMPANY") {
  lazy val database = Database.forDataSource(DB.getDataSource())

  def id = column[Int]("ID")
  def name = column[String]("NAME", O.NotNull, O.DBType("varchar(64)"))

  def * = id ~ name <> (Company.apply _, Company.unapply _)

  def all() = database.withSession { implicit session: Session =>
    (for (company <- Company) yield Company).list
  }

  def find(id: Int) = database.withSession { implicit session: Session =>
    (for { company <- Company if company.id is id } yield company).list
  }

  def create(company: Company) {
    database withSession { implicit session: Session =>
      Company.insert(company);
    }
  }
}