package data

import model.OrderItem

/** Contains data for testing purposes.
  *
  * @author Alexander Chugunov
  */
object TestData {

  /** Assumes that json should contains array.*/
  val invalidJsonOrder =
    """
      {
        "description":"Book",
        "count":1,
        "unitPrice":12.49
      }
    """

  val validJsonOrder = """[{"description":"Book","count":1,"unitPrice":12.49},{"description":"Chocolate Bar","count":1,"unitPrice":0.85},{"description":"Music CD","count":1,"unitPrice":14.99}]"""

  val parsedJsonOrder = (Seq(
    OrderItem("Book", 1, 12.49),
    OrderItem("Chocolate Bar", 1, 0.85),
    OrderItem("Music CD", 1, 14.99)
  ), 1.5, 29.83)

  val orders = Seq(
    (Seq(
      OrderItem("imported box of chocolates", 1, 10),
      OrderItem("imported bottle of perfume", 1, 47.50)
    ), 7.65, 65.15),

    (Seq(
      OrderItem("imported bottle of perfume", 1, 27.99),
      OrderItem("bottle of perfume", 1, 18.99),
      OrderItem("packet of headache pills", 1, 9.75),
      OrderItem("box of imported chocolates", 1, 11.25)
    ), 6.70, 74.68),

    parsedJsonOrder
  )
}
