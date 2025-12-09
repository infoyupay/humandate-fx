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
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A {@link SimpleObjectProperty} specialization for {@link LocalDate} values,
 * providing helper bindings to represent the date as a formatted string.
 * <br/>
 * <p>
 * These bindings update automatically whenever the property value changes,
 * making them useful for UI labels or read-only displays.
 *
 * @author David Vidal, Infoyupay
 * @version 1.0
 */
public final class LocalDateProperty extends SimpleObjectProperty<LocalDate> {

    /**
     * Creates an empty {@code LocalDateProperty} with no initial value.
     * <br/>
     * Useful when the value will be provided later, either via binding
     * or explicit calls to {@link #setValue(Object)}.
     */
    public LocalDateProperty() {
    }

    /**
     * Creates a {@code LocalDateProperty} holding the given initial value.
     *
     * @param localDate initial {@link LocalDate} value, may be {@code null}
     */
    public LocalDateProperty(final LocalDate localDate) {
        super(localDate);
    }

    /**
     * Creates a named {@code LocalDateProperty} associated with a bean.
     * <br/>
     * This is typically used when exposing bean-style properties in custom
     * controls or view-model classes.
     *
     * @param bean the owning bean (may be {@code null})
     * @param name the property name (for debugging and introspection)
     */
    public LocalDateProperty(final Object bean, final String name) {
        super(bean, name);
    }

    /**
     * Creates a named {@code LocalDateProperty} associated with a bean
     * and initialized with a value.
     * <br/>
     * This constructor is ideal when defining a property as part of a
     * custom component, to ensure a meaningful default and metadata.
     *
     * @param bean      the owning bean (may be {@code null})
     * @param name      the property name
     * @param localDate initial value (may be {@code null})
     */
    public LocalDateProperty(final Object bean, final String name, final LocalDate localDate) {
        super(bean, name, localDate);
    }


    /**
     * Creates a string binding based on a provided {@link DateTimeFormatter}.
     *
     * @param formatter Java date-time formatter (must not be null)
     * @return a string binding reflecting the formatted value
     */
    public StringBinding asFormattedString(final DateTimeFormatter formatter) {
        return Bindings.createStringBinding(() -> {
            var val = getValue();
            if (val == null) return "";
            return formatter.format(val);
        }, this);
    }

    /**
     * Creates a string binding using a human-friendly formatter obtained
     * from an {@link ObjectBinding} of {@link HumanDateFormatter}.
     * <br/>
     * Useful when formatter configuration may change at runtime.
     *
     * @param formatterBinding binding providing the active formatter
     * @return a string binding reflecting human-friendly output
     */
    public StringBinding asHumanString(final ObjectBinding<HumanDateFormatter> formatterBinding) {
        return Bindings.createStringBinding(() -> {
            var fmt = formatterBinding.get();
            var val = getValue();
            if (fmt == null || val == null) return "";
            return fmt.apply(val);
        }, formatterBinding, this);
    }

    /**
     * Convenience overload for static human-friendly formatting.
     *
     * @param formatter active formatter
     * @return binding displaying formatted text
     */
    public StringBinding asHumanString(final HumanDateFormatter formatter) {
        return Bindings.createStringBinding(() -> {
            var val = getValue();
            if (val == null) return "";
            return formatter.apply(val);
        }, this);
    }
}
