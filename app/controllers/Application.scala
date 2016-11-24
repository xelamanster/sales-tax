package controllers

import model.{Bill, OrderItem}

import play.api.libs.json._
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

class Application extends Controller {
  def index = Action(validateJson[Seq[OrderItem]]) { request =>
    val elements = request.body
    val bill = Bill(elements)

    Ok(Bill.toJson(bill))
  }

  private def validateJson[A : Reads] = BodyParsers.parse.json.validate(
    _.validate[A].asEither.left.map(e => BadRequest(JsError.toJson(e)))
  )
}