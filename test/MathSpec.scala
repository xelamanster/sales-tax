import org.scalatestplus.play.PlaySpec
import utils.MathUtils

/**
  * Created by Alexander Chugunov on 24.11.16.
  */
class MathSpec extends PlaySpec {
  "MathUtils.part()" should {
    "follow precision" in {
      MathUtils.part(1, 10) mustBe 0.1
      println(MathUtils.part(12.49, 15))
      println(MathUtils.part(12.49, 5))
      println(MathUtils.part(0.85, 15))
      println(MathUtils.part(12.49, 10))
      println(MathUtils.part(14.99, 15))
      println(MathUtils.part(14.99, 10))
      println(MathUtils.part(12.49, 5))
      MathUtils.part(12.49, 15) mustBe 0.1
    }

    "follow precision2" in {
      MathUtils.part(27.99, 15) +
      MathUtils.part(18.99, 10) +
      MathUtils.part(9.75, 0) +
      MathUtils.part(11.25, 5) mustBe 6.7
    }
  }
}
