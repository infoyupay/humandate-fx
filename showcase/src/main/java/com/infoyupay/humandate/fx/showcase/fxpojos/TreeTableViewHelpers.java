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
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.function.Function;

/**
 * Helper utilities for configuring {@code TreeTableView} columns in the
 * humandate-fx showcase.
 * <br/>
 * This class provides stateless, type-safe factory methods used to configure
 * {@code TreeTableColumn} instances directly from FXML, avoiding:
 * <ul>
 *   <li>Large {@code initialize()} methods in controllers.</li>
 *   <li>Reflection-based {@code PropertyValueFactory} usage.</li>
 *   <li>String-based property lookups.</li>
 * </ul>
 * All helpers are designed to work with {@link FxInvoice} and to demonstrate
 * hierarchical data rendering, aggregation and human-readable date handling
 * using humandate-fx.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public final class TreeTableViewHelpers {

    /**
     * Utility class, not meant to be instantiated.
     */
    private TreeTableViewHelpers() {
    }

    /**
     * Creates a {@code TreeTableColumn} cell value factory based on a property
     * mapper.
     * <br/>
     * <br/>
     * This method centralizes the boilerplate required to safely extract values
     * from {@link javafx.scene.control.TreeItem} instances, handling both
     * structural nodes and leaf nodes gracefully.
     *
     * @param mapper function mapping a {@link FxInvoice} instance to one of its
     *               {@link ObservableValue} properties.
     * @param <T>    the value type exposed by the property.
     * @return a {@link Callback} suitable for
     * {@code TreeTableColumn#setCellValueFactory}.
     */
    private static <T>
    Callback<TreeTableColumn.CellDataFeatures<FxInvoice, T>, ObservableValue<T>>
    property(Function<FxInvoice, ObservableValue<T>> mapper) {

        return f -> {
            var leaf = f.getValue();
            if (leaf == null) return null;
            var value = leaf.getValue();
            if (value == null) return null;
            return mapper.apply(value);
        };
    }

    /**
     * Cell value factory for the invoice due date column.
     * <br/>
     * <br/>
     * Maps {@link FxInvoice#dueDateProperty()} to a {@code TreeTableColumn},
     * typically used for grouping and hierarchical visualization.
     *
     * @return cell value factory for the due date column.
     */
    public static Callback<TreeTableColumn.CellDataFeatures<FxInvoice, LocalDate>,
            ObservableValue<LocalDate>> dueDate() {

        return property(FxInvoice::dueDateProperty);
    }

    /**
     * Cell value factory for the invoice issue date column.
     * <br/>
     * <br/>
     * This column is commonly used to demonstrate human-readable date rendering
     * via humandate-fx in a hierarchical context.
     *
     * @return cell value factory for the issue date column.
     */
    public static Callback<TreeTableColumn.CellDataFeatures<FxInvoice, LocalDate>,
            ObservableValue<LocalDate>> issueDate() {

        return property(FxInvoice::issueDateProperty);
    }

    /**
     * Cell value factory for the invoice reference number column.
     *
     * @return cell value factory for the invoice number.
     */
    public static Callback<TreeTableColumn.CellDataFeatures<FxInvoice, String>,
            ObservableValue<String>> number() {

        return property(FxInvoice::numberProperty);
    }

    /**
     * Cell value factory for the invoice amount column.
     * <br/>
     * <br/>
     * In parent nodes, this value typically represents an aggregated total of
     * all child invoices.
     *
     * @return cell value factory for the invoice amount.
     */
    public static Callback<TreeTableColumn.CellDataFeatures<FxInvoice, BigDecimal>,
            ObservableValue<BigDecimal>> amount() {

        return property(FxInvoice::amountProperty);
    }

    /**
     * Cell factory for editable monetary amount cells.
     * <br/>
     * <br/>
     * This factory provides a minimal, showcase-oriented implementation for
     * rendering and editing {@link BigDecimal} values using a fixed decimal
     * format.
     * <br/>
     * <br/>
     * The implementation prioritizes clarity and visual correctness over full
     * financial precision, making it suitable for demos and documentation
     * examples rather than production-grade accounting logic.
     *
     * @return a {@link Callback} producing {@link TreeTableCell} instances for
     * monetary amounts.
     */
    public static Callback<TreeTableColumn<FxInvoice, BigDecimal>,
            TreeTableCell<FxInvoice, BigDecimal>> amountCellFactory() {

        return TextFieldTreeTableCell.forTreeTableColumn(
                new StringConverter<>() {

                    private final DecimalFormat format =
                            new DecimalFormat("#,##0.00");

                    {
                        format.setParseBigDecimal(true);
                    }

                    @Override
                    public String toString(BigDecimal d) {
                        return d == null ? "" : format.format(d);
                    }

                    @Override
                    public BigDecimal fromString(String s) {
                        if (s == null || s.isBlank()) return null;
                        try {
                            var value = format.parse(s);
                            return switch (value) {
                                case null -> null;
                                case BigDecimal bd -> bd;
                                default -> BigDecimal.valueOf(value.doubleValue());
                            };
                        } catch (ParseException e) {
                            return null;
                        }
                    }
                }
        );
    }
}
