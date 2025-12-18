# humandate-fx Showcase

This directory contains the **humandate-fx showcase application**.

The showcase is a standalone JavaFX application designed to demonstrate the
ideas, patterns and UX concepts behind the `humandate-fx` library in a concrete,
runnable form.

It is intentionally **not published to Maven Central**.

---

## Purpose

The showcase exists to:

- Demonstrate how `humandate-fx` feels in real UI scenarios
- Validate bindings, converters and cells from a user perspective
- Provide a reference implementation for developers exploring the library

Think of it as a **living example**, not as part of the public API.

---

## Building and running locally

You can build and run the showcase directly using Gradle.

### Build

```bash
./gradlew build
```

### Run

```bash
./gradlew run
```

This will launch the JavaFX application and allow you to interactively explore
the concepts provided by `humandate-fx`.

---

## Pre-built artifacts

For convenience, **pre-compiled binaries are provided for common platforms**:

- Linux
- macOS
- Windows

These artifacts are built automatically by the project’s **CI pipeline** and
attached to GitHub Releases.

If you prefer not to build locally, simply download the appropriate artifact
from the latest release and run it on your system.

---

## Relationship to the main library

- `humandate-core` provides the domain semantics
- `humandate-fx` provides the JavaFX adapter layer
- This showcase demonstrates how both come together in practice

The showcase depends on the library, never the other way around.

---

## Feedback and contributions

Feedback is welcome.

If you have ideas to improve the UX, optimize the implementation, or clarify
the examples, feel free to:

- Open a Pull Request
- Start a discussion
- Or contact us at **info@infoyupay.com**

---

## License

The showcase follows the same **Apache License 2.0** as the main project.
