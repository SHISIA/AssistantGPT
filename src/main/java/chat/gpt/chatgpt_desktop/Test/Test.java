package chat.gpt.chatgpt_desktop.Test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Fade Transition Example");

        // Create a button to apply the fade transition
        Button button = new Button("Fade In/Out");

        // Create a stack pane to hold the button
        StackPane root = new StackPane();
        root.getChildren().add(button);

        // Create a scene
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);

        // Initialize the fade transition
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), button);
        fadeTransition.setFromValue(1.0); // Fully visible (1.0 opacity)
        fadeTransition.setToValue(0.2);   // Mostly transparent (0.2 opacity)
        fadeTransition.setAutoReverse(true); // Fade out and then fade in
        fadeTransition.setCycleCount(FadeTransition.INDEFINITE); // Repeat indefinitely

        // Set an event handler to start the transition when the button is clicked
        button.setOnAction(event -> {
            if (fadeTransition.getStatus() == javafx.animation.Animation.Status.RUNNING) {
                fadeTransition.stop();
            } else {
                fadeTransition.play();
            }
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
