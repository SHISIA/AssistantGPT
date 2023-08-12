module chat.gpt.chatgpt_desktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
//    requires eu.hansolo.tilesfx;

    opens chat.gpt.chatgpt_desktop to javafx.fxml;
    exports chat.gpt.chatgpt_desktop;
}