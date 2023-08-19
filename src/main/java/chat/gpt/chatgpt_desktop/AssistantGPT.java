package chat.gpt.chatgpt_desktop;

import chat.gpt.chatgpt_desktop.Model.DBConnector;
import chat.gpt.chatgpt_desktop.view.WindowLoader;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.*;

public class AssistantGPT extends Application {
    //Connect to the Database
    private final DBConnector connector=new DBConnector();
    private boolean introScreenShown;
    private final Connection connection=connector.getConnection();
    @Override
    public void start(Stage stage) throws IOException {
        if (checkIfEULAIsShown()) {
            //if Eula is  shown. By Eula, I mean the welcome introduction onboarding screens,
            //start normally from splash screen
            startFromSplash(stage);
        } else {
            //if Eula is not shown. By Eula, I mean the welcome introduction onboarding screens,
            // then save the state and then start from Onboard User
            showEULA();
            startFromIntro(stage);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    //start from an introduction
    public void startFromIntro(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AssistantGPT.class.getResource("views/Intro.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("AssistantGPT");
        stage.getIcons().add(new Image(String.valueOf(AssistantGPT.class.getResource("/chat/gpt/chatgpt_desktop/icons/logo.png"))));
        stage.setScene(scene);
        stage.show();
    }
    //start normally from the splashscreen
    public void startFromSplash(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AssistantGPT.class.getResource("/chat/gpt/chatgpt_desktop/views/Home.fxml"));
//        AnchorPane anchorPane=fxmlLoader.load();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("AssistantGPT");
//        // Initialize the fade transition
//        FadeTransition fadeTransition = getFadeTransition(anchorPane);
//
//        // Create a pause transition to stop the fade after specified seconds
//        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(9));
//        pauseTransition.setOnFinished(event -> {
//            fadeTransition.stop(); // Stop the fade transition
//           stage.close();
//           //load the home controller
//            WindowLoader.loadWindowFromPassedFxmlPath("Home");
//        });
//        pauseTransition.play();
//        stage.initStyle(StageStyle.TRANSPARENT);
//        scene.setFill(Color.TRANSPARENT);
        stage.getIcons().add(new Image(String.valueOf(AssistantGPT.class.getResource("/chat/gpt/chatgpt_desktop/icons/logo.png"))));
        stage.setScene(scene);
        stage.show();
    }

    //fade transition
    private static FadeTransition getFadeTransition(AnchorPane anchorPane) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(9), anchorPane);
        fadeTransition.setFromValue(1.0); // Fully visible (1.0 opacity)
        fadeTransition.setToValue(0.5);   // Mostly transparent (0.2 opacity)
        fadeTransition.setAutoReverse(true); // Fade out and then fade in
        fadeTransition.setCycleCount(FadeTransition.INDEFINITE); // Repeat indefinitely

        // Set an event handler to start the transition when the button is clicked
        // Start the fade transition when the application is launched
        fadeTransition.play();
        return fadeTransition;
    }

    public boolean checkIfEULAIsShown(){
        try {
            PreparedStatement statement=connection.prepareStatement("SELECT IsShown FROM EULA;");
            // Execute the prepared statement
           ResultSet set= statement.executeQuery();
           while (set.next()){
              String shownAnswer = set.getString("IsShown");

               introScreenShown = shownAnswer.equals("YES");
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return introScreenShown;
    }

    public void showEULA(){
       try {

           PreparedStatement statement=connection.prepareStatement("Insert into EULA (IsShown) VALUES (?);");
           //bind the values to placeholders
           statement.setString(1,"YES");
           // Execute the prepared statement
           statement.executeUpdate();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

    public static void main(String[] args) {
        launch();
    }
}