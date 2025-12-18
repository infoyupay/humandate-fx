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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static com.infoyupay.humandate.fx.HumanDateDefaults.DEFAULT_FORMAT;

/**
 * A {@link Callback} implementation that produces editable {@link TableCell}
 * instances capable of formatting and parsing {@link LocalDate} values using
 * human-friendly rules.
 *
 * <p>
 * This factory configures {@link TextFieldTableCell} instances with a
 * {@link HumanDateConverter} whose language and formatting pattern are
 * <b>reactively updated</b>. {@link javafx.scene.control.TableView} invokes
 * {@code TableCell.updateItem(Object, boolean)} during its normal lifecycle,
 * allowing changes to the factory's internal properties to be reflected in
 * already created cells.
 * </p>
 *
 * <p>
 * As a result, this factory is intentionally designed as a <b>mutable</b>
 * component. Updates to {@link #languageProperty()} or
 * {@link #formatProperty()} automatically recreate the underlying
 * {@link HumanDateConverter} and are propagated to visible cells without
 * requiring the factory to be replaced.
 * </p>
 *
 * <p><b>Semantic notes:</b><br/>
 * This class does not provide a custom {@code TableCell} implementation.
 * The underlying editor remains a {@link javafx.scene.control.TextField}.
 * Its responsibility is strictly to adapt formatting and parsing behavior,
 * not to redefine the editing UI (e.g., a calendar popup). A specialized
 * date-picker table cell would be a separate component.
 * </p>
 *
 * <h2>Example usage</h2>
 * {@snippet :
 * TableColumn<Person, LocalDate> birthColumn =
 *         new TableColumn<>("Birth date");
 * birthColumn.setCellValueFactory(
 *         new PropertyValueFactory<>("birthDate")
 * );
 *
 * HumanDateTableCellFactory<Person> factory =
 *         new HumanDateTableCellFactory<>();
 * birthColumn.setCellFactory(factory);
 *
 * // Update language at runtime (cells refresh automatically)
 * factory.setLanguage(Languages.es());
 *}
 *
 * @param <S> the type of items contained within the {@link javafx.scene.control.TableView}
 * @author David Vidal, Infoyupay
 * @version 1.0
 * @see HumanDateConverter
 * @see HumanDateTextFormatter
 * @see javafx.scene.control.TableCell
 * @see javafx.scene.control.cell.TextFieldTableCell
 */
public final class HumanDateTableCellFactory<S>
        implements Callback<TableColumn<S, LocalDate>, TableCell<S, LocalDate>> {

    /**
     * Formatting rule used to render {@link LocalDate} values as text.
     */
    private final ObjectProperty<DateTimeFormatter> format =
            new SimpleObjectProperty<>(this, "format", DEFAULT_FORMAT);

    /**
     * Natural language rules applied when parsing and formatting dates.
     */
    private final ObjectProperty<LanguageSupport> language =
            new SimpleObjectProperty<>(this, "language", Languages.es());

    /**
     * Reactive binding that recreates the {@link HumanDateConverter} whenever
     * the active language or format changes.
     */
    private ObjectBinding<HumanDateConverter> converter;

    /**
     * Creates a cell factory using the default language and format.
     *
     * <p>
     * The underlying {@link HumanDateConverter} is recreated automatically
     * whenever the {@link #languageProperty()} or {@link #formatProperty()}
     * changes.
     * </p>
     */
    public HumanDateTableCellFactory() {
        initBindings();
    }

    /**
     * Creates a cell factory pre-configured with the given language and format.
     *
     * <p>
     * Property values are applied before initializing the reactive converter
     * binding, ensuring consistent startup behavior.
     * </p>
     *
     * @param language the natural-language parsing rules to apply
     * @param format   the {@link DateTimeFormatter} used for string conversion
     * @throws NullPointerException if either argument is {@code null}
     */
    public HumanDateTableCellFactory(final LanguageSupport language,
                                     final DateTimeFormatter format) {
        this.language.set(Objects.requireNonNull(language));
        this.format.set(Objects.requireNonNull(format));
        initBindings();
    }

    /**
     * Initializes the reactive binding responsible for creating
     * {@link HumanDateConverter} instances whenever the language or format
     * properties change.
     *
     * <p>
     * This method is invoked exclusively from constructors and enforces
     * one-time initialization to prevent accidental rebinding.
     * </p>
     *
     * @throws IllegalStateException if the converter binding is already initialized
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
     * Produces a new {@link TextFieldTableCell} configured with the
     * currently active {@link HumanDateConverter}.
     *
     * <p>
     * The converter is reassigned during item updates to ensure that
     * changes to language or format are reflected in reused cells.
     * </p>
     *
     * @param column the column requesting a cell
     * @return a configured cell for editing {@link LocalDate} values
     */
    @Override
    public TableCell<S, LocalDate> call(final TableColumn<S, LocalDate> column) {
        return new TextFieldTableCell<>(converter.get()) {
            @Override
            public void updateItem(LocalDate localDate, boolean b) {
                super.updateItem(localDate, b);
                setConverter(getConverter());
            }
        };
    }

    /**
     * Returns the currently active parsing language.
     *
     * @return the active {@link LanguageSupport}
     */
    public LanguageSupport getLanguage() {
        return language.get();
    }

    /**
     * Updates the human-language parsing behavior of cells created by this factory.
     *
     * @param language the new language to apply
     */
    public void setLanguage(final LanguageSupport language) {
        this.language.set(language);
    }

    /**
     * Property enabling reactive control of the parsing language.
     *
     * @return a JavaFX property representing the active language
     */
    public ObjectProperty<LanguageSupport> languageProperty() {
        return language;
    }

    /**
     * Returns the active string formatting pattern.
     *
     * @return the active {@link DateTimeFormatter}
     */
    public DateTimeFormatter getFormat() {
        return format.get();
    }

    /**
     * Sets the formatting rule applied when converting dates to text in cells.
     *
     * @param format the formatter to apply
     */
    public void setFormat(final DateTimeFormatter format) {
        this.format.set(format);
    }

    /**
     * Property enabling reactive control of formatting rules at runtime.
     *
     * @return a JavaFX property representing the active formatter
     */
    public ObjectProperty<DateTimeFormatter> formatProperty() {
        return format;
    }
}
