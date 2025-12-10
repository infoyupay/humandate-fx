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

import com.infoyupay.humandate.core.LanguageSupport;
import com.infoyupay.humandate.core.Languages;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static com.infoyupay.humandate.fx.HumanDateDefaults.DEFAULT_FORMAT;

/**
 * A {@link Callback} implementation that produces editable list cells capable
 * of formatting and parsing {@link LocalDate} using human-friendly rules.
 * <br/><br/>
 *
 * <p>
 * This factory configures {@link TextFieldListCell} instances with a
 * {@link HumanDateConverter} whose language and formatting pattern are dynamic,
 * allowing real-time UI updates when these properties change.
 * </p>
 * <br/>
 *
 * <p><b>Semantic notes:</b><br/>
 * This class intentionally does not provide a custom {@code ListCell}
 * implementation, because the underlying editor remains a
 * {@link javafx.scene.control.TextField}. Its responsibility is strictly to
 * adapt formatting/parsing, not to redefine the editing UI (e.g., a calendar
 * popup). A specialized date-picker list-cell would be a separate component.
 * </p>
 * <br/>
 *
 * <h2>Example usage</h2>
 * {@snippet :
 * ListView<LocalDate> listView = new ListView<>();
 * listView.getItems().addAll(LocalDate.now(),
 *                            LocalDate.now().plusDays(1),
 *                            LocalDate.now().minusDays(1));
 *
 * // Enable human-friendly formatting/parsing in the list
 * listView.setCellFactory(new HumanDateListCellFactory());
 *
 * // Change to Spanish and "dd/MM/yyyy" format at runtime
 * var factory =
 *     (HumanDateListCellFactory) listView.getCellFactory();
 * factory.setLanguage(Languages.es());
 * factory.setFormat(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
 *}
 *
 * @author David Vidal
 * @version 1.0
 * @see HumanDateConverter
 * @see HumanDateTextFormatter
 * @see javafx.scene.control.cell.TextFieldListCell
 * @see javafx.scene.control.ListCell
 * @see javafx.scene.control.ListView
 */
public final class HumanDateListCellFactory
        implements Callback<ListView<LocalDate>, ListCell<LocalDate>> {

    private final ObjectProperty<DateTimeFormatter> format =
            new SimpleObjectProperty<>(this, "format", DEFAULT_FORMAT);
    private final ObjectProperty<LanguageSupport> language =
            new SimpleObjectProperty<>(this, "language", Languages.en());
    private ObjectBinding<HumanDateConverter> converter;

    /**
     * Creates a cell factory using English language and "dd/MM/yyyy" format by default.
     * <br/>
     * The underlying converter instance is automatically recreated whenever
     * {@link #languageProperty()} or {@link #formatProperty()} changes.
     */
    public HumanDateListCellFactory() {
        initBindings();
    }

    /**
     * Creates a cell factory pre-configured with the given language and format.
     * <br/><br/>
     * <p>
     * This constructor ensures that the converter binding is initialized
     * <b>after</b> the desired defaults are applied, preventing the creation
     * of multiple underlying converters and reducing start-up overhead.
     * <br/><br/>
     *
     * @param language the natural-language parsing rule to apply
     * @param format   the {@link DateTimeFormatter} used for string conversion
     * @throws NullPointerException if either argument is {@code null}
     */
    public HumanDateListCellFactory(final LanguageSupport language,
                                    final DateTimeFormatter format) {
        this.language.set(Objects.requireNonNull(language));
        this.format.set(Objects.requireNonNull(format));
        initBindings();
    }

    /**
     * Initializes the reactive binding responsible for creating
     * {@link HumanDateConverter} instances whenever the language or format
     * properties change.
     * <br/><br/>
     * <p>
     * This method enforces one-time initialization to preserve the semantic
     * immutability of the converter binding and to prevent accidental
     * reconfiguration outside of constructors.
     *
     * @throws IllegalStateException if the converter is already initialized
     */
    private void initBindings() {
        if (converter != null)
            throw new IllegalStateException("Converter is already initialized.");

        this.converter = Bindings.createObjectBinding(
                () -> new HumanDateConverter(getLanguage(), getFormat()),
                format, language
        );
    }


    /**
     * Produces a new {@link TextFieldTableCell} bound to the live converter property.
     *
     * @param column column requesting a cell
     * @return a configured cell for editing {@link LocalDate} values
     */
    @Override
    public ListCell<LocalDate> call(final ListView<LocalDate> column) {
        var cell = new TextFieldListCell<LocalDate>();
        cell.converterProperty().bind(converter);
        return cell;
    }

    // --- Language Property -------------------------------------------------

    /**
     * Returns the currently active parsing language.
     */
    public LanguageSupport getLanguage() {
        return language.get();
    }

    /**
     * Updates the human-language parsing behavior of cells created by this factory.
     */
    public void setLanguage(final LanguageSupport language) {
        this.language.set(language);
    }

    /**
     * Property enabling human-readable language binding.
     *
     * @return JavaFx Property.
     */
    public ObjectProperty<LanguageSupport> languageProperty() {
        return language;
    }

    // --- Format Property ---------------------------------------------------

    /**
     * Returns the active string representation pattern.
     */
    public DateTimeFormatter getFormat() {
        return format.get();
    }

    /**
     * Sets the formatting rule applied when converting dates to text in cells.
     */
    public void setFormat(final DateTimeFormatter format) {
        this.format.set(format);
    }

    /**
     * Property enabling control of formatting rules at runtime.
     *
     * @return JavaFx Property.
     */
    public ObjectProperty<DateTimeFormatter> formatProperty() {
        return format;
    }
}
