# Donk (task list CLI)

Donk is a small command-line task manager built for CS2103T/IP. It parses simple text commands, stores tasks to disk, and prints responses in a text UI.

## Prerequisites

- JDK 17
- macOS, Linux, or Windows with a terminal

## Setup in IntelliJ

1. Open IntelliJ (close any open project first).
2. Click `Open` and select the project directory.
3. When prompted, accept the defaults.
4. Set the Project SDK to **JDK 17** and the language level to `SDK default`.

## Run the app

Using Gradle:

```bash
./gradlew run
```

If you run it from IntelliJ, open `src/main/java/donk/controller/ui/Donk.java` and choose `Run`.

## Run tests

All tests:

```bash
./gradlew test
```

Single test class:

```bash
./gradlew test --tests "donk.controller.command.ParserTest"
```

Optional text UI tests (from `text-ui-test/`):

```bash
cd text-ui-test
./runtest.sh
```

## Project layout

```
src/main/java/donk/
  controller/
    command/   # command parsing and execution
    ui/        # entry point and UI loop
  model/
    exception/ # custom exceptions
    storage/   # file persistence
    task/      # task models
src/test/java/ # JUnit 5 tests
text-ui-test/  # end-to-end test script
```

## Data storage

Tasks are stored in `data/donk.txt` using a pipe-separated format. You can delete this file to reset local data.

## Notes

- Keep `src/main/java` as the root for Java sources.
- Gradle is configured in `build.gradle` with JUnit 5.
