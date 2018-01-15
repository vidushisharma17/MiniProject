name := "FileCount"

version := "0.1"

scalaVersion := "2.12.4"
lazy val akkaVersion = "2.5.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.8",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.8",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)