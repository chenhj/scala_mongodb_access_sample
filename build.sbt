name := "scalajack_sample"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.github.nscala-time" %% "nscala-time" % "2.8.0",
  "org.mongodb" %% "casbah" % "3.1.0",
  "org.json4s" % "json4s-mongo_2.11" % "3.3.0"
)