package chat.gpt.chatgpt_desktop.Controller;

import chat.gpt.chatgpt_desktop.view.WindowLoader;
import com.jfoenix.controls.JFXButton;
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
