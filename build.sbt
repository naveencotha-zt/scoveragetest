name := "scoveragetest"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies ++= {

  val sparkV = "2.3.1"

  Seq(

    "org.apache.spark" %% "spark-core" % sparkV % Provided,
    "org.apache.spark" %% "spark-streaming" % sparkV % Provided,
    "org.apache.spark" %% "spark-sql" % sparkV % Provided
  )
}

mainClass in(Compile, run) := Some("com.scoverage.Example")
mainClass in assembly := Some("com.scoverage.Example")

//Avoid this error `NoClassDefFoundError: scoverage/Invoker$`
coverageEnabled.in(Test, test) := true
coverageEnabled in(Compile, compile) := false

parallelExecution in Test := false
