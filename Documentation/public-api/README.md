# How to generate javadoc and integrate it to Portal documentation

1. File pom.xml is created to run on Linux for Jenkins builds. If you run on Windows, update pom.xml as comments in pom.xml. Find <!--On Windows, replace character : by character ;--> and replace character in the next line.
2. Execute: `maven mvn clean process-resources`. Javadoc is created in folder `target`
3. How to integrate javadoc to Portal documentation
    * Start docker to generate Portal documenatation
    * Copy all stuff in `Documentation\public-api\target` to `Documentation\portal-guide\build\html\portal-developer-guide\public-api`