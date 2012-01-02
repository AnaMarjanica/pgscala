import sbt._
import Keys._

object BuildSettings {

  val commonSettings =
    Default.settings ++ Seq(
      organization := "hr.element.pgscala"
    )

//  ---------------------------------------------------------------------------

  val bsPGScalaUtil = commonSettings ++ Seq(
    name         := "PGScala-Util",
    version      := "0.2.1",

    javacOptions := Seq("-deprecation", "-encoding", "UTF-8", "-source", "1.5", "-target", "1.5"), // , "-g:none"),
    compileOrder := CompileOrder.JavaThenScala,
    autoScalaLibrary := false,
    crossPaths := false
  )

  val bsPGScalaConverters = commonSettings ++ Seq(
    name         := "PGScala-Converters",
    version      := "0.0.4"
  )

  val bsPGScala = commonSettings ++ Seq(
    name         := "PGScala",
    version      := "0.6.1-SNAPSHOT"
  )
}

//  ---------------------------------------------------------------------------

object Dependencies {
  import Implicits._

  val jodaTime = Seq(
    "org.joda" % "joda-convert" % "1.1",
    "joda-time" % "joda-time" % "2.0"
  )

  val iorc = "hr.element.etb" %% "iorc" % "0.0.21"

  val postgres = "postgresql" % "postgresql" % "9.1-901.jdbc4"

  val configrity = (scalaVersion: String) => {
    val sV = scalaVersion match {
      case "2.9.0" => "2.9.0-1"
      case x => x
    }

    "org.streum" % ("configrity_" + sV) % "0.9.0" % "test"
  }

  val scalaTest = "org.scalatest" %% "scalatest" % "1.6.1" % "test"

//  ---------------------------------------------------------------------------

  val pgscalaUtil = "hr.element.pgscala" % "pgscala-util" % "0.2.1"

  val depsPGScalaUtil = libDeps(
    //test
    postgres % "test",
    configrity,
    scalaTest
  )

  val depsPGScalaConverters = libDeps(
    pgscalaUtil,
    jodaTime,

    //test
    postgres % "test",
    configrity,
    scalaTest
  )

  val depsPGScala = libDeps(
    pgscalaUtil,
    jodaTime,
    iorc,
    postgres,

    //test
    configrity,
    scalaTest
  )
}

//  ---------------------------------------------------------------------------

object PGScalaBuild extends Build {
  import BuildSettings._
  import Dependencies._

  lazy val pgscalaUtil = Project(
    "PGScala-Util",
    file("pgscala-util"),
    settings = bsPGScalaUtil :+ depsPGScalaUtil
  )

  lazy val pgscalaConverters = Project(
    "PGScala-Converters",
    file("pgscala-converters"),
    settings = bsPGScalaConverters :+ depsPGScalaConverters
  ) dependsOn(pgscalaUtil)

  lazy val pgscala = Project(
    "PGScala",
    file("pgscala"),
    settings = bsPGScala :+ depsPGScala
  )
}
