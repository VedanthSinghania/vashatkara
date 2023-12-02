module vaskhatkara2 {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires bcrypt;
	requires jfoenix;
	requires at.favre.lib.bytes;
	requires java.desktop;
	requires javafx.base;
	exports sds;
	
	opens application to javafx.graphics, javafx.fxml;
}
