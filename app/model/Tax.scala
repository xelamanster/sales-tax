package model

import utils.MathUtils

/** Contains tax rates, keywords for specifying tax
  * rule and is a factory for [[Tax]] instances.
  *
  * @author Alexander Chugunov
  */
object Tax {

  val ExemptTaxRate = 0
  val ImportTaxRate = 5
  val BasicTaxRate = 10

  val ImportedKeyword = "imported"

  /** Keywords for find exempt items */
  val ExemptionKeywords = Seq(
    "book",
    "chocolate",
    "pills"
  )

  /** Creates the [[Tax]] instance with rate specified according
    * to the rules and defined using items description.
    *
    * @param item  sales item for which need to calculate tax.
    * @return      [[Tax]] instance.
    */
  def apply(item: SalesItem): Tax = {
    val itemContains = item.description.toLowerCase.contains _

    var rule: TaxRule = DefaultTaxRule

    if (ExemptionKeywords.exists(itemContains))
      rule = ExemptTaxRule

    if (itemContains(ImportedKeyword))
      rule = new ImportedTaxRule(rule)

    new Tax(item.unitPrice, rule)
  }
}

/** Holds tax for one sales unit.
  *
  * @author Alexander Chugunov
  */
class Tax private(price: BigDecimal, rule: TaxRule) {
  val unitTax = MathUtils.part(price, rule.rate)
}

private trait TaxRule {
  val rate: Int
}

private object DefaultTaxRule extends TaxRule {
  override val rate = Tax.BasicTaxRate
}

private object ExemptTaxRule extends TaxRule {
  override val rate = Tax.ExemptTaxRate
}

private class ImportedTaxRule(baseRule: TaxRule) extends TaxRule {
  override val rate = baseRule.rate + Tax.ImportTaxRate
}