package chat.gpt.chatgpt_desktop.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PromptController {

    @FXML
    public Button closeBtn;

    //closes the popup when the close button is clicked
    public void close(){
        Stage stage=(Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
}
