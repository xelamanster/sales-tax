import data.TestData
import model.SaleItem
import org.scalatestplus.play.PlaySpec
import play.api.libs.json._
import utils.JsonUtils._

class ParsingSpec extends PlaySpec {

  "JsonUtils" should {

    val salesWithIndex = TestData.sales.map(_._3).zipWithIndex

    salesWithIndex.foreach { case (sale, index) =>
      val jsonSale: String = TestData.jsonSales(index)

      s"read a sale: $sale from json: $jsonSale" in {
        val json: JsValue = Json.parse(jsonSale)
        val items = json.validate[Seq[SaleItem]].get

        items mustBe sale
      }

      s"write a sale: $sale to json: $jsonSale" in {
        val json: JsValue = Json.toJson(sale)

        json.toString() mustBe jsonSale
      }
    }
  }

}
