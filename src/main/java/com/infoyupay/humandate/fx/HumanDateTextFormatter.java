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

import javafx.scene.control.TextFormatter;

import java.time.LocalDate;

/**
 * A {@link TextFormatter} specialization that uses {@link HumanDateConverter}
 * to provide ergonomic typing of dates within text-based controls.
 * <br/>
 * <p>
 * This formatter can be applied to {@code TextField} or similar controls to allow
 * free-form date entry while still maintaining a conversion contract to
 * {@link LocalDate}. Parsing and formatting rules are driven by the underlying
 * {@code HumanDateConverter}.
 * <br/>
 * <p>
 * Usage example (Java):
 * {@snippet :
 * var textField = new TextField();
 * textField.setTextFormatter(new HumanDateTextFormatter());
 *}
 * <br/>
 * Usage example (FXML with Spanish defaults):
 * {@snippet lang = "fxml":
 * <TextField>
 *     <textFormatter>
 *         <HumanDateTextFormatter fx:factory="es"/>
 *     </textFormatter>
 * </TextField>
 *}
 *
 * @author David Vidal, Infoyupay
 * @version 1.0
 */
public class HumanDateTextFormatter extends TextFormatter<LocalDate> {

    /**
     * Creates a formatter using the provided {@link HumanDateConverter}.
     *
     * @param converter the converter controlling parsing and formatting behavior
     */
    public HumanDateTextFormatter(HumanDateConverter converter) {
        super(converter);
    }

    /**
     * Creates a formatter using the Spanish default converter:
     * <br/>
     * {@link HumanDateConverter#es()}
     */
    public HumanDateTextFormatter() {
        this(new HumanDateConverter());
    }

    /**
     * Factory for a Spanish-enabled formatter.
     *
     * @return a new {@code HumanDateTextFormatter} using {@code es()} converter
     */
    public static HumanDateTextFormatter es() {
        return new HumanDateTextFormatter(HumanDateConverter.es());
    }

    /**
     * Factory for an English-enabled formatter.
     *
     * @return a new {@code HumanDateTextFormatter} using {@code en()} converter
     */
    public static HumanDateTextFormatter en() {
        return new HumanDateTextFormatter(HumanDateConverter.en());
    }

    /**
     * Factory for a Quechua-enabled formatter.
     *
     * @return a new {@code HumanDateTextFormatter} using {@code que()} converter
     */
    public static HumanDateTextFormatter que() {
        return new HumanDateTextFormatter(HumanDateConverter.que());
    }
}
