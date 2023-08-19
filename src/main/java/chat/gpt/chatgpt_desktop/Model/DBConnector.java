package chat.gpt.chatgpt_desktop.Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private Connection connection;
    public Connection getConnection(){
        // SQLite database URL
        String url = "jdbc:sqlite:./src/main/resources/chat/gpt/chatgpt_desktop/db/AssistantGPT.db"; // Replace with your database path

        try {
            // Create a connection to the database
             connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database connection errors
            e.getMessage();
        }
        return connection;
    }
}
