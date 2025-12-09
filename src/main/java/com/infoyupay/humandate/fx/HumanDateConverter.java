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
 * This converter delegates the formatting to a {@link HumanDateFormatter},
 * and the parsing to a {@link HumanDateParser}. It is designed to integrate
 * seamlessly with JavaFX controls such as {@code TextField}, {@code DatePicker},
 * or any component that requires a bidirectional conversion between
 * {@code LocalDate} and {@code String}.
 * <br/>
 * <p>
 * There are two supported ways to customize its behavior:
 * <ul>
 *     <li><b>Dependency injection:</b> Provide your own formatter and parser
 *         instances through the constructor.</li>
 *     <li><b>Mutable defaults:</b> Use the default constructor and then adjust
 *         the internal {@link HumanDateFormatter} or {@link HumanDateParser}
 *         via their getters (e.g., changing language or rules without replacing
 *         the entire components).</li>
 * </ul>
 * This dual approach enables both simple tweaks (language/pattern change) and full
 * customization when required.
 * <br/>
 * <p>
 * By default, this converter uses:
 * <ul>
 *     <li>A new {@code HumanDateFormatter} with its default pattern.</li>
 *     <li>A {@code HumanDateParser} configured with Spanish support ({@link Languages#es()}).</li>
 * </ul>
 * These defaults match common usage in Spanish-speaking environments.
 * <br/>
 * <p><b>Example usage:</b></p>
 * {@snippet :
 * import com.infoyupay.humandate.fx.HumanDateConverter;
 * import com.infoyupay.humandate.core.Languages;
 * import javafx.scene.control.DatePicker;
 *
 * DatePicker dp = new DatePicker();
 * HumanDateConverter converter = new HumanDateConverter();
 * // Adjust only the parser language (no need to replace the whole parser)
 * converter.getParser().setLanguage(Languages.en());
 * dp.setConverter(converter);
 *}
 *
 * @author David Vidal, Infoyupay
 * @version 1.0
 */
public final class HumanDateConverter extends StringConverter<LocalDate> {

    private final SimpleObjectProperty<HumanDateFormatter> formatter
            = new SimpleObjectProperty<>(this, "formatter");
    private final SimpleObjectProperty<HumanDateParser> parser
            = new SimpleObjectProperty<>(this, "parser");

    /**
     * Creates a converter with the given formatter and parser.
     * Both arguments must be non-null.
     *
     * @param formatter the formatter used to convert a date to string
     * @param parser    the parser used to convert a string to date
     */
    public HumanDateConverter(HumanDateFormatter formatter, HumanDateParser parser) {
        this.formatter.set(Objects.requireNonNull(formatter));
        this.parser.set(Objects.requireNonNull(parser));
    }

    /**
     * Creates a converter with default components:
     * <br/>
     * <ul>
     *     <li>a new {@code HumanDateFormatter}</li>
     *     <li>a new {@code HumanDateParser} configured with {@code Languages.es()}</li>
     * </ul>
     */
    public HumanDateConverter() {
        this(new HumanDateFormatter(), new HumanDateParser().setLanguage(Languages.es()));
    }


    /**
     * Creates a {@code HumanDateConverter} configured for Spanish.
     * <br/>
     * <p>
     * This factory uses a new {@link HumanDateFormatter} with its default
     * pattern and a {@link HumanDateParser} configured with
     * {@link Languages#es()}.
     *
     * @return a converter using Spanish language rules
     */
    public static HumanDateConverter es() {
        return new HumanDateConverter(
                new HumanDateFormatter(),
                new HumanDateParser().setLanguage(Languages.es())
        );
    }

    /**
     * Creates a {@code HumanDateConverter} configured for English.
     * <br/>
     * <p>
     * This factory uses a new {@link HumanDateFormatter} with its default
     * pattern and a {@link HumanDateParser} configured with
     * {@link Languages#en()}.
     *
     * @return a converter using English language rules
     */
    public static HumanDateConverter en() {
        return new HumanDateConverter(
                new HumanDateFormatter(),
                new HumanDateParser().setLanguage(Languages.en())
        );
    }

    /**
     * Creates a {@code HumanDateConverter} configured for Quechua.
     * <br/>
     * <p>
     * This factory uses a new {@link HumanDateFormatter} with its default
     * pattern and a {@link HumanDateParser} configured with
     * {@link Languages#que()}.
     *
     * @return a converter using Quechua language rules
     */
    public static HumanDateConverter que() {
        return new HumanDateConverter(
                new HumanDateFormatter(),
                new HumanDateParser().setLanguage(Languages.que())
        );
    }

    /**
     * Converts the given {@link LocalDate} into a human-readable text using the configured
     * formatter. If {@code localDate} is {@code null}, this method returns {@code null}.
     *
     * @param localDate the date to convert, or {@code null}
     * @return formatted text or {@code null}
     */
    @Override
    public String toString(LocalDate localDate) {
        return getFormatter().apply(localDate);
    }

    /**
     * Parses the given string into a {@link LocalDate} using the configured parser.
     * If {@code s} is {@code null} or blank, this method returns {@code null}.
     *
     * @param s the string to parse, may be blank
     * @return parsed date or {@code null}
     */
    @Override
    public LocalDate fromString(String s) {
        return getParser().apply(s);
    }

    /**
     * Returns the formatter instance used by this converter.
     *
     * @return the {@link HumanDateFormatter} in use
     */
    public HumanDateFormatter getFormatter() {
        return formatter.get();
    }

    /**
     * Returns the parser instance used by this converter.
     *
     * @return the {@link HumanDateParser} in use
     */
    public HumanDateParser getParser() {
        return parser.get();
    }

    public void setFormatter(HumanDateFormatter formatter){
        this.formatter.setValue(formatter);
    }

    public void setParser(HumanDateParser parser){
        this.parser.setValue(parser);
    }

    public ObjectProperty<HumanDateParser> parserProperty(){
        return parser;
    }

    public ObjectProperty<HumanDateFormatter> formatterProperty(){
        return formatter;
    }

}
