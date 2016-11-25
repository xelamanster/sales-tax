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

    var rule: TaxRule = DefaultTaxRule()

    if (ExemptionKeyWords.exists(itemContains))
      rule = ExemptTaxRule()

    if (itemContains(ImportedKeyWord))
      rule = ImportedTaxRule(rule)

    new Tax(item, rule)
  }

  class Tax(item: OrderItem, rule: TaxRule) {
    val unitTax = MathUtils.part(item.unitPrice, rule.rate)
  }

  private trait TaxRule {
    def rate: Int
  }

  private case class DefaultTaxRule extends TaxRule {
    override def rate = Tax.DefaultTax
  }

  private case class ExemptTaxRule extends TaxRule {
    override def rate = Tax.ExemptTax
  }

  private case class ImportedTaxRule(baseRule: TaxRule) extends TaxRule {
    override def rate = baseRule.rate + Tax.ImportedTax
  }
}