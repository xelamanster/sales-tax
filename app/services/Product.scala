package services

trait Product {
  val description: String
  val count: Int
  val unitPrice: Double
}

//trait ExtendedProduct extends Product {
//  val exempt: Boolean
//  val imported: Boolean
//}
//
//trait Tax {
//  self: Product =>
//  def value: Double
//
//  def fullPrice = unitPrice + unitPrice * value
//}
//
//trait BaseTax extends Tax {
//  self: Product =>
//  override def value: Double = 0.1
//}
//
//trait FoodTax extends Tax {
//  self: Product =>
//  override def value: Double = 0
//}
//
//trait ImportedTax extends Tax {
//  self: Product =>
//  abstract override def value: Double = super.value + 0.05
//}
//
//class MyProduct(val description: String, val count: Int, val unitPrice: Double) extends Product
//
//object Test extends App {
//  val test = new MyProduct(" " , 1, 1) with FoodTax with ImportedTax
//  println(test.fullPrice)
//}