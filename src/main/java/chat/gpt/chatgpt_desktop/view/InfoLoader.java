package chat.gpt.chatgpt_desktop.view;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class InfoLoader {
    public void loadWindowFromPassedFxmlPath(String path){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WindowLoader.class.getResource("/chat/gpt/chatgpt_desktop/views/"+path+".fxml"));
            AnchorPane anchorPane= loader.load();
            FadeTransition ft = new FadeTransition(Duration.millis(2000), anchorPane);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play();
            Scene scene = new Scene(anchorPane);
            scene.setFill(Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.getIcons().add(new Image(String.valueOf(WindowLoader.class.getResource("/chat/gpt/chatgpt_desktop/icons/logo.png"))));
            stage.setTitle("AssistantGPT");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            showAndCloseAfterDelay(stage,Duration.seconds(5));
            //set window screen position
            setStageOnRightCenterScreenSide(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAndCloseAfterDelay(Stage stage, Duration delay) {
        Timeline timeline = new Timeline(new KeyFrame(delay, event -> {
            stage.close();
        }));
        timeline.play();
    }

    public void setStageOnRightCenterScreenSide(Stage primaryStage){
        // Calculate the window position for bottom-right corner
        Screen screen = Screen.getPrimary();
        double screenWidth = screen.getBounds().getWidth();
        double screenHeight = screen.getBounds().getHeight();
        double xPos = screenWidth/1.28;
        double yPos = screenHeight/1.8;

        primaryStage.setX(xPos);
        primaryStage.setY(yPos);
    }
}
