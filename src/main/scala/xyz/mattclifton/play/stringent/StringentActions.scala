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

  //  /** Generates a ‘203 NON_AUTHORITATIVE_INFORMATION’ result. */
  //  val NonAuthoritativeInformation = new Status(NON_AUTHORITATIVE_INFORMATION)

  override val NonAuthoritativeInformation: NonAuthoritativeInformationStatus = new NonAuthoritativeInformationStatus()
  class NonAuthoritativeInformationStatus() extends StringentStatus(play.api.http.Status.NON_AUTHORITATIVE_INFORMATION) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): NonAuthoritativeInformationWithContent[C] =
      new NonAuthoritativeInformationWithContent[C](super.withContent(content))
  }
  class NonAuthoritativeInformationWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘204 NO_CONTENT’ result. */
  //  val NoContent = Result(header = ResponseHeader(NO_CONTENT), body = HttpEntity.NoEntity)

  override val NoContent: NoContentStatus = new NoContentStatus()
  class NoContentStatus() extends StringentResultImpl(ResponseHeader(play.api.http.Status.NO_CONTENT))

  //  /** Generates a ‘205 RESET_CONTENT’ result. */
  //  val ResetContent = Result(header = ResponseHeader(RESET_CONTENT), body = HttpEntity.NoEntity)
  //

  override val ResetContent: ResetContentStatus = new ResetContentStatus()
  class ResetContentStatus() extends StringentResultImpl(ResponseHeader(play.api.http.Status.RESET_CONTENT))

  //  /** Generates a ‘206 PARTIAL_CONTENT’ result. */
  //  val PartialContent = new Status(PARTIAL_CONTENT)
  //
  override val PartialContent: PartialContentStatus = new PartialContentStatus()
  class PartialContentStatus() extends StringentStatus(play.api.http.Status.PARTIAL_CONTENT) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): PartialContentWithContent[C] =
      new PartialContentWithContent[C](super.withContent(content))
  }
  class PartialContentWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘207 MULTI_STATUS’ result. */
  //  val MultiStatus = new Status(MULTI_STATUS)

  override val MultiStatus: MultiStatusStatus = new MultiStatusStatus()
  class MultiStatusStatus() extends StringentStatus(play.api.http.Status.MULTI_STATUS) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): MultiStatusWithContent[C] =
      new MultiStatusWithContent[C](super.withContent(content))
  }
  class MultiStatusWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /**
  //    * Generates a ‘301 MOVED_PERMANENTLY’ simple result.
  //    *
  //    * @param url the URL to redirect to
  //    */
  //  def MovedPermanently(url: String): Result = Redirect(url, MOVED_PERMANENTLY)

  override def MovedPermanently(url: String): MovedPermanentlyStatus = RedirectTo(new MovedPermanentlyStatus(), url, Map.empty)
  class MovedPermanentlyStatus() extends StringentStatus(play.api.http.Status.MOVED_PERMANENTLY)

  //  /**
  //    * Generates a ‘302 FOUND’ simple result.
  //    *
  //    * @param url the URL to redirect to
  //    */
  //  def Found(url: String): Result = Redirect(url, FOUND)

  override def Found(url: String): FoundStatus = RedirectTo(new FoundStatus(), url, Map.empty)
  class FoundStatus() extends StringentStatus(play.api.http.Status.FOUND)

  //  /**
  //    * Generates a ‘303 SEE_OTHER’ simple result.
  //    *
  //    * @param url the URL to redirect to
  //    */
  //  def SeeOther(url: String): Result = Redirect(url, SEE_OTHER)

  override def SeeOther(url: String): SeeOtherStatus = RedirectTo(new SeeOtherStatus(), url, Map.empty)
  class SeeOtherStatus() extends StringentStatus(play.api.http.Status.SEE_OTHER)

  //  /** Generates a ‘304 NOT_MODIFIED’ result. */
  //  val NotModified = Result(header = ResponseHeader(NOT_MODIFIED), body = HttpEntity.NoEntity)

  override val NotModified: NotModifiedStatus = new NotModifiedStatus()
  class NotModifiedStatus() extends StringentResultImpl(ResponseHeader(play.api.http.Status.NOT_MODIFIED))

  //  /**
  //    * Generates a ‘307 TEMPORARY_REDIRECT’ simple result.
  //    *
  //    * @param url the URL to redirect to
  //    */
  //  def TemporaryRedirect(url: String): Result = Redirect(url, TEMPORARY_REDIRECT)

  override def TemporaryRedirect(url: String): TemporaryRedirectStatus = RedirectTo(new TemporaryRedirectStatus(), url, Map.empty)
  class TemporaryRedirectStatus() extends StringentStatus(play.api.http.Status.TEMPORARY_REDIRECT)

  //  /**
  //    * Generates a ‘308 PERMANENT_REDIRECT’ simple result.
  //    *
  //    * @param url the URL to redirect to
  //    */
  //  def PermanentRedirect(url: String): Result = Redirect(url, PERMANENT_REDIRECT)

  override def PermanentRedirect(url: String): PermanentRedirectStatus = RedirectTo(new PermanentRedirectStatus(), url, Map.empty)
  class PermanentRedirectStatus() extends StringentStatus(play.api.http.Status.PERMANENT_REDIRECT)

  private def RedirectTo[A <: StringentStatus](status: A, url: String, queryString: Map[String, Seq[String]]): A = {
    val redirect = super.Redirect(url)
    status.withHeaders(redirect.header.headers.toSeq:_*).asInstanceOf[A]
  }
  def RedirectTo(url: String, queryString: Map[String, Seq[String]] = Map.empty): SeeOtherStatus = RedirectTo(new SeeOtherStatus(), url, queryString)
  override def Redirect(call: Call): SeeOtherStatus = RedirectTo(new SeeOtherStatus(), call.url, Map.empty)

  //
  //  /** Generates a ‘402 PAYMENT_REQUIRED’ result. */
  //  val PaymentRequired = new Status(PAYMENT_REQUIRED)
  //
  
  override val PaymentRequired: PaymentRequiredStatus = new PaymentRequiredStatus()
  class PaymentRequiredStatus() extends StringentStatus(play.api.http.Status.PAYMENT_REQUIRED) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): PaymentRequiredWithContent[C] =
      new PaymentRequiredWithContent[C](super.withContent(content))
  }
  class PaymentRequiredWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘403 FORBIDDEN’ result. */
  //  val Forbidden = new Status(FORBIDDEN)

  override val Forbidden: ForbiddenStatus = new ForbiddenStatus()
  class ForbiddenStatus() extends StringentStatus(play.api.http.Status.FORBIDDEN) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): ForbiddenWithContent[C] =
      new ForbiddenWithContent[C](super.withContent(content))
  }
  class ForbiddenWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘404 NOT_FOUND’ result. */
  //  val NotFound = new Status(NOT_FOUND)

  override val NotFound: NotFoundStatus = new NotFoundStatus()
  class NotFoundStatus() extends StringentStatus(play.api.http.Status.NOT_FOUND) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): NotFoundWithContent[C] =
      new NotFoundWithContent[C](super.withContent(content))
  }
  class NotFoundWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘405 METHOD_NOT_ALLOWED’ result. */
  //  val MethodNotAllowed = new Status(METHOD_NOT_ALLOWED)

  override val MethodNotAllowed: MethodNotAllowedStatus = new MethodNotAllowedStatus()
  class MethodNotAllowedStatus() extends StringentStatus(play.api.http.Status.METHOD_NOT_ALLOWED) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): MethodNotAllowedWithContent[C] =
      new MethodNotAllowedWithContent[C](super.withContent(content))
  }
  class MethodNotAllowedWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘406 NOT_ACCEPTABLE’ result. */
  //  val NotAcceptable = new Status(NOT_ACCEPTABLE)

  override val NotAcceptable: NotAcceptableStatus = new NotAcceptableStatus()
  class NotAcceptableStatus() extends StringentStatus(play.api.http.Status.NOT_ACCEPTABLE) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): NotAcceptableWithContent[C] =
      new NotAcceptableWithContent[C](super.withContent(content))
  }
  class NotAcceptableWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘408 REQUEST_TIMEOUT’ result. */
  //  val RequestTimeout = new Status(REQUEST_TIMEOUT)

  override val RequestTimeout: RequestTimeoutStatus = new RequestTimeoutStatus()
  class RequestTimeoutStatus() extends StringentStatus(play.api.http.Status.REQUEST_TIMEOUT) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): RequestTimeoutWithContent[C] =
      new RequestTimeoutWithContent[C](super.withContent(content))
  }
  class RequestTimeoutWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘409 CONFLICT’ result. */
  //  val Conflict = new Status(CONFLICT)

  override val Conflict: ConflictStatus = new ConflictStatus()
  class ConflictStatus() extends StringentStatus(play.api.http.Status.CONFLICT) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): ConflictWithContent[C] =
      new ConflictWithContent[C](super.withContent(content))
  }
  class ConflictWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘410 GONE’ result. */
  //  val Gone = new Status(GONE)

  override val Gone: GoneStatus = new GoneStatus()
  class GoneStatus() extends StringentStatus(play.api.http.Status.GONE) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): GoneWithContent[C] =
      new GoneWithContent[C](super.withContent(content))
  }
  class GoneWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘412 PRECONDITION_FAILED’ result. */
  //  val PreconditionFailed = new Status(PRECONDITION_FAILED)

  override val PreconditionFailed: PreconditionFailedStatus = new PreconditionFailedStatus()
  class PreconditionFailedStatus() extends StringentStatus(play.api.http.Status.PRECONDITION_FAILED) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): PreconditionFailedWithContent[C] =
      new PreconditionFailedWithContent[C](super.withContent(content))
  }
  class PreconditionFailedWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘413 REQUEST_ENTITY_TOO_LARGE’ result. */
  //  val EntityTooLarge = new Status(REQUEST_ENTITY_TOO_LARGE)

  override val EntityTooLarge: EntityTooLargeStatus = new EntityTooLargeStatus()
  class EntityTooLargeStatus() extends StringentStatus(play.api.http.Status.REQUEST_ENTITY_TOO_LARGE) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): EntityTooLargeWithContent[C] =
      new EntityTooLargeWithContent[C](super.withContent(content))
  }
  class EntityTooLargeWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘414 REQUEST_URI_TOO_LONG’ result. */
  //  val UriTooLong = new Status(REQUEST_URI_TOO_LONG)

  override val UriTooLong: UriTooLongStatus = new UriTooLongStatus()
  class UriTooLongStatus() extends StringentStatus(play.api.http.Status.REQUEST_URI_TOO_LONG) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): UriTooLongWithContent[C] =
      new UriTooLongWithContent[C](super.withContent(content))
  }
  class UriTooLongWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘415 UNSUPPORTED_MEDIA_TYPE’ result. */
  //  val UnsupportedMediaType = new Status(UNSUPPORTED_MEDIA_TYPE)

  override val UnsupportedMediaType: UnsupportedMediaTypeStatus = new UnsupportedMediaTypeStatus()
  class UnsupportedMediaTypeStatus() extends StringentStatus(play.api.http.Status.UNSUPPORTED_MEDIA_TYPE) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): UnsupportedMediaTypeWithContent[C] =
      new UnsupportedMediaTypeWithContent[C](super.withContent(content))
  }
  class UnsupportedMediaTypeWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘417 EXPECTATION_FAILED’ result. */
  //  val ExpectationFailed = new Status(EXPECTATION_FAILED)

  override val ExpectationFailed: ExpectationFailedStatus = new ExpectationFailedStatus()
  class ExpectationFailedStatus() extends StringentStatus(play.api.http.Status.EXPECTATION_FAILED) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): ExpectationFailedWithContent[C] =
      new ExpectationFailedWithContent[C](super.withContent(content))
  }
  class ExpectationFailedWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘422 UNPROCESSABLE_ENTITY’ result. */
  //  val UnprocessableEntity = new Status(UNPROCESSABLE_ENTITY)

  override val UnprocessableEntity: UnprocessableEntityStatus = new UnprocessableEntityStatus()
  class UnprocessableEntityStatus() extends StringentStatus(play.api.http.Status.UNPROCESSABLE_ENTITY) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): UnprocessableEntityWithContent[C] =
      new UnprocessableEntityWithContent[C](super.withContent(content))
  }
  class UnprocessableEntityWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘423 LOCKED’ result. */
  //  val Locked = new Status(LOCKED)

  override val Locked: LockedStatus = new LockedStatus()
  class LockedStatus() extends StringentStatus(play.api.http.Status.LOCKED) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): LockedWithContent[C] =
      new LockedWithContent[C](super.withContent(content))
  }
  class LockedWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘424 FAILED_DEPENDENCY’ result. */
  //  val FailedDependency = new Status(FAILED_DEPENDENCY)

  override val FailedDependency: FailedDependencyStatus = new FailedDependencyStatus()
  class FailedDependencyStatus() extends StringentStatus(play.api.http.Status.FAILED_DEPENDENCY) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): FailedDependencyWithContent[C] =
      new FailedDependencyWithContent[C](super.withContent(content))
  }
  class FailedDependencyWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘429 TOO_MANY_REQUESTS’ result. */
  //  val TooManyRequests = new Status(TOO_MANY_REQUESTS)

  override val TooManyRequests: TooManyRequestsStatus = new TooManyRequestsStatus()
  class TooManyRequestsStatus() extends StringentStatus(play.api.http.Status.TOO_MANY_REQUESTS) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): TooManyRequestsWithContent[C] =
      new TooManyRequestsWithContent[C](super.withContent(content))
  }
  class TooManyRequestsWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘500 INTERNAL_SERVER_ERROR’ result. */
  //  val InternalServerError = new Status(INTERNAL_SERVER_ERROR)

  override val InternalServerError: InternalServerErrorStatus = new InternalServerErrorStatus()
  class InternalServerErrorStatus() extends StringentStatus(play.api.http.Status.INTERNAL_SERVER_ERROR) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): InternalServerErrorWithContent[C] =
      new InternalServerErrorWithContent[C](super.withContent(content))
  }
  class InternalServerErrorWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘501 NOT_IMPLEMENTED’ result. */
  //  val NotImplemented = new Status(NOT_IMPLEMENTED)

  override val NotImplemented: NotImplementedStatus = new NotImplementedStatus()
  class NotImplementedStatus() extends StringentStatus(play.api.http.Status.NOT_IMPLEMENTED) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): NotImplementedWithContent[C] =
      new NotImplementedWithContent[C](super.withContent(content))
  }
  class NotImplementedWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘502 BAD_GATEWAY’ result. */
  //  val BadGateway = new Status(BAD_GATEWAY)

  override val BadGateway: BadGatewayStatus = new BadGatewayStatus()
  class BadGatewayStatus() extends StringentStatus(play.api.http.Status.BAD_GATEWAY) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): BadGatewayWithContent[C] =
      new BadGatewayWithContent[C](super.withContent(content))
  }
  class BadGatewayWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘503 SERVICE_UNAVAILABLE’ result. */
  //  val ServiceUnavailable = new Status(SERVICE_UNAVAILABLE)

  override val ServiceUnavailable: ServiceUnavailableStatus = new  ServiceUnavailableStatus()
  class ServiceUnavailableStatus() extends StringentStatus(play.api.http.Status.SERVICE_UNAVAILABLE) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): ServiceUnavailableWithContent[C] =
      new ServiceUnavailableWithContent[C](super.withContent(content))
  }
  class ServiceUnavailableWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘504 GATEWAY_TIMEOUT’ result. */
  //  val GatewayTimeout = new Status(GATEWAY_TIMEOUT)

  override val GatewayTimeout: GatewayTimeoutStatus = new GatewayTimeoutStatus()
  class GatewayTimeoutStatus() extends StringentStatus(play.api.http.Status.GATEWAY_TIMEOUT) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): GatewayTimeoutWithContent[C] =
      new GatewayTimeoutWithContent[C](super.withContent(content))
  }
  class GatewayTimeoutWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘505 HTTP_VERSION_NOT_SUPPORTED’ result. */
  //  val HttpVersionNotSupported = new Status(HTTP_VERSION_NOT_SUPPORTED)

  override val HttpVersionNotSupported: HttpVersionNotSupportedStatus = new HttpVersionNotSupportedStatus()
  class HttpVersionNotSupportedStatus() extends StringentStatus(play.api.http.Status.HTTP_VERSION_NOT_SUPPORTED) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): HttpVersionNotSupportedWithContent[C] =
      new HttpVersionNotSupportedWithContent[C](super.withContent(content))
  }
  class HttpVersionNotSupportedWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘507 INSUFFICIENT_STORAGE’ result. */
  //  val InsufficientStorage = new Status(INSUFFICIENT_STORAGE)

  override val InsufficientStorage: InsufficientStorageStatus = new InsufficientStorageStatus()
  class InsufficientStorageStatus() extends StringentStatus(play.api.http.Status.INSUFFICIENT_STORAGE) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): InsufficientStorageWithContent[C] =
      new InsufficientStorageWithContent[C](super.withContent(content))
  }
  class InsufficientStorageWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

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
    
    class StringentResultImpl(override val header: ResponseHeader, override val body: HttpEntity = HttpEntity.NoEntity) extends Result(header, body) with StringentResult

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

