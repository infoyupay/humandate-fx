/**
 * TODO: write documentation.
 *
 * @author David Vidal, Infoyupay
 */
module com.infoyupay.humandate.fx.showcase {
    opens com.infoyupay.humandate.fx.showcase;
    opens com.infoyupay.humandate.fx.showcase.fxpojos to javafx.fxml;
    //TODO:
    requires com.infoyupay.humandate.fx;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.infoyupay.humandate.core;
}