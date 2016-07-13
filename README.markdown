## Purpose

The goal of play-stringent is to bring stricter typing and enhanced clarity to Play Framework applications written in Scala.

Specifically, play-stringent gives you compile time result checks for actions:

    def myAction = Action.stringent[OkResult, BadRequest] = {
        if(requestIsValid()) {
            Ok
        } else {
            BadRequest
        }
    }

If you tried to return a response you didn't specify, play-stringent will return a compile time error:

    def myAction = Action.stringent[OkResult, BadRequest] = {
        if(requestIsValid()) {
            Ok
        }
        if(userIsAuthorized()) {
            Unauthorized // compile time error
        } else {
            BadRequest
        }
    }

This gives you self describing actions and compile time safety, so you know the exact response types each action can return.

## sbt

    resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

    "xyz.mattclifton" %% "play-stringent" % "2.5.3-SNAPSHOT"

## Getting Started

Simply mix in the *StringentActions* trait into your controller:

    class Application extends Controller with StringentActions

## Examples and supported features

Many actions are supported, though some have slightly different semantics:

### Example of primary features

    def asyncBodyContent = Action.stringent.withContentAsync[TestContent, OkWithContent[TestResponse], BadRequestResult](parse.json[TestContent]){ request =>
        if(!requestIsValid(request)) {
            Future(BadRequest)
        } else {
            Future(Ok.withContent(TestResponse(1, "test")))
        }
    }

Note that the method name to return content is *withContent* in play-stringent, rather than *apply*. (also make sure you define a [Writeable](#writeable))

### Example: Basic Block

#### Standard Play Framework

    def block = Action {
        Ok
    }

#### Play Stringent

    def block = Action.stringent[OkResult]{
        Ok
    }

### Example: Basic Block with Result Content

#### Standard Play Framework

    def block = Action {
        Ok(Json.toJson(TestResponse(1, "test")))
    }

#### Play Stringent

    def block = Action.stringent[OkWithContent[TestResponse]{
        Ok.withContent(TestResponse(1, "test"))
    }

Make sure you define a [Writeable](#writeable)

### Example: Block with request

#### Standard Play Framework

    def anyContent = Action { request =>
        Ok
    }

#### Play Stringent

    def anyContent = Action.stringent.anyContent[OkResult]{ request =>
        Ok
    }

### Example: Block with body parser

#### Standard Play Framework

    def withBodyParser = Action(parse.json[TestContent]){ request =>
        Ok
    }

#### Play Stringent

    def withBodyParser = Action.stringent.withContent[TestContent, OkResult](parse.json[TestContent]){ request =>
        Ok
    }

### Example: Async block

#### Standard Play Framework

    def asyncBlock = Action.async {
        Future(Ok)
    }

#### Play Stringent

    def asyncBlock = Action.stringent.async[OkResult]{
        Future(Ok)
    }

### Example: Async with request

#### Standard Play Framework

    def asyncAnyContent = Action.async{ request =>
        Future(Ok)
    }

#### Play Stringent

    def asyncAnyContent = Action.stringent.anyContentAsync[OkResult]{ request =>
        Future(Ok)
    }

### Example: Async with body parser

#### Standard Play Framework

    def asyncBodyContent = Action.async(parse.json[TestContent]){ request =>
        Future(Ok)
    }

#### Play Stringent

    def asyncBodyContent = Action.stringent.withContentAsync[TestContent, OkResult](parse.json[TestContent]){ request =>
        Future(Ok)
    }

## Matching Results with Result types

Result types are simply the name of the Status/Result code with a _Result_ suffix. For example:

    // Ok + Result = OkResult
    def myAction = Action.stringent[OkResult] = {
        Ok
    }

If the result is a Status, then it can return a body. To ensure you are returning a result with the specified body, use the status name with a _WithContent_ suffix. For Example:

    // Ok + WithContent + [Content Entity] = OkWithContent[TestResponse]
    def blockWithContent = Action.stringent[OkWithContent[TestResponse]]{
        Ok.withContent(TestResponse(1, "test"))
    }

Make sure you define a [Writeable](#writeable) to use *WithContent* statuses.

## Notes

Since there are many types of redirects, *SeeOther* was chosen as the default (as Play Framework uses this as it's default redirect).

*RedirectTo* is the play-stringent equivalent of *Redirect*, due to typing collisions:

    def redirect = Action.stringent[SeeOtherResult]{
        RedirectTo("https://mattclifton.xyz")
    }

<a name="writeable"/>
## Writeable[T]

Note that to use the *withContent* method, you must define a *Writeable[T]* rather than using something like *Json.toJson*. A simple generic JSON solution would look like this:

    implicit def jsonWriteable[A](implicit jsonWriter: Writes[A]): Writeable[A] = new Writeable[A](b => ByteString(Json.toJson(b).toString()), Some("application/json"))

## Limitations

While actions cannot return unspecified result types, they can return less than what is specified in the return type list.

In this example, no error will be returned at compile time due to a lack of a *BadRequest* return path:

    def myAction = Action.stringent[OkResult, BadRequest] = {
        Ok
    }

## Method Comparison Lookup

<table>
    <tr>
        <th>Standard Play Framework</th>
        <th>Stringent Equivalent</th>
    </tr>
    <tr>
        <td>    apply[A](bodyParser: BodyParser[A])(block: R[A] => Result): Action[A]</td>
        <td>    withContent[A, S1 <: StringentResult](bodyParser: BodyParser[A])(block: R[A] => StatusOf1.AnyOf[S1])</td>
    </tr>
    <tr>
        <td>    apply(block: => Result): Action[AnyContent]</td>
        <td>    apply[S1 <: StringentResult](block: => StatusOf1.AnyOf[S1])</td>
    </tr>
    <tr>
        <td>    apply(block: => Result): Action[AnyContent]</td>
        <td>    apply[S1 <: StringentResult](block: => StatusOf1.AnyOf[S1])</td>
    </tr>
    <tr>
        <td>    apply(block: R[AnyContent] => Result): Action[AnyContent]</td>
        <td>    anyContent[S1 <: StringentResult](block: R[AnyContent] => StatusOf1.AnyOf[S1])</td>
    </tr>
    <tr>
        <td>    async(block: => Future[Result]): Action[AnyContent]</td>
        <td>    async[S1 <: StringentResult](block: => Future[StatusOf1.AnyOf[S1]])</td>
    </tr>
    <tr>
        <td>    async(block: R[AnyContent] => Future[Result]): Action[AnyContent]</td>
        <td>    anyContentAsync[S1 <: StringentResult](block: R[AnyContent] => Future[StatusOf1.AnyOf[S1]])</td>
    </tr>
    <tr>
        <td>    async[A](bodyParser: BodyParser[A])(block: R[A] => Future[Result]): Action[A]</td>
        <td>    withContentAsync[A, S1 <: StringentResult](bodyParser: BodyParser[A])(block: R[A] => Future[StatusOf1.AnyOf[S1]])</td>
    </tr>
</table>

## License

[MIT](https://github.com/lynx44/play-stringent/blob/master/LICENSE)