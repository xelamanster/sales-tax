package utils

import scala.math.BigDecimal._

/** The object contains methods for performing
  * numeric operations.
  *
  * @author Alexander Chugunov
  */
object MathUtils {

  /**
    * Returns the closest `BigDecimal` to the argument, that
    * rounded up according to the specified `scale`.
    *
    * @param num value to be rounded.
    * @param scale number of decimal places.
    * @return value of the argument rounded up.
    */
  def round(num: BigDecimal, scale: Int = 0): BigDecimal =
    num.setScale(scale, RoundingMode.UP)

  /**
    * Returns part of the argument rounded up with specified step value.
    *
    * @param value a floating-point value from which need to get a part.
    * @param percentage a part of the value to get.
    * @param step rounding increment.
    * @return result.
    */
  def part(value: BigDecimal, percentage: Int, step: Double = 0.05): BigDecimal = {
    val part = value * percentage / 100
    round(part / step) * step
  }
}
