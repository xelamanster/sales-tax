import model.{OrderItem, Tax}
import org.scalatestplus.play.PlaySpec
import play.api.libs.json._
import utils.MathUtils

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
//      def parse(value: JsValue): Either[JsObject, Seq[OrderItem]] = {
//        val parseResult = value.validate[Seq[OrderItem]]
//
//        parseResult.isSuccess
//        parseResult.fold(
//          errors => parseSingle(value),
//          place => Right(Seq() ++ place)
//        )
//      }

//      val prod2 = parse(json2)
//      val prod3 = parse(json)

//      prod2 match {
//        case Right(order) => println(Tax.calculate(order).fullTax)
//      }
//
//      prod3 match {
//        case Right(order) => println(TaxCalculator.calculate(order).fullTax)
//      }
    }
  }

  "CurrencyUtils" should {
    "follow precision" in {
      MathUtils.part(1, 10) mustBe 0.1
      println(MathUtils.part(12.49, 15))
      println(MathUtils.part(12.49, 5))
      println(MathUtils.part(0.85, 15))
      println(MathUtils.part(12.49, 10))
      println(MathUtils.part(14.99, 15))
      println(MathUtils.part(14.99, 10))
      println(MathUtils.part(12.49, 5))
      MathUtils.part(12.49, 15) mustBe 0.1
    }
  }
}
