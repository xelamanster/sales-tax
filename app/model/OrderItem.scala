package model


/** Contains names of the Order item's fields.
  *
  * @author Alexander Chugunov
  */
object OrderItem {
  val Description = "description"
  val Count = "count"
  val UnitPrice = "unitPrice"
}

/** Representation of the Order item.
  *
  * @param description description of the Unit.
  * @param count       Units count.
  * @param unitPrice   the price of the one Unit.
  * @author Alexander Chugunov
  */
case class OrderItem(description: String, count: Long, unitPrice: BigDecimal)