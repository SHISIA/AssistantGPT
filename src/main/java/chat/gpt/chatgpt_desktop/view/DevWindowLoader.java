package chat.gpt.chatgpt_desktop.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.awt.event.KeyListener;
import java.io.IOException;

public class DevWindowLoader {

    public static void loadWindowFromPassedFxmlPath(String path){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WindowLoader.class.getResource("/chat/gpt/chatgpt_desktop/views/"+path+".fxml"));
            Scene scene = new Scene(loader.load());
            // Set up key event handler
            scene.setOnKeyPressed(event -> {
                if (event.isControlDown() && event.getCode() == KeyCode.WINDOWS) {
                  scene.getWindow().hide();
                }
            });
            Stage stage = new Stage();
            stage.getIcons().add(new Image(String.valueOf(WindowLoader.class.getResource("/chat/gpt/chatgpt_desktop/icons/logo.png"))));
            stage.setTitle("ChatGPT Desktop");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
