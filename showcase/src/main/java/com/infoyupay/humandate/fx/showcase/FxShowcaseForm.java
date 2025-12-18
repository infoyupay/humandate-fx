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

import com.infoyupay.humandate.core.HumanDateFormatter;
import com.infoyupay.humandate.fx.*;
import com.infoyupay.humandate.fx.showcase.fxpojos.FxCustomer;
import com.infoyupay.humandate.fx.showcase.fxpojos.FxInvoice;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.LocalDate;
import java.util.Objects;

/**
 * JavaFX controller for the HumanDate showcase.
 *
 * <p>
 * This controller coordinates the interaction between the UI controls declared
 * in {@code showcase.fxml} and the HumanDate formatting infrastructure. Its main
 * responsibility is to propagate the currently selected language to all
 * HumanDate-aware controls in the scene.
 * </p>
 *
 * <p>
 * The showcase demonstrates HumanDate integration across multiple JavaFX
 * components, including DatePicker, TextField, Label, Hyperlink, TableView,
 * TreeTableView and ListView.
 * </p>
 *
 * <p>
 * Language selection is handled via a {@link ToggleGroup} whose toggles store
 * {@link SupportedLanguages} instances as {@code userData}, enabling a fully
 * declarative and type-safe mapping from FXML to controller logic.
 * </p>
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxShowcaseForm {

    /**
     * Localized text used as the title of the help tab.
     */
    private final ReadOnlyStringWrapper helpText =
            new ReadOnlyStringWrapper(this, "helpText", "Ayuda");

    /**
     * Date value backing the hyperlink example.
     *
     * <p>
     * This property demonstrates the intended usage of {@link LocalDateProperty}
     * as a bindable source for human-readable date representations.
     * </p>
     */
    private final LocalDateProperty hyperlinkDate =
            new LocalDateProperty(this, "hyperlinkDate", LocalDate.now());

    /**
     * Human-readable string representation of {@link #hyperlinkDate}.
     */
    private final ReadOnlyStringWrapper hyperlinkText =
            new ReadOnlyStringWrapper(this, "hyperlinkText");

    /**
     * TreeTableView showcasing hierarchical HumanDate rendering.
     */
    @FXML
    private TreeTableView<FxInvoice> treeTable;

    /**
     * Cell factory used to render HumanDate values in the TreeTableView.
     */
    @FXML
    private HumanDateTreeTableCellFactory<FxInvoice> treeTableCellFactory;

    /**
     * ListView demonstrating HumanDate rendering in list cells.
     */
    @FXML
    private ListView<LocalDate> listView;

    /**
     * TableView showcasing editable HumanDate columns.
     */
    @FXML
    private TableView<FxCustomer> table;

    /**
     * Cell factory used to render HumanDate values in the TableView.
     */
    @FXML
    private HumanDateTableCellFactory<FxCustomer> tableCellFactory;

    /**
     * Label rendering a HumanDate value using the selected language.
     */
    @FXML
    private HumanDateLabel lblDate;

    /**
     * TextFormatter used by the HumanDate TextField example.
     */
    @FXML
    private HumanDateTextFormatter textFieldFormatter;

    /**
     * Converter used by the HumanDate DatePicker example.
     */
    @FXML
    private HumanDateConverter datePickerConverter;

    /**
     * Embedded help view displaying language-specific documentation.
     */
    @FXML
    private HelpView help;

    /**
     * ToggleGroup controlling the currently selected language.
     */
    @FXML
    private ToggleGroup tgpLanguage;

    /**
     * Scene instance associated with this controller.
     */
    @FXML
    private Scene scene;

    /**
     * Loads the showcase UI and installs it into the given {@link Stage}.
     *
     * @param stage the stage into which the showcase scene will be set
     */
    public static void loadInto(Stage stage) {
        stage.setScene(load().scene);
    }

    /**
     * Loads the showcase FXML and returns its controller instance.
     *
     * @return the initialized {@link FxShowcaseForm} controller
     * @throws UncheckedIOException if the FXML cannot be loaded
     */
    public static FxShowcaseForm load() {
        try {
            var resource = FxShowcaseForm.class.getResource("showcase.fxml");
            var loader = new FXMLLoader(Objects
                    .requireNonNull(resource, "Resource showcase.fxml not found."));
            loader.load();
            return loader.getController();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Returns the current human-readable hyperlink text.
     *
     * @return the current hyperlink text value
     */
    public final String getHyperlinkText() {
        return hyperlinkText.get();
    }

    /**
     * Read-only JavaFX property exposing the hyperlink text.
     *
     * @return a read-only string property
     */
    @SuppressWarnings("unused")
    public final ReadOnlyStringProperty hyperlinkTextProperty() {
        return hyperlinkText.getReadOnlyProperty();
    }

    /**
     * Returns the current help tab title.
     *
     * @return the help tab title
     */
    public final String getHelpText() {
        return helpText.get();
    }

    /**
     * Read-only JavaFX property exposing the help tab title.
     *
     * @return a read-only string property
     */
    @SuppressWarnings("unused")
    public final ReadOnlyStringProperty helpTextProperty() {
        return helpText.getReadOnlyProperty();
    }

    /**
     * Returns the current hyperlink date value.
     *
     * @return the current {@link LocalDate}
     */
    public LocalDate getHyperlinkDate() {
        return hyperlinkDate.get();
    }

    /**
     * Updates the hyperlink date value.
     *
     * @param hyperlinkDate the new date value
     */
    public void setHyperlinkDate(LocalDate hyperlinkDate) {
        this.hyperlinkDate.set(hyperlinkDate);
    }

    /**
     * JavaFX property backing the hyperlink date example.
     *
     * @return the {@link LocalDateProperty}
     */
    @SuppressWarnings("unused")
    public LocalDateProperty hyperlinkDateProperty() {
        return hyperlinkDate;
    }

    /**
     * Initializes the controller after FXML loading.
     *
     * <p>
     * This method wires the language selection listener, establishes property
     * bindings, and populates sample data for the showcase controls.
     * </p>
     */
    @FXML
    void initialize() {
        tgpLanguage.selectedToggleProperty()
                .addListener((v, o, n) -> onToggleSelection(n));

        hyperlinkText.bind(
                hyperlinkDate.asHumanString(new HumanDateFormatter())
        );

        table.getItems().setAll(FxCustomer.samples());

        listView.setCellFactory(new HumanDateListCellFactory());
        RandomUtils.randomDates(50).forEach(listView.getItems()::add);
    }

    /**
     * Handles language selection changes triggered by the ToggleGroup.
     *
     * <p>
     * When a new {@link SupportedLanguages} value is selected, all HumanDate-aware
     * components are updated to reflect the chosen language and the UI is refreshed.
     * </p>
     *
     * @param selected the newly selected toggle
     */
    private void onToggleSelection(Toggle selected) {
        if (selected != null
                && selected.getUserData() instanceof SupportedLanguages lng) {

            help.setLanguage(lng);

            switch (lng) {
                case EN -> helpText.set("Help");
                case ES -> helpText.set("Ayuda");
                case QUE -> helpText.set("Yanapa");
            }

            datePickerConverter.withLanguage(lng.get());
            textFieldFormatter.withLanguage(lng.get());
            tableCellFactory.setLanguage(lng.get());

            listView.setCellFactory(
                    new HumanDateListCellFactory(
                            lng.get(),
                            HumanDateDefaults.DEFAULT_FORMAT
                    )
            );

            treeTableCellFactory.setLanguage(lng.get());

            table.refresh();
            listView.refresh();
            treeTable.refresh();
        }
    }

    /**
     * Handles clicks on the label hyperlink example.
     *
     * <p>
     * Assigns a new random date to the {@link HumanDateLabel}.
     * </p>
     */
    @FXML
    void linkLabelClicked() {
        lblDate.setDateValue(RandomUtils.randomDate());
    }

    /**
     * Handles activation of the hyperlink field example.
     *
     * <p>
     * Assigns a new random date to the hyperlink date property.
     * </p>
     */
    public void hyperlinkFieldAction() {
        setHyperlinkDate(RandomUtils.randomDate());
    }
}
