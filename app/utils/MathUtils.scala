package utils

import scala.math.BigDecimal._

/**
  * Created by Alexander Chugunov on 24.11.16.
  */
object MathUtils {
  def part(value: Double, percentage: Int, step: Double = 0.05): Double = {
    def round(num: BigDecimal): BigDecimal = num.setScale(0, RoundingMode.UP)

    val part = value.bigDecimal * percentage / 100
    val roundedPart = round(part / step) * step

    roundedPart.doubleValue()
  }
}
