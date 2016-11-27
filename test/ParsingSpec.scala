import data.TestData
import model.SalesItem
import org.scalatestplus.play.PlaySpec
import play.api.libs.json._
import utils.JsonUtils._

class ParsingSpec extends PlaySpec {

  "JsonUtils" should {

    val salesWithIndex = TestData.sales.map(_._3).zipWithIndex

    salesWithIndex.foreach { case (sales, index) =>
      val jsonSales: String = TestData.jsonSales(index)

      s"read a sales: $sales from json: $jsonSales" in {
        val json = Json.parse(jsonSales)
        val items = json.validate[Seq[SalesItem]].get

        items mustBe sales
      }

      s"write a sales: $sales to json: $jsonSales" in {
        val json = Json.toJson(sales)

        json.toString() mustBe jsonSales
      }
    }

    "fail validation of the json with invalid format" in {
        val json = Json.parse(TestData.invalidJsonSales)
        val result = json.validate[Seq[SalesItem]]

        result.isSuccess mustBe false
    }
  }

}
