resolvers in Global ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.bintrayRepo("io-monadplus", "maven")
)

lazy val contributors = Seq(
  "monadplus" -> "Arnau Abella"
)

val catsV = "1.6.0"
val kittensV = "1.2.0"
val catsEffectV = "1.2.0"
val mouseV = "0.20"
val catsMtlV = "0.4.0"
val fs2V = "1.0.3"
val http4sV = "0.20.0-M5"
val circeV = "0.11.1"
val doobieV = "0.6.0"
val pureConfigV = "0.10.2"
val equalityV = "0.0.1"

val scalaTestV = "3.0.5"
val scalaCheckV = "1.14.0"

val kindProjectorV = "0.9.9"
val betterMonadicForV = "0.3.0-M4"

lazy val commonDependencies = Seq(
  "org.typelevel" %% "cats-core" % catsV,
  "org.typelevel" %% "cats-kernel" % catsV,
  "org.typelevel" %% "cats-macros" % catsV,
  "org.typelevel" %% "cats-free" % catsV,
  "org.typelevel" %% "cats-laws" % catsV,
  "org.typelevel" %% "kittens" % kittensV,
  "org.typelevel" %% "alleycats-core" % catsV,
  "org.typelevel" %% "mouse" % mouseV,
  "org.typelevel" %% "cats-mtl-core" % catsMtlV,
  "org.typelevel" %% "cats-effect" % catsEffectV,
  "co.fs2" %% "fs2-core" % fs2V,
  "co.fs2" %% "fs2-io" % fs2V,
  "org.http4s" %% "http4s-dsl" % http4sV,
  "org.http4s" %% "http4s-blaze-server" % http4sV,
  "org.http4s" %% "http4s-blaze-client" % http4sV,
  "org.http4s" %% "http4s-circe" % http4sV,
  "io.circe" %% "circe-core" % circeV,
  "io.circe" %% "circe-generic" % circeV,
  "io.circe" %% "circe-parser" % circeV,
  "org.tpolecat" %% "doobie-core" % doobieV,
  "org.tpolecat" %% "doobie-h2" % doobieV,
  "org.tpolecat" %% "doobie-hikari" % doobieV,
  "org.tpolecat" %% "doobie-postgres" % doobieV,
  "com.github.pureconfig" %% "pureconfig" % pureConfigV,
  "io.monadplus" %% "equality-core" % equalityV,

  "org.scalatest" %% "scalatest" % scalaTestV % Test,
  "org.scalacheck" %% "scalacheck" % scalaCheckV % Test,
  "org.tpolecat" %% "doobie-scalatest" % doobieV % Test,
  "org.typelevel" %% "cats-testkit" % catsV % Test
)

lazy val compilerFlags = Seq(
  scalacOptions ++= Seq(
    "-feature",
    "-deprecation",
    "-language:implicitConversions",
    "-language:higherKinds"
  ) ++ (if (scalaBinaryVersion.value.startsWith("2.12"))
          List(
            "-Xlint",
            "-Xfatal-warnings",
            "-Yno-adapted-args",
            "-Ywarn-value-discard",
            "-Ywarn-unused-import",
            "-Ypartial-unification"
          )
        else Nil),
  scalacOptions in (Test, compile) --= Seq(
    "-Ywarn-unused-import",
    "-Xlint",
    "-Xfatal-warnings"
  )
)
lazy val commonSettings = Seq(
  organization := "io.monadplus",
  scalaVersion := "2.12.8",
  licenses := Seq("Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0.html")),
  description := "TODO",
  parallelExecution in Test := true,
  fork in Test := true,
  addCompilerPlugin("org.spire-math" % "kind-projector" % kindProjectorV cross CrossVersion.binary),
  addCompilerPlugin("com.olegpy" %% "better-monadic-for" % betterMonadicForV),
  libraryDependencies ++= commonDependencies
)

lazy val `jmh-example` = project
  .in(file("."))
  .settings(commonSettings)
  .aggregate(core)

lazy val core = project
  .in(file("core"))
  .settings(commonSettings)
  .settings(
    name := "jmh-example"
  ).enablePlugins(JmhPlugin)
