package utils

import scala.math.BigDecimal._

/**
  * Created by Alexander Chugunov on 24.11.16.
  */
object MathUtils {
  def round(num: BigDecimal, scale: Int = 0): BigDecimal =
    num.setScale(scale, RoundingMode.UP)

  def part(value: BigDecimal, percentage: Int, step: Double = 0.05): BigDecimal = {
    val part = value * percentage / 100
    round(part / step) * step
  }
}
