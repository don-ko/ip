# Running JUnit Tests with Gradle

JUnit tests live under `src/test/java` and can be run with Gradle.

## Run all tests:

```bash
./gradlew test
```

## Run a single test class:

```bash
./gradlew test --tests "donk.controller.command.ParserTest"
```
