package utils

import scala.math.BigDecimal._

object CurrencyUtils {
  def taxValue(price: Double, percentage: Int, increment: Double = 0.05): Double = {

    def round(num: BigDecimal): BigDecimal = num.setScale(0, RoundingMode.UP)

    val value = price.bigDecimal * percentage / 100
    val rounded = round(value / increment) * increment

    rounded.doubleValue()
  }
}
