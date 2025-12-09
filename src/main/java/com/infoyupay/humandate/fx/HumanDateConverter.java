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
import com.infoyupay.humandate.core.HumanDateParser;
import com.infoyupay.humandate.core.LanguageSupport;
import com.infoyupay.humandate.core.Languages;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * A {@link StringConverter} implementation that bridges a {@link LocalDate}
 * value with its human-friendly string representation.
 * <br/>
 * <p>
 * This converter delegates the formatting to an internal {@link HumanDateFormatter},
 * and the parsing to an internal {@link HumanDateParser}. It is designed to integrate
 * seamlessly with JavaFX controls such as {@code TextField}, {@code DatePicker},
 * or any component that requires a bidirectional conversion between
 * {@code LocalDate} and {@code String}.
 * <br/>
 * <p>
 * Its behavior can be adapted dynamically through two observable JavaFX
 * properties:
 * <ul>
 *     <li>{@link #languageProperty() language}: controls natural-language parsing rules</li>
 *     <li>{@link #formatProperty() format}: controls string formatting rules</li>
 * </ul>
 * These properties govern the internal components, ensuring that any change has
 * immediate effect without replacing this converter or any attached
 * {@link HumanDateTextFormatter}.
 * <br/>
 * <p><b>Important:</b> If either property is <em>bound</em> to another property,
 * mutating it via {@code withLanguage(...)} or {@code withFormat(...)} will trigger
 * an {@link IllegalStateException}. In that case, unbind first.
 * <br/>
 * <p><b>Example usage:</b></p>
 * {@snippet :
 * var converter = HumanDateConverter.es();
 * converter.withLanguage(Languages.en())
 *          .withFormat(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
 *}
 * <br/>
 * <p><b>FXML usage:</b></p>
 * {@snippet lang = "fxml":
 * <TextField>
 *     <textFormatter>
 *         <HumanDateTextFormatter fx:factory="es"/>
 *     </textFormatter>
 * </TextField>
 *}
 *
 * @author David Vidal
 * @version 1.3
 */
public final class HumanDateConverter extends StringConverter<LocalDate> {

    /**
     * Default formatting rule used when no custom format is provided.
     * <br/>
     * Pattern: {@code dd/MM/yyyy}
     */
    private static final DateTimeFormatter DEFAULT_FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Internal formatter instance used for rendering text output.
     * <br/>
     * This component is updated automatically when the
     * {@link #formatProperty() format} property changes.
     */
    private final HumanDateFormatter formatter =
            new HumanDateFormatter().withFormatter(DEFAULT_FORMAT);

    /**
     * Internal parser instance used for interpreting input text.
     * <br/>
     * This component is updated automatically when the
     * {@link #languageProperty() language} property changes.
     */
    private final HumanDateParser parser =
            new HumanDateParser().setLanguage(Languages.es());

    /**
     * Language used to interpret human-friendly expressions such as
     * {@code "hoy"}, {@code "mañana"}, {@code "now"}, {@code "yesterday"}.
     * <br/>
     * Mutating this property updates the internal parser accordingly.
     */
    private final ObjectProperty<LanguageSupport> language =
            new SimpleObjectProperty<>(this, "language", Languages.es()) {
                @Override
                public void set(final LanguageSupport lang) {
                    parser.setLanguage(lang);
                    super.set(lang);
                }
            };

    /**
     * Formatting rule used for textual representation.
     * <br/>
     * Mutating this property updates the internal formatter accordingly.
     */
    private final ObjectProperty<DateTimeFormatter> format =
            new SimpleObjectProperty<>(this, "format", DEFAULT_FORMAT) {
                @Override
                public void set(final DateTimeFormatter dtf) {
                    formatter.withFormatter(dtf);
                    super.set(dtf);
                }
            };

    /**
     * Creates a converter with specific initial language and formatting rule.
     *
     * @param lang language configuration
     * @param dtf  formatting rule
     */
    public HumanDateConverter(final LanguageSupport lang,
                              final DateTimeFormatter dtf) {
        this.language.set(Objects.requireNonNull(lang));
        this.format.set(Objects.requireNonNull(dtf));

        parser.setLanguage(lang);
        formatter.withFormatter(dtf);
    }

    /**
     * Creates a converter with Spanish language and default formatting rule.
     */
    public HumanDateConverter() {
        this(Languages.es(), DEFAULT_FORMAT);
    }

    // --- Static factories for FXMLLoader usage ---

    /**
     * @return converter configured for Spanish
     */
    public static HumanDateConverter es() {
        return new HumanDateConverter(Languages.es(), DEFAULT_FORMAT);
    }

    /**
     * @return converter configured for English
     */
    public static HumanDateConverter en() {
        return new HumanDateConverter(Languages.en(), DEFAULT_FORMAT);
    }

    /**
     * @return converter configured for Quechua
     */
    public static HumanDateConverter que() {
        return new HumanDateConverter(Languages.que(), DEFAULT_FORMAT);
    }

    // --- Conversion overrides ---

    /**
     * Converts a date into human-friendly text using the configured formatter.
     * If {@code localDate} is {@code null}, returns {@code null}.
     */
    @Override
    public String toString(final LocalDate localDate) {
        return formatter.apply(localDate);
    }

    /**
     * Parses the given string into a {@link LocalDate}.
     * If {@code s} is {@code null} or blank, returns {@code null}.
     */
    @Override
    public LocalDate fromString(final String s) {
        return parser.apply(s);
    }

    // --- Language property access ---

    /**
     * Current language used for human-friendly parsing.
     *
     * @return current language configuration
     */
    public LanguageSupport getLanguage() {
        return language.get();
    }

    /**
     * Language property for binding in JavaFX.
     *
     * @return mutable JavaFX property for language rules
     */
    public ObjectProperty<LanguageSupport> languageProperty() {
        return language;
    }

    /**
     * Fluent setter variant for language configuration.
     * <br/>
     * Notes: if {@link #languageProperty()} is currently bound, this will trigger
     * {@link IllegalStateException}.
     *
     * @param lang new language
     * @return this instance, for method chaining
     */
    public HumanDateConverter withLanguage(final LanguageSupport lang) {
        this.language.setValue(Objects.requireNonNull(lang));
        return this;
    }

    // --- Format property access ---

    /**
     * Current pattern rule for formatting.
     *
     * @return current {@link DateTimeFormatter}
     */
    public DateTimeFormatter getFormat() {
        return format.get();
    }

    /**
     * Format property for binding in JavaFX.
     *
     * @return mutable JavaFX property for formatting rule
     */
    public ObjectProperty<DateTimeFormatter> formatProperty() {
        return format;
    }

    /**
     * Fluent setter variant for formatting configuration.
     * <br/>
     * Notes: if {@link #formatProperty()} is currently bound, this will trigger
     * {@link IllegalStateException}.
     *
     * @param dtf new pattern formatter
     * @return this instance, for method chaining
     */
    public HumanDateConverter withFormat(final DateTimeFormatter dtf) {
        this.format.setValue(Objects.requireNonNull(dtf));
        return this;
    }
}
