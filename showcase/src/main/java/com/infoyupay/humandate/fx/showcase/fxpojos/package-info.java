/*
 * Copyright 2025 Ingeniería Informática Yupay S.A.C.S.
 * RUC 20607854247
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

/**
 * JavaFX domain models and helpers used in the humandate-fx showcase.
 * <br/>
 * This package contains a small set of UI-oriented domain models
 * ({@link com.infoyupay.humandate.fx.showcase.fxpojos.FxCustomer FxCustomer} and
 * {@link com.infoyupay.humandate.fx.showcase.fxpojos.FxInvoice FxInvoice})
 * together with helper classes used to configure JavaFX {@code TableView} and
 * {@code TreeTableView} components.
 * <br/>
 * The goal of these classes is not to provide business logic, but to:
 * <ul>
 *   <li>Demonstrate realistic data structures for tables and tree tables.</li>
 *   <li>Showcase human-readable date rendering using humandate-fx.</li>
 *   <li>Keep FXML controllers small, focused and easy to reason about.</li>
 * </ul>
 * <br/>
 * <h2>Helpers and declarative configuration</h2>
 * Helper classes in this package expose stateless, type-safe factory methods
 * that are designed to be invoked directly from FXML. This approach avoids:
 * <ul>
 *   <li>Large {@code initialize()} methods in controllers.</li>
 *   <li>Reflection-based factories such as {@code PropertyValueFactory}.</li>
 *   <li>String-based property lookups that are fragile under refactoring.</li>
 * </ul>
 * <br/>
 * All helpers act as explicit adapters between the domain model and JavaFX
 * controls, centralizing structural configuration in a single, well-defined
 * place.
 * <br/>
 * <h2>FXML conventions</h2>
 * In this showcase, object creation and configuration in FXML is performed
 * exclusively using {@code fx:factory}.
 * <br/>
 * The JavaFX Expression Language (EL) is intentionally restricted to values
 * originating from the controller instance (such as observable properties or
 * simple state), and is not used to construct helpers, callbacks or factories.
 * <br/>
 * This convention keeps FXML declarative, explicit and aligned with the
 * officially documented JavaFX factory mechanisms, while avoiding implicit
 * evaluation rules or reflection-based behavior.
 * <br/>
 * <h2>Scope and intent</h2>
 * All classes in this package exist solely to support the humandate-fx showcase
 * and documentation. They are intentionally simple, predictable and easy to
 * read, prioritizing clarity over completeness.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
package com.infoyupay.humandate.fx.showcase.fxpojos;
