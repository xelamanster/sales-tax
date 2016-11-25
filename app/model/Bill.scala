package model

/** The object contains methods for performing
  * numeric operations.
  *
  * @author Alexander Chugunov
  */
object Bill {
  val SalesTax: String = "SalesTax"

  def apply(items: Seq[OrderItem]): Bill =
    new Bill(items.map(toBillItem))

  private def toBillItem(item: OrderItem): BillItem =
    new BillItem(item, Tax(item))
}

/** The object contains methods for performing
  * numeric operations.
  *
  * @param items bill items.
  * @author Alexander Chugunov
  */
class Bill(items: Seq[BillItem]) {
  val fullTax = items.map(_.fullTax).sum
  val fullPrice = items.map(_.fullPrice).sum
}

/** The object contains methods for performing
  * numeric operations.
  *
  * @param item order item.
  * @param tax  tax of the order item's unit.
  * @author Alexander Chugunov
  */
class BillItem(item: OrderItem, tax: Tax) {
  val fullTax = tax.unitTax * item.count
  val fullPrice = item.unitPrice * item.count + fullTax
}