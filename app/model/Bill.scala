package model

/** Is a factory for [[model.Bill]] instances.
  *
  * @author Alexander Chugunov
  */
object Bill {
  val SalesTax: String = "SalesTax"

  /** Returns [[model.Bill]] that holds sale items
    * with summary tax and price.
    *
    * @param items bill items.
    */
  def apply(items: Seq[SaleItem]): Bill =
    new Bill(items.map(toBillItem))

  private def toBillItem(item: SaleItem): BillItem =
    new BillItem(item, Tax(item))
}

/** Holds summary tax and price for all sales.
  *
  * @param items bill items.
  * @author Alexander Chugunov
  */
class Bill(items: Seq[BillItem]) {
  val salesTax = items.map(_.saleTax).sum
  val salesPrice = items.map(_.salePrice).sum
}

/** Holds sale tax and price for one sale item.
  *
  * @param item sale item.
  * @param tax  tax calculated for the sale item's unit.
  * @author Alexander Chugunov
  */
class BillItem(item: SaleItem, tax: Tax) {
  val saleTax = tax.unitTax * item.count
  val salePrice = item.unitPrice * item.count + saleTax
}