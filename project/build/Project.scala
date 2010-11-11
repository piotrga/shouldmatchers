import sbt._

class Should(info: ProjectInfo) extends DefaultProject(info)
{ 
  val bryanjswift = "Bryan J Swift Repository" at "http://repos.bryanjswift.com/maven2/" 
  val junitInterface = "com.novocode" % "junit-interface" % "0.5" % "test->default" 
  val hamcrest =  "org.hamcrest" % "hamcrest-all" % "1.1"
  val guava = "com.google.guava" % "guava" % "r06"
  val cglib = "cglib" % "cglib-full" % "2.0.2"
  val junit = "junit" % "junit" % "4.8.1"
  val joda = "joda-time" % "joda-time" % "1.6" % "provided"
}
