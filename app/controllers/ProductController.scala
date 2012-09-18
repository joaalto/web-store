package controllers

import play.api._
import play.api.mvc._
import play.api.libs.iteratee.Enumerator
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import models._

object ProductController extends Controller {

  //  def products = Action {
  //    Ok(views.html.product(Product.all(), productForm, filteredProducts, productSearchForm))
  //  }

  def addProduct(companyId: Int) = Action { implicit request =>
    Logger.debug("Request: " + request)

    productForm.bindFromRequest().fold(
      errors =>
        BadRequest(views.html.errors(errors.toString)),
      product => {
        Product.create(product)
        Logger.debug("Created product: " + product)
        Ok(views.html.company(Company.find(product.companyId)(0), Product.products(product.companyId), productForm))
      })
  }

  val productForm = Form(getProductFieldMapping)
  val productSearchForm = Form(getProductFieldMapping)

  def getProductFieldMapping = mapping(
    "Name" -> text,
    "Colour" -> text,
    "Size" -> text,
    "companyId" -> number)(Product.apply)(Product.unapply)

  //  var filteredProducts: List[Product] = List()

  //  def searchProducts = Action { implicit request =>
  //    productForm.bindFromRequest().fold(
  //      errors =>
  //        BadRequest(views.html.errors(errors.toString)),
  //      product => {
  //        filteredProducts = Product.query(product)
  //        Logger.debug("Filtered: " + filteredProducts)
  //        Redirect(routes.ProductController.products)
  //      })
  //  }

}