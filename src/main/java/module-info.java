module chat.gpt.chatgpt_desktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.jfoenix;
    requires java.desktop;
    requires javafx.swing;
//    requires x86;
    requires org.eclipse.swt.win32.win32.x86_64;
    requires java.sql;

    opens chat.gpt.chatgpt_desktop to javafx.fxml;
    exports chat.gpt.chatgpt_desktop.Controller to javafx.fxml;
    opens chat.gpt.chatgpt_desktop.Controller to javafx.fxml;
    exports chat.gpt.chatgpt_desktop;
    exports chat.gpt.chatgpt_desktop.Test;
    opens chat.gpt.chatgpt_desktop.Test to javafx.fxml;
    exports chat.gpt.chatgpt_desktop.view to javafx.fxml;
    opens chat.gpt.chatgpt_desktop.view to javafx.fxml;
}