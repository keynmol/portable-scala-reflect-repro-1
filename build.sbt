lazy val psr = Seq(
  libraryDependencies += "org.portable-scala" %% "portable-scala-reflect" % "1.1.0"
)

lazy val root = project.aggregate(ok, notOk)

lazy val ok = project
  .in(file("."))
  .settings(
    target := baseDirectory.in(ThisBuild).value / "target" / "ok",
    libraryDependencies +=
      scalaOrganization.value % "scala-reflect" % scalaVersion.value
  )
  .settings(psr)

// come on sbt, just let me do this
lazy val notOk = project
  .in(file("other"))
  .settings(
    name := "notOk",
    Compile / unmanagedSourceDirectories += baseDirectory
      .in(ThisBuild)
      .value / "src" / "main" / "scala"
  )
  .settings(psr)
