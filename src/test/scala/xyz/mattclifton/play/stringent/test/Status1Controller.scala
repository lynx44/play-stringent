package xyz.mattclifton.play.stringent.test

import play.api.mvc.{Action, Controller}
import xyz.mattclifton.play.stringent.StringentActions
import xyz.mattclifton.play.stringent.test.helpers.FakeParsers._
import xyz.mattclifton.play.stringent.test.models.{TestResponse, TestContent}

import scala.concurrent.{ExecutionContext, Future}

class Status1Controller(implicit executionContext: ExecutionContext) extends Controller with StringentActions {
  def withBodyParser = Action.stringent.withContent[TestContent, OkResult](parse.json[TestContent]){ request =>
    Ok
  }

  def anyContent = Action.stringent.anyContent[OkResult]{ request =>
    Ok
  }

  def block = Action.stringent[OkResult]{
    Ok
  }

  def asyncBlock = Action.stringent.async[OkResult]{
    Future(Ok)
  }

  def asyncAnyContent = Action.stringent.anyContentAsync[OkResult]{ request =>
    Future(Ok)
  }

  def asyncBodyContent = Action.stringent.withContentAsync[TestContent, OkResult](parse.json[TestContent]){ request =>
    Future(Ok)
  }

  def blockWithContent = Action.stringent[OkWithContent[TestResponse]]{
    Ok.withContent(TestResponse(1, "test"))
  }

  def redirect = Action.stringent[SeeOtherResult]{
    RedirectTo("https://mattclifton.xyz")
  }
}
