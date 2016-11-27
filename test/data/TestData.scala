package data

import model.SalesItem
import org.scalatest.prop.TableDrivenPropertyChecks._

/** Contains data for testing purposes.
  *
  * @author Alexander Chugunov
  */
object TestData {

  /** Assumes that json should contains array.*/
  val invalidJsonSales = """{"description":"Book","count":1,"unitPrice":12.49}"""

  val jsonSales = Seq(
    unformatted(
    """[
      |{"description":"Imported box of chocolates","count":1,"unitPrice":10},
      |{"description":"Imported bottle of perfume","count":1,"unitPrice":47.5}
      |]"""
    ),

    unformatted(
    """[
      |{"description":"imported bottle of perfume","count":1,"unitPrice":27.99},
      |{"description":"bottle of perfume","count":1,"unitPrice":18.99},
      |{"description":"packet of headache pills","count":1,"unitPrice":9.75},
      |{"description":"box of imported chocolates","count":1,"unitPrice":11.25}
      |]"""
    ),

    unformatted(
    """[
      |{"description":"Book","count":1,"unitPrice":12.49},
      |{"description":"Chocolate Bar","count":1,"unitPrice":0.85},
      |{"description":"Music CD","count":1,"unitPrice":14.99}
      |]"""
    ),

    unformatted(
      """[
        |{"description":"Imported bottle of perfume","count":23,"unitPrice":11.3},
        |{"description":"Imported chocolate pills","count":11,"unitPrice":21.5}
        |]"""
    )
  )

  val sales = Table(
    ("expectedTax", "expectedPrice", "sales"),
    (7.65,          65.15,           Seq(
                                       SalesItem("Imported box of chocolates", 1, 10),
                                       SalesItem("Imported bottle of perfume", 1, 47.5))),

    (6.7,           74.68,           Seq(
                                       SalesItem("imported bottle of perfume", 1, 27.99),
                                       SalesItem("bottle of perfume", 1, 18.99),
                                       SalesItem("packet of headache pills", 1, 9.75),
                                       SalesItem("box of imported chocolates", 1, 11.25))),

    (1.5,           29.83,           Seq(
                                       SalesItem("Book", 1, 12.49),
                                       SalesItem("Chocolate Bar", 1, 0.85),
                                       SalesItem("Music CD", 1, 14.99))),

    (51.2,           547.6,           Seq(
                                       SalesItem("Imported bottle of perfume", 23, 11.3),
                                       SalesItem("Imported chocolate pills", 11, 21.5)))
  )

  private def unformatted(s: String): String =
    s.stripMargin.replace(System.lineSeparator, "")
}
