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

package com.infoyupay.humandate.fx.showcase.fxpojos;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.function.Function;

/**
 * Helper utilities for configuring {@code TableView} columns in the
 * humandate-fx showcase.
 * <br/>
 * This class provides type-safe {@link Callback} factories that map
 * {@link FxCustomer} properties to {@code TableColumn} cell value factories,
 * allowing table configuration to be declared directly in FXML without:
 * <ul>
 *   <li>Large {@code initialize()} methods.</li>
 *   <li>Reflection-based {@code PropertyValueFactory} usage.</li>
 *   <li>String-based property names.</li>
 * </ul>
 * All helpers are stateless and designed to be invoked directly from FXML.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public final class TableViewHelpers {

    /**
     * Utility class, not meant to be instantiated.
     */
    private TableViewHelpers() {
    }

    /**
     * Creates a {@code TableColumn} cell value factory based on a property mapper.
     * <br/>
     * <br/>
     * This method centralizes the null-safety and mapping logic required by
     * JavaFX {@code TableView} columns, avoiding repetitive lambda expressions
     * across controllers or FXML files.
     *
     * @param mapper function that maps a {@link FxCustomer} instance to one of
     *               its {@link ObservableValue} properties.
     * @param <T>    the value type exposed by the property.
     * @return a {@link Callback} suitable for {@code TableColumn#setCellValueFactory}.
     */
    private static <T>
    Callback<CellDataFeatures<FxCustomer, T>, ObservableValue<T>>
    property(Function<FxCustomer, ObservableValue<T>> mapper) {

        return f -> {
            var value = f.getValue();
            if (value == null) return null;
            return mapper.apply(value);
        };
    }

    /**
     * Cell value factory for the customer identification number column.
     * <br/>
     * <br/>
     * Maps {@link FxCustomer#idNumberProperty()} to a {@code TableColumn}
     * in a type-safe and refactor-friendly way.
     *
     * @return cell value factory for the identification number.
     */
    public static Callback<CellDataFeatures<FxCustomer, String>, ObservableValue<String>>
    idNumber() {
        return property(FxCustomer::idNumberProperty);
    }

    /**
     * Cell value factory for the customer name column.
     * <br/>
     * <br/>
     * Maps {@link FxCustomer#nameProperty()} to a {@code TableColumn} without
     * relying on reflection or string-based property names.
     *
     * @return cell value factory for the customer name.
     */
    public static Callback<CellDataFeatures<FxCustomer, String>, ObservableValue<String>>
    name() {
        return property(FxCustomer::nameProperty);
    }

    /**
     * Cell value factory for the customer birthday column.
     * <br/>
     * <br/>
     * Maps {@link FxCustomer#birthdayProperty()} to a {@code TableColumn},
     * serving as the primary entry point for demonstrating humandate-fx
     * human-readable date rendering.
     *
     * @return cell value factory for the customer birthday.
     */
    public static Callback<CellDataFeatures<FxCustomer, LocalDate>, ObservableValue<LocalDate>>
    birthday() {
        return property(FxCustomer::birthdayProperty);
    }
}
