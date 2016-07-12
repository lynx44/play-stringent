package xyz.mattclifton.play.stringent

import play.api.http.{HttpEntity, Writeable}
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

  override val NonAuthoritativeInformation: NonAuthoritativeInformationResult = new NonAuthoritativeInformationResult()
  class NonAuthoritativeInformationResult() extends StringentStatus(play.api.http.Status.NON_AUTHORITATIVE_INFORMATION) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): NonAuthoritativeInformationWithContent[C] =
      new NonAuthoritativeInformationWithContent[C](super.withContent(content))
  }
  class NonAuthoritativeInformationWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘204 NO_CONTENT’ result. */
  //  val NoContent = Result(header = ResponseHeader(NO_CONTENT), body = HttpEntity.NoEntity)

  override val NoContent: NoContentResult = new NoContentResult()
  class NoContentResult() extends StringentResultImpl(ResponseHeader(play.api.http.Status.NO_CONTENT))

  //  /** Generates a ‘205 RESET_CONTENT’ result. */
  //  val ResetContent = Result(header = ResponseHeader(RESET_CONTENT), body = HttpEntity.NoEntity)
  //

  override val ResetContent: ResetContentResult = new ResetContentResult()
  class ResetContentResult() extends StringentResultImpl(ResponseHeader(play.api.http.Status.RESET_CONTENT))

  //  /** Generates a ‘206 PARTIAL_CONTENT’ result. */
  //  val PartialContent = new Status(PARTIAL_CONTENT)
  //
  override val PartialContent: PartialContentResult = new PartialContentResult()
  class PartialContentResult() extends StringentStatus(play.api.http.Status.PARTIAL_CONTENT) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): PartialContentWithContent[C] =
      new PartialContentWithContent[C](super.withContent(content))
  }
  class PartialContentWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘207 MULTI_STATUS’ result. */
  //  val MultiStatus = new Status(MULTI_STATUS)

  override val MultiStatus: MultiStatusResult = new MultiStatusResult()
  class MultiStatusResult() extends StringentStatus(play.api.http.Status.MULTI_STATUS) {
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

  override def MovedPermanently(url: String): MovedPermanentlyResult = RedirectTo(new MovedPermanentlyResult(), url, Map.empty)
  class MovedPermanentlyResult() extends StringentStatus(play.api.http.Status.MOVED_PERMANENTLY)

  //  /**
  //    * Generates a ‘302 FOUND’ simple result.
  //    *
  //    * @param url the URL to redirect to
  //    */
  //  def Found(url: String): Result = Redirect(url, FOUND)

  override def Found(url: String): FoundResult = RedirectTo(new FoundResult(), url, Map.empty)
  class FoundResult() extends StringentStatus(play.api.http.Status.FOUND)

  //  /**
  //    * Generates a ‘303 SEE_OTHER’ simple result.
  //    *
  //    * @param url the URL to redirect to
  //    */
  //  def SeeOther(url: String): Result = Redirect(url, SEE_OTHER)

  override def SeeOther(url: String): SeeOtherResult = RedirectTo(new SeeOtherResult(), url, Map.empty)
  class SeeOtherResult() extends StringentStatus(play.api.http.Status.SEE_OTHER)

  //  /** Generates a ‘304 NOT_MODIFIED’ result. */
  //  val NotModified = Result(header = ResponseHeader(NOT_MODIFIED), body = HttpEntity.NoEntity)

  override val NotModified: NotModifiedResult = new NotModifiedResult()
  class NotModifiedResult() extends StringentResultImpl(ResponseHeader(play.api.http.Status.NOT_MODIFIED))

  //  /**
  //    * Generates a ‘307 TEMPORARY_REDIRECT’ simple result.
  //    *
  //    * @param url the URL to redirect to
  //    */
  //  def TemporaryRedirect(url: String): Result = Redirect(url, TEMPORARY_REDIRECT)

  override def TemporaryRedirect(url: String): TemporaryRedirectResult = RedirectTo(new TemporaryRedirectResult(), url, Map.empty)
  class TemporaryRedirectResult() extends StringentStatus(play.api.http.Status.TEMPORARY_REDIRECT)

  //  /**
  //    * Generates a ‘308 PERMANENT_REDIRECT’ simple result.
  //    *
  //    * @param url the URL to redirect to
  //    */
  //  def PermanentRedirect(url: String): Result = Redirect(url, PERMANENT_REDIRECT)

  override def PermanentRedirect(url: String): PermanentRedirectResult = RedirectTo(new PermanentRedirectResult(), url, Map.empty)
  class PermanentRedirectResult() extends StringentStatus(play.api.http.Status.PERMANENT_REDIRECT)

  private def RedirectTo[A <: StringentStatus](status: A, url: String, queryString: Map[String, Seq[String]]): A = {
    val redirect = super.Redirect(url)
    status.withHeaders(redirect.header.headers.toSeq:_*).asInstanceOf[A]
  }
  def RedirectTo(url: String, queryString: Map[String, Seq[String]] = Map.empty): SeeOtherResult = RedirectTo(new SeeOtherResult(), url, queryString)
  override def Redirect(call: Call): SeeOtherResult = RedirectTo(new SeeOtherResult(), call.url, Map.empty)

  //
  //  /** Generates a ‘402 PAYMENT_REQUIRED’ result. */
  //  val PaymentRequired = new Status(PAYMENT_REQUIRED)
  //
  
  override val PaymentRequired: PaymentRequiredResult = new PaymentRequiredResult()
  class PaymentRequiredResult() extends StringentStatus(play.api.http.Status.PAYMENT_REQUIRED) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): PaymentRequiredWithContent[C] =
      new PaymentRequiredWithContent[C](super.withContent(content))
  }
  class PaymentRequiredWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘403 FORBIDDEN’ result. */
  //  val Forbidden = new Status(FORBIDDEN)

  override val Forbidden: ForbiddenResult = new ForbiddenResult()
  class ForbiddenResult() extends StringentStatus(play.api.http.Status.FORBIDDEN) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): ForbiddenWithContent[C] =
      new ForbiddenWithContent[C](super.withContent(content))
  }
  class ForbiddenWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘404 NOT_FOUND’ result. */
  //  val NotFound = new Status(NOT_FOUND)

  override val NotFound: NotFoundResult = new NotFoundResult()
  class NotFoundResult() extends StringentStatus(play.api.http.Status.NOT_FOUND) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): NotFoundWithContent[C] =
      new NotFoundWithContent[C](super.withContent(content))
  }
  class NotFoundWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘405 METHOD_NOT_ALLOWED’ result. */
  //  val MethodNotAllowed = new Status(METHOD_NOT_ALLOWED)

  override val MethodNotAllowed: MethodNotAllowedResult = new MethodNotAllowedResult()
  class MethodNotAllowedResult() extends StringentStatus(play.api.http.Status.METHOD_NOT_ALLOWED) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): MethodNotAllowedWithContent[C] =
      new MethodNotAllowedWithContent[C](super.withContent(content))
  }
  class MethodNotAllowedWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘406 NOT_ACCEPTABLE’ result. */
  //  val NotAcceptable = new Status(NOT_ACCEPTABLE)

  override val NotAcceptable: NotAcceptableResult = new NotAcceptableResult()
  class NotAcceptableResult() extends StringentStatus(play.api.http.Status.NOT_ACCEPTABLE) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): NotAcceptableWithContent[C] =
      new NotAcceptableWithContent[C](super.withContent(content))
  }
  class NotAcceptableWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘408 REQUEST_TIMEOUT’ result. */
  //  val RequestTimeout = new Status(REQUEST_TIMEOUT)

  override val RequestTimeout: RequestTimeoutResult = new RequestTimeoutResult()
  class RequestTimeoutResult() extends StringentStatus(play.api.http.Status.REQUEST_TIMEOUT) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): RequestTimeoutWithContent[C] =
      new RequestTimeoutWithContent[C](super.withContent(content))
  }
  class RequestTimeoutWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘409 CONFLICT’ result. */
  //  val Conflict = new Status(CONFLICT)

  override val Conflict: ConflictResult = new ConflictResult()
  class ConflictResult() extends StringentStatus(play.api.http.Status.CONFLICT) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): ConflictWithContent[C] =
      new ConflictWithContent[C](super.withContent(content))
  }
  class ConflictWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘410 GONE’ result. */
  //  val Gone = new Status(GONE)

  override val Gone: GoneResult = new GoneResult()
  class GoneResult() extends StringentStatus(play.api.http.Status.GONE) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): GoneWithContent[C] =
      new GoneWithContent[C](super.withContent(content))
  }
  class GoneWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘412 PRECONDITION_FAILED’ result. */
  //  val PreconditionFailed = new Status(PRECONDITION_FAILED)

  override val PreconditionFailed: PreconditionFailedResult = new PreconditionFailedResult()
  class PreconditionFailedResult() extends StringentStatus(play.api.http.Status.PRECONDITION_FAILED) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): PreconditionFailedWithContent[C] =
      new PreconditionFailedWithContent[C](super.withContent(content))
  }
  class PreconditionFailedWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘413 REQUEST_ENTITY_TOO_LARGE’ result. */
  //  val EntityTooLarge = new Status(REQUEST_ENTITY_TOO_LARGE)

  override val EntityTooLarge: EntityTooLargeResult = new EntityTooLargeResult()
  class EntityTooLargeResult() extends StringentStatus(play.api.http.Status.REQUEST_ENTITY_TOO_LARGE) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): EntityTooLargeWithContent[C] =
      new EntityTooLargeWithContent[C](super.withContent(content))
  }
  class EntityTooLargeWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘414 REQUEST_URI_TOO_LONG’ result. */
  //  val UriTooLong = new Status(REQUEST_URI_TOO_LONG)

  override val UriTooLong: UriTooLongResult = new UriTooLongResult()
  class UriTooLongResult() extends StringentStatus(play.api.http.Status.REQUEST_URI_TOO_LONG) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): UriTooLongWithContent[C] =
      new UriTooLongWithContent[C](super.withContent(content))
  }
  class UriTooLongWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘415 UNSUPPORTED_MEDIA_TYPE’ result. */
  //  val UnsupportedMediaType = new Status(UNSUPPORTED_MEDIA_TYPE)

  override val UnsupportedMediaType: UnsupportedMediaTypeResult = new UnsupportedMediaTypeResult()
  class UnsupportedMediaTypeResult() extends StringentStatus(play.api.http.Status.UNSUPPORTED_MEDIA_TYPE) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): UnsupportedMediaTypeWithContent[C] =
      new UnsupportedMediaTypeWithContent[C](super.withContent(content))
  }
  class UnsupportedMediaTypeWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘417 EXPECTATION_FAILED’ result. */
  //  val ExpectationFailed = new Status(EXPECTATION_FAILED)

  override val ExpectationFailed: ExpectationFailedResult = new ExpectationFailedResult()
  class ExpectationFailedResult() extends StringentStatus(play.api.http.Status.EXPECTATION_FAILED) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): ExpectationFailedWithContent[C] =
      new ExpectationFailedWithContent[C](super.withContent(content))
  }
  class ExpectationFailedWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘422 UNPROCESSABLE_ENTITY’ result. */
  //  val UnprocessableEntity = new Status(UNPROCESSABLE_ENTITY)

  override val UnprocessableEntity: UnprocessableEntityResult = new UnprocessableEntityResult()
  class UnprocessableEntityResult() extends StringentStatus(play.api.http.Status.UNPROCESSABLE_ENTITY) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): UnprocessableEntityWithContent[C] =
      new UnprocessableEntityWithContent[C](super.withContent(content))
  }
  class UnprocessableEntityWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘423 LOCKED’ result. */
  //  val Locked = new Status(LOCKED)

  override val Locked: LockedResult = new LockedResult()
  class LockedResult() extends StringentStatus(play.api.http.Status.LOCKED) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): LockedWithContent[C] =
      new LockedWithContent[C](super.withContent(content))
  }
  class LockedWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘424 FAILED_DEPENDENCY’ result. */
  //  val FailedDependency = new Status(FAILED_DEPENDENCY)

  override val FailedDependency: FailedDependencyResult = new FailedDependencyResult()
  class FailedDependencyResult() extends StringentStatus(play.api.http.Status.FAILED_DEPENDENCY) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): FailedDependencyWithContent[C] =
      new FailedDependencyWithContent[C](super.withContent(content))
  }
  class FailedDependencyWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘429 TOO_MANY_REQUESTS’ result. */
  //  val TooManyRequests = new Status(TOO_MANY_REQUESTS)

  override val TooManyRequests: TooManyRequestsResult = new TooManyRequestsResult()
  class TooManyRequestsResult() extends StringentStatus(play.api.http.Status.TOO_MANY_REQUESTS) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): TooManyRequestsWithContent[C] =
      new TooManyRequestsWithContent[C](super.withContent(content))
  }
  class TooManyRequestsWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘500 INTERNAL_SERVER_ERROR’ result. */
  //  val InternalServerError = new Status(INTERNAL_SERVER_ERROR)

  override val InternalServerError: InternalServerErrorResult = new InternalServerErrorResult()
  class InternalServerErrorResult() extends StringentStatus(play.api.http.Status.INTERNAL_SERVER_ERROR) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): InternalServerErrorWithContent[C] =
      new InternalServerErrorWithContent[C](super.withContent(content))
  }
  class InternalServerErrorWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘501 NOT_IMPLEMENTED’ result. */
  //  val NotImplemented = new Status(NOT_IMPLEMENTED)

  override val NotImplemented: NotImplementedResult = new NotImplementedResult()
  class NotImplementedResult() extends StringentStatus(play.api.http.Status.NOT_IMPLEMENTED) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): NotImplementedWithContent[C] =
      new NotImplementedWithContent[C](super.withContent(content))
  }
  class NotImplementedWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘502 BAD_GATEWAY’ result. */
  //  val BadGateway = new Status(BAD_GATEWAY)

  override val BadGateway: BadGatewayResult = new BadGatewayResult()
  class BadGatewayResult() extends StringentStatus(play.api.http.Status.BAD_GATEWAY) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): BadGatewayWithContent[C] =
      new BadGatewayWithContent[C](super.withContent(content))
  }
  class BadGatewayWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘503 SERVICE_UNAVAILABLE’ result. */
  //  val ServiceUnavailable = new Status(SERVICE_UNAVAILABLE)

  override val ServiceUnavailable: ServiceUnavailableResult = new  ServiceUnavailableResult()
  class ServiceUnavailableResult() extends StringentStatus(play.api.http.Status.SERVICE_UNAVAILABLE) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): ServiceUnavailableWithContent[C] =
      new ServiceUnavailableWithContent[C](super.withContent(content))
  }
  class ServiceUnavailableWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘504 GATEWAY_TIMEOUT’ result. */
  //  val GatewayTimeout = new Status(GATEWAY_TIMEOUT)

  override val GatewayTimeout: GatewayTimeoutResult = new GatewayTimeoutResult()
  class GatewayTimeoutResult() extends StringentStatus(play.api.http.Status.GATEWAY_TIMEOUT) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): GatewayTimeoutWithContent[C] =
      new GatewayTimeoutWithContent[C](super.withContent(content))
  }
  class GatewayTimeoutWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘505 HTTP_VERSION_NOT_SUPPORTED’ result. */
  //  val HttpVersionNotSupported = new Status(HTTP_VERSION_NOT_SUPPORTED)

  override val HttpVersionNotSupported: HttpVersionNotSupportedResult = new HttpVersionNotSupportedResult()
  class HttpVersionNotSupportedResult() extends StringentStatus(play.api.http.Status.HTTP_VERSION_NOT_SUPPORTED) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): HttpVersionNotSupportedWithContent[C] =
      new HttpVersionNotSupportedWithContent[C](super.withContent(content))
  }
  class HttpVersionNotSupportedWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  //  /** Generates a ‘507 INSUFFICIENT_STORAGE’ result. */
  //  val InsufficientStorage = new Status(INSUFFICIENT_STORAGE)

  override val InsufficientStorage: InsufficientStorageResult = new InsufficientStorageResult()
  class InsufficientStorageResult() extends StringentStatus(play.api.http.Status.INSUFFICIENT_STORAGE) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): InsufficientStorageWithContent[C] =
      new InsufficientStorageWithContent[C](super.withContent(content))
  }
  class InsufficientStorageWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  override val Ok: OkResult = new OkResult()
  class OkResult() extends StringentStatus(play.api.http.Status.OK) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): OkWithContent[C] =
        new OkWithContent[C](super.withContent(content))
  }
  class OkWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  override val Created: CreatedResult = new CreatedResult()
  class CreatedResult() extends StringentStatus(play.api.http.Status.CREATED) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): CreatedWithContent[C] =
        new CreatedWithContent[C](super.withContent(content))
  }
  class CreatedWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  override val Accepted: AcceptedResult = new AcceptedResult()
  class AcceptedResult() extends StringentStatus(play.api.http.Status.ACCEPTED) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): AcceptedWithContent[C] =
        new AcceptedWithContent[C](super.withContent(content))
  }
  class AcceptedWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  override val Unauthorized: UnauthorizedResult = new UnauthorizedResult()
  class UnauthorizedResult() extends StringentStatus(play.api.http.Status.UNAUTHORIZED) {
    override def withContent[C](content: C)(implicit writeable: Writeable[C]): UnauthorizedWithContent[C] =
      new UnauthorizedWithContent[C](super.withContent(content))
  }
  class UnauthorizedWithContent[C](content: StringentContentStatus[C]) extends StringentContentStatus[C](content.status, content.body)

  override val BadRequest: BadRequestResult = new BadRequestResult()
  class BadRequestResult() extends StringentStatus(play.api.http.Status.BAD_REQUEST) {
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

