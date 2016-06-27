
name := "chat-demo"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

lazy val akkaVersion = "2.4.7"
lazy val sprayVersion = "1.3.2"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-remote" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "2.2.4" % "src/test",
  "org.scalamock" % "scalamock-scalatest-support_2.11" % "3.2.2"
)
