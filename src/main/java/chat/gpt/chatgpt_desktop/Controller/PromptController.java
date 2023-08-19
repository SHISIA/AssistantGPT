package chat.gpt.chatgpt_desktop.Controller;

import chat.gpt.chatgpt_desktop.Model.DBConnector;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PromptController {

    @FXML
    public Button closeBtn;
    @FXML
    public TextField titleField;
    //Connect to the Database
    private final DBConnector connector=new DBConnector();
    private final Connection connection=connector.getConnection();
    @FXML
    public TextArea content;

    //closes the popup when the close button is clicked
    public void close(){
        Stage stage=(Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    /**Saves the prompt to the db**/
    public void savePrompt(){
        try {
            PreparedStatement statement=connection.prepareStatement("Insert into Prompts (Title,Content) VALUES (?,?);");
            //bind the values to placeholders
            statement.setString(1,titleField.getText());
            statement.setString(2,content.getText());
            // Execute the prepared statement
            statement.executeUpdate();
            connection.close();
            // For demonstration purposes, show an alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setWidth(100);
            alert.setHeight(150);
            alert.setTitle("Popup Alert");
            alert.setHeaderText(null);
            alert.setContentText("Saved Successfully");
            alert.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
