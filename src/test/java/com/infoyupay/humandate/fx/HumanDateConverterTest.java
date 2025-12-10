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

import com.infoyupay.humandate.core.Languages;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link HumanDateConverter}.
 * <br/>
 * <p>
 * This suite verifies the expected behavior of both string formatting
 * and parsing functions, focusing on the ergonomic rules of
 * {@code HumanDateParser} and {@code HumanDateFormatter}.
 * <br/>
 * <p>
 * Each test covers a specific UX behavior:
 * default formatting, natural-language parsing, blank input handling,
 * and language-dependent keyword interpretation.
 * <div style="border: 1px solid black; padding: 2px">
 *    <strong>Execution Notes:</strong> dvidal@infoyupay.com passed 6 tests in 0.169s at 2025-12-09 11:13 UTC-5.
 * </div>
 *
 * @author David Vidal, Infoyupay
 * @version 1.0
 */
final class HumanDateConverterTest {

    private final LocalDate sampleDate = LocalDate.of(2024, 6, 19);

    /**
     * Verifies that the default formatter produces the standard dd/MM/yyyy
     * representation for a valid {@link LocalDate}.
     */
    @Test
    void toString_shouldFormatDateUsingDefaultFormatter() {
        var converter = new HumanDateConverter();

        var result = converter.toString(sampleDate);

        assertThat(result).isEqualTo("19/06/2024");
    }

    /**
     * Verifies that formatting a {@code null} input yields {@code null},
     * preserving null-safety.
     */
    @Test
    void toString_shouldReturnNullWhenInputIsNull() {
        var converter = new HumanDateConverter();
        assertThat(converter.toString(null)).isNull();
    }

    /**
     * Ensures that the default parser can interpret a compact numeric
     * representation (ddMMyyyy) without delimiters.
     */
    @Test
    void fromString_shouldParseStringUsingDefaultParser() {
        var converter = new HumanDateConverter();

        var result = converter.fromString("19062024");

        assertThat(result).isEqualTo(sampleDate);
    }

    /**
     * Null-equivalent inputs such as empty or blank strings must result
     * in {@code null}, aligning with ergonomic parsing rules.
     */
    @Test
    void fromString_shouldReturnNullForBlankInput() {
        var converter = new HumanDateConverter();

        assertThat(converter.fromString("")).isNull();
        assertThat(converter.fromString("   ")).isNull();
    }

    /**
     * Validates Spanish natural expressions understood by the parser:
     * <ul>
     *     <li>{@code "hoy"} → today</li>
     *     <li>{@code "mañana"} → tomorrow</li>
     *     <li>{@code "ayer"} → yesterday</li>
     * </ul>
     * These represent common and ergonomic date inputs.
     */
    @Test
    void parser_es_shouldUnderstandHoyAyerManana() {
        var converter = new HumanDateConverter();
        assertThat(converter.fromString("hoy")).isEqualTo(LocalDate.now());
        assertThat(converter.fromString("mañana")).isEqualTo(LocalDate.now().plusDays(1));
        assertThat(converter.fromString("ayer")).isEqualTo(LocalDate.now().minusDays(1));
    }

    /**
     * Validates English natural expressions once the parser language
     * is changed via mutation:
     * <ul>
     *     <li>{@code "now"} → today</li>
     *     <li>{@code "tomorrow"} → tomorrow</li>
     *     <li>{@code "yesterday"} → yesterday</li>
     * </ul>
     * This confirms both the keyword UX behavior and the ability to
     * customize language through getter access.
     */
    @Test
    void parser_en_shouldUnderstandNowYesterdayTomorrow() {
        var converter = new HumanDateConverter();
        converter.languageProperty().set(Languages.en());
        assertThat(converter.fromString("now")).isEqualTo(LocalDate.now());
        assertThat(converter.fromString("tomorrow")).isEqualTo(LocalDate.now().plusDays(1));
        assertThat(converter.fromString("yesterday")).isEqualTo(LocalDate.now().minusDays(1));
    }
}
