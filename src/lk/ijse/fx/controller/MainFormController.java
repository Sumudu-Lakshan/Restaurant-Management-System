package lk.ijse.fx.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class MainFormController {

    public AnchorPane root;
    @FXML
    private Label lblMenu;
    @FXML
    private Label lblDescription;
    @FXML
    private Circle crlAll;
    @FXML
    private Circle crlFood;
    @FXML
    private Circle crlTable;
    @FXML
    private Circle crlPlaceOrder;
    @FXML
    private Circle crlViewOrder;
    @FXML
    private Circle crlEmployee;

    public void initialize(){

        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        crlFood.setStroke(Color.grayRgb(100));
        Image menu = new Image("/lk/ijse/fx/asset/menu1.jpg",false);
        crlFood.setFill(new ImagePattern(menu));
       // cir2.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

        crlEmployee.setStroke(Color.grayRgb(100));
        Image waiter = new Image("/lk/ijse/fx/asset/waiter2.jpg");
        crlEmployee.setFill(new ImagePattern(waiter));

        crlTable.setStroke(Color.grayRgb(100));
        Image table = new Image("/lk/ijse/fx/asset/table.jpg");
        crlTable.setFill(new ImagePattern(table));

        crlPlaceOrder.setStroke(Color.grayRgb(100));
        Image placeorder = new Image("/lk/ijse/fx/asset/order3.png");
        crlPlaceOrder.setFill(new ImagePattern(placeorder));

        crlViewOrder.setStroke(Color.grayRgb(100));
        Image vieworder = new Image("/lk/ijse/fx/asset/eye3.jpg");
        crlViewOrder.setFill(new ImagePattern(vieworder));



    }
    @FXML
    private void navigate(MouseEvent mouseEvent) throws IOException {

        if (mouseEvent.getSource() instanceof Circle){
            Circle icon = (Circle) mouseEvent.getSource();

            Parent root = null;

            switch(icon.getId()){
                case "crlFood":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/fx/view/MenuItemForm.fxml"));
                    break;
                case "crlEmployee":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/fx/view/EmployeeForm.fxml"));
                    break;
                case "crlTable":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/fx/view/TableForm.fxml"));
                    break;
                case "crlPlaceOrder":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/fx/view/PlaceOrderForm.fxml"));
                    break;
                case "crlViewOrder":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/fx/view/SearchOrderForm.fxml"));
                    break;
            }

            if (root != null){
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(250), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }


    }
    @FXML
    private void playMouseEnterAnimation(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() instanceof Circle){
            Circle circle = (Circle) mouseEvent.getSource();

            switch (circle.getId()){

                case "crlFood":
                    lblMenu.setText("Manage MenuDAO");
                    lblDescription.setText("Click to Add,Edit,Search,Delete or View MenuDAO");
                    break;

                case "crlEmployee":
                    lblMenu.setText("Manage Employees");
                    lblDescription.setText("Click to Add,Edit,Search,Delete or View Employees");
                    break;

                case "crlTable":
                    lblMenu.setText("Manage Tables");
                    lblDescription.setText("Click to Add,Edit,Search,Delete or View Tables");
                    break;

                case "crlPlaceOrder":
                    lblMenu.setText("Place Order");
                    lblDescription.setText("Click here to place an order");
                    break;

                case "crlViewOrder":
                    lblMenu.setText("View Orders");
                    lblDescription.setText("Click here to view orders");
                    break;

            }

            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), circle);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            circle.setEffect(glow);

        }


    }
    @FXML
    private void playMouseExitAnimation(MouseEvent mouseEvent) {

        if (mouseEvent.getSource() instanceof Circle){
            Circle circle = (Circle) mouseEvent.getSource();
            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), circle);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            circle.setEffect(null);
            lblMenu.setText("Welcome");
            lblDescription.setText("Please select one of above main operations to proceed");
        }

    }
}
