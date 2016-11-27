package model


/** Contains names of the sales item's fields.
  *
  * @author Alexander Chugunov
  */
object SalesItem {
  val Description = "description"
  val Count = "count"
  val UnitPrice = "unitPrice"
}

/** Representation of the sales item.
  *
  * @param description description of the item.
  * @param count       Units count.
  * @param unitPrice   the price of the one unit.
  * @author Alexander Chugunov
  */
case class SalesItem(description: String, count: Long, unitPrice: BigDecimal)