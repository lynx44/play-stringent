package xyz.mattclifton.play.stringent.test

import play.api.mvc.{Request, AnyContent, Action, Controller}
import xyz.mattclifton.play.stringent.StringentActions
import xyz.mattclifton.play.stringent.test.helpers.FakeParsers._
import xyz.mattclifton.play.stringent.test.models.{TestResponse, TestContent}

import scala.concurrent.{ExecutionContext, Future}

class Status2Controller(implicit executionContext: ExecutionContext) extends Controller with StringentActions {
  def withBodyParser = Action.stringent.withContent[TestContent, OkStatus, BadRequestStatus](parse.json[TestContent]){ request =>
    Ok
  }

  def anyContent = Action.stringent.anyContent[OkStatus, BadRequestStatus]{ request =>
    Ok
  }

  def block = Action.stringent[OkStatus, BadRequestStatus]{
    Ok
  }

  def asyncBlock = Action.stringent.async[OkStatus, BadRequestStatus]{
    Future(Ok)
  }

  def asyncAnyContent = Action.stringent.anyContentAsync[OkStatus, BadRequestStatus]{ request =>
    Future(Ok)
  }

  def asyncBodyContent = Action.stringent.withContentAsync[TestContent, OkStatus, BadRequestStatus](parse.json[TestContent]){ request =>
    Future(Ok)
  }
}
