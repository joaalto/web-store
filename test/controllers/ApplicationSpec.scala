package controllers

import org.specs2.mutable._
import models.Product
import org.specs2.matcher.MustMatchers
import specs2.html

import play.api.test._
import play.api.test.Helpers._

import play.api.data._
import play.api.data.Forms._

import play.api._
import play.api.mvc._

class ApplicationSpec extends Specification {

  "Render error template" in {
    val html = views.html.errors("Hello")

    contentType(html) must equalTo("text/html")
    contentAsString(html) must contain("Hello")
  }

  "Render product template" in {
    val productForm = Form(
      mapping(
        "Id" -> number,
        "Name" -> Forms.text,
        "Colour" -> Forms.text)(Product.apply)(Product.unapply))

    val html = views.html.product(List(Product(1, "Pants", "Green")), productForm)

    contentAsString(html) must contain("productRow0")
  }
}