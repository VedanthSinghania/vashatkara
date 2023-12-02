package sds;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class Navig_pane_controller implements javafx.fxml.Initializable{
	@SuppressWarnings("exports")
	@javafx.fxml.FXML
	public ImageView user_ImageView;
	@SuppressWarnings("exports")
	@javafx.fxml.FXML
	public Label welcLabel;
	
	private String Username;
	
	public void getusermame(String username) {
		Username = username;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		welcLabel.setText("Welcome \n "+Username);
		Circle imageciew_shapeCircle = new Circle(68.5,68.5,69);
		user_ImageView.setClip(imageciew_shapeCircle);
		// TODO Auto-generated method stub
		
	}
}
