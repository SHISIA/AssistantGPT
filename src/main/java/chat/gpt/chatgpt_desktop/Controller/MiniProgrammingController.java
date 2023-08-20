package chat.gpt.chatgpt_desktop.Controller;

import chat.gpt.chatgpt_desktop.view.WindowLoader;
import com.jfoenix.controls.JFXToggleButton;
import javafx.concurrent.Worker;
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

        // Set the user agent to mimic Firefox
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3";
        webView.getEngine().setUserAgent(userAgent);

        //load the deep AI chat site as our primary chat agent
        webEngine.load("https://deepai.org/chat");
        //scroll to 600 px and attempt to block ads
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                webEngine.executeScript(
                        "window.scrollTo(0, 600);" +
//                                "var iframes = document.querySelectorAll('iframe'); " +
//                                "for (var i = 0; i < iframes.length; i++) { " +
//                                "    iframes[i].setAttribute('hidden', 'true'); " +
//                                "}" +
                                " //add sentry JS:\n" +
                                "  console.log('adding sentry JS');\n" +
                                "  var sentryScript = document.createElement('script');\n" +
                                "  sentryScript.src = 'https://browser.sentry-cdn.com/7.19.0/bundle.min.js';\n" +
                                "  sentryScript.crossorigin = \"anonymous\";\n" +
                                "  sentryScript.onload = \"Sentry.init{ dsn: 'https://97bfbb7bc651472085dab062d23194f6@sentry.io/1460722' });\";\n" +
                                "  sentryScript.async = true;\n" +
                                "  document.head.appendChild(sentryScript);\n" +
                                "  console.log('added sentry JS');"
                );
            }
        });
    }

    //keeps the window on top as I browse other apps
    public void toggleAlwaysOnTop() {
        Stage stage=(Stage) toggle.getScene().getWindow();
        stage.setAlwaysOnTop(!stage.isAlwaysOnTop());
        
    }

    public void closeApp(){
        System.exit(0);
    }

}
