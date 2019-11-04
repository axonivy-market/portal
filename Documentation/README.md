# doc projects

## Releasing

1. Remove `-SNAPSHOT` from property `revision` in `pom.xml`
2. Commit change and push it to remote repository
3. Run Jenkins Build http://zugprojenkins
4. Prepare next development cycle by increasing version `revision` in `pom.xml` and adding `-SNASPHOT`
5. Commit change and push it to remote repository
