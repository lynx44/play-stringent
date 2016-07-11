name := "play-stringent"

organization := "xyz.mattclifton"

version := "2.5.3"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.5.3",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value
)