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

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX application entry point for the HumanDate-FX showcase.
 *
 * <p>
 * This class bootstraps the JavaFX runtime and loads the showcase UI,
 * demonstrating HumanDate integration across multiple JavaFX controls.
 * </p>
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxShowcaseApp extends Application {

    /**
     * Launches the JavaFX application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes and displays the primary application stage.
     *
     * @param primaryStage the primary stage provided by the JavaFX runtime
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("HumanDate-FX Showcase");
        FxShowcaseForm.loadInto(primaryStage);
        primaryStage.show();
    }
}
