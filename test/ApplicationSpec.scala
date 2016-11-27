import data.TestData
import model.Receipt
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._
import utils.JsonUtils._

class ApplicationSpec extends PlaySpec with OneAppPerTest {

  "Routes" should {
    "respond on a bad request with 404" in {
      val respond = route(app, FakeRequest(GET, "/wrong")).get

      status(respond) mustBe NOT_FOUND
    }
  }

  "TaxController" should {

    "respond on improperly formatted json with 'unprocessable entity'" in {
      val json = Json.parse(TestData.invalidJsonSales)
      val respond = route(app, FakeRequest(POST, "/taxcalculator", FakeHeaders(), json)).get

      status(respond) mustBe UNPROCESSABLE_ENTITY
    }

    forAll(TestData.sales) { (expectedTax, expectedPrice, sales) =>
      val json = Json.toJson(sales)

      s"respond on $json with $expectedTax" in {
        val respond = route(app, FakeRequest(POST, "/taxcalculator", FakeHeaders(), json)).get

        status(respond) mustBe OK
        contentType(respond) mustBe Some("application/json")
        contentAsString(respond) must equal(s"""{"${Receipt.SalesTax}":$expectedTax}""")
      }
    }
  }

}
