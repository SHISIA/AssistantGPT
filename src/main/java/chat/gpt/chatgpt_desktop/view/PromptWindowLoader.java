package chat.gpt.chatgpt_desktop.view;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PromptWindowLoader {
    public static void loadWindowFromPassedFxmlPath(String path){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WindowLoader.class.getResource("/chat/gpt/chatgpt_desktop/views/"+path+".fxml"));
            Scene scene = new Scene(loader.load());
            scene.setFill(Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.getIcons().add(new Image(String.valueOf(WindowLoader.class.getResource("/chat/gpt/chatgpt_desktop/icons/logo.png"))));
            stage.setTitle("ChatGPT Desktop");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
