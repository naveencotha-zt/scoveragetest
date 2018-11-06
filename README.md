# scoveragetest
testing scoverage options

# Building the jar with coverage
sbt ";clean ;coverage ;assembly ;coverageReport"

# Building without coverage
sbt ";clean ;assembly"

# Command to run the application
~/path/to/spark-2.3.1/bin/spark-submit  --class com.scoverage.Example --master local[*] target/scala-2.11/scoveragetest-assembly-0.1.jar

# Error happens only when ran against jar built with coverage.