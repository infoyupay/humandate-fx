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

import com.infoyupay.humandate.fx.LocalDateProperty;
import com.infoyupay.humandate.fx.showcase.RandomUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TreeItem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * JavaFX domain model used in the humandate-fx TreeTableView showcase.
 * <br/>
 * {@code FxInvoice} represents a simplified invoice entity designed to
 * demonstrate hierarchical data structures, date handling and aggregation
 * inside {@code TreeTableView} components.
 * <br/>
 * This class is intentionally UI-oriented and focuses on:
 * <ul>
 *   <li>Demonstrating multiple {@link LocalDate} properties in a single row.</li>
 *   <li>Showcasing human-readable date rendering using humandate-fx.</li>
 *   <li>Providing realistic parent/child aggregation scenarios.</li>
 * </ul>
 * It contains no business rules and should be considered a showcase-only
 * domain model.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxInvoice {

    /**
     * Internal immutable identifier used exclusively for equality and hashing.
     */
    private final UUID _id = UUID.randomUUID();

    /**
     * Invoice issue date.
     */
    private final LocalDateProperty issueDate =
            new LocalDateProperty(this, "issueDate");

    /**
     * Invoice due date.
     */
    private final LocalDateProperty dueDate =
            new LocalDateProperty(this, "dueDate");

    /**
     * Invoice reference number.
     */
    private final StringProperty number =
            new SimpleStringProperty(this, "number");

    /**
     * Invoice monetary amount.
     * <br/>
     * <br/>
     * For parent nodes, this value typically represents the aggregated total
     * of all child invoices.
     */
    private final ObjectProperty<BigDecimal> amount =
            new SimpleObjectProperty<>(this, "amount", new BigDecimal("0.00"));

    /**
     * Creates a hierarchical sample tree of invoices for TreeTableView demos.
     * <br/>
     * <br/>
     * The returned tree simulates a receivables structure where:
     * <ul>
     *   <li>The root node represents a logical container.</li>
     *   <li>Intermediate nodes group invoices by due date.</li>
     *   <li>Leaf nodes represent individual invoices.</li>
     * </ul>
     * <br/>
     * <br/>
     * Monetary amounts are aggregated automatically at each parent level.
     *
     * @return a fully initialized and expanded {@code TreeItem} hierarchy.
     */
    public static TreeItem<FxInvoice> sampleTree() {
        var root = new TreeItem<>(new FxInvoice().withNumber("Receivables"));

        RandomUtils.randomDates(8)
                .map(FxInvoice::leafSample)
                .forEach(partial -> root.getChildren().add(partial));

        var total = root.getChildren().stream()
                .map(TreeItem::getValue)
                .map(FxInvoice::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        root.getValue().withAmount(total);
        root.setExpanded(true);
        return root;
    }

    /**
     * Creates a grouped subtree of invoices sharing the same due date.
     * <br/>
     * <br/>
     * This method is used internally to generate realistic TreeTableView
     * structures with aggregated parent values.
     *
     * @param dueDate common due date for all generated invoices.
     * @return a {@code TreeItem} containing grouped invoice children.
     */
    private static TreeItem<FxInvoice> leafSample(LocalDate dueDate) {
        var r = new TreeItem<>(new FxInvoice().withDueDate(dueDate));

        RandomUtils.randomNumbers()
                .mapToObj(i -> new FxInvoice()
                        .withAmount(RandomUtils.randomAmount())
                        .withDueDate(dueDate)
                        .withIssueDate(RandomUtils.randomDate())
                        .withNumber("%06d".formatted(i)))
                .map(TreeItem::new)
                .forEach(leaf -> r.getChildren().add(leaf));

        var sum = r.getChildren().stream()
                .map(TreeItem::getValue)
                .map(FxInvoice::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        r.getValue().withAmount(sum);
        r.setExpanded(true);
        return r;
    }

    /**
     * Returns the invoice issue date.
     *
     * @return issue date value.
     */
    public LocalDate getIssueDate() {
        return issueDate.get();
    }

    /**
     * JavaFX property representing the invoice issue date.
     * <br/>
     * <br/>
     * Used in the showcase to demonstrate human-readable date rendering.
     *
     * @return the {@link LocalDateProperty} backing the issue date.
     */
    public LocalDateProperty issueDateProperty() {
        return issueDate;
    }

    /**
     * Fluent setter for the invoice issue date.
     *
     * @param issueDate date to assign.
     * @return this mutated instance.
     */
    public FxInvoice withIssueDate(LocalDate issueDate) {
        this.issueDate.set(issueDate);
        return this;
    }

    /**
     * Returns the invoice due date.
     *
     * @return due date value.
     */
    public LocalDate getDueDate() {
        return dueDate.get();
    }

    /**
     * JavaFX property representing the invoice due date.
     * <br/>
     * <br/>
     * This property is commonly used as a grouping key in TreeTableView demos.
     *
     * @return the {@link LocalDateProperty} backing the due date.
     */
    public LocalDateProperty dueDateProperty() {
        return dueDate;
    }

    /**
     * Fluent setter for the invoice due date.
     *
     * @param dueDate date to assign.
     * @return this mutated instance.
     */
    public FxInvoice withDueDate(LocalDate dueDate) {
        this.dueDate.set(dueDate);
        return this;
    }

    /**
     * Returns the invoice reference number.
     *
     * @return invoice number value.
     */
    public final String getNumber() {
        return number.get();
    }

    /**
     * JavaFX property representing the invoice reference number.
     *
     * @return the {@link StringProperty} backing the invoice number.
     */
    public final StringProperty numberProperty() {
        return number;
    }

    /**
     * Fluent setter for the invoice reference number.
     *
     * @param number value to assign.
     * @return this mutated instance.
     */
    public final FxInvoice withNumber(String number) {
        this.number.set(number);
        return this;
    }

    /**
     * Returns the invoice monetary amount.
     *
     * @return amount value.
     */
    public final BigDecimal getAmount() {
        return amount.get();
    }

    /**
     * JavaFX property representing the invoice monetary amount.
     * <br/>
     * <br/>
     * In parent nodes, this value typically represents the sum of all child
     * invoice amounts.
     *
     * @return the {@link ObjectProperty} backing the amount.
     */
    public final ObjectProperty<BigDecimal> amountProperty() {
        return amount;
    }

    /**
     * Fluent setter for the invoice monetary amount.
     *
     * @param amount value to assign.
     * @return this mutated instance.
     */
    public final FxInvoice withAmount(BigDecimal amount) {
        this.amount.set(amount);
        return this;
    }

    /**
     * Equality is based on an internal immutable UUID rather than visible fields.
     * <br/>
     * <br/>
     * This guarantees stable identity when instances are used inside TreeTableView
     * structures and observable collections.
     */
    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof FxInvoice fxInvoice)) return false;
        return Objects.equals(_id, fxInvoice._id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(_id);
    }
}
