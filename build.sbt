name := """play-api"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs
)

libraryDependencies += "com.couchbase.client" % "couchbase-client" % "1.4.13"
libraryDependencies += "com.couchbase.client" % "java-client" % "2.0.0"