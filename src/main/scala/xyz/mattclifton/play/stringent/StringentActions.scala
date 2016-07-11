package xyz.mattclifton.play.stringent

import play.api.http.{HttpEntity, Writeable}
import play.api.mvc._

import scala.concurrent.Future
import scala.reflect.runtime.universe._

trait StringentActions extends Results {

  import BaseTypes._

  /*
   * Updated Status Codes
   */
  override val Ok: OkStatus = new OkStatus()
  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
        new OkWithContent[C](super.withContent(content))
  }
  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  override val Created: CreatedStatus = new CreatedStatus()
  class CreatedStatus() extends StringentStatus(play.api.http.Status.CREATED) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): CreatedWithContent[C] =
        new CreatedWithContent[C](super.withContent(content))
  }
  class CreatedWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  override val Accepted: AcceptedStatus = new AcceptedStatus()
  class AcceptedStatus() extends StringentStatus(play.api.http.Status.ACCEPTED) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): AcceptedWithContent[C] =
        new AcceptedWithContent[C](super.withContent(content))
  }
  class AcceptedWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  override val Unauthorized: UnauthorizedStatus = new UnauthorizedStatus()
  class UnauthorizedStatus() extends StringentStatus(play.api.http.Status.UNAUTHORIZED) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): UnauthorizedWithContent[C] =
      new UnauthorizedWithContent[C](super.withContent(content))
  }
  class UnauthorizedWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  override val BadRequest: BadRequestStatus = new BadRequestStatus()
  class BadRequestStatus() extends StringentStatus(play.api.http.Status.BAD_REQUEST) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): BadRequestWithContent[C] =
      new BadRequestWithContent[C](super.withContent(content))
  }
  class BadRequestWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  /*
   * Status Groups
   */

  trait StatusOf {
    def result: Result
  }

  object StatusOf2 {

    class StringentActionResult[T, S1 <: StringentStatus, S2 <: StringentStatus](action: Action[T]) extends Action[T] {
      def parser: BodyParser[T] = action.parser

      def apply(request: Request[T]): Future[Result] = action.apply(request)
    }

    class AnyOf[S1 <: StringentStatus, S2 <: StringentStatus](s1: Option[S1], s2: Option[S2]) extends StatusOf {
      def result = s1.getOrElse(s2.get)
    }

    implicit def result1[S1 <: StringentStatus, S2 <: StringentStatus](s1: S1)(implicit tag: TypeTag[S1]) = {
      new AnyOf[S1, S2](Some(s1), None)
    }

    implicit def result2[S1 <: StringentStatus, S2 <: StringentStatus](s2: S2)(implicit tag: TypeTag[S2]) = {
      new AnyOf[S1, S2](None, Some(s2))
    }
  }

  object StatusOf3 {

    class StringentActionResult[T, S1 <: StringentStatus, S2 <: StringentStatus, S3 <: StringentStatus](action: Action[T]) extends Action[T] {
      def parser: BodyParser[T] = action.parser

      def apply(request: Request[T]): Future[Result] = action.apply(request)
    }

    class AnyOf[S1 <: StringentStatus, S2 <: StringentStatus, S3 <: StringentStatus](s1: Option[S1], s2: Option[S2], s3: Option[S3]) extends StatusOf {
      def result = s1.getOrElse(s2.getOrElse(s3.get))
    }

    implicit def result1[S1 <: StringentStatus, S2 <: StringentStatus, S3 <: StringentStatus](s1: S1)(implicit tag: TypeTag[S1]) = {
      new AnyOf[S1, S2, S3](Some(s1), None, None)
    }

    implicit def result2[S1 <: StringentStatus, S2 <: StringentStatus, S3 <: StringentStatus](s2: S2)(implicit tag: TypeTag[S2]) = {
      new AnyOf[S1, S2, S3](None, Some(s2), None)
    }

    implicit def result3[S1 <: StringentStatus, S2 <: StringentStatus, S3 <: StringentStatus](s3: S3)(implicit tag: TypeTag[S3]) = {
      new AnyOf[S1, S2, S3](None, None, Some(s3))
    }
  }

  object StatusOf4 {

    class StringentActionResult[T, S1 <: StringentStatus, S2 <: StringentStatus, S3 <: StringentStatus, S4 <: StringentStatus](action: Action[T]) extends Action[T] {
      def parser: BodyParser[T] = action.parser

      def apply(request: Request[T]): Future[Result] = action.apply(request)
    }

    class AnyOf[S1 <: StringentStatus, S2 <: StringentStatus, S3 <: StringentStatus, S4 <: StringentStatus](s1: Option[S1], s2: Option[S2], s3: Option[S3], s4: Option[S4]) extends StatusOf {
      def result = s1.getOrElse(s2.getOrElse(s3.getOrElse(s4.get)))
    }

    implicit def result1[S1 <: StringentStatus, S2 <: StringentStatus, S3 <: StringentStatus, S4 <: StringentStatus](s1: S1)(implicit tag: TypeTag[S1]) = {
      new AnyOf[S1, S2, S3, S4](Some(s1), None, None, None)
    }

    implicit def result2[S1 <: StringentStatus, S2 <: StringentStatus, S3 <: StringentStatus, S4 <: StringentStatus](s2: S2)(implicit tag: TypeTag[S2]) = {
      new AnyOf[S1, S2, S3, S4](None, Some(s2), None, None)
    }

    implicit def result3[S1 <: StringentStatus, S2 <: StringentStatus, S3 <: StringentStatus, S4 <: StringentStatus](s3: S3)(implicit tag: TypeTag[S3]) = {
      new AnyOf[S1, S2, S3, S4](None, None, Some(s3), None)
    }

    implicit def result4[S1 <: StringentStatus, S2 <: StringentStatus, S3 <: StringentStatus, S4 <: StringentStatus](s4: S4)(implicit tag: TypeTag[S4]) = {
      new AnyOf[S1, S2, S3, S4](None, None, None, Some(s4))
    }
  }

  object StatusOf5 {

    class StringentActionResult[T, S1 <: StringentStatus, S2 <: StringentStatus, S3 <: StringentStatus, S4 <: StringentStatus, S5 <: StringentStatus](action: Action[T]) extends Action[T] {
      def parser: BodyParser[T] = action.parser

      def apply(request: Request[T]): Future[Result] = action.apply(request)
    }

    class AnyOf[S1 <: StringentStatus, S2 <: StringentStatus, S3 <: StringentStatus, S4 <: StringentStatus, S5 <: StringentStatus](s1: Option[S1], s2: Option[S2], s3: Option[S3], s4: Option[S4], s5: Option[S5]) extends StatusOf {
      def result = s1.getOrElse(s2.getOrElse(s3.getOrElse(s4.getOrElse(s5.get))))
    }

    implicit def result1[S1 <: StringentStatus, S2 <: StringentStatus, S3 <: StringentStatus, S4 <: StringentStatus, S5 <: StringentStatus](s1: S1)(implicit tag: TypeTag[S1]) = {
      new AnyOf[S1, S2, S3, S4, S5](Some(s1), None, None, None, None)
    }

    implicit def result2[S1 <: StringentStatus, S2 <: StringentStatus, S3 <: StringentStatus, S4 <: StringentStatus, S5 <: StringentStatus](s2: S2)(implicit tag: TypeTag[S2]) = {
      new AnyOf[S1, S2, S3, S4, S5](None, Some(s2), None, None, None)
    }

    implicit def result3[S1 <: StringentStatus, S2 <: StringentStatus, S3 <: StringentStatus, S4 <: StringentStatus, S5 <: StringentStatus](s3: S3)(implicit tag: TypeTag[S3]) = {
      new AnyOf[S1, S2, S3, S4, S5](None, None, Some(s3), None, None)
    }

    implicit def result4[S1 <: StringentStatus, S2 <: StringentStatus, S3 <: StringentStatus, S4 <: StringentStatus, S5 <: StringentStatus](s4: S4)(implicit tag: TypeTag[S4]) = {
      new AnyOf[S1, S2, S3, S4, S5](None, None, None, Some(s4), None)
    }

    implicit def result5[S1 <: StringentStatus, S2 <: StringentStatus, S3 <: StringentStatus, S4 <: StringentStatus, S5 <: StringentStatus](s5: S5)(implicit tag: TypeTag[S5]) = {
      new AnyOf[S1, S2, S3, S4, S5](None, None, None, None, Some(s5))
    }
  }

  object BaseTypes {

    class StringentStatus(status: Int) extends Status(status) {
      def withContent[C](content: C)(implicit writeable: Writeable[C]): StringentContentStatus[C] = {
        new StringentContentStatus[C](status, apply(content).body)
      }
    }

    class StringentContentStatus[C](val status: Int, override val body: HttpEntity) extends StringentStatus(status)

    case class StringentWrapper[R[A]](stringent: StringentAction[R])
    case class StringentActionImpl[R[A]](override val action: play.api.mvc.ActionBuilder[R]) extends StringentAction[R]

    trait StringentAction[+R[_]] {
      def action: play.api.mvc.ActionBuilder[R]

      /*
       * Status 1
       */

      //apply[A](bodyParser: BodyParser[A])(block: R[A] => Result): Action[A]
      def withContent[A, S1 <: StringentStatus](bodyParser: BodyParser[A])(block: R[A] => S1)(implicit s1tag: TypeTag[S1]): Action[A] = action.async(bodyParser) { req: R[A] =>
        Future.successful(block(req))
      }

      //apply(block: R[AnyContent] => Result): Action[AnyContent]
//      def apply[S1 <: StringentStatus](block: R[AnyContent] => S1)(implicit s1tag: TypeTag[S1]): Action[AnyContent] = action { req: R[AnyContent] =>
//        block(req)
//      }

      //apply(block: => Result): Action[AnyContent]
//      def apply[S1 <: StringentStatus](block: => S1)(implicit s1tag: TypeTag[S1]): Action[AnyContent] = action.apply(block)

      //async(block: => Future[Result]): Action[AnyContent]
      //async(block: R[AnyContent] => Future[Result]): Action[AnyContent]
      //async[A](bodyParser: BodyParser[A])(block: R[A] => Future[Result]): Action[A]

      /*
       * Status 2
       */

      //apply[A](bodyParser: BodyParser[A])(block: R[A] => Result): Action[A]
      def withContent[A, S1 <: StringentStatus, S2 <: StringentStatus](bodyParser: BodyParser[A])(block: R[A] => StatusOf2.AnyOf[S1, S2])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2]): StatusOf2.StringentActionResult[A, S1, S2] =
        new StatusOf2.StringentActionResult[A, S1, S2](action.async(bodyParser) { req: R[A] =>
          Future.successful(block(req).result)
        })

      //apply(block: => Result): Action[AnyContent]
      def apply[S1 <: StringentStatus, S2 <: StringentStatus](block: => StatusOf2.AnyOf[S1, S2])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2]): StatusOf2.StringentActionResult[AnyContent, S1, S2] =
        new StatusOf2.StringentActionResult[AnyContent, S1, S2](action.async { req: R[AnyContent] =>
          Future.successful(block.result)
        })

      //apply(block: R[AnyContent] => Result): Action[AnyContent]
      // while OkStatus can be converted to StatusOf2.AnyOf[S1, S2], that is not it's usual type. so the compiler
      // thinks we're passing R[AnyContent] => OkStatus instead of R[AnyContent] => StatusOf2.AnyOf[S1, S2] and
      // cant find the correct overload
      def anyContent[S1 <: StringentStatus, S2 <: StringentStatus](block: R[AnyContent] => StatusOf2.AnyOf[S1, S2])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2]): StatusOf2.StringentActionResult[AnyContent, S1, S2] =
        new StatusOf2.StringentActionResult[AnyContent, S1, S2](action.async { req: R[AnyContent] =>
          Future.successful(block(req).result)
        })

      /*
       * Status 3
       */

      def withContent[A, S1 <: StringentStatus, S2 <: StringentStatus, S3 <: StringentStatus](bodyParser: BodyParser[A])(block: R[A] => StatusOf3.AnyOf[S1, S2, S3])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], s3tag: TypeTag[S3]): StatusOf3.StringentActionResult[A, S1, S2, S3] =
        new StatusOf3.StringentActionResult[A, S1, S2, S3](action.async(bodyParser) { req: R[A] =>
          Future.successful(block(req).result)
        })

      def withContent[A, S1 <: StringentStatus, S2 <: StringentStatus, S3 <: StringentStatus, S4 <: StringentStatus](bodyParser: BodyParser[A])(block: R[A] => StatusOf4.AnyOf[S1, S2, S3, S4])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], s3tag: TypeTag[S3], s4tag: TypeTag[S4]): StatusOf4.StringentActionResult[A, S1, S2, S3, S4] =
        new StatusOf4.StringentActionResult[A, S1, S2, S3, S4](action.async(bodyParser) { req: R[A] =>
          Future.successful(block(req).result)
        })

      def withContent[A, S1 <: StringentStatus, S2 <: StringentStatus, S3 <: StringentStatus, S4 <: StringentStatus, S5 <: StringentStatus](bodyParser: BodyParser[A])(block: R[A] => StatusOf5.AnyOf[S1, S2, S3, S4, S5])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], s3tag: TypeTag[S3], s4tag: TypeTag[S4], s5tag: TypeTag[S5]): StatusOf5.StringentActionResult[A, S1, S2, S3, S4, S5] =
        new StatusOf5.StringentActionResult[A, S1, S2, S3, S4, S5](action.async(bodyParser) { req: R[A] =>
          Future.successful(block(req).result)
        })
    }
  }

  implicit def toStringent[R[A]](action: play.api.mvc.ActionBuilder[R]) = {
    BaseTypes.StringentWrapper(BaseTypes.StringentActionImpl(action))
  }
}

