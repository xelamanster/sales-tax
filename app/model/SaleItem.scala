package model


/** Contains names of the sale item's fields.
  *
  * @author Alexander Chugunov
  */
object SaleItem {
  val Description = "description"
  val Count = "count"
  val UnitPrice = "unitPrice"
}

/** Representation of the sale item.
  *
  * @param description description of the Unit.
  * @param count       Units count.
  * @param unitPrice   the price of the one Unit.
  * @author Alexander Chugunov
  */
case class SaleItem(description: String, count: Long, unitPrice: BigDecimal)