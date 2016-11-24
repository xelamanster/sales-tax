import org.scalatestplus.play.PlaySpec
import play.api.libs.json._
import utils.ProductParser

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
    }
  }
}
