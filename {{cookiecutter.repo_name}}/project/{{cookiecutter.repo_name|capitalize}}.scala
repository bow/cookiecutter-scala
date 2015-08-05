import sbt._
import Keys._
import com.sksamuel.scapegoat.sbt.ScapegoatSbtPlugin.autoImport._
import com.typesafe.sbt.SbtScalariform._
import com.typesafe.sbt.SbtSite.site
import de.heikoseeberger.sbtheader._

object {{ cookiecutter.repo_name|capitalize }} extends Build {

  val Organization = "{{ cookiecutter.organization }}"
  val Name = "{{ cookiecutter.project_name }}"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "{{ cookiecutter.scala_version }}"
  val JavaVersion = "{{ cookiecutter.java_version }}"

  lazy val dependencies = Seq(
    "org.specs2"             %%  "specs2-core"                % "3.6.4"               % "test")

  lazy val formattingPreferences = {
    import scalariform.formatter.preferences._
    FormattingPreferences()
      .setPreference(AlignParameters, true)
      .setPreference(AlignSingleLineCaseStatements, true)
      .setPreference(CompactStringConcatenation, false)
      .setPreference(CompactControlReadability, false)
      .setPreference(DoubleIndentClassDeclaration, true)
      .setPreference(IndentLocalDefs, false)
      .setPreference(IndentPackageBlocks, true)
      .setPreference(IndentSpaces, 2)
      .setPreference(IndentWithTabs, false)
      .setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, false)
      .setPreference(PreserveDanglingCloseParenthesis, true)
      .setPreference(PreserveSpaceBeforeArguments, false)
      .setPreference(RewriteArrowSymbols, false)
      .setPreference(SpaceBeforeColon, false)
      .setPreference(SpaceInsideBrackets, false)
      .setPreference(SpaceInsideParentheses, false)
      .setPreference(SpacesWithinPatternBinders, true)
  }

  lazy val headerSettings = Seq(HeaderPlugin.autoImport.headers := Map(
    "scala" -> (
      HeaderPattern.cStyleBlockComment,
      """|/*
        | * Copyright (c) {{ cookiecutter.year }} {{ cookiecutter.full_name }} and contributors
        | *                    (see AUTHORS.rst file for details).
        | *
        | * Licensed under the Apache License, Version 2.0 (the "License");
        | * you may not use this file except in compliance with the License.
        | * You may obtain a copy of the License at
        | *
        | * http://www.apache.org/licenses/LICENSE-2.0
        | *
        | * Unless required by applicable law or agreed to in writing, software
        | * distributed under the License is distributed on an "AS IS" BASIS,
        | * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        | * See the License for the specific language governing permissions and
        | * limitations under the License.
        | */
        |""".stripMargin
      ))) ++ AutomateHeaderPlugin.automateFor(IntegrationTest)

  lazy val docsSiteSettings = site.settings ++ site.includeScaladoc(s"scaladoc/$Version")

  lazy val projectSettings = scalariformSettings ++ headerSettings ++ docsSiteSettings ++
    Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      scalacOptions ++= Seq(
        "-unchecked",
        "-deprecation",
        "-feature",
        "-Xmax-classfile-name", "200"),
      scapegoatConsoleOutput := false,
      testOptions in Test += Tests.Argument("console", "junitxml"),
      testOptions in IntegrationTest += Tests.Argument("console", "junitxml"),
      ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) },
      resolvers += Classpaths.typesafeReleases,
      resolvers += "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository",
      resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
      resolvers += "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/",
      ScalariformKeys.preferences := formattingPreferences,
      libraryDependencies ++= dependencies)

  lazy val project = Project("{{ cookiecutter.repo_name }}",  file("."))
    .enablePlugins(AutomateHeaderPlugin)
    .configs(IntegrationTest)
    .settings(projectSettings)
}
