name := "Aurum"

version := "1.0-SNAPSHOT"

organization := "org.aurum"

scalaVersion := "2.9.1"

libraryDependencies ++= Seq(
  "net.databinder" %% "dispatch-http" % "0.8.7",
  "net.liftweb" %% "lift-json" % "2.4",
  "org.scalatest" %% "scalatest" % "1.7.1" % "test",
  "net.databinder" %% "dispatch-lift-json" % "0.8.5",
  "junit" % "junit" % "4.8.1" % "test"
)
