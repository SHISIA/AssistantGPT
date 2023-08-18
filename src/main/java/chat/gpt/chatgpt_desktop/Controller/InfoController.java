package chat.gpt.chatgpt_desktop.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class InfoController {
    @FXML
    public Hyperlink link;

    public void browse(){
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/SHISIA/AssistantGPT"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
