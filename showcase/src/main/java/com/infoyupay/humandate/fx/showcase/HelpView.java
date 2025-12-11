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

package com.infoyupay.humandate.fx.showcase;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * Visual component that displays a language-specific showcase of parsing examples
 * supported by the HumanDate library. The view renders a two-column grid where
 * the left column shows the input expression and the right column displays its
 * interpreted result according to the selected {@link SupportedLanguages} option.<br/>
 * <br/>
 * This control dynamically rebuilds its internal {@link GridPane} structure when
 * its language property changes. To preserve grid line visibility, the
 * refresh sequence explicitly disables and re-enables grid lines around the
 * reconstruction routine.
 *
 * @author David Vidal, Infoyupay
 * @version 1.0
 */
public final class HelpView extends GridPane {

    /**
     * The language currently associated with the rendered showcase.
     * Defaults to {@link SupportedLanguages#ES}. Changing this property triggers
     * a full grid rebuild.
     */
    private final ObjectProperty<SupportedLanguages> language =
            new SimpleObjectProperty<>(this, "language", SupportedLanguages.ES);

    /**
     * Constructs a new {@code HelpView} instance with the default language
     * ({@link SupportedLanguages#ES}). The initial grid display is generated
     * immediately. Subsequent language changes automatically update the grid.
     */
    public HelpView() {
        generateGrid(SupportedLanguages.ES);
        setGridLinesVisible(true);
        language.addListener((obs, o, n) -> {
            setGridLinesVisible(false);
            generateGrid(n);
            setGridLinesVisible(true);
        });
    }

    /**
     * Returns the currently selected language for this showcase.<br/>
     * This value determines which parsing samples are displayed.
     *
     * @return the active {@link SupportedLanguages} value
     */
    public SupportedLanguages getLanguage() {
        return language.get();
    }

    /**
     * Sets the language that determines which version of the showcase will be rendered.<br/>
     * Assigning a new language results in a full reconstruction of the underlying grid.
     *
     * @param language the new language to display
     */
    public void setLanguage(SupportedLanguages language) {
        this.language.set(language);
    }

    /**
     * Returns the JavaFX property that stores the active language selection.<br/>
     * This enables external binding and observation from FXML or controllers.
     *
     * @return the {@link ObjectProperty} carrying the selected language
     */
    @SuppressWarnings("unused")
    public ObjectProperty<SupportedLanguages> languageProperty() {
        return language;
    }

    /**
     * Rebuilds the entire grid according to the specified language.<br/>
     * Existing nodes are removed and replaced with a fresh set of sample rows.
     *
     * @param language the language variant to generate
     */
    private void generateGrid(SupportedLanguages language) {
        getChildren().clear();
        setPadding(new Insets(5.0));
        switch (language) {
            case ES -> generateES();
            case EN -> generateEN();
            case QUE -> generateQUE();
        }
    }

    /**
     * Generates the Spanish showcase content. Rows are appended sequentially using
     * {@link #addRow(int, String, String)} for clarity and maintainability.
     */
    private void generateES() {
        addRow(0, "Entrada", "Resultado");
        addRow(1, "02", "2 de este mes, de este año.");
        addRow(2, "2", "2 de este mes, de este año.");
        addRow(3, "0405", "4 de mayo, de este año.");
        addRow(4, "040515", "4 de mayo de 2015.");
        addRow(5, "04052015", "4 de mayo de 2015.");
        addRow(6, "1.4.12", "1 de abril de 2012.");
        addRow(7, "1-4-12", "1 de abril de 2012.");
        addRow(8, "1·4·12", "1 de abril de 2012.");
        addRow(9, "1/4/12", "1 de abril de 2012.");
        addRow(10, "01.04.12", "1 de abril de 2012.");
        addRow(11, "01-04-12", "1 de abril de 2012.");
        addRow(12, "01·04·12", "1 de abril de 2012.");
        addRow(13, "01/04/12", "1 de abril de 2012.");
        addRow(14, "1.4.2012", "1 de abril de 2012.");
        addRow(15, "01-4-2012", "1 de abril de 2012.");
        addRow(16, "01·04·2012", "1 de abril de 2012.");
        addRow(17, "1/04/2012", "1 de abril de 2012.");
        addRow(18, "1.4", "1 de abril de este año.");
        addRow(19, "1-4", "1 de abril de este año.");
        addRow(20, "1·4", "1 de abril de este año.");
        addRow(21, "1/4", "1 de abril de este año.");
        addRow(22, "01.04", "1 de abril de este año.");
        addRow(23, "01-04", "1 de abril de este año.");
        addRow(24, "01·04", "1 de abril de este año.");
        addRow(25, "01/04", "1 de abril de este año.");
        addRow(26, "0", "fecha actual");
        addRow(27, "+2", "hoy +2 días.");
        addRow(28, "-4", "hoy -4 días.");
        addRow(29, "+4d", "hoy +4 días.");
        addRow(30, "-4d", "hoy -4 días.");
        addRow(31, "+2s", "hoy +2 semanas.");
        addRow(32, "-2s", "hoy -2 semanas.");
        addRow(33, "+1m", "hoy +1 mes.");
        addRow(34, "-1m", "hoy -1 mes.");
        addRow(35, "+5a", "hoy +5 años.");
        addRow(36, "-5a", "hoy -5 años.");
        addRow(37, "ya", "Fecha actual.");
        addRow(38, "hoy", "Fecha actual.");
        addRow(39, "ahora", "Fecha actual.");
        addRow(40, "mañana", "Fecha de mañana.");
        addRow(41, "ayer", "Fecha de ayer.");
    }

    /**
     * Generates the English showcase content. Rows follow the same structure as
     * {@link #generateES()} for cross-language consistency.
     */
    private void generateEN() {
        addRow(0, "Input", "Output");
        addRow(1, "02", "2 this month, this year.");
        addRow(2, "2", "2 this month, this year.");
        addRow(3, "0405", "may 4th, this year.");
        addRow(4, "040515", "may 4th, 2015.");
        addRow(5, "04052015", "may 4th, 2015.");
        addRow(6, "1.4.12", "april 1st, 2012.");
        addRow(7, "1-4-12", "april 1st, 2012.");
        addRow(8, "1·4·12", "april 1st, 2012.");
        addRow(9, "1/4/12", "april 1st, 2012.");
        addRow(10, "01.04.12", "april 1st, 2012.");
        addRow(11, "01-04-12", "april 1st, 2012.");
        addRow(12, "01·04·12", "april 1st, 2012.");
        addRow(13, "01/04/12", "april 1st, 2012.");
        addRow(14, "1.4.2012", "april 1st, 2012.");
        addRow(15, "01-4-2012", "april 1st, 2012.");
        addRow(16, "01·04·2012", "april 1st, 2012.");
        addRow(17, "1/04/2012", "april 1st, 2012.");
        addRow(18, "1.4", "april 1st, this year.");
        addRow(19, "1-4", "april 1st, this year.");
        addRow(20, "1·4", "april 1st, this year.");
        addRow(21, "1/4", "april 1st, this year.");
        addRow(22, "01.04", "april 1st, this year.");
        addRow(23, "01-04", "april 1st, this year.");
        addRow(24, "01·04", "april 1st, this year.");
        addRow(25, "01/04", "april 1st, this year.");
        addRow(26, "0", "today date");
        addRow(27, "+2", "today +2 days.");
        addRow(28, "-4", "today -4 days.");
        addRow(29, "+4d", "today +4 days.");
        addRow(30, "-4d", "today -4 days.");
        addRow(31, "+2w", "today +2 weeks.");
        addRow(32, "-2w", "today -2 weeks.");
        addRow(33, "+1m", "today +1 month.");
        addRow(34, "-1m", "today -1 month.");
        addRow(35, "+5y", "today +5 years.");
        addRow(36, "-5y", "today -5 years.");
        addRow(37, "now", "Present date.");
        addRow(38, "today", "Present date.");
        addRow(39, "yesterday", "Yesterday.");
        addRow(40, "ytd", "Yesterday.");
        addRow(41, "tomorrow", "Tomorrow.");
        addRow(42, "tmr", "Tomorrow.");
        addRow(43, "tmw", "Tomorrow.");
        addRow(44, "tmrw", "Tomorrow.");
    }

    /**
     * Generates the Quechua showcase content. Adheres to the same two-column layout
     * as the rest of the language variants.
     */
    private void generateQUE() {
        addRow(0, "Haykuna", "Llujsiy");
        addRow(1, "02", "Kay killa 2 p'unchaw (Kunan watapi).");
        addRow(2, "2", "Kay killa 2 p'unchaw (Kunan watapi).");
        addRow(3, "0405", "Kunan watapi 4 p'unchaw Aymuray killa.");
        addRow(4, "040515", "4 Aymuray killa, 2015.");
        addRow(5, "04052015", "4 Aymuray killa, 2015.");
        addRow(6, "1.4.12", "1 Ayriway, 2012.");
        addRow(7, "1-4-12", "1 Ayriway, 2012.");
        addRow(8, "1·4·12", "1 Ayriway, 2012.");
        addRow(9, "1/4/12", "1 Ayriway, 2012.");
        addRow(10, "01.04.12", "1 Ayriway, 2012.");
        addRow(11, "01-04-12", "1 Ayriway, 2012.");
        addRow(12, "01·04·12", "1 Ayriway, 2012.");
        addRow(13, "01/04/12", "1 Ayriway, 2012.");
        addRow(14, "1.4.2012", "1 Ayriway, 2012.");
        addRow(15, "01-4-2012", "1 Ayriway, 2012.");
        addRow(16, "01·04·2012", "1 Ayriway, 2012.");
        addRow(17, "1/04/2012", "1 Ayriway, 2012.");
        addRow(18, "1.4", "Ayriway killa 1 p'unchay (Kunan watapi).");
        addRow(19, "1-4", "Ayriway killa 1 p'unchay (Kunan watapi).");
        addRow(20, "1·4", "Ayriway killa 1 p'unchay (Kunan watapi).");
        addRow(21, "1/4", "Ayriway killa 1 p'unchay (Kunan watapi).");
        addRow(22, "01.04", "Ayriway killa 1 p'unchay (Kunan watapi).");
        addRow(23, "01-04", "Ayriway killa 1 p'unchay (Kunan watapi).");
        addRow(24, "01·04", "Ayriway killa 1 p'unchay (Kunan watapi).");
        addRow(25, "01/04", "Ayriway killa 1 p'unchay (Kunan watapi).");
        addRow(26, "0", "Kunan p'unchay");
        addRow(27, "+2", "kunan +2 p'unchay.");
        addRow(28, "-4", "kunan -4 p'unchay.");
        addRow(29, "+4p", "kunan +4 p'unchay.");
        addRow(30, "-4p", "kunan -4 p'unchay.");
        addRow(31, "+2h", "kunan +2 hunkay.");
        addRow(32, "-2h", "kunan -2 hunkay.");
        addRow(33, "+1k", "kunan +1 killa.");
        addRow(34, "-1k", "kunan -1 killa.");
        addRow(35, "+5w", "kunan +5 wata.");
        addRow(36, "-5w", "kunan -5 wata.");
        addRow(37, "kunan", "Kunan p'unchay.");
        addRow(38, "kaypi", "Kunan p'unchay.");
        addRow(39, "ña", "Kunan p'unchay.");
        addRow(40, "paqarin", "Paqarin p'unchay.");
        addRow(41, "qaya", "Paqarin p'unchay.");
        addRow(42, "haya", "Paqarin p'unchay.");
        addRow(43, "qayna", "Qayna p'unchay.");
        addRow(44, "jainapunchau", "Qayna p'unchay.");
        addRow(45, "qaynunchay", "Qayna p'unchay.");
    }

    /**
     * Appends a single row to the underlying grid. Both input and output columns are
     * created through {@link #prepareLabel(String, boolean)} to ensure consistent sizing
     * and alignment across all language variants.
     *
     * @param row   the target row index
     * @param left  the input expression text
     * @param right the formatted output description
     */
    private void addRow(int row, String left, String right) {
        add(prepareLabel(left, row == 0), 0, row);
        add(prepareLabel(right, row == 0), 1, row);
    }

    /**
     * Prepares a label suitable for placement in a grid cell. Header rows are centered
     * while data rows use default alignment. All labels share a consistent height,
     * width behavior, and padding for readability.
     *
     * @param text   the displayed text
     * @param center whether the label should be center-aligned
     * @return a configured {@link Label} instance
     */
    private Label prepareLabel(String text, boolean center) {
        var r = new Label(text);
        if (center) r.setAlignment(Pos.CENTER);
        r.setMaxWidth(Double.MAX_VALUE);
        r.setMinHeight(Region.USE_PREF_SIZE);
        r.setMaxHeight(Region.USE_PREF_SIZE);
        r.setPrefHeight(32.0);
        r.setPadding(new Insets(0.0, 5.0, 0.0, 5.0));
        return r;
    }
}