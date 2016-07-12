package xyz.mattclifton.play.stringent

import java.io.File
import java.nio.file.Path

import akka.stream.scaladsl.Source
import play.api.http.{HttpEntity, Writeable}
import play.api.libs.iteratee.Enumerator
import play.api.mvc.Results.Status
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}
import scala.reflect.runtime.universe._

trait StringentActions extends Results {

  import BaseTypes._

  /*
   * Updated Status Codes
   */

////  /** Generates a ‘203 NON_AUTHORITATIVE_INFORMATION’ result. */
////  val NonAuthoritativeInformation = new Status(NON_AUTHORITATIVE_INFORMATION)
////
//  override val NonAuthoritativeInformation: NonAuthoritativeInformationStatus = new NonAuthoritativeInformationStatus()
//  class NonAuthoritativeInformationStatus() extends StringentStatus(play.api.http.Status.NON_AUTHORITATIVE_INFORMATION) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): NonAuthoritativeInformationWithContent[C] =
//      new NonAuthoritativeInformationWithContent[C](super.withContent(content))
//  }
//  class NonAuthoritativeInformationWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
//
////  /** Generates a ‘204 NO_CONTENT’ result. */
////  val NoContent = Result(header = ResponseHeader(NO_CONTENT), body = HttpEntity.NoEntity)
////
//  override val NoContent: NoContentStatus = new NoContentStatus()
//  class NoContentStatus() extends StringentStatus(play.api.http.Status.NO_CONTENT) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): NoContentWithContent[C] =
//      new NoContentWithContent[C](super.withContent(content))
//  }
//  class NoContentWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘205 RESET_CONTENT’ result. */
////  val ResetContent = Result(header = ResponseHeader(RESET_CONTENT), body = HttpEntity.NoEntity)
////
//
//  override val ResetContent: ResetContentStatus = new ResetContentStatus()
//  class ResetContentStatus() extends StringentStatus(play.api.http.Status.RESET_CONTENT) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): ResetContentWithContent[C] =
//      new ResetContentWithContent[C](super.withContent(content))
//  }
//  class ResetContentWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘206 PARTIAL_CONTENT’ result. */
////  val PartialContent = new Status(PARTIAL_CONTENT)
////
//
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘207 MULTI_STATUS’ result. */
////  val MultiStatus = new Status(MULTI_STATUS)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /**
////    * Generates a ‘301 MOVED_PERMANENTLY’ simple result.
////    *
////    * @param url the URL to redirect to
////    */
////  def MovedPermanently(url: String): Result = Redirect(url, MOVED_PERMANENTLY)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /**
////    * Generates a ‘302 FOUND’ simple result.
////    *
////    * @param url the URL to redirect to
////    */
////  def Found(url: String): Result = Redirect(url, FOUND)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /**
////    * Generates a ‘303 SEE_OTHER’ simple result.
////    *
////    * @param url the URL to redirect to
////    */
////  def SeeOther(url: String): Result = Redirect(url, SEE_OTHER)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘304 NOT_MODIFIED’ result. */
////  val NotModified = Result(header = ResponseHeader(NOT_MODIFIED), body = HttpEntity.NoEntity)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /**
////    * Generates a ‘307 TEMPORARY_REDIRECT’ simple result.
////    *
////    * @param url the URL to redirect to
////    */
////  def TemporaryRedirect(url: String): Result = Redirect(url, TEMPORARY_REDIRECT)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /**
////    * Generates a ‘308 PERMANENT_REDIRECT’ simple result.
////    *
////    * @param url the URL to redirect to
////    */
////  def PermanentRedirect(url: String): Result = Redirect(url, PERMANENT_REDIRECT)
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
//
////
////  /** Generates a ‘402 PAYMENT_REQUIRED’ result. */
////  val PaymentRequired = new Status(PAYMENT_REQUIRED)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘403 FORBIDDEN’ result. */
////  val Forbidden = new Status(FORBIDDEN)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘404 NOT_FOUND’ result. */
////  val NotFound = new Status(NOT_FOUND)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘405 METHOD_NOT_ALLOWED’ result. */
////  val MethodNotAllowed = new Status(METHOD_NOT_ALLOWED)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘406 NOT_ACCEPTABLE’ result. */
////  val NotAcceptable = new Status(NOT_ACCEPTABLE)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘408 REQUEST_TIMEOUT’ result. */
////  val RequestTimeout = new Status(REQUEST_TIMEOUT)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘409 CONFLICT’ result. */
////  val Conflict = new Status(CONFLICT)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘410 GONE’ result. */
////  val Gone = new Status(GONE)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘412 PRECONDITION_FAILED’ result. */
////  val PreconditionFailed = new Status(PRECONDITION_FAILED)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘413 REQUEST_ENTITY_TOO_LARGE’ result. */
////  val EntityTooLarge = new Status(REQUEST_ENTITY_TOO_LARGE)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘414 REQUEST_URI_TOO_LONG’ result. */
////  val UriTooLong = new Status(REQUEST_URI_TOO_LONG)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘415 UNSUPPORTED_MEDIA_TYPE’ result. */
////  val UnsupportedMediaType = new Status(UNSUPPORTED_MEDIA_TYPE)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘417 EXPECTATION_FAILED’ result. */
////  val ExpectationFailed = new Status(EXPECTATION_FAILED)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘422 UNPROCESSABLE_ENTITY’ result. */
////  val UnprocessableEntity = new Status(UNPROCESSABLE_ENTITY)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘423 LOCKED’ result. */
////  val Locked = new Status(LOCKED)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘424 FAILED_DEPENDENCY’ result. */
////  val FailedDependency = new Status(FAILED_DEPENDENCY)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘429 TOO_MANY_REQUESTS’ result. */
////  val TooManyRequests = new Status(TOO_MANY_REQUESTS)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘429 TOO_MANY_REQUEST’ result. */
////  @deprecated("Use TooManyRequests instead", "3.0.0")
////  val TooManyRequest = TooManyRequests
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘500 INTERNAL_SERVER_ERROR’ result. */
////  val InternalServerError = new Status(INTERNAL_SERVER_ERROR)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘501 NOT_IMPLEMENTED’ result. */
////  val NotImplemented = new Status(NOT_IMPLEMENTED)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘502 BAD_GATEWAY’ result. */
////  val BadGateway = new Status(BAD_GATEWAY)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘503 SERVICE_UNAVAILABLE’ result. */
////  val ServiceUnavailable = new Status(SERVICE_UNAVAILABLE)
////
//  override val Ok: OkStatus = new  OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘504 GATEWAY_TIMEOUT’ result. */
////  val GatewayTimeout = new Status(GATEWAY_TIMEOUT)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘505 HTTP_VERSION_NOT_SUPPORTED’ result. */
////  val HttpVersionNotSupported = new Status(HTTP_VERSION_NOT_SUPPORTED)
////
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)
////  /** Generates a ‘507 INSUFFICIENT_STORAGE’ result. */
////  val InsufficientStorage = new Status(INSUFFICIENT_STORAGE)
//  override val Ok: OkStatus = new OkStatus()
//  class OkStatus() extends StringentStatus(play.api.http.Status.OK) {
//    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
//      new OkWithContent[C](super.withContent(content))
//  }
//  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

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

  object StatusOf1 {

    class StringentReturn[T, S1 <: StringentResult](action: Action[T]) extends Action[T] {
      def parser: BodyParser[T] = action.parser

      def apply(request: Request[T]): Future[Result] = action.apply(request)
    }

    class AnyOf[S1 <: StringentResult](s1: Option[S1]) extends StatusOf {
      def result = toResult(s1.get)
    }

    implicit def result1[S1 <: StringentResult](s1: S1)(implicit tag: TypeTag[S1]) = {
      new AnyOf[S1](Some(s1))
    }
  }

  object StatusOf2 {

    class StringentReturn[T, S1 <: StringentResult, S2 <: StringentResult](action: Action[T]) extends Action[T] {
      def parser: BodyParser[T] = action.parser

      def apply(request: Request[T]): Future[Result] = action.apply(request)
    }

    class AnyOf[S1 <: StringentResult, S2 <: StringentResult](s1: Option[S1], s2: Option[S2]) extends StatusOf {
      def result = toResult(s1.getOrElse(s2.get))
    }

    implicit def result1[S1 <: StringentResult, S2 <: StringentResult](s1: S1)(implicit tag: TypeTag[S1]) = {
      new AnyOf[S1, S2](Some(s1), None)
    }

    implicit def result2[S1 <: StringentResult, S2 <: StringentResult](s2: S2)(implicit tag: TypeTag[S2]) = {
      new AnyOf[S1, S2](None, Some(s2))
    }
  }

  object StatusOf3 {

    class StringentReturn[T, S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult](action: Action[T]) extends Action[T] {
      def parser: BodyParser[T] = action.parser

      def apply(request: Request[T]): Future[Result] = action.apply(request)
    }

    class AnyOf[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult](s1: Option[S1], s2: Option[S2], s3: Option[S3]) extends StatusOf {
      def result = toResult(s1.getOrElse(s2.getOrElse(s3.get)))
    }

    implicit def result1[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult](s1: S1)(implicit tag: TypeTag[S1]) = {
      new AnyOf[S1, S2, S3](Some(s1), None, None)
    }

    implicit def result2[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult](s2: S2)(implicit tag: TypeTag[S2]) = {
      new AnyOf[S1, S2, S3](None, Some(s2), None)
    }

    implicit def result3[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult](s3: S3)(implicit tag: TypeTag[S3]) = {
      new AnyOf[S1, S2, S3](None, None, Some(s3))
    }
  }

  object StatusOf4 {

    class StringentReturn[T, S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult](action: Action[T]) extends Action[T] {
      def parser: BodyParser[T] = action.parser

      def apply(request: Request[T]): Future[Result] = action.apply(request)
    }

    class AnyOf[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult](s1: Option[S1], s2: Option[S2], s3: Option[S3], s4: Option[S4]) extends StatusOf {
      def result = toResult(s1.getOrElse(s2.getOrElse(s3.getOrElse(s4.get))))
    }

    implicit def result1[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult](s1: S1)(implicit tag: TypeTag[S1]) = {
      new AnyOf[S1, S2, S3, S4](Some(s1), None, None, None)
    }

    implicit def result2[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult](s2: S2)(implicit tag: TypeTag[S2]) = {
      new AnyOf[S1, S2, S3, S4](None, Some(s2), None, None)
    }

    implicit def result3[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult](s3: S3)(implicit tag: TypeTag[S3]) = {
      new AnyOf[S1, S2, S3, S4](None, None, Some(s3), None)
    }

    implicit def result4[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult](s4: S4)(implicit tag: TypeTag[S4]) = {
      new AnyOf[S1, S2, S3, S4](None, None, None, Some(s4))
    }
  }

  object StatusOf5 {

    class StringentReturn[T, S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult, S5 <: StringentResult](action: Action[T]) extends Action[T] {
      def parser: BodyParser[T] = action.parser

      def apply(request: Request[T]): Future[Result] = action.apply(request)
    }

    class AnyOf[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult, S5 <: StringentResult](s1: Option[S1], s2: Option[S2], s3: Option[S3], s4: Option[S4], s5: Option[S5]) extends StatusOf {
      def result = toResult(s1.getOrElse(s2.getOrElse(s3.getOrElse(s4.getOrElse(s5.get)))))
    }

    implicit def result1[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult, S5 <: StringentResult](s1: S1)(implicit tag: TypeTag[S1]) = {
      new AnyOf[S1, S2, S3, S4, S5](Some(s1), None, None, None, None)
    }

    implicit def result2[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult, S5 <: StringentResult](s2: S2)(implicit tag: TypeTag[S2]) = {
      new AnyOf[S1, S2, S3, S4, S5](None, Some(s2), None, None, None)
    }

    implicit def result3[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult, S5 <: StringentResult](s3: S3)(implicit tag: TypeTag[S3]) = {
      new AnyOf[S1, S2, S3, S4, S5](None, None, Some(s3), None, None)
    }

    implicit def result4[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult, S5 <: StringentResult](s4: S4)(implicit tag: TypeTag[S4]) = {
      new AnyOf[S1, S2, S3, S4, S5](None, None, None, Some(s4), None)
    }

    implicit def result5[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult, S5 <: StringentResult](s5: S5)(implicit tag: TypeTag[S5]) = {
      new AnyOf[S1, S2, S3, S4, S5](None, None, None, None, Some(s5))
    }
  }


  implicit def toResult(stringentResult: StringentResult): Result = new Result(stringentResult.header, stringentResult.body)

  object BaseTypes {

    class StringentStatus(statusCode: Int) extends Status(statusCode) with StringentResult {

//      private val status = new Status(statusCode)
      def withContent[C](content: C)(implicit writeable: Writeable[C]): StringentContentStatus[C] = {
        new StringentContentStatus[C](statusCode, apply(content).body)
      }
    }
    
    class StringentResultImpl(override val header: ResponseHeader, override val body: HttpEntity) extends Result(header, body) with StringentResult

    trait StringentResult {
      def header: ResponseHeader
      def body: HttpEntity
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
      def withContent[A, S1 <: StringentResult](bodyParser: BodyParser[A])(block: R[A] => StatusOf1.AnyOf[S1])(implicit s1tag: TypeTag[S1]) =
        new StatusOf1.StringentReturn[A, S1](action(bodyParser) { req: R[A] =>
          block(req).result
        })

      //apply(block: => Result): Action[AnyContent]
      def apply[S1 <: StringentResult](block: => StatusOf1.AnyOf[S1])(implicit s1tag: TypeTag[S1]) =
        new StatusOf1.StringentReturn[AnyContent, S1](action { block.result })

      //apply(block: R[AnyContent] => Result): Action[AnyContent]
      def anyContent[S1 <: StringentResult](block: R[AnyContent] => StatusOf1.AnyOf[S1])(implicit s1tag: TypeTag[S1]) =
        new StatusOf1.StringentReturn[AnyContent, S1](action { req: R[AnyContent] =>
          block(req).result
        })

      //async(block: => Future[Result]): Action[AnyContent]
      def async[S1 <: StringentResult](block: => Future[StatusOf1.AnyOf[S1]])(implicit s1tag: TypeTag[S1], executionContext: ExecutionContext) =
        new StatusOf1.StringentReturn[AnyContent, S1](action.async {
          block.map(_.result)
        })

      //async(block: R[AnyContent] => Future[Result]): Action[AnyContent]
      def anyContentAsync[S1 <: StringentResult](block: R[AnyContent] => Future[StatusOf1.AnyOf[S1]])(implicit s1tag: TypeTag[S1], executionContext: ExecutionContext) =
        new StatusOf1.StringentReturn[AnyContent, S1](action.async{ req: R[AnyContent] =>
          block(req).map(_.result)
        })

      //async[A](bodyParser: BodyParser[A])(block: R[A] => Future[Result]): Action[A]
      def withContentAsync[A, S1 <: StringentResult](bodyParser: BodyParser[A])(block: R[A] => Future[StatusOf1.AnyOf[S1]])(implicit s1tag: TypeTag[S1], executionContext: ExecutionContext) =
        new StatusOf1.StringentReturn[A, S1](action.async(bodyParser) { req: R[A] =>
          block(req).map(_.result)
        })

      /*
       * Status 2
       */

      //apply[A](bodyParser: BodyParser[A])(block: R[A] => Result): Action[A]
      def withContent[A, S1 <: StringentResult, S2 <: StringentResult](bodyParser: BodyParser[A])(block: R[A] => StatusOf2.AnyOf[S1, S2])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2]) =
        new StatusOf2.StringentReturn[A, S1, S2](action(bodyParser) { req: R[A] =>
          block(req).result
        })

      //apply(block: => Result): Action[AnyContent]
      def apply[S1 <: StringentResult, S2 <: StringentResult](block: => StatusOf2.AnyOf[S1, S2])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2]) =
        new StatusOf2.StringentReturn[AnyContent, S1, S2](action { block.result })

      //apply(block: R[AnyContent] => Result): Action[AnyContent]
      // while OkStatus can be converted to StatusOf2.AnyOf[S1, S2], that is not it's usual type. so the compiler
      // thinks we're passing R[AnyContent] => OkStatus instead of R[AnyContent] => StatusOf2.AnyOf[S1, S2] and
      // cant find the correct overload. this is why a rename is required
      def anyContent[S1 <: StringentResult, S2 <: StringentResult](block: R[AnyContent] => StatusOf2.AnyOf[S1, S2])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2]) =
        new StatusOf2.StringentReturn[AnyContent, S1, S2](action { req: R[AnyContent] =>
          block(req).result
        })

      //async(block: => Future[Result]): Action[AnyContent]
      def async[S1 <: StringentResult, S2 <: StringentResult](block: => Future[StatusOf2.AnyOf[S1, S2]])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], executionContext: ExecutionContext) =
        new StatusOf2.StringentReturn[AnyContent, S1, S2](action.async {
          block.map(_.result)
        })

      //async(block: R[AnyContent] => Future[Result]): Action[AnyContent]
      def anyContentAsync[S1 <: StringentResult, S2 <: StringentResult](block: R[AnyContent] => Future[StatusOf2.AnyOf[S1, S2]])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], executionContext: ExecutionContext) =
        new StatusOf2.StringentReturn[AnyContent, S1, S2](action.async{ req: R[AnyContent] =>
          block(req).map(_.result)
        })

      //async[A](bodyParser: BodyParser[A])(block: R[A] => Future[Result]): Action[A]
      def withContentAsync[A, S1 <: StringentResult, S2 <: StringentResult](bodyParser: BodyParser[A])(block: R[A] => Future[StatusOf2.AnyOf[S1, S2]])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], executionContext: ExecutionContext) =
        new StatusOf2.StringentReturn[A, S1, S2](action.async(bodyParser) { req: R[A] =>
          block(req).map(_.result)
        })

      /*
       * Status 3
       */

      //apply[A](bodyParser: BodyParser[A])(block: R[A] => Result): Action[A]
      def withContent[A, S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult](bodyParser: BodyParser[A])(block: R[A] => StatusOf3.AnyOf[S1, S2, S3])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], s3tag: TypeTag[S3]) =
        new StatusOf3.StringentReturn[A, S1, S2, S3](action(bodyParser) { req: R[A] =>
          block(req).result
        })

      //apply(block: => Result): Action[AnyContent]
      def apply[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult](block: => StatusOf3.AnyOf[S1, S2, S3])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], s3tag: TypeTag[S3]) =
        new StatusOf3.StringentReturn[AnyContent, S1, S2, S3](action { block.result })

      //apply(block: R[AnyContent] => Result): Action[AnyContent]
      def anyContent[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult](block: R[AnyContent] => StatusOf3.AnyOf[S1, S2, S3])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], s3tag: TypeTag[S3]) =
        new StatusOf3.StringentReturn[AnyContent, S1, S2, S3](action { req: R[AnyContent] =>
          block(req).result
        })

      //async(block: => Future[Result]): Action[AnyContent]
      def async[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult](block: => Future[StatusOf3.AnyOf[S1, S2, S3]])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], s3tag: TypeTag[S3], executionContext: ExecutionContext) =
        new StatusOf3.StringentReturn[AnyContent, S1, S2, S3](action.async {
          block.map(_.result)
        })

      //async(block: R[AnyContent] => Future[Result]): Action[AnyContent]
      def anyContentAsync[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult](block: R[AnyContent] => Future[StatusOf3.AnyOf[S1, S2, S3]])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], s3tag: TypeTag[S3], executionContext: ExecutionContext) =
        new StatusOf3.StringentReturn[AnyContent, S1, S2, S3](action.async{ req: R[AnyContent] =>
          block(req).map(_.result)
        })

      //async[A](bodyParser: BodyParser[A])(block: R[A] => Future[Result]): Action[A]
      def withContentAsync[A, S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult](bodyParser: BodyParser[A])(block: R[A] => Future[StatusOf3.AnyOf[S1, S2, S3]])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], s3tag: TypeTag[S3], executionContext: ExecutionContext) =
        new StatusOf3.StringentReturn[A, S1, S2, S3](action.async(bodyParser) { req: R[A] =>
          block(req).map(_.result)
        })

      /*
       * Status 4
       */

      //apply[A](bodyParser: BodyParser[A])(block: R[A] => Result): Action[A]
      def withContent[A, S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult](bodyParser: BodyParser[A])(block: R[A] => StatusOf4.AnyOf[S1, S2, S3, S4])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], s3tag: TypeTag[S3], s4tag: TypeTag[S4]) =
        new StatusOf4.StringentReturn[A, S1, S2, S3, S4](action(bodyParser) { req: R[A] =>
          block(req).result
        })

      //apply(block: => Result): Action[AnyContent]
      def apply[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult](block: => StatusOf4.AnyOf[S1, S2, S3, S4])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], s3tag: TypeTag[S3], s4tag: TypeTag[S4]) =
        new StatusOf4.StringentReturn[AnyContent, S1, S2, S3, S4](action { block.result })

      //apply(block: R[AnyContent] => Result): Action[AnyContent]
      def anyContent[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult](block: R[AnyContent] => StatusOf4.AnyOf[S1, S2, S3, S4])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], s3tag: TypeTag[S3], s4tag: TypeTag[S4]) =
        new StatusOf4.StringentReturn[AnyContent, S1, S2, S3, S4](action { req: R[AnyContent] =>
          block(req).result
        })

      //async(block: => Future[Result]): Action[AnyContent]
      def async[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult](block: => Future[StatusOf4.AnyOf[S1, S2, S3, S4]])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], s3tag: TypeTag[S3], s4tag: TypeTag[S4], executionContext: ExecutionContext) =
        new StatusOf4.StringentReturn[AnyContent, S1, S2, S3, S4](action.async {
          block.map(_.result)
        })

      //async(block: R[AnyContent] => Future[Result]): Action[AnyContent]
      def anyContentAsync[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult](block: R[AnyContent] => Future[StatusOf4.AnyOf[S1, S2, S3, S4]])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], s3tag: TypeTag[S3], s4tag: TypeTag[S4], executionContext: ExecutionContext) =
        new StatusOf4.StringentReturn[AnyContent, S1, S2, S3, S4](action.async{ req: R[AnyContent] =>
          block(req).map(_.result)
        })

      //async[A](bodyParser: BodyParser[A])(block: R[A] => Future[Result]): Action[A]
      def withContentAsync[A, S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult](bodyParser: BodyParser[A])(block: R[A] => Future[StatusOf4.AnyOf[S1, S2, S3, S4]])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], s3tag: TypeTag[S3], s4tag: TypeTag[S4], executionContext: ExecutionContext) =
        new StatusOf4.StringentReturn[A, S1, S2, S3, S4](action.async(bodyParser) { req: R[A] =>
          block(req).map(_.result)
        })

      /*
       * Status 5
       */

      //apply[A](bodyParser: BodyParser[A])(block: R[A] => Result): Action[A]
      def withContent[A, S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult, S5 <: StringentResult](bodyParser: BodyParser[A])(block: R[A] => StatusOf5.AnyOf[S1, S2, S3, S4, S5])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], s3tag: TypeTag[S3], s4tag: TypeTag[S4], s5tag: TypeTag[S5]) =
        new StatusOf5.StringentReturn[A, S1, S2, S3, S4, S5](action(bodyParser) { req: R[A] =>
          block(req).result
        })

      //apply(block: => Result): Action[AnyContent]
      def apply[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult, S5 <: StringentResult](block: => StatusOf5.AnyOf[S1, S2, S3, S4, S5])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], s3tag: TypeTag[S3], s4tag: TypeTag[S4], s5tag: TypeTag[S5]) =
        new StatusOf5.StringentReturn[AnyContent, S1, S2, S3, S4, S5](action { block.result })

      //apply(block: R[AnyContent] => Result): Action[AnyContent]
      def anyContent[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult, S5 <: StringentResult](block: R[AnyContent] => StatusOf5.AnyOf[S1, S2, S3, S4, S5])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], s3tag: TypeTag[S3], s4tag: TypeTag[S4], s5tag: TypeTag[S5]) =
        new StatusOf5.StringentReturn[AnyContent, S1, S2, S3, S4, S5](action { req: R[AnyContent] =>
          block(req).result
        })

      //async(block: => Future[Result]): Action[AnyContent]
      def async[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult, S5 <: StringentResult](block: => Future[StatusOf5.AnyOf[S1, S2, S3, S4, S5]])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], s3tag: TypeTag[S3], s4tag: TypeTag[S4], s5tag: TypeTag[S5], executionContext: ExecutionContext) =
        new StatusOf5.StringentReturn[AnyContent, S1, S2, S3, S4, S5](action.async {
          block.map(_.result)
        })

      //async(block: R[AnyContent] => Future[Result]): Action[AnyContent]
      def anyContentAsync[S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult, S5 <: StringentResult](block: R[AnyContent] => Future[StatusOf5.AnyOf[S1, S2, S3, S4, S5]])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], s3tag: TypeTag[S3], s4tag: TypeTag[S4], s5tag: TypeTag[S5], executionContext: ExecutionContext) =
        new StatusOf5.StringentReturn[AnyContent, S1, S2, S3, S4, S5](action.async{ req: R[AnyContent] =>
          block(req).map(_.result)
        })

      //async[A](bodyParser: BodyParser[A])(block: R[A] => Future[Result]): Action[A]
      def withContentAsync[A, S1 <: StringentResult, S2 <: StringentResult, S3 <: StringentResult, S4 <: StringentResult, S5 <: StringentResult](bodyParser: BodyParser[A])(block: R[A] => Future[StatusOf5.AnyOf[S1, S2, S3, S4, S5]])(implicit s1tag: TypeTag[S1], s2tag: TypeTag[S2], s3tag: TypeTag[S3], s4tag: TypeTag[S4], s5tag: TypeTag[S5], executionContext: ExecutionContext) =
        new StatusOf5.StringentReturn[A, S1, S2, S3, S4, S5](action.async(bodyParser) { req: R[A] =>
          block(req).map(_.result)
        })
    }
  }

  implicit def toStringent[R[A]](action: play.api.mvc.ActionBuilder[R]) = {
    BaseTypes.StringentWrapper(BaseTypes.StringentActionImpl(action))
  }
}

