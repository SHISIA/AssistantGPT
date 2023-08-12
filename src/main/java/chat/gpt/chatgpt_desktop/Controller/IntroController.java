package chat.gpt.chatgpt_desktop.Controller;

import chat.gpt.chatgpt_desktop.HelloApplication;
import chat.gpt.chatgpt_desktop.view.WindowLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IntroController{

    //open the features window
    public void openFeaturesWindow() {
        WindowLoader.loadWindowFromPassedFxmlPath("Features");
    }
}
