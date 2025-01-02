package packagee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class WithdrawController {
    private Controller controller;
    public Button Cancel;
    public Button M100;
    public Button M500;
    public Button M200;
    public Button M1000;
    public Button M1500;
    public Button M2000;
    @FXML
    public void initialize() {
        controller = Controller.getInstance(); // Reference the singleton instance
    }

    @FXML
    private void handleAmountButtonClick(ActionEvent event) throws IOException {
        try {
            Button clickedButton = (Button) event.getSource();
            String buttonId = clickedButton.getId();
            double amount=0;
            switch (buttonId){
                case "M100": amount=100; break;
                case "M200": amount=200; break;
                case "M500": amount=500; break;
                case "M1000": amount=1000; break;
                case "M1500": amount=1500; break;
                case "M2000": amount=2000; break;
            }
            if (controller.withdraw(amount)) {
                System.out.println(controller.getcurrent_user()); //to see if withdrawal's saved
                navigateToScene(event, "viewme/WithdrawSuccess.fxml");
            } else {
                navigateToScene(event, "viewme/Wrong.fxml");
            }
        }catch(Exception e) {navigateToScene(event, "viewme/SystemFailure.fxml");}
    }

    @FXML
    private void handleCancelButtonClick(ActionEvent event) throws IOException {
        navigateToScene(event, "viewme/Hello.fxml");
    }

    private void navigateToScene(ActionEvent event, String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
