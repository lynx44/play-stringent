package xyz.mattclifton.play.stringent.test

import play.api.mvc.{Action, Controller}
import xyz.mattclifton.play.stringent.StringentActions
import xyz.mattclifton.play.stringent.test.helpers.FakeParsers._
import xyz.mattclifton.play.stringent.test.models.{TestResponse, TestContent}

import scala.concurrent.{ExecutionContext, Future}

class Status1Controller(implicit executionContext: ExecutionContext) extends Controller with StringentActions {
  def withBodyParser = Action.stringent.withContent[TestContent, OkStatus](parse.json[TestContent]){ request =>
    Ok
  }

  def anyContent = Action.stringent.anyContent[OkStatus]{ request =>
    Ok
  }

  def block = Action.stringent[OkStatus]{
    Ok
  }

  def asyncBlock = Action.stringent.async[OkStatus]{
    Future(Ok)
  }

  def asyncAnyContent = Action.stringent.anyContentAsync[OkStatus]{ request =>
    Future(Ok)
  }

  def asyncBodyContent = Action.stringent.withContentAsync[TestContent, OkStatus](parse.json[TestContent]){ request =>
    Future(Ok)
  }

  def blockWithContent = Action.stringent[OkWithContent[TestResponse]]{
    Ok.withContent(TestResponse(1, "test"))
  }

  def redirect = Action.stringent[SeeOtherStatus]{
    RedirectTo("https://mattclifton.xyz")
  }
}
