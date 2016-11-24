package utils

import scala.math.BigDecimal._

object CurrencyUtils {
  def getPart(value: Double, percentage: Int, step: Double = 0.05): Double = {

    def round(num: BigDecimal): BigDecimal = num.setScale(0, RoundingMode.UP)

    val part = value.bigDecimal * percentage / 100
    val roundedPart = round(part / step) * step

    roundedPart.doubleValue()
  }
}
