package model

/** Is a factory for [[model.Bill]] instances.
  *
  * @author Alexander Chugunov
  */
object Bill {
  val SalesTax: String = "SalesTax"

  /** Returns [[model.Bill]] that holds order items
    * with calculated tax.
    *
    * @param items bill items.
    */
  def apply(items: Seq[OrderItem]): Bill =
    new Bill(items.map(toBillItem))

  private def toBillItem(item: OrderItem): BillItem =
    new BillItem(item, Tax(item))
}

/** Holds full tax and price for whole order.
  *
  * @param items bill items.
  * @author Alexander Chugunov
  */
class Bill(items: Seq[BillItem]) {
  val fullTax = items.map(_.fullTax).sum
  val fullPrice = items.map(_.fullPrice).sum
}

/** Holds full tax and price for one order item.
  *
  * @param item order item.
  * @param tax  tax calculated for the order item's unit.
  * @author Alexander Chugunov
  */
class BillItem(item: OrderItem, tax: Tax) {
  val fullTax = tax.unitTax * item.count
  val fullPrice = item.unitPrice * item.count + fullTax
}