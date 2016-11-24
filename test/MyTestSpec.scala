import org.scalatestplus.play.PlaySpec
import play.api.libs.json._
import services.TaxCalculator
import utils._

/**
  * Created by Alexander Chugunov on 24.11.16.
  */
class MyTestSpec extends PlaySpec {
  "" should {
    "" in {
      val json: JsValue = Json.parse(
        """
      [
        {
          "description": "Book",
          "count": 1,
          "unitPrice": 12.49
        },
        {
          "description": "Chocolate Bar",
         "count": 1,
         "unitPrice": 0.85
        },
        {
          "description": "Music CD",
          "count": 1,
          "unitPrice": 14.99
        }
      ]
        """)

      val json2: JsValue = Json.parse(
        """
        {
          "description": "Book",
          "count": 1,
          "unitPrice": 12.49
        }

        """)

      val prod2 = ProductParser.parse(json2)
      val prod3 = ProductParser.parse(json)

      prod2 match {
        case Right(order) => println(TaxCalculator.calculate(order).fullTax)
      }

      prod3 match {
        case Right(order) => println(TaxCalculator.calculate(order).fullTax)
      }
    }
  }

  "CurrencyUtils" should {
    "follow precision" in {
      CurrencyUtils.taxValue(1, 10) mustBe 0.1
      println(CurrencyUtils.taxValue(12.49, 15))
      println(CurrencyUtils.taxValue(12.49, 5))
      println(CurrencyUtils.taxValue(0.85, 15))
      println(CurrencyUtils.taxValue(12.49, 10))
      println(CurrencyUtils.taxValue(14.99, 15))
      println(CurrencyUtils.taxValue(14.99, 10))
      println(CurrencyUtils.taxValue(12.49, 5))
      CurrencyUtils.taxValue(12.49, 15) mustBe 0.1
    }
  }
}
