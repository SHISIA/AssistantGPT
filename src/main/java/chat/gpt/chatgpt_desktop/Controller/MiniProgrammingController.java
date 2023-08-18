package chat.gpt.chatgpt_desktop.Controller;

import chat.gpt.chatgpt_desktop.view.DevWindowLoader;
import chat.gpt.chatgpt_desktop.view.PromptWindowLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MiniProgrammingController implements Initializable {
    public Button exitBtn;

    //closes the mini window for programmers
    public void closeMini(){
        Stage stage=(Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

    //minimize to system tray
    public void minimize(){

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
