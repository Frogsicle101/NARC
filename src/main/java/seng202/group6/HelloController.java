package seng202.group6;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class HelloController {
    @FXML
    private TextField enterUsername;

    @FXML
    private Text buttonPressedText;

    @FXML
    private PasswordField enterPass;

    @FXML
    public void login() {
        if (!enterUsername.getText().isEmpty()) {
            // Get that text and put it inside a preformatted message.
            String loginText = String.format("Thanks for logging in, %s!", enterUsername.getText());
            // Set the hidden Text node value to be that string.
            buttonPressedText.setText(loginText);
            // Show the Text node.
            buttonPressedText.setVisible(true);
        }
    }
}