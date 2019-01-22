package lk.ijse.fx.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.fx.business.BOFactory;
import lk.ijse.fx.business.custom.ManageOrdersBO;
import lk.ijse.fx.business.custom.ManagePaymentBO;
import lk.ijse.fx.dto.PaymentDTO;
import lk.ijse.fx.entity.Payment;
import lk.ijse.fx.view.util.PaymentTM;

import java.io.IOException;
import java.util.List;

public class PaymentFormController {
    @FXML
    private JFXTextField txtPaymentId;
    @FXML
    private JFXTextField txtOrderId;
    @FXML
    private JFXTextField txtTotal;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<PaymentTM> tblPayment;
    @FXML
    private JFXComboBox<String> cmbMethod;

    @FXML
    private Circle crlBack;
    @FXML
    private Circle crlNext;
    @FXML
    private AnchorPane root;

    private ManageOrdersBO manageOrdersBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_ORDERS);
    private ManagePaymentBO managePaymentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_PAYMENT);


    public void initialize(){

        tblPayment.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("payment_id"));
        tblPayment.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("order_id"));
        tblPayment.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("payment_method"));
        tblPayment.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("total"));

        ObservableList<String> method = cmbMethod.getItems();
        method.add("Cash");
        method.add("Card");

        try {
           txtPaymentId.setText(managePaymentBO.generatePaymentId());

        } catch (Exception e) {
            e.printStackTrace();
        }

        List<PaymentDTO> paymentDB = null;

        try {
            paymentDB = managePaymentBO.getPaymentList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ObservableList<PaymentDTO> paymentDTOS = FXCollections.observableArrayList(paymentDB);
        ObservableList<PaymentTM> tblPayments = FXCollections.observableArrayList();

        for (PaymentDTO p : paymentDTOS) {
            tblPayments.add(new PaymentTM(p.getPayment_id(),p.getOrder_id(),p.getPayment_method(),p.getTotal()));
        }

        tblPayment.setItems(tblPayments);


    }


    @FXML
    private void navigation(MouseEvent mouseEvent) throws IOException {
        Parent placeordr =FXMLLoader.load(this.getClass().getResource("/lk/ijse/fx/view/PlaceOrderForm.fxml"));
        Scene scene = new Scene(placeordr);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);

    }
    @FXML
    private void save_OnAction(ActionEvent actionEvent) {

        PaymentDTO paymentDTO = new PaymentDTO(txtPaymentId.getText(), txtOrderId.getText(), cmbMethod.getValue(), Integer.parseInt(txtTotal.getText()));
        PaymentTM paymentTM = new PaymentTM(txtPaymentId.getText(), txtOrderId.getText(), cmbMethod.getValue(), Integer.parseInt(txtTotal.getText()));
        tblPayment.getItems().add(paymentTM);

        boolean result = false;

        try {
            result = managePaymentBO.createPayment(paymentDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(result){

            new Alert(Alert.AlertType.CONFIRMATION,"Payment has successfully added", ButtonType.OK).showAndWait();
            tblPayment.scrollTo(paymentTM);
        }else {
            new Alert(Alert.AlertType.ERROR, "Failed to save the payment", ButtonType.OK).showAndWait();
        }

    }
    @FXML
    private void delete_OnAction(ActionEvent actionEvent) {
    }

// set total to text field
    public void getTotal(String subTotal){
        this.txtTotal.setText(subTotal);
    }

    //set payment id from place order form
    public void getOrderId(String orderid){
        txtOrderId.setText(orderid);
    }

}
