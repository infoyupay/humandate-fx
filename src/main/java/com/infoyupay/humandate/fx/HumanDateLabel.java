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
package com.infoyupay.humandate.fx;

import com.infoyupay.humandate.core.HumanDateFormatter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;

import java.time.LocalDate;

/**
 * A JavaFX {@link Label} that displays a {@link LocalDate} using
 * a human-friendly textual representation provided by a
 * {@link HumanDateFormatter}.
 * <br/>
 * <p>
 * This control is fully reactive: whenever the internal date value or
 * the formatter configuration changes, the label text updates immediately
 * without requiring additional code or listeners.
 * <br/>
 * <p><b>Usage examples:</b></p>
 * {@snippet :
 * var lbl = new HumanDateLabel(LocalDate.now());
 * lbl.setDateFormatter(
 *         new HumanDateFormatter()
 *                 .withFormatter(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
 * );
 *}
 * <br/>
 * <p>FXML example:</p>
 * {@snippet lang = "fxml":
 * <fx:root type="HumanDateLabel"
 *          xmlns="http://javafx.com/javafx"
 *          xmlns:fx="http://javafx.com/fxml"
 *          dateValue="2024-06-19"/>
 *}
 *
 * @author David Vidal
 * @version 1.0
 */
public final class HumanDateLabel extends Label {

    private final LocalDateProperty dateValue =
            new LocalDateProperty(this, "dateValue");

    private final ObjectProperty<HumanDateFormatter> dateFormatter =
            new SimpleObjectProperty<>(this, "dateFormatter", new HumanDateFormatter());

    /**
     * Creates a label with no initial date.
     * <br/>
     * The label remains blank until a non-null date is assigned.
     * <br/>
     * The label text is bound to the date value using the default formatter.
     */
    public HumanDateLabel() {
        textProperty().bind(dateValue.asHumanString(dateFormatter));
    }

    /**
     * Creates a label using a custom formatter for text rendering.
     *
     * @param formatter the formatter to use (must not be {@code null})
     */
    public HumanDateLabel(final HumanDateFormatter formatter) {
        this();
        setDateFormatter(formatter);
    }

    /**
     * Creates a label initialized with a date value, using
     * the default formatting rules.
     *
     * @param initialValue the initial date, may be {@code null}
     */
    public HumanDateLabel(final LocalDate initialValue) {
        this();
        setDateValue(initialValue);
    }

    // --- Date value ---

    /**
     * Returns the currently displayed date.
     *
     * @return current date or {@code null} if not set
     */
    public LocalDate getDateValue() {
        return dateValue.get();
    }

    /**
     * Updates the date value displayed by this label.
     * <br/>
     * Changing this value triggers an automatic update of the label text.
     *
     * @param value the new date to display, may be {@code null}
     */
    public void setDateValue(final LocalDate value) {
        dateValue.set(value);
    }

    /**
     * Exposes the JavaFX property storing the date value.
     * <br/>
     * Useful for binding to view models or other UI controls.
     *
     * @return the observable date property
     */
    public LocalDateProperty dateValueProperty() {
        return dateValue;
    }

    // --- Date Formatter ---

    /**
     * Returns the active formatter used for generating human-friendly text.
     *
     * @return current formatter in use (never {@code null})
     */
    public HumanDateFormatter getDateFormatter() {
        return dateFormatter.get();
    }

    /**
     * Sets a new formatter controlling human-friendly rendering.
     * <br/>
     * This triggers an automatic refresh of the label text.
     *
     * @param formatter the new formatter (must not be {@code null})
     */
    public void setDateFormatter(final HumanDateFormatter formatter) {
        dateFormatter.set(formatter);
    }

    /**
     * Exposes the JavaFX property representing the formatter.
     * <br/>
     * Useful for dynamic UI changes such as language or format switching.
     *
     * @return the observable formatter property
     */
    public ObjectProperty<HumanDateFormatter> dateFormatterProperty() {
        return dateFormatter;
    }
}
