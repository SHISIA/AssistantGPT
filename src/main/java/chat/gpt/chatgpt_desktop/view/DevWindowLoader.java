package chat.gpt.chatgpt_desktop.view;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DevWindowLoader {
    //System tray  icon
    private  java.awt.Image trayImage = Toolkit.getDefaultToolkit().getImage(WindowLoader.class.getResource("/chat/gpt/chatgpt_desktop/icons/logo.png"));
    private  TrayIcon trayIcon = new TrayIcon(trayImage,"AssistantGPT");
    private double xOffset = 0;
    private double yOffset = 0;

    public  void loadWindowFromPassedFxmlPath(String path){

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WindowLoader.class.getResource("/chat/gpt/chatgpt_desktop/views/"+path+".fxml"));
            Scene scene = new Scene(loader.load());
            scene.setFill(Color.TRANSPARENT);
            // Set up key event handler

            Stage stage = new Stage();
            stage.getIcons().add(new Image(String.valueOf(WindowLoader.class.getResource("/chat/gpt/chatgpt_desktop/icons/logo.png"))));
            stage.setTitle("AssistantGPT");
            scene.setOnKeyPressed(event -> {
                if (event.isControlDown() && event.getCode() == KeyCode.WINDOWS) {
                    minimizeToSystemTray(stage);
                }
            });

            // Add event handlers for dragging the stage
            scene.setOnMousePressed(this::onMousePressed);
            scene.setOnMouseDragged(this::onMouseDragged);
            scene.setOnMouseReleased(e->
            {
                stage.setOpacity(1);
            });
            stage.initModality(Modality.APPLICATION_MODAL);

//            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setOnCloseRequest(e -> System.exit(0));
            stage.setResizable(true);
            stage.setScene(scene);
            //set window screen position
            setStageOnRightCenterScreenSide(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void setStageOnRightCenterScreenSide(Stage primaryStage){
        // Calculate the window position for bottom-right corner
        Screen screen = Screen.getPrimary();
        double screenWidth = screen.getBounds().getWidth();
        double screenHeight = screen.getBounds().getHeight();
        double windowHeight = primaryStage.getHeight();
        double xPos = screenWidth/1.35;
        double yPos = screenHeight - windowHeight;

        primaryStage.setX(xPos);
        primaryStage.setY(yPos);
    }

    //implement dragging
    private void onMouseDragged(MouseEvent event) {
        Stage stage = (Stage) ((Scene) event.getSource()).getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
        stage.setOpacity(0.7); // Reduce opacity while dragging
        stage.setOpacity(0.7); // Reduce opacity while dragging
    }
    //implement pressing
    private void onMousePressed(MouseEvent event) {
        Stage stage = (Stage) ((Scene) event.getSource()).getWindow();
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
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
