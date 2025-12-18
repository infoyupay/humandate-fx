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

import com.infoyupay.humandate.core.LanguageSupport;
import com.infoyupay.humandate.core.Languages;

import java.util.function.Supplier;

/**
 * Enumerates the languages supported by the showcase application and provides
 * a direct bridge between UI selection and {@link LanguageSupport} resolution.
 *
 * <p>
 * This enum is intentionally designed to be used directly from FXML via
 * {@code fx:constant}, allowing each {@link javafx.scene.control.RadioButton}
 * to declare its associated language in a fully declarative way:
 * </p>
 *
 * <pre>{@code
 * <RadioButton text="Español">
 *     <userData>
 *         <SupportedLanguages fx:constant="ES"/>
 *     </userData>
 * </RadioButton>
 * }</pre>
 *
 * <p>
 * At runtime, the controller only needs to read the {@code userData} from the
 * selected toggle, cast it to {@code SupportedLanguages}, and invoke
 * {@link #get()} to obtain the corresponding {@link LanguageSupport} instance.
 * This avoids string-based switches, fragile IDs, or duplicated mapping logic.
 * </p>
 *
 * <p>
 * Each enum constant acts as a lightweight, type-safe factory by implementing
 * {@link java.util.function.Supplier}, delegating the actual language
 * construction to the {@link Languages} utility class.
 * </p>
 *
 * <p>
 * In this version of the library, the showcase ships out of the box with
 * support for:
 * </p>
 *
 * <ul>
 *     <li>Spanish ({@code ES})</li>
 *     <li>English ({@code EN})</li>
 *     <li>Quechua ({@code QUE})</li>
 * </ul>
 *
 * <p>
 * Adding a new language requires only a new enum constant and a corresponding
 * factory method in {@link Languages}, keeping the UI and controller logic
 * unchanged.
 * </p>
 *
 * @author InfoYupay SACS
 * @version 1.0
 * @see LanguageSupport
 * @see Languages
 */
public enum SupportedLanguages implements Supplier<LanguageSupport> {
    /**
     * Spanish language support.
     */
    ES {
        @Override
        public LanguageSupport get() {
            return Languages.es();
        }
    },

    /**
     * English language support.
     */
    EN {
        @Override
        public LanguageSupport get() {
            return Languages.en();
        }
    },

    /**
     * Quechua language support.
     */
    QUE {
        @Override
        public LanguageSupport get() {
            return Languages.que();
        }
    }
}
