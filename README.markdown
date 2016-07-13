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
            *Unauthorized* // compile time error
        } else {
            BadRequest
        }
    }

This gives you self describing actions and compile time safety, so you know the exact response types each action can return.

## sbt

    resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

    "xyz.mattclifton.com" %% "play-stringent" % "2.5.3-SNAPSHOT"

## What's supported

Many actions are supported, though some have slightly different semantics:

### Example of primary features

    def asyncBodyContent = Action.stringent.withContentAsync[TestContent, OkWithContent[TestResponse], BadRequestResult](parse.json[TestContent]){ request =>
        if(!requestIsValid(request)) {
            Future(BadRequest)
        } else {
            Future(Ok.withContent(TestResponse(1, "test")))
        }
    }

### Example 1: Basic Block

#### Standard Play Framework

    def block = Action {
        Ok
    }

#### Play Stringent

    def block = Action.stringent[OkResult]{
        Ok
    }

### Example 2: Block with request

#### Standard Play Framework

    def anyContent = Action { request =>
        Ok
    }

#### Play Stringent

    def anyContent = Action.stringent.*anyContent*[OkResult]{ request =>
        Ok
    }

### Example 3: Block with body parser

#### Standard Play Framework

    def withBodyParser = Action(parse.json[TestContent]){ request =>
        Ok
    }

#### Play Stringent

    def withBodyParser = Action.stringent.withContent[TestContent, OkResult](parse.json[TestContent]){ request =>
        Ok
    }

### Example 4: Async block

#### Standard Play Framework

    def asyncBlock = Action.async {
        Future(Ok)
    }

#### Play Stringent

    def asyncBlock = Action.stringent.async[OkResult]{
        Future(Ok)
    }

### Example 5: Async with request

#### Standard Play Framework

    def asyncAnyContent = Action.async{ request =>
        Future(Ok)
    }

#### Play Stringent

    def asyncAnyContent = Action.stringent.anyContentAsync[OkResult]{ request =>
        Future(Ok)
    }

### Example 6: Async with body parser

#### Standard Play Framework

    def asyncBodyContent = Action.async(parse.json[TestContent]){ request =>
        Future(Ok)
    }

#### Play Stringent

    def asyncBodyContent = Action.stringent.withContentAsync[TestContent, OkResult](parse.json[TestContent]){ request =>
        Future(Ok)
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

## Notes

Since there are many types of redirects, SeeOther was chosen as the default (as Play Framework uses this as it's default redirect).

*RedirectTo* is the play-stringent equivalent of Redirect, due to typing collisions:

    def redirect = Action.stringent[SeeOtherResult]{
        RedirectTo("https://mattclifton.xyz")
    }

## Limitations

While actions cannot return unspecified result types, they can return less than what is specified in the return type list.

In this example, no error will be returned at compile time due to a lack of a _BadRequest_ return path:

    def myAction = Action.stringent[OkResult, BadRequest] = {
        Ok
    }

## License

MIT