package xyz.mattclifton.play.stringent.test

import play.api.mvc.{Action, Controller}
import xyz.mattclifton.play.stringent.StringentActions
import xyz.mattclifton.play.stringent.test.helpers.FakeParsers._
import xyz.mattclifton.play.stringent.test.models.TestContent

import scala.concurrent.{ExecutionContext, Future}

class Status3Controller(implicit executionContext: ExecutionContext) extends Controller with StringentActions {
  def withBodyParser = Action.stringent.withContent[TestContent, OkStatus, BadRequestStatus, UnauthorizedStatus](parse.json[TestContent]){ request =>
    Ok
  }

  def anyContent = Action.stringent.anyContent[OkStatus, BadRequestStatus, UnauthorizedStatus]{ request =>
    Ok
  }

  def block = Action.stringent[OkStatus, BadRequestStatus, UnauthorizedStatus]{
    Ok
  }

  def asyncBlock = Action.stringent.async[OkStatus, BadRequestStatus, UnauthorizedStatus]{
    Future(Ok)
  }

  def asyncAnyContent = Action.stringent.anyContentAsync[OkStatus, BadRequestStatus, UnauthorizedStatus]{ request =>
    Future(Ok)
  }

  def asyncBodyContent = Action.stringent.withContentAsync[TestContent, OkStatus, BadRequestStatus, UnauthorizedStatus](parse.json[TestContent]){ request =>
    Future(Ok)
  }
}
