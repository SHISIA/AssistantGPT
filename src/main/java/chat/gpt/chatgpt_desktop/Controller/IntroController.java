package chat.gpt.chatgpt_desktop.Controller;

import chat.gpt.chatgpt_desktop.view.WindowLoader;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;


public class IntroController{


    public JFXButton enterAPIKeyBtn;

    //open the features window
    public void openFeaturesWindow() {
        Stage stage=(Stage) enterAPIKeyBtn.getScene().getWindow();
        stage.close();
        WindowLoader.loadWindowFromPassedFxmlPath("Features");
    }

}
