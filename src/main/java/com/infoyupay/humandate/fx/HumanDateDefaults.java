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

import java.time.format.DateTimeFormatter;

/**
 * Library-wide default constants for date formatting.
 *
 * @author David Vidal, Infoyupay
 * @version 1.0
 */
public final class HumanDateDefaults {
    /**
     * Default date format for human-readable date strings.<br>
     * <strong>Format:</strong> {@code "dd/MM/yyyy"}
     */
    public static final DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Prevents instantiation.
     */
    private HumanDateDefaults() {
    }

}
