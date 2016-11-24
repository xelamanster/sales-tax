package model

import utils.CurrencyUtils

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

  def calculate(product: OrderItem): Tax = {
    val isExempt = ExemptionKeyWords.exists(product.description.toLowerCase.contains)
    val isImported = product.description.toLowerCase.contains(ImportedKeyWord)

    if(isExempt && isImported) new Tax(product) with ExemptTaxRule with ImportedTaxRule
    else if(isImported) new Tax(product) with BaseTaxRule with ImportedTaxRule
    else if(isExempt) new Tax(product) with ExemptTaxRule
    else new Tax(product) with BaseTaxRule
  }
}

class Tax(item: OrderItem) {
  self: TaxRule =>
  val tax = CurrencyUtils.getPart(item.unitPrice, rate)
}

private trait TaxRule {
  def rate: Int
}

private trait BaseTaxRule extends TaxRule {
  override def rate = 10
}

private trait ExemptTaxRule extends TaxRule {
  override def rate = 0
}

private trait ImportedTaxRule extends TaxRule {
  abstract override def rate = super.rate + 5
}