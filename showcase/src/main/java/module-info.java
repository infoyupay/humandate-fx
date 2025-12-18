/**
 * JavaFX showcase module for <b>HumanDate-FX</b>.
 *
 * <p>
 * This module provides a self-contained JavaFX application intended to
 * demonstrate and visually validate the integration of the
 * {@code com.infoyupay.humandate.fx} library across common UI controls.
 * </p>
 *
 * <p>
 * The module is <b>not</b> part of the public API surface of HumanDate-FX and
 * is not meant to be used as a runtime dependency. Its sole purpose is to
 * serve as an executable, educational reference.
 * </p>
 *
 * <p>
 * The {@code opens} directives are required to allow JavaFX FXML to perform
 * reflective access to controllers and FX-bound POJOs at runtime.
 * </p>
 *
 * @author David Vidal, Infoyupay
 * @since 1.0
 */
module com.infoyupay.humandate.fx.showcase {

    /* Opens the main showcase package for JavaFX FXML controller resolution. */
    opens com.infoyupay.humandate.fx.showcase;

    /* Opens FX-specific POJOs used by FXML factories and cell bindings. */
    opens com.infoyupay.humandate.fx.showcase.fxpojos to javafx.fxml;

    /* HumanDate-FX JavaFX integration layer. */
    requires com.infoyupay.humandate.fx;

    /* JavaFX UI controls. */
    requires javafx.controls;

    /* JavaFX FXML support. */
    requires javafx.fxml;

    /* Core HumanDate formatting and language support. */
    requires com.infoyupay.humandate.core;
}
