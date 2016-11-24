import org.scalatestplus.play._
import play.api.libs.json.{JsValue, Json}
import play.api.test._
import play.api.test.Helpers._

class ApplicationSpec extends PlaySpec with OneAppPerTest {

  "Routes" should {

    "send 404 on a bad request" in  {
      route(app, FakeRequest(GET, "/boum")).map(status) mustBe Some(NOT_FOUND)
    }

  }

  "ApplicationController" should {

    "render the index page" in {
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

      val home = route(app, FakeRequest(POST, "/", FakeHeaders(), json)).get

      status(home) mustBe OK
      contentType(home) mustBe Some("application/json")
      contentAsString(home) must include ("""{"SalesTax":1.5}""")
    }

    "render error" in {
      val json: JsValue = Json.parse(
        """
        {
          "description": "Book",
          "count": 1,
          "unitPrice": 12.49
        }
        """)

      val home = route(app, FakeRequest(POST, "/", FakeHeaders(), json)).get
      status(home) mustBe BAD_REQUEST
    }
  }

}


