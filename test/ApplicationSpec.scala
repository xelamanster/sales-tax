import data.TestData
import model.Bill
import org.scalatestplus.play._
import play.api.libs.json.{JsValue, Json}
import play.api.test.Helpers._
import play.api.test._
import utils.JsonUtils._

class ApplicationSpec extends PlaySpec with OneAppPerTest {

  "Routes" should {
    "send 404 on a bad request" in {
      route(app, FakeRequest(GET, "/wrong")).map(status) mustBe Some(NOT_FOUND)
    }
  }

  "TaxController" should {
    "return a bad request" in {
      val json: JsValue = Json.parse(TestData.invalidJsonOrder)
      val respond = route(app, FakeRequest(POST, "/taxcalculator", FakeHeaders(), json)).get

      status(respond) mustBe BAD_REQUEST
    }

    TestData.orders.foreach { order =>
      val (items, tax, _) = order
      val json = Json.toJson(items)

      s"return a valid respond for $json" in {
        val respond = route(app, FakeRequest(POST, "/taxcalculator", FakeHeaders(), json)).get

        status(respond) mustBe OK
        contentType(respond) mustBe Some("application/json")
        contentAsString(respond) must include(s"""{"${Bill.SalesTax}":$tax}""")
      }
    }
  }

}
