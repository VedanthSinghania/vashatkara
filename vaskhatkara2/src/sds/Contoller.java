package sds;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;

import javafx.fxml.FXMLLoader;


public class Contoller implements javafx.fxml.Initializable {
//hak
	@SuppressWarnings("exports")
	@javafx.fxml.FXML
	public JFXHamburger icon_Hamburger;
	@SuppressWarnings("exports")
	@javafx.fxml.FXML
	public JFXDrawer navig_pane_drawer;
	private boolean is_arow_left=false;
	 String username;
	
	
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		icon_Hamburger.setStyle("-fx-paint: #ffffff;");
			java.io.File file=new java.io.File("../vaskhatkara2/src/sds/drawer.fxml");
			try {
				FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
				file=null;
				javafx.scene.layout.Pane root = loader.load();
				Navig_pane_controller sub_Controller = loader.getController();
				sub_Controller.getusermame(username);
				sub_Controller.initialize(null, null);
				sub_Controller =null;
				loader = null;
				System.gc();
				navig_pane_drawer.setSidePane(root);
			} catch (Exception e1) {
				e1.printStackTrace();
			
		file=null;
			}
	
	icon_Hamburger.setOnMouseClicked(event ->{
		HamburgerBasicCloseTransition transition = new HamburgerBasicCloseTransition(icon_Hamburger);
		if (is_arow_left) {
			transition.setRate(-1);
			transition.play();
			navig_pane_drawer.close();
			is_arow_left=false;
		}
		else {
			transition.setRate(1);
			transition.play();
			navig_pane_drawer.open();
			is_arow_left=true;
		     }
	});
				
   }	
}