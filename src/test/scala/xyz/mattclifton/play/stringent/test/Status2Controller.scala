package xyz.mattclifton.play.stringent.test

import play.api.mvc.{Request, AnyContent, Action, Controller}
import xyz.mattclifton.play.stringent.StringentActions
import xyz.mattclifton.play.stringent.test.helpers.FakeParsers._
import xyz.mattclifton.play.stringent.test.models.{TestResponse, TestContent}

import scala.concurrent.{ExecutionContext, Future}

class Status2Controller(implicit executionContext: ExecutionContext) extends Controller with StringentActions {
  def withBodyParser = Action.stringent.withContent[TestContent, OkResult, BadRequestResult](parse.json[TestContent]){ request =>
    Ok
  }

  def anyContent = Action.stringent.anyContent[OkResult, BadRequestResult]{ request =>
    Ok
  }

  def block = Action.stringent[OkResult, BadRequestResult]{
    Ok
  }

  def asyncBlock = Action.stringent.async[OkResult, BadRequestResult]{
    Future(Ok)
  }

  def asyncAnyContent = Action.stringent.anyContentAsync[OkResult, BadRequestResult]{ request =>
    Future(Ok)
  }

  def asyncBodyContent = Action.stringent.withContentAsync[TestContent, OkResult, BadRequestResult](parse.json[TestContent]){ request =>
    Future(Ok)
  }
}
