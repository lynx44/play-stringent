package xyz.mattclifton.play.stringent.test

import play.api.mvc.{Request, AnyContent, Action, Controller}
import xyz.mattclifton.play.stringent.StringentActions
import xyz.mattclifton.play.stringent.test.helpers.FakeParsers._
import xyz.mattclifton.play.stringent.test.models.TestContent

class Status2Controller extends Controller with StringentActions {
  def withBodyParser = Action.stringent.withContent[TestContent, OkStatus, BadRequestStatus](parse.json[TestContent]){ request =>
    Ok
  }

  def anyContent = Action.stringent.anyContent[OkStatus, BadRequestStatus]{ request =>
    Ok
  }
//
  def block = Action.stringent[OkStatus, BadRequestStatus]{
    Ok
  }

  def normalBlock = Action {
    Ok
  }

  def normalAnyContent = Action({ request =>
    Ok
  })
}
