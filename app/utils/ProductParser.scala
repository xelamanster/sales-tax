package utils

import model.Product
import play.api.libs.json.{JsValue, _}
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._


object ProductParser {
  implicit val locationReads: Reads[Product] = (
      (JsPath \ "description").read[String] and
      (JsPath \ "count").read[Long] and
      (JsPath \ "unitPrice").read[Double]
    )(Product.apply _)

  def parse(value: JsValue): Either[JsObject, Seq[Product]] = {
    val parseResult = value.validate[Seq[Product]]

    parseResult.fold(
        errors => parseSingle(value),
        place => Right(Seq() ++ place)
      )
  }

  private def parseSingle(value: JsValue): Either[JsObject, Seq[Product]] = {
    val parseResult = value.validate[Product]

    parseResult.fold(
      errors => Left(JsError.toJson(errors)),
      place => Right(Seq() :+ place)
    )
  }
}
