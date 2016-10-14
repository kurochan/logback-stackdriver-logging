import sbt.Keys._
import sbt._

object Build extends Build {

  lazy val logbackStackDriverLogging = Project(
    id = "logback-stackdriver-logging",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "logback-stackdriver-logging",
      organization := "org.kurochan",
      version := "0.1",
      scalaVersion := "2.11.8",
      libraryDependencies ++= Seq(
        "ch.qos.logback" % "logback-classic" % "1.1.4"
      ),
     publishTo := Some(Resolver.file("logback-game-image-generator",file("publish"))(Patterns(true, Resolver.mavenStyleBasePattern)))
    )
  )
}
