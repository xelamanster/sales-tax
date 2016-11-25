package utils

import model.{Bill, OrderItem}
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json._

/** The object contains implicit `Reads`, `Writes` and methods
  * for converting model's classes to and from Json.
  *
  * @author Alexander Chugunov
  */
object JsonUtils {
  implicit val orderItemReads: Reads[OrderItem] = (
    (JsPath \ OrderItem.Description).read[String] and
      (JsPath \ OrderItem.Count).read[Long] and
      (JsPath \ OrderItem.UnitPrice).read[BigDecimal]
    )(OrderItem.apply _)

  implicit val orderItemWrites: Writes[OrderItem] = (
    (JsPath \ OrderItem.Description).write[String] and
      (JsPath \ OrderItem.Count).write[Long] and
      (JsPath \ OrderItem.UnitPrice).write[BigDecimal]
    )(unlift(OrderItem.unapply))

  def toJson(bill: Bill): JsObject =
    Json.obj(Bill.SalesTax -> bill.fullTax)
}
