package model

/**
  * Created by Alexander Chugunov on 24.11.16.
  */
object Bill {
  val SalesTax: String = "SalesTax"

  def apply(order: Seq[OrderItem]): Bill =
    new Bill(order.map(toBillItem))

  private def toBillItem(item: OrderItem): BillItem =
    new BillItem(item, Tax(item))
}

class Bill(items: Seq[BillItem]) {
  val fullTax = items.map(_.fullTax).sum
  val fullPrice = items.map(_.fullPrice).sum
}

class BillItem(item: OrderItem, tax: Tax) {
  val fullTax = tax.tax * item.count
  val fullPrice = item.unitPrice * item.count + fullTax
}


