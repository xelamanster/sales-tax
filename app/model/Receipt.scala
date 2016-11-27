package model

/** Is a factory for [[Receipt]] instances.
  *
  * @author Alexander Chugunov
  */
object Receipt {
  val SalesTax: String = "SalesTax"

  /** Returns [[Receipt]] that holds sales items
    * with summary tax and price.
    *
    * @param items sales items.
    */
  def apply(items: Seq[SalesItem]): Receipt =
    new Receipt(items.map(toReceiptItem))

  private def toReceiptItem(item: SalesItem): ReceiptItem =
    new ReceiptItem(item, Tax(item))
}

/** Holds summary tax and price for all sales.
  *
  * @param items receipt items.
  * @author Alexander Chugunov
  */
class Receipt(items: Seq[ReceiptItem]) {
  val salesTax = items.map(_.salesTax).sum
  val salesPrice = items.map(_.salesPrice).sum
}

/** Holds sale tax and price for one sales item.
  *
  * @param item sales item.
  * @param tax  tax calculated for the sales item's unit.
  * @author Alexander Chugunov
  */
class ReceiptItem(item: SalesItem, tax: Tax) {
  val salesTax = tax.unitTax * item.count
  val salesPrice = item.unitPrice * item.count + salesTax
}