package model

import utils.MathUtils

/**
  * Created by Alexander Chugunov on 24.11.16.
  */
object Tax {
  val ExemptTax = 0
  val ImportedTax = 5
  val DefaultTax = 10

  val ImportedKeyWord = "imported"
  val ExemptionKeyWords = Seq(
    "book",
    "chocolate",
    "pills"
  )

  def apply(item: OrderItem): Tax = {
    val itemContains = item.description.toLowerCase.contains _

    val isExempt = ExemptionKeyWords.exists(itemContains)
    val isImported = itemContains(ImportedKeyWord)

    Tax(item, isExempt, isImported)
  }

  def apply(item: OrderItem, isExempt: Boolean, isImported: Boolean): Tax =
    if(isExempt && isImported) new Tax(item) with ExemptTaxRule with ImportedTaxRule
    else if(isImported) new Tax(item) with DefaultTaxRule with ImportedTaxRule
    else if(isExempt) new Tax(item) with ExemptTaxRule
    else new Tax(item) with DefaultTaxRule
}

class Tax (item: OrderItem) {
  self: TaxRule =>
  val unitTax = MathUtils.part(item.unitPrice, rate)
}

trait TaxRule {
  def rate: Int
}

trait DefaultTaxRule extends TaxRule {
  override def rate = Tax.DefaultTax
}

trait ExemptTaxRule extends TaxRule {
  override def rate = Tax.ExemptTax
}

trait ImportedTaxRule extends TaxRule {
  abstract override def rate = super.rate + Tax.ImportedTax
}