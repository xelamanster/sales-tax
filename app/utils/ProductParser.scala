package utils

import model.OrderUnit
import play.api.libs.json.{JsValue, _}
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._


object ProductParser {
  implicit val locationReads: Reads[OrderUnit] = (
      (JsPath \ "description").read[String] and
      (JsPath \ "count").read[Long] and
      (JsPath \ "unitPrice").read[Double]
    )(OrderUnit.apply _)

  def parse(value: JsValue): Either[JsObject, Seq[OrderUnit]] = {
    val parseResult = value.validate[Seq[OrderUnit]]

    parseResult.fold(
        errors => parseSingle(value),
        place => Right(Seq() ++ place)
      )
  }

  private def parseSingle(value: JsValue): Either[JsObject, Seq[OrderUnit]] = {
    val parseResult = value.validate[OrderUnit]

    parseResult.fold(
      errors => Left(JsError.toJson(errors)),
      place => Right(Seq() :+ place)
    )
  }
}
