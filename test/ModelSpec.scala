import model.{Bill, OrderItem, Tax}
import org.scalatestplus.play.PlaySpec
import utils.MathUtils
import Tax._
import data.TestData

class ModelSpec extends PlaySpec {
  val testValue = 1

  "Tax" should {
    ExemptionKeyWords.foreach { exempt =>
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

  "Bill" should {
    TestData.orders.foreach { order =>
      val (items, tax, price) = order
      val bill = Bill(items)

      s"calculate valid tax for $items" in {
        bill.fullTax mustBe tax
      }

      s"calculate valid price for $items" in {
        bill.fullPrice mustBe price
      }
    }
  }

  def testTax(percentage: Int): BigDecimal =
    MathUtils.part(testValue, percentage)

  def testItem(description: String): OrderItem =
    OrderItem(description, 1, testValue)
}
