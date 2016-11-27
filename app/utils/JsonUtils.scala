package utils

import model.{Receipt, SalesItem}
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json._

/** Contains methods and implicit `Reads`, `Writes`
  * for converting model's classes to and from Json.
  *
  * @author Alexander Chugunov
  */
object JsonUtils {
  implicit val salesItemReads: Reads[SalesItem] = (
    (JsPath \ SalesItem.Description).read[String] and
      (JsPath \ SalesItem.Count).read[Long] and
      (JsPath \ SalesItem.UnitPrice).read[BigDecimal]
    )(SalesItem.apply _)

  implicit val salesItemWrites: Writes[SalesItem] = (
    (JsPath \ SalesItem.Description).write[String] and
      (JsPath \ SalesItem.Count).write[Long] and
      (JsPath \ SalesItem.UnitPrice).write[BigDecimal]
    )(unlift(SalesItem.unapply))

  def toJson(receipt: Receipt): JsObject =
    Json.obj(Receipt.SalesTax -> receipt.salesTax)
}
