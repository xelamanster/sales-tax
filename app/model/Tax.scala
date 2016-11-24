package model

import utils.MathUtils

/**
  * Created by Alexander Chugunov on 24.11.16.
  */
object Tax {
  val ImportedKeyWord = "imported"
  val ExemptionKeyWords = Seq(
    "book",
    "chocolate",
    "pills"
  )

  def apply(product: OrderItem): Tax = {
    val itemContains = product.description.toLowerCase.contains _

    val isExempt = ExemptionKeyWords.exists(itemContains)
    val isImported = itemContains(ImportedKeyWord)

    Tax(product, isExempt, isImported)
  }

  def apply(product: OrderItem, isExempt: Boolean, isImported: Boolean): Tax = {
    if(isExempt && isImported) new Tax(product) with ExemptTaxRule with ImportedTaxRule
    else if(isImported) new Tax(product) with BaseTaxRule with ImportedTaxRule
    else if(isExempt) new Tax(product) with ExemptTaxRule
    else new Tax(product) with BaseTaxRule
  }
}

class Tax (item: OrderItem) {
  self: TaxRule =>
  val tax = MathUtils.part(item.unitPrice, rate)
}

trait TaxRule {
  def rate: Int
}

trait BaseTaxRule extends TaxRule {
  override def rate = 10
}

trait ExemptTaxRule extends TaxRule {
  override def rate = 0
}

trait ImportedTaxRule extends TaxRule {
  abstract override def rate = super.rate + 5
}