package controllers

import play.api._
import play.api.mvc._
import play.api.libs.iteratee.Enumerator
import play.api.data.Form
import play.api.data.Forms._
import models.Product

object Application extends Controller {

  def products = Action {
    Ok(views.html.product(Product.all(), productForm))
  }

  def addProduct = Action { implicit request =>
    Logger.debug("Request: " + request)
    productForm.bindFromRequest().fold(
      errors =>
        BadRequest(views.html.errors(errors.toString)),
      product => {
        Product.create(product)
        Logger.debug("Created product: " + product)
        Redirect(routes.Application.products)
      })
  }

  val productForm = Form(
    mapping(
      "id" -> number,
      "name" -> text)(Product.apply)(Product.unapply))
}