import data.TestData
import model.Bill
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatestplus.play._
import play.api.libs.json.{JsValue, Json}
import play.api.test.Helpers._
import play.api.test._
import utils.JsonUtils._

class ApplicationSpec extends PlaySpec with OneAppPerTest {

  "Routes" should {
    "respond with 404 on a bad request" in {
      val respond = route(app, FakeRequest(GET, "/wrong")).get

      status(respond) mustBe NOT_FOUND
    }
  }

  "TaxController" should {

    "respond with 'unprocessable entity'" in {
      val json: JsValue = Json.parse(TestData.invalidJsonSale)
      val respond = route(app, FakeRequest(POST, "/taxcalculator", FakeHeaders(), json)).get

      status(respond) mustBe UNPROCESSABLE_ENTITY
    }

    forAll(TestData.sales) { (expectedTax, expectedPrice, sales) =>
      val json = Json.toJson(sales)

      s"respond for $json with $expectedTax" in {
        val respond = route(app, FakeRequest(POST, "/taxcalculator", FakeHeaders(), json)).get

        status(respond) mustBe OK
        contentType(respond) mustBe Some("application/json")
        contentAsString(respond) must equal(s"""{"${Bill.SalesTax}":$expectedTax}""")
      }
    }
  }

}
