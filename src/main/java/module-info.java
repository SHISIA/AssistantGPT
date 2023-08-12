module chat.gpt.chatgpt_desktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.jfoenix;

    opens chat.gpt.chatgpt_desktop to javafx.fxml;
    exports chat.gpt.chatgpt_desktop.Controller to javafx.fxml;
    opens chat.gpt.chatgpt_desktop.Controller to javafx.fxml;
    exports chat.gpt.chatgpt_desktop;
}