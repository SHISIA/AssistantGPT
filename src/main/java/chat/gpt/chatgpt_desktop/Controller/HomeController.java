package chat.gpt.chatgpt_desktop.Controller;

import chat.gpt.chatgpt_desktop.Model.DBConnector;
import chat.gpt.chatgpt_desktop.view.DevWindowLoader;
import chat.gpt.chatgpt_desktop.view.InfoLoader;
import chat.gpt.chatgpt_desktop.view.PromptWindowLoader;
import chat.gpt.chatgpt_desktop.view.WindowLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    private final DBConnector connector=new DBConnector();
    private final Connection connection=connector.getConnection();
    public JFXListView<VBox> promptsList;
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
        //load the saved prompts
        loadPrompts();

        // Load the image for info button icon
        Image iconImage = new Image(String.valueOf(getClass().getResource("/chat/gpt/chatgpt_desktop/icons/info.png")));

        // Create an ImageView with the image
        ImageView iconImageView = new ImageView(iconImage);
        iconImageView.setFitWidth(31);
        iconImageView.setFitHeight(31);
        // Create a button and set the image as its graphic
//        popInfo.setGraphic(iconImageView);

        //initialize the webview browser with our chat window set
        WebEngine webEngine = webView.getEngine();

        // Set the user agent to mimic Firefox
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3";
        webView.getEngine().setUserAgent(userAgent);
        webView.setContextMenuEnabled(true);


        //load the deep AI chat site as our primary chat agent
        webEngine.load("https://deepai.org/chat");

        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                webEngine.executeScript(
                        "window.scrollTo(0, 600);" +
                                "var iframes = document.querySelectorAll('iframe'); " +
                                "for (var i = 0; i < iframes.length; i++) { " +
                                "    iframes[i].style.display = 'none'; " +
                                "    iframes[i].style.opacity = '0'; " +
                                "    iframes[i].setAttribute('sandbox', ''); " +
                                "}"
                );
            }
        });
    }

    /**populate the prompts list with saved prompts onLoad**/
    public void loadPrompts(){
        try {
            PreparedStatement statement=connection.prepareStatement("SELECT * FROM Prompts;");
            //bind the values to placeholders
            ResultSet set=statement.executeQuery();
            while (set.next()){
                //loop while creating cards for each prompt
                prompt(set.getString("Title"),set.getString("Content"));
            }
            connection.close();
        }catch (SQLException e){
            e.getMessage();
        }
    }

    /**Individual Prompt**/
    public void prompt(String titleText,String contentTitle){
        VBox vBox=new VBox();
        Label title=new Label(titleText);
        title.setPrefHeight(50);
        title.setFont(Font.font(20));
        title.setStyle("-fx-text-fill:white;");
        vBox.setStyle("-fx-border-radius:12;" +
                "-fx-background-radius:12;" +
                "-fx-background-color:#23252A;");

        Label content=new Label(contentTitle);
        content.setStyle("-fx-text-fill:grey;");
        content.setWrapText(true);
        content.setTooltip(new Tooltip(title.getText()));
        vBox.getChildren().addAll(title,content);
        vBox.setPrefWidth(150);
        vBox.setPrefHeight(120);
        vBox.setPadding(new Insets(10,10,10,10));
        promptsList.getItems().add(vBox);
    }

    /**Add a new prompt:: Loads the window to add/save  a prompt**/
    public void addPrompt() {
        PromptWindowLoader.loadWindowFromPassedFxmlPath("Prompt");
    }

    public void loadMini(){
        Stage stage=(Stage) miniButton.getScene().getWindow();
        stage.hide();
        new DevWindowLoader().loadWindowFromPassedFxmlPath("MiniProgramming");
    }

}
