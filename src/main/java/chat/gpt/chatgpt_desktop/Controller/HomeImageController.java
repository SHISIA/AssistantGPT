package chat.gpt.chatgpt_desktop.Controller;

import chat.gpt.chatgpt_desktop.view.WindowLoader;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class HomeImageController {
    @FXML
    public JFXButton promptButton;

    //open the Intro window
    public void goBackToIntroWindow() {
        //close the current window and go back to Intro
        Stage stage=(Stage) promptButton.getScene().getWindow();
        stage.close();
        WindowLoader.loadWindowFromPassedFxmlPath("Intro");
    }
}
