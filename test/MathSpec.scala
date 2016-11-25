import org.scalatestplus.play.PlaySpec
import utils.MathUtils.part
import org.scalatest.prop.TableDrivenPropertyChecks._

class MathSpec extends PlaySpec {
  private val testSamples = Table(
    ("value", "percentage", "result"),
    (1.0,     10,           0.10),
    (12.49,   15,           1.90),
    (0.85,    15,           0.15),
    (12.49,   10,           1.25),
    (14.99,   15,           2.25),
    (14.99,   10,           1.50),
    (12.49,   5,            0.65)
  )

  "MathUtils.part()" should {
    forAll(testSamples) {(value, percentage, result) =>
      s"return rounded up $percentage% from $value" in {
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
}
