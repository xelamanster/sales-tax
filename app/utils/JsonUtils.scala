package utils

import model.{Bill, OrderItem}
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json._

/**
  * Created by Alexander Chugunov on 24.11.16.
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