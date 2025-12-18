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
package com.infoyupay.humandate.fx.showcase.fxpojos;

import com.infoyupay.humandate.fx.LocalDateProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * JavaFX domain model used in the humandate-fx showcase.
 * <br/>
 * {@code FxCustomer} represents a simple customer entity whose primary purpose
 * is to demonstrate how {@link LocalDate} values can be rendered in a
 * human-friendly way inside JavaFX controls such as {@code TableView} and
 * {@code TreeTableView}.
 * <br/>
 * <br/>
 * This class is intentionally UI-oriented and minimal:
 * <ul>
 *   <li>It uses JavaFX properties for direct binding with controls.</li>
 *   <li>It contains no business logic.</li>
 *   <li>It serves exclusively as a showcase and documentation model.</li>
 * </ul>
 * <br/>
 * Identity is handled via an internal immutable UUID to ensure stable behavior
 * when instances are used inside observable collections.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxCustomer {

    /**
     * Internal immutable identifier used exclusively for equality and hashing.
     */
    private final UUID _id = UUID.randomUUID();

    /**
     * Customer identification number (e.g. national ID).
     */
    private final StringProperty idNumber =
            new SimpleStringProperty(this, "idNumber");

    /**
     * Customer full name.
     */
    private final StringProperty name =
            new SimpleStringProperty(this, "name");

    /**
     * Customer birthday.
     * <br/>
     * <br/>
     * This property uses {@link LocalDateProperty} in order to demonstrate
     * humandate-fx integration with JavaFX date-based controls.
     */
    private final LocalDateProperty birthday =
            new LocalDateProperty(this, "birthday");

    /**
     * Creates a fully initialized {@code FxCustomer}.
     *
     * @param idNumber customer identification number.
     * @param name     customer full name.
     * @param birthday customer birth date.
     */
    public FxCustomer(String idNumber, String name, LocalDate birthday) {
        setIdNumber(idNumber);
        setName(name);
        setBirthday(birthday);
    }

    /**
     * Provides a static list of sample customers for showcase purposes.
     * <br/>
     * <br/>
     * The returned data set is deterministic and intentionally realistic,
     * allowing JavaFX table-based controls to demonstrate sorting, formatting
     * and human-readable date rendering.
     *
     * @return immutable list of sample {@code FxCustomer} instances.
     */
    public static List<FxCustomer> samples() {

        return List.of(
                new FxCustomer("48573869", "THIAGO HENRÍQUEZ", LocalDate.of(1962, 4, 28)),
                new FxCustomer("17034153", "MARTINA CASTRO", LocalDate.of(1964, 11, 12)),
                new FxCustomer("16817827", "AMELIA MALDONADO", LocalDate.of(1959, 12, 12)),
                new FxCustomer("34687593", "MARTÍN SALAZAR", LocalDate.of(1992, 8, 24)),
                new FxCustomer("12106277", "BAUTISTA ARANDA", LocalDate.of(1974, 9, 30)),
                new FxCustomer("63012932", "DAMIÁN QUIÑÓNEZ", LocalDate.of(1961, 4, 17)),
                new FxCustomer("52788243", "DELFINA SEPÚLVEDA", LocalDate.of(1963, 7, 18)),
                new FxCustomer("72231991", "MARÍA GIRÓN", LocalDate.of(1983, 4, 4)),
                new FxCustomer("13873712", "AGUSTINA ZAYAS", LocalDate.of(2003, 9, 13)),
                new FxCustomer("53933342", "GABRIEL MARROQUÍN", LocalDate.of(1972, 5, 6)),
                new FxCustomer("46635623", "CHRISTOPHER CHAPA", LocalDate.of(1998, 1, 7)),
                new FxCustomer("17070581", "OLIVA MURO", LocalDate.of(2003, 7, 5)),
                new FxCustomer("20402313", "GAEL MURO", LocalDate.of(2000, 6, 25)),
                new FxCustomer("35536645", "JUANA CABALLERO", LocalDate.of(1950, 7, 3)),
                new FxCustomer("95757327", "ALEJANDRA BANDA", LocalDate.of(1977, 12, 1)),
                new FxCustomer("22985229", "CAMILO ÁVALOS", LocalDate.of(1987, 8, 29)),
                new FxCustomer("25074644", "ZOE GÁLVEZ", LocalDate.of(1956, 1, 11)),
                new FxCustomer("58203965", "JUAN MANUEL MOLINA", LocalDate.of(2000, 1, 25)),
                new FxCustomer("19838554", "ISAAC AYALA", LocalDate.of(1965, 6, 21)),
                new FxCustomer("27493809", "VICTORIA PEDRAZA", LocalDate.of(1950, 1, 25)),
                new FxCustomer("92158222", "SOFÍA ORELLANA", LocalDate.of(1997, 8, 15)),
                new FxCustomer("76778510", "GAEL CARRERA", LocalDate.of(1966, 3, 29)),
                new FxCustomer("39720249", "RICARDO ECHEVARRÍA", LocalDate.of(1999, 12, 1)),
                new FxCustomer("70074091", "ALEXA CASÁREZ", LocalDate.of(1984, 10, 19)),
                new FxCustomer("72671798", "RAFAELA SÁENZ", LocalDate.of(1959, 12, 10)),
                new FxCustomer("16458560", "JIMENA ALONSO", LocalDate.of(1978, 3, 30))
        );
    }

    /**
     * Returns the customer identification number.
     *
     * @return current identification number value.
     */
    public final String getIdNumber() {
        return idNumber.get();
    }

    /**
     * Sets the customer identification number.
     *
     * @param idNumber value to assign.
     */
    public final void setIdNumber(String idNumber) {
        this.idNumber.set(idNumber);
    }

    /**
     * JavaFX property representing the customer identification number.
     * <br/>
     * <br/>
     * Exposed for direct binding with JavaFX controls.
     *
     * @return the {@link StringProperty} backing the identification number.
     */
    public final StringProperty idNumberProperty() {
        return idNumber;
    }

    /**
     * Returns the customer full name.
     *
     * @return current name value.
     */
    public final String getName() {
        return name.get();
    }

    /**
     * Sets the customer full name.
     *
     * @param name value to assign.
     */
    public final void setName(String name) {
        this.name.set(name);
    }

    /**
     * JavaFX property representing the customer full name.
     *
     * @return the {@link StringProperty} backing the name.
     */
    public final StringProperty nameProperty() {
        return name;
    }

    /**
     * Returns the customer's birthday.
     *
     * @return birth date value.
     */
    public LocalDate getBirthday() {
        return birthday.get();
    }

    /**
     * Sets the customer's birthday.
     *
     * @param birthday date to assign.
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    /**
     * JavaFX property representing the customer's birthday.
     * <br/>
     * This property is the primary input used in the humandate-fx showcase to
     * demonstrate human-readable date rendering.
     *
     * @return the {@link LocalDateProperty} backing the birthday.
     */
    public LocalDateProperty birthdayProperty() {
        return birthday;
    }

    /**
     * Equality is based on an internal immutable UUID rather than visible fields.
     * <br/>
     * This guarantees stable identity when instances are used inside observable
     * collections and JavaFX table-based controls.
     */
    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof FxCustomer that)) return false;
        return _id.equals(that._id);
    }

    @Override
    public int hashCode() {
        return _id.hashCode();
    }
}
