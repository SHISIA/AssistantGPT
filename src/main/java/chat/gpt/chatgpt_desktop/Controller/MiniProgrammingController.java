package chat.gpt.chatgpt_desktop.Controller;

import chat.gpt.chatgpt_desktop.view.WindowLoader;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MiniProgrammingController implements Initializable {
    @FXML
    public Button exitBtn;
    @FXML
    public JFXToggleButton toggle;

    //closes the mini window for programmers
    public void closeMini(){
        Stage stage=(Stage) exitBtn.getScene().getWindow();
        stage.close();
        WindowLoader.appStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    //keeps the window on top as I browse other apps
    public void toggleAlwaysOnTop() {
        Stage stage=(Stage) toggle.getScene().getWindow();
        if (stage.isAlwaysOnTop()) {
            stage.setAlwaysOnTop(false);
        } else {
            stage.setAlwaysOnTop(true);
        }
    }

    public void closeApp(){
        System.exit(0);
    }

}
