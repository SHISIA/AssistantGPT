package chat.gpt.chatgpt_desktop.Controller;

import chat.gpt.chatgpt_desktop.view.InfoLoader;
import chat.gpt.chatgpt_desktop.view.WindowLoader;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class IntroController implements Initializable {
    @FXML
    public JFXButton enterAPIKeyBtn;
    @FXML
    public JFXButton popInfo;

    //open the features window
    public void openFeaturesWindow() {
        Stage stage=(Stage) enterAPIKeyBtn.getScene().getWindow();
        stage.close();
        WindowLoader.loadWindowFromPassedFxmlPath("Features");
    }

    public void info(){
        new InfoLoader().loadWindowFromPassedFxmlPath("Info");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Load the image
        Image iconImage = new Image(String.valueOf(getClass().getResource("/chat/gpt/chatgpt_desktop/icons/info.png")));

        // Create an ImageView with the image
        ImageView iconImageView = new ImageView(iconImage);
        iconImageView.setFitWidth(31);
        iconImageView.setFitHeight(31);
        // Create a button and set the image as its graphic
        popInfo.setGraphic(iconImageView);
    }
}
