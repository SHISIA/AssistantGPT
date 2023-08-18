package chat.gpt.chatgpt_desktop.Test;

import org.eclipse.swt.*;
import org.eclipse.swt.browser.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {
    public static void main(String[] args) {
        // Launch an external browser to open the website
        launchExternalBrowser("https://null.perchance.org/ai-text-to-image-generator");

        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setLayout(new GridLayout(1, true));

        // Create the Browser instance
        Browser browser = new Browser(shell, SWT.NONE);
        browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        // Retrieve HTML content and set it in the Browser widget
        String htmlContent = fetchHtmlContent("https://null.perchance.org/ai-text-to-image-generator");
        browser.setText(htmlContent);

        shell.setSize(800, 600);
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        display.dispose();
    }

    // Launch an external browser to open the URL
    private static void launchExternalBrowser(String url) {
        try {
            Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fetch HTML content from a URL
    private static String fetchHtmlContent(String url) {
        StringBuilder content = new StringBuilder();
        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
