import data.TestData
import model.SalesItem
import org.scalatestplus.play.PlaySpec
import play.api.libs.json._
import utils.JsonUtils._

class ParsingSpec extends PlaySpec {

  "JsonUtils" should {

    val salesWithIndex = TestData.sales.map(_._3).zipWithIndex

    salesWithIndex.foreach { case (sale, index) =>
      val jsonSale: String = TestData.jsonSales(index)

      s"read a sale: $sale from json: $jsonSale" in {
        val json = Json.parse(jsonSale)
        val items = json.validate[Seq[SalesItem]].get

        items mustBe sale
      }

      s"write a sale: $sale to json: $jsonSale" in {
        val json = Json.toJson(sale)

        json.toString() mustBe jsonSale
      }
    }

    "fail validation of the json with invalid format" in {
        val json = Json.parse(TestData.invalidJsonSale)
        val result = json.validate[Seq[SalesItem]]

        result.isSuccess mustBe false
    }
  }

}
