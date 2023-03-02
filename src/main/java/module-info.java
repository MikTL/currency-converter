module com.conversior.conversormonedas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.conversior.conversormonedas to javafx.fxml;
    exports com.conversior.conversormonedas;
}