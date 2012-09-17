package controllers

import play.api._
import play.api.mvc._
import play.api.libs.iteratee.Enumerator
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import models._

object CompanyController extends Controller {

  def companies = Action {
    Ok(views.html.companies(Company.all(), companyForm))
  }

  def company(companyId: Int) = Action {
    val comp = Company.find(companyId)(0)
    Logger.debug("Find company: " + comp)
    Ok(views.html.company(comp,
      Product.products(companyId),
      ProductController.productForm))
  }

  def addCompany = Action { implicit request =>
    companyForm.bindFromRequest().fold(
      errors =>
        BadRequest(views.html.errors(errors.toString)),
      company => {
        Company.create(company)
        Logger.debug("Created company: " + company)
        Redirect(routes.CompanyController.companies)
      })
  }

  val companyForm = Form(
    mapping(
      "Id" -> number,
      "Name" -> text)(Company.apply)(Company.unapply))

}