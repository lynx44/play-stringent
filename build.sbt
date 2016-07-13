name := "play-stringent"

organization := "xyz.mattclifton"

version := "2.5.3-SNAPSHOT"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.5.3",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value
)

licenses := Seq("MIT" -> url("https://opensource.org/licenses/MIT"))

homepage := Some(url("https://github.com/lynx44/play-stringent"))

pomExtra := (<scm>
  <url>git@github.com:lynx44/play-stringent.git</url>
  <connection>scm:git:git@github.com:lynx44/play-stringent.git</connection>
</scm>
  <developers>
    <developer>
      <id>lynx44</id>
      <name>Matt Clifton</name>
      <url>https://github.com/lynx44</url>
    </developer>
  </developers>)

credentials ~= { c =>
  (Option(System.getenv().get("SONATYPE_USERNAME")), Option(System.getenv().get("SONATYPE_PASSWORD"))) match {
    case (Some(username), Some(password)) =>
      c :+ Credentials(
        "Sonatype Nexus Repository Manager",
        "oss.sonatype.org",
        username,
        password)
    case _ => c
  }
}

publishTo <<= version { v => //add credentials to ~/.sbt/sonatype.sbt
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }