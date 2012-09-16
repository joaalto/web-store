import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "web-store"
    val appVersion      = "1.0-SNAPSHOT"
      
    val appDependencies = Seq(
        "org.scalaquery" %% "scalaquery" % "0.10.0-M1" withSources(),
        "com.h2database" % "h2" % "1.3.168" withSources(),
        "org.specs2" %% "specs2" % "1.12.1" % "test" withSources()
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      // Add your own project settings here      
    )

}
