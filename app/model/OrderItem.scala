package model

import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json.{JsPath, Reads}

/**
  * Created by Alexander Chugunov on 24.11.16.
  */
object OrderItem {
  private val Description = "description"
  private val Count = "count"
  private val UnitPrice = "unitPrice"

  implicit val orderItemReads: Reads[OrderItem] = (
      (JsPath \ Description).read[String] and
      (JsPath \ Count).read[Long] and
      (JsPath \ UnitPrice).read[Double]
    )(OrderItem.apply _)
}

case class OrderItem(description: String, count: Long, unitPrice: Double)