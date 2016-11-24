package model



/**
  * Created by Alexander Chugunov on 24.11.16.
  */
object OrderItem {
  val Description = "description"
  val Count = "count"
  val UnitPrice = "unitPrice"
}

case class OrderItem(description: String, count: Long, unitPrice: BigDecimal)