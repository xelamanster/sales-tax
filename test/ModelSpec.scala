import model.{OrderItem, Tax}
import org.scalatestplus.play.PlaySpec
import utils.MathUtils
import Tax._

/**
  * Created by Alexander Chugunov on 25.11.16.
  */
class ModelSpec extends PlaySpec {
  val testValue = 1

  "Tax" should {
    ExemptionKeyWords.foreach{ exempt =>
      s"return exempt tax for $exempt" in {
        Tax(testItem(s"Test $exempt "))
          .unitTax mustBe testTax(ExemptTax)
      }

      s"return imported exempt tax for $exempt" in {
        Tax(testItem(s"Test $exempt some text $ImportedKeyWord"))
          .unitTax mustBe testTax(ExemptTax + ImportedTax)
      }
    }

    "return imported tax" in {
      Tax(testItem(s"Test $ImportedKeyWord"))
        .unitTax mustBe testTax(DefaultTax + ImportedTax)
    }

    "return default tax" in {
      Tax(testItem(s"Test some description"))
        .unitTax mustBe testTax(DefaultTax)
    }
  }

  def testTax(percentage: Int): BigDecimal =
    MathUtils.part(testValue, percentage)

  def testItem(description: String): OrderItem =
    OrderItem(description, 1, testValue)
}
