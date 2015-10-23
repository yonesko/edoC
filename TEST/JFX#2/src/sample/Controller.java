package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class Controller {
    @FXML private Text act;
    @FXML public void handleSubmitButtonAction(ActionEvent actionEvent) {
        act.setText("Нажато");
    }
}
