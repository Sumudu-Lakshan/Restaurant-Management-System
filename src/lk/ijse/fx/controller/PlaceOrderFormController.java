package lk.ijse.fx.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.fx.business.BOFactory;
import lk.ijse.fx.business.custom.ManageEmployeeBO;
import lk.ijse.fx.business.custom.ManageMenuBO;
import lk.ijse.fx.business.custom.ManageOrdersBO;
import lk.ijse.fx.business.custom.ManageRestaurantTableBO;
import lk.ijse.fx.dto.MenuDTO;
import lk.ijse.fx.dto.OrderDTO;
import lk.ijse.fx.main.AppInitializer;
import lk.ijse.fx.view.util.OrderDetailTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PlaceOrderFormController {

    @FXML
    private Label lblSubTotal;
    @FXML
    private JFXTextField txtOrderID;
    @FXML
    private JFXTextField txtTableID;
    @FXML
    private JFXComboBox cmbDining;
    @FXML
    private JFXDatePicker dtpDate;
    @FXML
    private JFXTextField txtWaiterID;
    @FXML
    private JFXTextField txtItemCode;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXTextField txtUnitPrice;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private TableView<OrderDetailTM> tblOrderDetail;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnRemove;
    @FXML
    private JFXButton btnPlaceOrder;
    @FXML
    private AnchorPane root;

    private ManageOrdersBO manageOrdersBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_ORDERS);
    private ManageMenuBO manageItemsBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_MENU);
    private ManageEmployeeBO manageCustomersBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_EMPLOYEE);
    private ManageRestaurantTableBO restaurantTable = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_RESTAURATNTTABLE);


    public void initialize() {

        tblOrderDetail.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblOrderDetail.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblOrderDetail.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblOrderDetail.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblOrderDetail.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));

        txtOrderID.setEditable(false);
        try {
            txtOrderID.setText(manageOrdersBO.generateOrderID());
        } catch (Exception e) {
            e.printStackTrace();
        }

        dtpDate.setValue(LocalDate.now());

        ObservableList items = cmbDining.getItems();
        items.add("Dining");
        items.add("Take Away");


        btnRemove.setDisable(true);

        tblOrderDetail.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<OrderDetailTM>() {
            @Override
            public void changed(ObservableValue<? extends OrderDetailTM> observable, OrderDetailTM oldValue, OrderDetailTM selectedItem) {

                if(selectedItem == null){
                    return;
                }

                txtItemCode.setText(selectedItem.getCode());
                txtDescription.setText(selectedItem.getDescription());
                txtQty.setText(String.valueOf(selectedItem.getQty()));
                txtUnitPrice.setText(String.valueOf(selectedItem.getUnitPrice()));

                btnRemove.setDisable(false);



            }
        });

        tblOrderDetail.getItems().addListener(new ListChangeListener<OrderDetailTM>() {
            @Override
            public void onChanged(Change<? extends OrderDetailTM> c) {
                subTotal();
            }
        });


    }

    @FXML
    private void navigation(MouseEvent mouseEvent) throws IOException {
        AppInitializer.navigateToHome(root, (Stage) this.root.getScene().getWindow());
    }

    @FXML
    private void navigateToPayment(MouseEvent mouseEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Pane placeordr = loader.load(this.getClass().getResource("/lk/ijse/fx/view/PaymentForm.fxml").openStream());
        PaymentFormController paymentFormController =  (PaymentFormController) loader.getController();
        paymentFormController.getTotal(lblSubTotal.getText());
        paymentFormController.getOrderId(txtOrderID.getText());

        Scene scene = new Scene(placeordr);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);


    }

    @FXML
    private void add_OnAction(ActionEvent actionEvent) {

        if (cmbDining.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please Select Dining Type", ButtonType.OK).showAndWait();
            txtItemCode.requestFocus();
            return;
        }
        if(txtQty.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please enter the quantity", ButtonType.OK).showAndWait();
            txtQty.requestFocus();
            return;
        }

        if (tblOrderDetail.getSelectionModel().isEmpty()) {
            // New

            OrderDetailTM orderDetailTM = null;

            if ((orderDetailTM = isItemExist(txtItemCode.getText())) == null) {

                OrderDetailTM newOrderDetailTM = new OrderDetailTM(txtItemCode.getText(), txtDescription.getText(),
                        Integer.parseInt(txtQty.getText()),
                        Integer.parseInt(txtUnitPrice.getText()),
                        Integer.parseInt(txtQty.getText()) * Integer.parseInt(txtUnitPrice.getText()));

                tblOrderDetail.getItems().add(newOrderDetailTM);

                txtItemCode.clear();
                txtDescription.clear();
                txtUnitPrice.clear();
                txtQty.clear();

            }

        }else {
            OrderDetailTM selectedItem = tblOrderDetail.getSelectionModel().getSelectedItem();
            selectedItem.setQty(Integer.parseInt(txtQty.getText()));
            selectedItem.setTotal(Integer.parseInt(txtQty.getText())*Integer.parseInt(txtUnitPrice.getText()));
            tblOrderDetail.refresh();

        }
    }

    @FXML
    private void remove_OnAction(ActionEvent actionEvent) {
        OrderDetailTM selectedItem = tblOrderDetail.getSelectionModel().getSelectedItem();
        tblOrderDetail.getItems().remove(selectedItem);

        txtItemCode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQty.clear();
        tblOrderDetail.getSelectionModel().clearSelection();

    }

    @FXML
    private void placeOrder_OnAction(ActionEvent actionEvent) {

        if(tblOrderDetail.getItems().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please add the items", ButtonType.OK).showAndWait();
            return;
        }

        ObservableList<OrderDetailTM> items = tblOrderDetail.getItems();
        ArrayList<OrderDTO> orderDetail = new ArrayList<>();

        for (OrderDetailTM item : items) {
            orderDetail.add(new OrderDTO(item.getCode(),item.getDescription(),item.getQty(),item.getUnitPrice()));
        }

        try {
            manageOrdersBO.createOrder(new OrderDTO(txtOrderID.getText(),dtpDate.getValue(),txtTableID.getText(),
                    txtWaiterID.getText(),cmbDining.getValue().toString(),orderDetail));
           // new Alert(Alert.AlertType.CONFIRMATION, "Order has been saved successfully", ButtonType.OK).showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public void getItemDetails(ActionEvent actionEvent) throws Exception {
        MenuDTO menuItem = manageItemsBO.findMenuItem(txtItemCode.getText());
        txtDescription.setText(menuItem.getDescription());
        txtUnitPrice.setText(String.valueOf(menuItem.getUnit_price()));
        txtQty.requestFocus();

    }


    private OrderDetailTM isItemExist(String itemdcode) {
        ObservableList<OrderDetailTM> items = tblOrderDetail.getItems();
        for (OrderDetailTM item : items) {
            if (item.getCode().equals(itemdcode)) {
                return item;
            }
        }
        return null;
    }

    public void subTotal(){

        ObservableList<OrderDetailTM> items = tblOrderDetail.getItems();
        int total = 0;
        for (OrderDetailTM item : items) {
            total += item.getTotal();
        }

        lblSubTotal.setText(String.valueOf(total));
    }

}
