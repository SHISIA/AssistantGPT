package chat.gpt.chatgpt_desktop.Controller;

import chat.gpt.chatgpt_desktop.view.DevWindowLoader;
import chat.gpt.chatgpt_desktop.view.InfoLoader;
import chat.gpt.chatgpt_desktop.view.PromptWindowLoader;
import chat.gpt.chatgpt_desktop.view.WindowLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseButton;
import javafx.scene.web.PopupFeatures;
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
    @FXML
    public JFXButton popInfo;

    //open the Intro window
    public void goToHomeImageIfEnabled() {
        //close the current window and go back to Intro
        Stage stage=(Stage) miniButton.getScene().getWindow();
        stage.close();
        WindowLoader.loadWindowFromPassedFxmlPath("HomeImage");
    }

    //load the info window
    public void info(){
        new InfoLoader().loadWindowFromPassedFxmlPath("Info");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Load the image for info button icon
        Image iconImage = new Image(String.valueOf(getClass().getResource("/chat/gpt/chatgpt_desktop/icons/info.png")));

        // Create an ImageView with the image
        ImageView iconImageView = new ImageView(iconImage);
        iconImageView.setFitWidth(31);
        iconImageView.setFitHeight(31);
        // Create a button and set the image as its graphic
        popInfo.setGraphic(iconImageView);

        //initialize the webview browser with our chat window set
        WebEngine webEngine = webView.getEngine();

        // Set the user agent to mimic Firefox
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3";
        webView.getEngine().setUserAgent(userAgent);
        webEngine.setOnAlert(Event::consume);
        webView.setContextMenuEnabled(true);

        //load the deep AI chat site as our primary chat agent
        webEngine.load("https://deepai.org/chat");
        webView.setContextMenuEnabled(false);

        ContextMenu contextMenu = new ContextMenu();
        MenuItem copyMenuItem = new MenuItem("Copy");
        copyMenuItem.setOnAction(event -> copySelectedText(webView));

        contextMenu.getItems().addAll(copyMenuItem);
        // Set a custom handler for popups
        // You can also suppress the popup by not showing the alert
    }

    //copy selected text from WebView
    private void copySelectedText(WebView webView) {
        String selectedText = (String) webView.getEngine().executeScript("window.getSelection().toString()");

        if (!selectedText.isEmpty()) {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(selectedText);
            clipboard.setContent(content);
        }
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
