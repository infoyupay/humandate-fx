/**
 * JavaFX integration module for the HumanDate library.
 * <br/><br/>
 * <p>
 * Provides UI components and utilities for working with {@link java.time.LocalDate}
 * values in a human-friendly manner, including:
 * <ul>
 *     <li>{@code StringConverter} support via {@code HumanDateConverter}</li>
 *     <li>Editable text formatting via {@code HumanDateTextFormatter}</li>
 *     <li>Convenience controls such as {@code HumanDateLabel}</li>
 *     <li>Cell factories for {@code ListView}, {@code TableView} and
 *         {@code TreeTableView} that support natural-language parsing</li>
 * </ul>
 *
 * <p>
 * This module requires JavaFX controls and the {@code humandate-core} module.
 * Default behavior uses Spanish language rules, with language and formatting
 * fully configurable at runtime.
 * </p>
 *
 * @author David Vidal, Infoyupay
 */
module com.infoyupay.humandate.fx {
    exports com.infoyupay.humandate.fx;

    requires javafx.base;
    requires javafx.controls;
    requires com.infoyupay.humandate.core;
}