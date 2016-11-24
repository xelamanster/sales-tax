import org.scalatestplus.play.PlaySpec
import play.api.libs.json._
import utils.MathUtils

/**
  * Created by Alexander Chugunov on 24.11.16.
  */
class ParsingSpec extends PlaySpec {

  "" should {
    "" in {
//      val json: JsValue = Json.parse(
//       )

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
}
