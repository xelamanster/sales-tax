package model

import utils.MathUtils

/** Contains tax rates, keywords for specifying tax rule
  * and is a factory for [[model.Tax]] instances.
  *
  * @author Alexander Chugunov
  */
object Tax {

  val ExemptTax = 0
  val ImportedTax = 5
  val BasicTax = 10

  val ImportedKeyWord = "imported"

  /** Keywords for find exempt items */
  val ExemptionKeyWords = Seq(
    "book",
    "chocolate",
    "pills"
  )

  /** Creates the [[model.Tax]] instance with rate specified according
    * to the rules and defined using items description.
    *
    * @param item  order item for which need to calculate tax.
    * @return      [[model.Tax]] instance.
    */
  def apply(item: OrderItem): Tax = {
    val itemContains = item.description.toLowerCase.contains _

    var rule: TaxRule = DefaultTaxRule

    if (ExemptionKeyWords.exists(itemContains))
      rule = ExemptTaxRule

    if (itemContains(ImportedKeyWord))
      rule = new ImportedTaxRule(rule)

    new TaxImpl(item.unitPrice, rule)
  }

  private class TaxImpl(price: BigDecimal, rule: TaxRule) extends Tax {
    override val unitTax = MathUtils.part(price, rule.rate)
  }
}

/** Holds tax for one order unit.
  *
  * @author Alexander Chugunov
  */
trait Tax {
  val unitTax: BigDecimal
}

private trait TaxRule {
  def rate: Int
}

private object DefaultTaxRule extends TaxRule {
  override def rate = Tax.BasicTax
}

private object ExemptTaxRule extends TaxRule {
  override def rate = Tax.ExemptTax
}

private class ImportedTaxRule(baseRule: TaxRule) extends TaxRule {
  override def rate = baseRule.rate + Tax.ImportedTax
}