package utils

import model.{Bill, SaleItem}
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json._

/** Contains methods and implicit `Reads`, `Writes`
  * for converting model's classes to and from Json.
  *
  * @author Alexander Chugunov
  */
object JsonUtils {
  implicit val orderItemReads: Reads[SaleItem] = (
    (JsPath \ SaleItem.Description).read[String] and
      (JsPath \ SaleItem.Count).read[Long] and
      (JsPath \ SaleItem.UnitPrice).read[BigDecimal]
    )(SaleItem.apply _)

  implicit val orderItemWrites: Writes[SaleItem] = (
    (JsPath \ SaleItem.Description).write[String] and
      (JsPath \ SaleItem.Count).write[Long] and
      (JsPath \ SaleItem.UnitPrice).write[BigDecimal]
    )(unlift(SaleItem.unapply))

  def toJson(bill: Bill): JsObject =
    Json.obj(Bill.SalesTax -> bill.salesTax)
}
