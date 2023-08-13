package chat.gpt.chatgpt_desktop.Controller;

import chat.gpt.chatgpt_desktop.view.WindowLoader;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class HomeController {
    @FXML
    public JFXButton promptsButton;
    @FXML
    public JFXButton imageButton;

    //open the Intro window
    public void goToHomeImageIfEnabled() {
        //close the current window and go back to Intro
        Stage stage=(Stage) imageButton.getScene().getWindow();
        stage.close();
        WindowLoader.loadWindowFromPassedFxmlPath("HomeImage");
    }
}
