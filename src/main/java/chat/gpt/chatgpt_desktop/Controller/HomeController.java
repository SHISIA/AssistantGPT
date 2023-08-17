package chat.gpt.chatgpt_desktop.Controller;

import chat.gpt.chatgpt_desktop.view.WindowLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.w3c.dom.Document;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    public JFXButton promptsButton;
    @FXML
    public JFXButton imageButton;
    public JFXListView promptsList;
    @FXML
    public WebView webView;
    @FXML
    public Label waitText;

    //open the Intro window
    public void goToHomeImageIfEnabled() {
        //close the current window and go back to Intro
        Stage stage=(Stage) imageButton.getScene().getWindow();
        stage.close();
        WindowLoader.loadWindowFromPassedFxmlPath("HomeImage");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WebEngine webEngine = webView.getEngine();
        // Set up a CookieManager with a permissive CookiePolicy
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
        webEngine.setUserStyleSheetLocation(getClass().getResource("/chat/gpt/chatgpt_desktop/css/webView.css").toString());



        // Set the user agent to mimic Firefox
        String edgeUserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.0.0 Safari/537.36 Edg/100.0.0.0";
        webEngine.setUserAgent(edgeUserAgent);
        webEngine.getLoadWorker().stateProperty().addListener(
                (ov, oldState, newState) -> {
                    if (newState == Worker.State.SUCCEEDED) {
                        waitText.setVisible(false);
                    }
                });
        webEngine.load("https://deepai.org/chat");

    }

//    public void goBack() {
//        if (webView.getEngine().getHistory().getCurrentIndex() > 0) {
//            webView.getEngine().getHistory().go(-1);
//        }
//    }
}
