package services

import model.OrderUnit
import utils.CurrencyUtils

object TaxCalculator {
  def calculate(order: Seq[OrderUnit]): Bil = {
    val items = order.map(TaxParser.getTax)

    Bil(items)
  }
}

object TaxParser {
  val ExemptionKeyWords = Seq(
    "book",
    "chocolate",
    "pills"
  )

  val ImportedKeyWord = "imported"

  def getTax(product: OrderUnit): BilItem = {
    val isExempt = ExemptionKeyWords.exists(product.description.toLowerCase.contains)
    val isImported = product.description.toLowerCase.contains(ImportedKeyWord)

    def genTax: UnitTax = {
      if(isExempt && isImported) new UnitTax(product) with ExemptTaxRule with ImportedTaxRule
      else if(isImported) new UnitTax(product) with BaseTaxRule with ImportedTaxRule
      else if(isExempt) new UnitTax(product) with ExemptTaxRule
      else new UnitTax(product) with BaseTaxRule
    }

    BilItem(product, genTax)
  }
}

case class Bil(items: Seq[BilItem]) {
  val fullPrice = items.map(_.fullPrice).sum
  val fullTax = items.map(_.fullTax).sum
}

case class BilItem(order: OrderUnit, tax: UnitTax) {
  val fullTax = tax.taxValue * order.count
  val fullPrice = order.unitPrice * order.count + fullTax
}

class UnitTax(product: OrderUnit) {
  self: TaxRule =>
  val taxValue = CurrencyUtils.taxValue(product.unitPrice, tax)
}

trait TaxRule {
  def tax: Int
}

trait BaseTaxRule extends TaxRule {
  override def tax = 10
}

trait ExemptTaxRule extends TaxRule {
  override def tax = 0
}

trait ImportedTaxRule extends TaxRule {
  abstract override def tax = super.tax + 5
}