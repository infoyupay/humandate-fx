# humandate-fx

**humandate-fx** is a JavaFX companion library for  
[`humandate-core`](https://github.com/infoyupay/humandate-core).

While `humandate-core` focuses on *pure domain semantics* for human-friendly dates,
**humandate-fx bridges that model into the JavaFX world**, making it easy to build
clear, expressive and pleasant user interfaces without re-inventing formatting,
parsing or binding logic.

This library exists to make **beautiful UX feel natural**, not accidental.

---

## What this library is

- A **JavaFX adapter layer** for `humandate-core`
- A collection of:
  - JavaFX converters
  - bindings
  - properties
  - cells and helpers
- Designed to integrate seamlessly with:
  - `ObjectProperty`
  - `ObjectBinding`
  - `Callback`
  - JavaFX controls such as `ListView`, `TableView`, `TreeTableView`, etc.

It is intentionally **JavaFX-specific** and declares JavaFX as a first-class dependency.

---

## What this library is *not*

- It is **not** a general date library (that is the role of `humandate-core`)
- It is **not** UI-framework-agnostic
- It does **not** hide or abstract JavaFX away

This is a bridge, not a disguise.

---

## Installation

Simply add the dependency to your build.

### Gradle

```kotlin
dependencies {
    implementation("com.infoyupay.humandate:humandate-fx:1.0.0")
}
```

The module declares all required transitive dependencies, so no additional
JavaFX or core declarations are needed when using JPMS.

---

## Modular usage (JPMS)

If you use Java modules:

```
requires com.infoyupay.humandate.fx;
```

That is enough.  
`humandate-core` and the required JavaFX modules are exposed transitively
as part of the public API contract.

---

## Showcase application

If you want to *feel* how the library behaves from a user perspective,
check out the **`showcase`** sub-project in this repository.

The showcase is:
- a standalone JavaFX application
- not published to Maven Central
- built and validated in CI
- meant for experimentation and exploration

It demonstrates the ideas, patterns and UX decisions behind `humandate-fx`
in a concrete, runnable form.

---

## Extending the model

This project follows a simple philosophy:

- `humandate-core` defines the **semantic model**
- UI toolkits provide the **presentation**
- Adapter libraries connect the two

If you want to build:
- `humandate-qt`
- `humandate-swt`
- `humandate-lanterna`
- or any other UI-specific adaptation

you are absolutely welcome to do so.

The model is open, the idea is reusable, and the ecosystem benefits from
multiple adapters rather than a single monolith.

---

## Contributing

Contributions are welcome.

- Ideas for improving usability
- API refinements
- Performance optimizations
- Documentation improvements

Feel free to open a Pull Request, or contact us directly at:

📧 **info@infoyupay.com**

---

## License

This project is licensed under the **Apache License 2.0**.

You are free to use it, adapt it, extend it, and build upon it.
