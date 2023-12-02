package application;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import at.favre.lib.crypto.bcrypt.*;

import javafx.scene.control.Hyperlink;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;



public class login_slide_controller {	
	public Pane slidingPane;
	public Button loginButton;
	public Label welLabel;
	public Label username_loLabel;
	public Label pass_loLabel;
	public TextField username_lodetail;
	public PasswordField password_lodetail;
	public Label loginLabel;
	public boolean is_pane_onleft = true;
	public Hyperlink forgotpass;
	
	public Label sighupLabel;
	public Label pass_siLabel;
	public Label username_siLabel;
	public Label email_siLabel;
	public TextField username_sidetail;
	public TextField email_sidetail;
	public PasswordField password_sidetail;
	public Button sighupButton;
	
	public void togglePane(javafx.event.ActionEvent event) {

		TranslateTransition slide = new TranslateTransition(Duration.millis(70), slidingPane);

		if (is_pane_onleft) {
			slide.setToX(289);
			welLabel.setText("Hello Freind!");
			loginButton.setVisible(false);
			loginLabel.setVisible(false);
			username_lodetail.setVisible(false);
			username_loLabel.setVisible(false);
			pass_loLabel.setVisible(false);
			password_lodetail.setVisible(false);
			forgotpass.setOpacity(0);
			is_pane_onleft = false;
			
			sighupButton.setVisible(true);
			pass_siLabel.setVisible(true);
			password_sidetail.setVisible(true);
			username_siLabel.setVisible(true);
			email_siLabel.setVisible(true);
			email_sidetail.setVisible(true);
			username_sidetail.setVisible(true);
			sighupLabel.setVisible(true);
		} else {
			slide.setToX(0);
			is_pane_onleft = true;
			welLabel.setText("Welcome Back!");
			loginButton.setVisible(true);
			loginLabel.setVisible(true);
			username_lodetail.setVisible(true);
			username_loLabel.setVisible(true);
			pass_loLabel.setVisible(true);
			password_lodetail.setVisible(true);
			loginLabel.setVisible(true);
			forgotpass.setOpacity(1);
			
			sighupButton.setVisible(false);
			pass_siLabel.setVisible(false);
			password_sidetail.setVisible(false);
			username_siLabel.setVisible(false);
			email_siLabel.setVisible(false);
			email_sidetail.setVisible(false);
			username_sidetail.setVisible(false);
			sighupLabel.setVisible(false);
		}
		slide.play();
	}
	
	public void login() {
		String useernamString=username_lodetail.getText();
		String password= password_lodetail.getText();
	try {
		useernamString.trim();
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vashatkara","root","root");
		String querry="SELECT * FROM vashatkara.user where username=?;";
		PreparedStatement statement= connection.prepareStatement(querry);
		statement.setString(1, useernamString);
		ResultSet checker=statement.executeQuery();
		username_lodetail.setText("");
		password_lodetail.setText("");
		if (checker.next()) {
			if (BCrypt.verifyer().verify(password.toCharArray(), checker.getNString("password")).verified) {
				Parent root;
				try {
					Stage primaryStage = new Stage();
					java.io.File file=new java.io.File("../vaskhatkara2/src/sds/dashboad.fxml");
					FXMLLoader loader = new
							FXMLLoader(file.toURI().toURL());
							 root = loader.load();
							 sds.Contoller contoller = loader.getController();
							 contoller.setUsername(useernamString);
							 contoller.initialize(null, null);
							 contoller=null;
							 loader=null;
					file=null;
					System.gc();
					Scene scene = new Scene(root);
					primaryStage.setScene(scene);
					primaryStage.show();
					Stage stage = (Stage) loginButton.getScene().getWindow();
					stage.close();
				} catch (IOException e) {
			    e.printStackTrace();
				} 
			} 
			else {
				Dialog<String> dialog = new Dialog<String>();
				 ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
			      dialog.setContentText("wrong username or password");
			      dialog.getDialogPane().getButtonTypes().add(type);
			      dialog.showAndWait();
			}
			checker.close();
			statement.close();
			checker.close();
		}
		else {
			Dialog<String> dialog = new Dialog<String>();
			 ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
		      dialog.setContentText("wrong username or password");
		      dialog.getDialogPane().getButtonTypes().add(type);
		      dialog.showAndWait();
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
	public void sighup() {
		
		if (username_sidetail.getText() == null || username_sidetail.getText().trim().isEmpty()) {
			Dialog<String> dialog = new Dialog<String>();
			 ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
		      dialog.setContentText("plese enter text");
		      dialog.getDialogPane().getButtonTypes().add(type);
		      dialog.showAndWait();
		}
		else {
		  	String usename=username_sidetail.getText().trim();
			String password=BCrypt.withDefaults().hashToString(12, password_sidetail.getText().toCharArray());
			String email=email_sidetail.getText();
			username_sidetail.setText("");
			email_sidetail.setText("");
			password_sidetail.setText("");
			Parent root;
			try {
				Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/vashatkara","root","root");
				PreparedStatement statement=connection.prepareStatement("INSERT INTO `vashatkara`.`user` (`username`, `password`, `email`) VALUES (?, ?, ?);");
				statement.setString(1, usename);
				statement.setString(2, password);
				statement.setString(3, email);
				statement.execute();
				statement=null;
				connection=null;
					Stage primaryStage = new Stage();
					java.io.File file=new java.io.File("../vaskhatkara2/src/sds/dashboad.fxml");
					FXMLLoader loader = new
							FXMLLoader(file.toURI().toURL());
							loader.load();
							 root = loader.getRoot();
							 sds.Contoller contoller = loader.getController();
							 contoller.setUsername(usename);
					file=null;
					Scene scene = new Scene(root);
					primaryStage.setScene(scene);
					primaryStage.show();
					Stage stage = (Stage) loginButton.getScene().getWindow();
					stage.close();
				
				 
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	public void forgotpassword() {
		Parent root;
		try {
			Stage primaryStage=new Stage();
			root = FXMLLoader.load(getClass().getResource("forgotpassword.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
