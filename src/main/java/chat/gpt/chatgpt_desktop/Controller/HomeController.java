package chat.gpt.chatgpt_desktop.Controller;

import chat.gpt.chatgpt_desktop.view.DevWindowLoader;
import chat.gpt.chatgpt_desktop.view.PromptWindowLoader;
import chat.gpt.chatgpt_desktop.view.WindowLoader;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public JFXListView<Node> promptsList;
    @FXML
    public WebView webView;
    @FXML
    public Button miniButton;

    //open the Intro window
    public void goToHomeImageIfEnabled() {
        //close the current window and go back to Intro
        Stage stage=(Stage) miniButton.getScene().getWindow();
        stage.close();
        WindowLoader.loadWindowFromPassedFxmlPath("HomeImage");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up an event handler for the key combination
        // Create a key combination Ctrl + O to open the view

        //initialize the webview browser with our chat window set
        WebEngine webEngine = webView.getEngine();
        // Set up a CookieManager with a permissive CookiePolicy
//        CookieManager cookieManager = new CookieManager();
//        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
          webView.getEngine().setJavaScriptEnabled(true);
//        CookieHandler.setDefault(cookieManager);
        webEngine.setUserStyleSheetLocation(getClass().getResource("/chat/gpt/chatgpt_desktop/css/webView.css").toString());

        // Set the user agent to mimic Firefox
        String edgeUserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.0.0 Safari/537.36 Edg/100.0.0.0";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3";
        webView.getEngine().setUserAgent(userAgent);

        //load the deep AI chat site as our primary chat agent
        webEngine.load("https://deepai.org/chat");
    }

    public void addPrompt() {
        PromptWindowLoader.loadWindowFromPassedFxmlPath("Prompt");
    }

    public void loadMini(){
        Stage stage=(Stage) miniButton.getScene().getWindow();
        stage.hide();
        new DevWindowLoader().loadWindowFromPassedFxmlPath("MiniProgramming");
    }
}
