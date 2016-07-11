package xyz.mattclifton.play.stringent.test

import play.api.mvc.{Action, Controller}
import xyz.mattclifton.play.stringent.StringentActions
import xyz.mattclifton.play.stringent.test.helpers.FakeParsers._
import xyz.mattclifton.play.stringent.test.models.{TestResponse, TestContent}

class Status1Controller extends Controller with StringentActions {
  def withBodyParser = Action.stringent.withContent[TestContent, OkStatus](parse.json[TestContent]){ request =>
    Ok
  }
}
