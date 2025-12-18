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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Utility class providing non-deterministic random values for showcase and
 * demonstration purposes.
 *
 * <p>
 * This class is intentionally designed for scenarios where reproducibility is
 * not required, such as UI demos, sample data generation, or visual showcases.
 * It must not be used in contexts that require cryptographic strength or
 * deterministic output.
 * </p>
 *
 * <p>
 * All methods are static and rely on {@link ThreadLocalRandom} to avoid
 * contention in multi-threaded environments.
 * </p>
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public final class RandomUtils {

    /**
     * Lower bound (inclusive) for random date generation, expressed as an epoch day.
     * <p>
     * The date corresponds to {@code 2015-01-01}.
     * </p>
     */
    private static final long from = LocalDate.of(2015, 1, 1).toEpochDay();

    /**
     * Upper bound (exclusive) for random date generation, expressed as an epoch day.
     * <p>
     * The value is evaluated at class initialization time using the current system date.
     * </p>
     */
    private static final long until = LocalDate.now().toEpochDay();

    /**
     * Thread-local random number generator used across all methods.
     */
    private static final Random local = ThreadLocalRandom.current();

    /**
     * Prevents instantiation of this utility class.
     */
    private RandomUtils() {
    }

    /**
     * Generates a random {@link LocalDate} between {@code 2015-01-01} (inclusive)
     * and the current date (exclusive).
     *
     * @return a randomly generated {@link LocalDate}
     */
    public static LocalDate randomDate() {
        return LocalDate.ofEpochDay(local.nextLong(from, until));
    }

    /**
     * Generates a stream of random {@link LocalDate} values.
     *
     * <p>
     * Each date is generated within the same bounds as {@link #randomDate()}.
     * The resulting stream is finite and non-deterministic.
     * </p>
     *
     * @param count the number of dates to generate
     * @return a {@link Stream} of random {@link LocalDate} instances
     */
    public static Stream<LocalDate> randomDates(int count) {
        return local.longs(count, from, until)
                .mapToObj(LocalDate::ofEpochDay);
    }

    /**
     * Generates a random monetary amount.
     *
     * <p>
     * The generated value is between {@code 100.00} and {@code 9,999,999.00},
     * scaled to two decimal places using {@link RoundingMode#HALF_EVEN}.
     * </p>
     *
     * @return a random {@link BigDecimal} representing a monetary amount
     */
    public static BigDecimal randomAmount() {
        return BigDecimal
                .valueOf(new Random().nextDouble(100, 9_999_999))
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     * Generates a stream of random integer values.
     *
     * <p>
     * The stream size is randomly chosen between 3 and 20 elements.
     * Each value is generated in the range {@code [0, 999999)}.
     * </p>
     *
     * @return an {@link IntStream} of random integer values
     */
    public static IntStream randomNumbers() {
        return local.ints(local.nextInt(3, 20), 0, 999999);
    }
}
