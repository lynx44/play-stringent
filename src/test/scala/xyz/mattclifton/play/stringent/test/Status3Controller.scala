package xyz.mattclifton.play.stringent.test

import play.api.mvc.{Action, Controller}
import xyz.mattclifton.play.stringent.StringentActions
import xyz.mattclifton.play.stringent.test.helpers.FakeParsers._
import xyz.mattclifton.play.stringent.test.models.TestContent

import scala.concurrent.{ExecutionContext, Future}

class Status3Controller(implicit executionContext: ExecutionContext) extends Controller with StringentActions {
  def withBodyParser = Action.stringent.withContent[TestContent, OkResult, BadRequestResult, UnauthorizedResult](parse.json[TestContent]){ request =>
    Ok
  }

  def anyContent = Action.stringent.anyContent[OkResult, BadRequestResult, UnauthorizedResult]{ request =>
    Ok
  }

  def block = Action.stringent[OkResult, BadRequestResult, UnauthorizedResult]{
    Ok
  }

  def asyncBlock = Action.stringent.async[OkResult, BadRequestResult, UnauthorizedResult]{
    Future(Ok)
  }

  def asyncAnyContent = Action.stringent.anyContentAsync[OkResult, BadRequestResult, UnauthorizedResult]{ request =>
    Future(Ok)
  }

  def asyncBodyContent = Action.stringent.withContentAsync[TestContent, OkResult, BadRequestResult, UnauthorizedResult](parse.json[TestContent]){ request =>
    Future(Ok)
  }
}
