module org.studia.javaproadmin {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
	requires static lombok;
	requires com.google.gson;
	opens org.studia.javaproadmin.entities to com.google.gson;

	opens org.studia.javaproadmin to javafx.fxml;
    exports org.studia.javaproadmin;
    exports org.studia.javaproadmin.controllers;
    opens org.studia.javaproadmin.controllers to javafx.fxml;
}