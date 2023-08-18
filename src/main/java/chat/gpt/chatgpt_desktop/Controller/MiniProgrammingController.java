package chat.gpt.chatgpt_desktop.Controller;

import chat.gpt.chatgpt_desktop.view.WindowLoader;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MiniProgrammingController implements Initializable {
    @FXML
    public Button exitBtn;
    @FXML
    public JFXToggleButton toggle;
    @FXML
    public WebView webView;

    //closes the mini window for programmers
    public void closeMini(){
        Stage stage=(Stage) exitBtn.getScene().getWindow();
        stage.close();
        WindowLoader.appStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialize the webview browser with our chat window set
        WebEngine webEngine = webView.getEngine();
        webView.getEngine().setJavaScriptEnabled(true);
        webEngine.setUserStyleSheetLocation(getClass().getResource("/chat/gpt/chatgpt_desktop/css/webView.css").toString());

        // Set the user agent to mimic Firefox
        String edgeUserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.0.0 Safari/537.36 Edg/100.0.0.0";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3";
        webView.getEngine().setUserAgent(userAgent);

        //load the deep AI chat site as our primary chat agent
        webEngine.load("https://deepai.org/chat");
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
