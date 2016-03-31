name := """LibrarayApi"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
filters,
  javaJdbc,
  cache,
  javaWs,
  "mysql" % "mysql-connector-java" % "5.1.18",
  "com.monitorjbl" % "json-view" % "0.8"
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
