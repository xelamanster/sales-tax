package controllers

import model.{Bill, SaleItem}

import play.api.libs.json._
import play.api.mvc._
import utils.JsonUtils._

import scala.concurrent.ExecutionContext.Implicits.global

/** Handles orders from requests in json format
  *
  * @author Alexander Chugunov
  */
class TaxController extends Controller {

  /** Returns `Action` that calculates bill for order items
    * from the request and send sum of the sales tax in respond.
    */
  def calculate = Action(validateJson[Seq[SaleItem]]) { request =>

    val items = request.body
    val bill = Bill(items)

    Ok(toJson(bill))
  }

  private def validateJson[A: Reads]: BodyParser[A] =
    BodyParsers.parse.json.validate(
      _.validate[A]
        .asEither
        .left
        .map(e => UnprocessableEntity(JsError.toJson(e)))
    )
}