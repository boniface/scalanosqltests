import com.typesafe.sbt.packager.docker._
import sbt.Resolver

name := """scalanosqltests"""
organization := "za.ac.cput"

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala, DebianPlugin, JavaAppPackaging, SystemdPlugin)

scalaVersion := "2.12.6"


maintainer := "Waldon Hendricks "
packageSummary in Linux := "CPUT "
packageDescription := "RESEARCH API "

val PlayFrameWorkVersion = "2.6.17"


maintainer := "Waldon Hendricks "
packageSummary in Linux := "CPUT"
packageDescription := "RESEARCH API "

dockerCommands := Seq(
  Cmd("FROM", "anapsix/alpine-java:jdk8"),
  Cmd("MAINTAINER", maintainer.value),
  Cmd("WORKDIR", "/opt/docker"),
  Cmd("ADD", "/opt /opt"),
  ExecCmd("RUN", "chown", "-R", "daemon:daemon", "."),
  Cmd("USER", "daemon"),
  Cmd("WORKDIR", "/opt/docker"),
  ExecCmd("ENTRYPOINT", "bin/scalanosqltests"),
  Cmd("VOLUME", "/opt/docker"),
  Cmd("EXPOSE", "9000"),
  Cmd("EXPOSE", "9999"),
  Cmd("EXPOSE", "8888")
)

javaOptions in Universal ++= Seq(
  // JVM memory tuning
  "-J-Xms4g",
  "-J-Xmx4g",
  "-J-Xmn2g"
)



libraryDependencies += guice
libraryDependencies += filters
libraryDependencies += "org.scalatestplus.play" % "scalatestplus-play_2.12" % "3.1.2" % "test"
libraryDependencies += "org.scalactic" % "scalactic_2.12" % "3.0.5"
// https://mvnrepository.com/artifact/com.google.code.gson/gson
libraryDependencies += "com.google.code.gson" % "gson" % "2.8.5"




libraryDependencies += "com.typesafe.play" % "play-iteratees_2.12" % "2.6.1"
libraryDependencies += "com.typesafe.play" % "play-iteratees-reactive-streams_2.12" % "2.6.1"


libraryDependencies += "com.typesafe.play" % "play-json_2.12" % "2.6.9"
libraryDependencies += "com.typesafe.play" % "play-akka-http-server_2.12" % PlayFrameWorkVersion
libraryDependencies += "com.typesafe.play" % "play-guice_2.12" % PlayFrameWorkVersion
libraryDependencies += "com.typesafe.play" % "play-ws_2.12" % PlayFrameWorkVersion

// MongoDB Drivers For Documents

libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.4.0"

// DGraph For Graph

// https://mvnrepository.com/artifact/io.dgraph/dgraph4j
libraryDependencies += "io.dgraph" % "dgraph4j" % "1.3.0"

// Redis For Key Pair

// https://mvnrepository.com/artifact/net.debasishg/redisclient
libraryDependencies += "net.debasishg" %% "redisclient" % "3.7"

// Cassandra for Column Databases

// https://mvnrepository.com/artifact/com.datastax.cassandra/cassandra-driver-core
libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-core" % "3.5.1"


resolvers ++= Seq(
  "Typesafe repository snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
  "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/",
  "softprops-maven" at "http://dl.bintray.com/content/softprops/maven",
  "Brando Repository" at "http://chrisdinn.github.io/releases/",
  "Sonatype repo" at "https://oss.sonatype.org/content/groups/scala-tools/",
  "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases",
  "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype staging" at "http://oss.sonatype.org/content/repositories/staging",
  "Java.net Maven2 Repository" at "http://download.java.net/maven/2/",
  "Twitter Repository" at "http://maven.twttr.com",
  "Websudos releases" at "https://dl.bintray.com/websudos/oss-releases/",
  "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/",
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sonatypeRepo("public")
)






