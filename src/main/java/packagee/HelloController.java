package packagee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    public Button LoginForm;
    public Button CardlessWithdrawal;
    @FXML
    public void initialize(){
        System.out.println(Controller.getInstance().getcurrent_user());
        System.out.println("HELLO");
    }
    @FXML
    private void handleLoginFormButtonClick(ActionEvent event) throws IOException {
        navigateToScene(event, "viewme/LoginForm.fxml");
    }

    @FXML
    private void handleCardlessWithdrawalButtonClick(ActionEvent event) throws IOException {
        navigateToScene(event, "viewme/CardlessWithdrawal.fxml");
    }

    private void navigateToScene(ActionEvent event, String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();

        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene
        stage.setScene(new Scene(root));
        stage.show();
    }
}