package controllers

import model.{Receipt, SalesItem}
import play.api.libs.json._
import play.api.mvc._
import utils.JsonUtils._

/** Handles sales from the request and responds in json format.
  *
  * @author Alexander Chugunov
  */
class TaxController extends Controller {

  /** Returns `Action` that generates receipt for sales items
    * from the request and send sum of the sales tax in respond.
    */
  def calculate = Action(BodyParsers.parse.json) { request =>
    val validationResult = request.body.validate[Seq[SalesItem]]

    def processSales(items: Seq[SalesItem]): JsObject = {
      val receipt = Receipt(items)
      toJson(receipt)
    }

    validationResult.fold(
      error => UnprocessableEntity(JsError.toJson(error)),
      sales => Ok(processSales(sales))
    )
  }
}