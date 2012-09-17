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
import models.Company

class ApplicationSpec extends Specification {

  "Render error template" in {
    val html = views.html.errors("Hello")

    contentType(html) must equalTo("text/html")
    contentAsString(html) must contain("Hello")
  }

  "Render product template" in {
    val productForm = Form(ProductController.getProductFieldMapping)
    val productSearchForm = Form(ProductController.getProductFieldMapping)

    val productList = List(Product("Pants", "Green", "Medium", 1))

    val html = views.html.product(productList, productForm, productList, productSearchForm)

    contentAsString(html) must contain("filteredProducts0")
  }

  "Render company template" in {
    val productForm = Form(ProductController.getProductFieldMapping)

    val company = Company(1, "Ekin firma")
    val productList = List(Product("Pants", "Green", "Medium", 1))

    val html = views.html.company(company, productList, productForm)

    contentAsString(html) must contain("products0")
  }
}