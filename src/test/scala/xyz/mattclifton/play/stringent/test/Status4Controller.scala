package xyz.mattclifton.play.stringent.test

import play.api.mvc.{Action, Controller}
import xyz.mattclifton.play.stringent.StringentActions
import xyz.mattclifton.play.stringent.test.helpers.FakeParsers._
import xyz.mattclifton.play.stringent.test.models.TestContent

import scala.concurrent.{ExecutionContext, Future}

class Status4Controller(implicit executionContext: ExecutionContext) extends Controller with StringentActions {
  def withBodyParser = Action.stringent.withContent[TestContent, OkResult, BadRequestResult, UnauthorizedResult, CreatedResult](parse.json[TestContent]){ request =>
    Ok
  }

  def anyContent = Action.stringent.anyContent[OkResult, BadRequestResult, UnauthorizedResult, CreatedResult]{ request =>
    Ok
  }

  def block = Action.stringent[OkResult, BadRequestResult, UnauthorizedResult, CreatedResult]{
    Ok
  }

  def asyncBlock = Action.stringent.async[OkResult, BadRequestResult, UnauthorizedResult, CreatedResult]{
    Future(Ok)
  }

  def asyncAnyContent = Action.stringent.anyContentAsync[OkResult, BadRequestResult, UnauthorizedResult, CreatedResult]{ request =>

    Future(Ok)
  }

  def asyncBodyContent = Action.stringent.withContentAsync[TestContent, OkResult, BadRequestResult, UnauthorizedResult, CreatedResult](parse.json[TestContent]){ request =>
    Future(Ok)
  }
}
