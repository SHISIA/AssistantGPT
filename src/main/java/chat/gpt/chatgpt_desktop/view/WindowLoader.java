package chat.gpt.chatgpt_desktop.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowLoader {
    public static Stage appStage;

    public static void loadWindowFromPassedFxmlPath(String path) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WindowLoader.class.getResource("/chat/gpt/chatgpt_desktop/views/" + path + ".fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.getIcons().add(new Image(String.valueOf(WindowLoader.class.getResource("/chat/gpt/chatgpt_desktop/icons/logo.png"))));
            stage.setTitle("AssistantGPT");
            scene.setOnKeyPressed(e -> {
                if (e.isControlDown() && e.getCode() == KeyCode.WINDOWS) {
                    stage.hide();
                    appStage = stage;
                    new DevWindowLoader().loadWindowFromPassedFxmlPath("MiniProgramming");
                }
            });
            if (path.equals("Home")){
                stage.setMaximized(true);
            }
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
