package packagee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TopUpAmountController {
    public Button Cancel;
    public Button M5;
    public Button M10;
    public Button M20;
    public Button M25;
    public Button M30;
    public Button M50;
    public Button M100;
    public Button M200;
    private String operator;
    @FXML
    private Controller controller;

    @FXML
    public void initialize() {
        controller = Controller.getInstance(); // Reference the singleton instance
    }

    @FXML
    private void handleAmountButtonClick(ActionEvent event) throws IOException {
        try {
            System.out.println(controller.getOperator());
            Button clickedButton = (Button) event.getSource();
            String buttonId = clickedButton.getId();
            double amount=0f;
            switch (buttonId){
                case "M5": amount=5f; break;
                case "M10": amount=10f; break;
                case "M20": amount=20f; break;
                case "M25": amount=25f; break;
                case "M30": amount=30f; break;
                case "M50": amount=50f; break;
                case "M100": amount=100f; break;
                case "M200": amount=200f; break;
            }
            if (controller.topup(amount,controller.getOperator(),controller.getCurrentUserPhone())) {
                System.out.println(controller.getcurrent_user()); //to see if topup is saved
                navigateToScene(event, "viewme/TopUpSuccess.fxml");
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
