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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A {@link Callback} implementation that produces editable {@link ListCell}
 * instances capable of formatting and parsing {@link LocalDate} values using
 * human-friendly rules.
 *
 * <p>
 * This factory configures {@link TextFieldListCell} instances with a
 * {@link HumanDateConverter}. Due to the lifecycle contract of
 * {@link javafx.scene.control.ListView}, changes to the internal state of a
 * cell factory are <b>not</b> propagated to already created cells.
 * </p>
 *
 * <p>
 * For this reason, this factory is intentionally designed as an immutable
 * value object. Whenever the language or formatting rules change, a <b>new</b>
 * instance of {@code HumanDateListCellFactory} must be created and installed
 * via {@link javafx.scene.control.ListView#setCellFactory(Callback)}.
 * </p>
 *
 * <p>
 * This behavior is demonstrated in the HumanDate-FX showcase and ensures that
 * all list cells are recreated with the updated converter configuration.
 * </p>
 *
 * <p><b>Semantic notes:</b><br/>
 * This class intentionally does not provide a custom {@code ListCell}
 * implementation. The underlying editor remains a
 * {@link javafx.scene.control.TextField}, and the responsibility of this
 * factory is strictly limited to adapting formatting and parsing behavior.
 * A specialized date-picker list cell would be a separate component.
 * </p>
 *
 * <h2>Example usage</h2>
 * {@snippet :
 * ListView<LocalDate> listView = new ListView<>();
 * listView.getItems().addAll(LocalDate.now(),
 *                            LocalDate.now().plusDays(1),
 *                            LocalDate.now().minusDays(1));
 *
 * // Initial factory
 * listView.setCellFactory(
 *     new HumanDateListCellFactory(Languages.en(), HumanDateDefaults.DEFAULT_FORMAT)
 * );
 *
 * // Later, when the language changes:
 * listView.setCellFactory(
 *     new HumanDateListCellFactory(Languages.es(), HumanDateDefaults.DEFAULT_FORMAT)
 * );
 *}
 *
 * @param converter the {@link HumanDateConverter} used for formatting and parsing
 * @author David Vidal
 * @version 1.0
 * @see HumanDateConverter
 * @see HumanDateTextFormatter
 * @see javafx.scene.control.cell.TextFieldListCell
 * @see javafx.scene.control.ListCell
 * @see javafx.scene.control.ListView
 */
public record HumanDateListCellFactory(HumanDateConverter converter)
        implements Callback<ListView<LocalDate>, ListCell<LocalDate>> {
    /**
     * Creates a cell factory pre-configured with the given language and format.
     *
     * <p>
     * The provided values are encapsulated in a dedicated
     * {@link HumanDateConverter} instance. To apply different language or
     * formatting rules, a new factory instance must be created.
     * </p>
     *
     * @param language the natural-language rules to apply
     * @param format   the {@link DateTimeFormatter} used for string conversion
     * @throws NullPointerException if either argument is {@code null}
     */
    public HumanDateListCellFactory(final LanguageSupport language,
                                    final DateTimeFormatter format) {
        this(new HumanDateConverter(language, format));
    }

    /**
     * Creates a cell factory using the default HumanDate configuration.
     */
    public HumanDateListCellFactory() {
        this(new HumanDateConverter());
    }

    /**
     * Produces a new {@link TextFieldListCell} configured with the factory's
     * {@link HumanDateConverter}.
     *
     * <p>
     * The converter is explicitly set during item updates and edit start to
     * ensure consistent behavior across cell reuse cycles.
     * </p>
     *
     * @param list the list view requesting a cell
     * @return a configured cell for editing {@link LocalDate} values
     */
    @Override
    public ListCell<LocalDate> call(final ListView<LocalDate> list) {
        return new TextFieldListCell<>() {
            @Override
            public void updateItem(LocalDate localDate, boolean b) {
                super.updateItem(localDate, b);
                setConverter(converter);
            }

            @Override
            public void startEdit() {
                setConverter(converter);
                super.startEdit();
            }
        };
    }
}
