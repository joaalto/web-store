package controllers

import play.api._
import play.api.mvc._
import play.api.libs.iteratee.Enumerator

object Application extends Controller {

  def index1 = Action { request =>
    Ok("Got request [" + request + "]")
    //    Ok(views.html.index("Your new application is ready."))
  }

  def index = Action {
    SimpleResult(
      header = ResponseHeader(200, Map(CONTENT_TYPE -> "text/plain")),
      body = Enumerator("Hello world!"))
  }

  def getProducts = TODO

  def addProduct = TODO

}