package data

import model.OrderItem

/**
  * Created by Alexander Chugunov on 24.11.16.
  */
object TestData {
  val invalidJsonBundle =
    """
      {
        "description":"Book",
        "count":1,
        "unitPrice":12.49
      },
    """

  val validJsonBundle =
    """
      [
        {
          "description":"Book",
          "count":1,
          "unitPrice":12.49
        },
        {
          "description":"Chocolate Bar",
          "count":1,
          "unitPrice":0.85
        },
        {
          "description":"Music CD",
          "count":1,
          "unitPrice":14.99
        }
      ]
    """

  val jsonBundle1Converted = (Seq(
    OrderItem("Book", 1, 12.49),
    OrderItem("Chocolate Bar", 1, 0.85),
    OrderItem("Music CD", 1, 14.99)
  ), 1.5)

  val bundle11 = (Seq(
    OrderItem("imported box of chocolates", 1, 10),
    OrderItem("imported bottle of perfume", 1, 47.50)
  ), 7.65)

  val bundle12 = (Seq(
    OrderItem("imported bottle of perfume", 1, 27.99),
    OrderItem("bottle of perfume", 1, 18.99),
    OrderItem("packet of headache pills", 1, 9.75),
    OrderItem("box of imported chocolates", 1, 11.25)
  ), 6.70)

  val bundle13 = (Seq(
    OrderItem("Book", 1, 12.49),
    OrderItem("Chocolate Bar", 1, 0.85),
    OrderItem("Music CD", 1, 14.99)
  ), 1.5)

  val bundles = Seq(
    jsonBundle1Converted,
    bundle11,
    bundle12,
    bundle13
  )
}
