package chat.gpt.chatgpt_desktop.view;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DevWindowLoader {
    //System tray  icon
    private  java.awt.Image trayImage = Toolkit.getDefaultToolkit().getImage(WindowLoader.class.getResource("/chat/gpt/chatgpt_desktop/icons/logo.png"));

    private  TrayIcon trayIcon = new TrayIcon(trayImage,"ChatGPT Desktop");

    public  void loadWindowFromPassedFxmlPath(String path){

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WindowLoader.class.getResource("/chat/gpt/chatgpt_desktop/views/"+path+".fxml"));
            Scene scene = new Scene(loader.load());
            scene.setFill(Color.TRANSPARENT);
            // Set up key event handler

            Stage stage = new Stage();
            stage.getIcons().add(new Image(String.valueOf(WindowLoader.class.getResource("/chat/gpt/chatgpt_desktop/icons/logo.png"))));
            stage.setTitle("ChatGPT Desktop");
            scene.setOnKeyPressed(event -> {
                if (event.isControlDown() && event.getCode() == KeyCode.WINDOWS) {
                    minimizeToSystemTray(stage);
                }
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void minimizeToSystemTray(Stage stage) {
        if (SystemTray.isSupported()) {
            Platform.setImplicitExit(false); // Prevent application from exiting on close

            try {
                //we make use of java.awt's SystemTray class
                SystemTray tray = SystemTray.getSystemTray();
                trayIcon.setImageAutoSize(true);

                // Define action for showing the application window from the system tray
                ActionListener showAction = e -> Platform.runLater(() -> {
                    stage.show();
                    tray.remove(trayIcon);
                });

                // Add action listener to the tray icon
                trayIcon.addActionListener(showAction);

                // Add the tray icon to the system tray
                tray.add(trayIcon);

                // Hide the application window
                stage.hide();
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }

}
