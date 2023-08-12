package chat.gpt.chatgpt_desktop.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowLoader {

    public static void loadWindowFromPassedFxmlPath(String path){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WindowLoader.class.getResource("/chat/gpt/chatgpt_desktop/views/"+path+".fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Features Window");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
