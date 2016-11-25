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

    var rule: TaxRule = DefaultTaxRule

    if (ExemptionKeyWords.exists(itemContains))
      rule = ExemptTaxRule

    if (itemContains(ImportedKeyWord))
      rule = new ImportedTaxRule(rule)

    new TaxImpl(item, rule)
  }

  private class TaxImpl (item: OrderItem, rule: TaxRule) extends Tax {
    override val unitTax = MathUtils.part(item.unitPrice, rule.rate)
  }
}

trait Tax {
  val unitTax: BigDecimal
}

private trait TaxRule {
  def rate: Int
}

private object DefaultTaxRule extends TaxRule {
  override def rate = Tax.DefaultTax
}

private object ExemptTaxRule extends TaxRule {
  override def rate = Tax.ExemptTax
}

private class ImportedTaxRule(baseRule: TaxRule) extends TaxRule {
  override def rate = baseRule.rate + Tax.ImportedTax
}