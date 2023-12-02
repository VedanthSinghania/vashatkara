package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Random;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class fp_controller {
	public TextField username;
	public TextField otp;
	 public TextField password;
	 public Label imform;
	 public Button username_but;
	 public Button otp_but;
	 public Button password_buttonButton;
	String email;
	public void getuser() {
		String user= username.getText();
		username.setText(null);
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vashatkara","root","root");
			Statement statemet=con.createStatement();
			ResultSet rSet= statemet.executeQuery("SELECT * FROM vashatkara.user where username='"+user+"'");
			if (rSet.next()) {
				email=rSet.getNString("email");
				otp.setVisible(true);
				otp_but.setVisible(true);
				sendotp(email);
			}
			imform.setText("An OTP is sent to "+ email.substring(0,4)+"XXXXXXXXXXXXX");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	int otp_code;
	public void sendotp(String addrss) {
		Random otpgen=new Random();
		otp_code=otpgen.nextInt(999999);
		System.out.println(otp_code);
		
	}
}