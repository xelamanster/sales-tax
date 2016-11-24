package controllers

import play.api._
import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

//  def savePlace = Action(BodyParsers.parse.json) { request =>
//    val placeResult = request.body.validate[Place]
//    placeResult.fold(
//      errors => {
//        BadRequest(Json.obj("status" ->"Error", "message" -> JsError.toJson(errors)))
//      },
//      place => {
//        Place.save(place)
//        Ok(Json.obj("status" ->"OK", "message" -> ("Place '"+place.name+"' saved.") ))
//      }
//    )
//  }
}