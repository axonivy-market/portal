# How to generate javadoc

1. File pom.xml is created to run on Linux for Jenkins builds. If you run on Windows, update pom.xml as comments in pom.xml. Find <!--On Windows, replace character : by character ;--> and replace character in the next line.
2. Execute: maven mvn clean process-resources