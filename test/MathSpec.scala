import org.scalatestplus.play.PlaySpec
import utils.MathUtils._
import org.scalatest.prop.TableDrivenPropertyChecks._

class MathSpec extends PlaySpec {
  private val partSamples = Table(
    ("value", "percentage", "result"),
    (1.0,     10,           0.10),
    (12.49,   15,           1.90),
    (0.85,    15,           0.15),
    (12.49,   10,           1.25),
    (14.99,   15,           2.25),
    (14.99,   10,           1.50),
    (12.49,   5,            0.65)
  )

  private val roundSamples = Table(
    ("value", "scale", "result"),
    (0.9,     0,       1),
    (0.4,     0,       1),
    (0.06,    1,       0.1),
    (0.39,    2,       0.39),
    (0.001,   2,       0.01)
  )

  "MathUtils.part()" should {
    forAll(partSamples) {(value, percentage, result) =>
      s"return $percentage% of $value rounded up to $result" in {
        part(value, percentage) mustBe result
      }
    }

    "save an initial precision" in {
      val sum = part(27.99, 15) +
        part(18.99, 10) +
        part(9.75, 0) +
        part(11.25, 5)

      sum mustBe 6.7
    }
  }

  "MathUtils.roundUp()" should {
    forAll(roundSamples) {(value, scale, result) =>
      s"round $value with scale $scale up to $result" in {
        roundUp(value, scale) mustBe result
      }
    }
  }
}
