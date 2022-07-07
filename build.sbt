ThisBuild / version       := "0.1.0-SNAPSHOT"
ThisBuild / versionScheme := Some("early-semver")
ThisBuild / organization  := "io.github.scottweaver"
ThisBuild / description   := "Another one of my projects."
ThisBuild / homepage      := Some(url("https://github.com/scottweaver/zymposium-with-containers"))
ThisBuild / licenses      := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))
ThisBuild / scmInfo       := Some(
  ScmInfo(
    url("https://github.com/scottweaver/zymposium-with-containers"),
    "scm:git@github.com:scottweaver/zymposium-with-containers.git"
  )
)

ThisBuild / developers    := List(
  Developer(
    id = "scottweaver",
    name = "Scott T Weaver",
    email = "scott.t.weaver@gmail.com",
    url = url("https://scottweaver.github.io/")
  )
)

ThisBuild / scalacOptions := stdOpts3
ThisBuild / scalaVersion  := "3.1.3"

lazy val stdOpts3 = Seq(
  "-Xfatal-warnings",
  "-deprecation",
  "-explaintypes",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:postfixOps",
  "-unchecked",
  "-encoding",
  "UTF-8",
  "-source:3.0-migration"
)

libraryDependencies ++= Seq(
  "dev.zio"               %% "zio"                               % V.zioVersion,
  "dev.zio"               %% "zio-test"                          % V.zioVersion,
  "io.getquill"           %% "quill-jdbc-zio"                    % "4.0.0",
  "io.github.scottweaver" %% "zio-2-0-testcontainers-postgresql" % V.testcontainersForZioVersion % Test,
  "io.github.scottweaver" %% "zio-2-0-db-migration-aspect"       % V.testcontainersForZioVersion % Test
  // "io.github.scottweaver" %% "zio-2.0-testcontainers-kafka"      % V.testcontainersForZioVersion % Test
)
