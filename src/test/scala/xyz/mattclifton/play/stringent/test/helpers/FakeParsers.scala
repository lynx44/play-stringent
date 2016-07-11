package xyz.mattclifton.play.stringent.test.helpers

import play.api.http.Writeable
import play.api.libs.json.Reads
import scala.reflect.runtime.universe._

object FakeParsers {
  implicit def writeable[T]: Writeable[T] = ???
  implicit def reads[T]: Reads[T] = ???
}
