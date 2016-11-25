import data.TestData
import model.OrderItem
import org.scalatestplus.play.PlaySpec
import play.api.libs.json._
import utils.JsonUtils._

class ParsingSpec extends PlaySpec {

  "JsonUtils" should {
    val (sampleItems, _, _) = TestData.parsedJsonOrder

    "read an item from json" in {
      val json: JsValue = Json.parse(TestData.validJsonOrder)
      val items = json.validate[Seq[OrderItem]].get

      items mustBe sampleItems
    }

    "write an item to json" in {
      val json: JsValue = Json.toJson(sampleItems)

      json.toString() mustBe TestData.validJsonOrder
    }
  }
}
