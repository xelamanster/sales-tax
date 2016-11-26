import model.{Bill, SaleItem, Tax}
import org.scalatestplus.play.PlaySpec
import utils.MathUtils
import Tax._
import data.TestData
import org.scalatest.prop.TableDrivenPropertyChecks._

class ModelSpec extends PlaySpec {
  val testValue = 1

  "Tax" should {
    ExemptionKeywords.foreach { exempt =>
      s"return exempt tax for $exempt" in {
        Tax(testItem(s"Test $exempt "))
          .unitTax mustBe testTax(ExemptTax)
      }

      s"return imported exempt tax for $exempt" in {
        Tax(testItem(s"Test $exempt some text $ImportedKeyword"))
          .unitTax mustBe testTax(ExemptTax + ImportedTax)
      }
    }

    "return imported tax" in {
      Tax(testItem(s"Test $ImportedKeyword"))
        .unitTax mustBe testTax(BasicTax + ImportedTax)
    }

    "return default tax" in {
      Tax(testItem(s"Test some description"))
        .unitTax mustBe testTax(BasicTax)
    }
  }

  "Bill" should {
    forAll(TestData.sales) { (expectedTax, expectedPrice, sales) =>
      val bill = Bill(sales)

      s"calculate tax for $sales as $expectedTax" in {
        bill.salesTax mustBe expectedTax
      }

      s"calculate price for $sales as $expectedPrice" in {
        bill.salesPrice mustBe expectedPrice
      }
    }
  }

  def testTax(percentage: Int): BigDecimal =
    MathUtils.part(testValue, percentage)

  def testItem(description: String): SaleItem =
    SaleItem(description, 1, testValue)
}
