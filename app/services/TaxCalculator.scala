package services

import model.Product

object TaxCalculator {
  def calculate(products: Seq[Product]) = {
    products.map(p => (TaxParser.getTax(p), p))
  }
}

object TaxParser {
  val ExemptionKeyWords = Seq(
    "bread",
    "chocolate",
    "pills"
  )

  val ImportedKeyWord = "imported"

  def getTax(product: Product): RecipeItem = {
    val isExempt = ExemptionKeyWords.exists(product.description.contains)
    val isImported = product.description.contains(ImportedKeyWord)

    def genTax: UnitTax = {
      if(isExempt && isImported) new UnitTax(product) with ExemptTaxRule with ImportedTaxRule
      else if(isImported) new UnitTax(product) with BaseTaxRule with ImportedTaxRule
      else if(isExempt) new UnitTax(product) with ExemptTaxRule
      else new UnitTax(product) with BaseTaxRule
    }

    RecipeItem(product, genTax)
  }
}

case class RecipeItem(product: Product, tax: UnitTax) {
  val fullTax = tax.value * product.count
  val fullPrice = product.unitPrice * product.count + fullTax
}

class UnitTax(product: Product) {
  self: TaxRule =>
  val value = product.unitPrice * increaseValue / 100
}

trait TaxRule {
  def increaseValue: Int
}

trait BaseTaxRule extends TaxRule {
  override def increaseValue = 10
}

trait ExemptTaxRule extends TaxRule {
  override def increaseValue = 0
}

trait ImportedTaxRule extends TaxRule {
  abstract override def increaseValue = super.increaseValue + 5
}