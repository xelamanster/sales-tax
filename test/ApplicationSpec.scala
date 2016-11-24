import data.TestData
import model.OrderItem
import org.scalatestplus.play._
import play.api.libs.json.{JsValue, Json}
import play.api.test._
import play.api.test.Helpers._
import utils.JsonUtils._

class ApplicationSpec extends PlaySpec with OneAppPerTest {

  "Routes" should {
    "send 404 on a bad request" in {
      route(app, FakeRequest(GET, "/wrong")).map(status) mustBe Some(NOT_FOUND)
    }
  }

  "TaxController" should {
    "return valid responds" in {
      def check(bundle: (Seq[OrderItem], Double)): Unit = {
        val (items, tax) = bundle

        val json = Json.toJson(items)
        val home = route(app, FakeRequest(POST, "/taxcalculator", FakeHeaders(), json)).get

        status(home) mustBe OK
        contentType(home) mustBe Some("application/json")
        contentAsString(home) must include (s"""{"SalesTax":$tax}""")
      }

      TestData.bundles.foreach(check)
    }

    "return bad request" in {
      val json: JsValue = Json.parse(TestData.invalidJsonBundle)
      val home = route(app, FakeRequest(POST, "/taxcalculator", FakeHeaders(), json)).get

      status(home) mustBe BAD_REQUEST
    }
  }
}


