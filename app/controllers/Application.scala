package controllers

import model.OrderUnit
import play.api.libs.json.{JsError, JsNumber, Json, Reads}
import play.api.mvc._
import services.TaxCalculator
import utils.ProductParser._

import scala.concurrent.ExecutionContext.Implicits.global

class Application extends Controller {


  def index = Action(validateJson[Seq[OrderUnit]]) { request =>
    val elementes = request.body
    val tax = TaxCalculator.calculate(elementes).fullTax

    Ok(Json.obj("SalesTax" -> tax))
  }

  def validateJson[A : Reads] = BodyParsers.parse.json.validate(
    _.validate[A].asEither.left.map(e => BadRequest(JsError.toJson(e)))
  )
}