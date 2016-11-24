package controllers

import model.{Bill, OrderItem}

import play.api.libs.json._
import play.api.mvc._
import utils.JsonUtils._

import scala.concurrent.ExecutionContext.Implicits.global

class TaxController extends Controller {
  def calculate = Action(validateJson[Seq[OrderItem]]) { request =>

    val items = request.body
    val bill = Bill(items)

    Ok(toJson(bill))
  }

  private def validateJson[A: Reads]: BodyParser[A] =
    BodyParsers.parse.json.validate(
      _.validate[A]
        .asEither
        .left
        .map(e => BadRequest(JsError.toJson(e)))
    )
}