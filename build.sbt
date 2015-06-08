lazy val root = (project in file(".")).
  settings(
    name := "connect-the-dots",
    version := "0.0.1",
    scalaVersion := "2.11.6",
    resolvers ++= Seq(
      "GHost" at "http://repo.ghost4j.org/maven2/releases",
      "MyGrid" at "http://repository.mygrid.org.uk/artifactory/mygrid-all",
      "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
    ),
    libraryDependencies ++= Seq(
      "com.github.jai-imageio" % "jai-imageio-core" % "1.3.0",
      "net.sourceforge.lept4j" % "lept4j" % "1.0.0-SNAPSHOT",
      "net.sourceforge.tess4j" % "tess4j" % "2.0.0"
    )
  )
