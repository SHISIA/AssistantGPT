package chat.gpt.chatgpt_desktop.Controller;

import chat.gpt.chatgpt_desktop.view.WindowLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FeaturesController {
    @FXML
    public JFXButton backButton;
    @FXML
    public ImageView infoBtn;
    @FXML
    public JFXButton continueButton;
    @FXML
    public JFXCheckBox chatgptCheck;
    @FXML
    public JFXCheckBox imgGenCheck;
    @FXML
    public JFXCheckBox allFeatureCheck;

    //open the Intro window
    public void goBackToIntroWindow() {
        //close the current window and go back to Intro
        Stage stage=(Stage) backButton.getScene().getWindow();
        stage.close();
        WindowLoader.loadWindowFromPassedFxmlPath("Intro");
    }
}
