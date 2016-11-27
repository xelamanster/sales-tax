import data.TestData
import model.Tax._
import model.{Receipt, SalesItem, Tax}
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatestplus.play.PlaySpec

class ModelSpec extends PlaySpec {

  "Tax" should {
    ExemptionKeywords.foreach { exempt =>
      s"be calculated for $exempt item from exemption category" in {
        val tax = Tax(s"Test $exempt ")

        tax.unitTax mustBe ExemptTaxRate
      }

      s"be calculated for imported $exempt item from exemption category" in {
        val tax = Tax(s"Test $exempt some description $ImportedKeyword")

        tax.unitTax mustBe (ExemptTaxRate + ImportTaxRate)
      }
    }

    "be calculated for imported item" in {
      val tax = Tax(s"Test $ImportedKeyword")

      tax.unitTax mustBe (BasicTaxRate + ImportTaxRate)
    }

    "be calculated with basic rate" in {
      val tax = Tax(s"Test some description")

      tax.unitTax mustBe BasicTaxRate
    }
  }

  "Receipt" should {
    forAll(TestData.sales) { (expectedTax, expectedPrice, sales) =>
      val receipt = Receipt(sales)

      s"contains tax for $sales equal to $expectedTax" in {
        receipt.salesTax mustBe expectedTax
      }

      s"contains price for $sales equal to $expectedPrice" in {
        receipt.salesPrice mustBe expectedPrice
      }
    }
  }

  implicit def stringToTestSalesItem(description: String): SalesItem =
    SalesItem(description, 1, 100)
}
