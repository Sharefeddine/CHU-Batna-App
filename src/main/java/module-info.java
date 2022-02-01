module com.example.chu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
	requires javafx.autocomplete.field;
	requires itextpdf;
	requires PDFViewerFX;
	requires javafx.web;
	requires org.apache.commons.io;
	requires controlsfx;
	requires java.prefs;
    opens com.example.chu to javafx.fxml;
    exports com.example.chu;
}