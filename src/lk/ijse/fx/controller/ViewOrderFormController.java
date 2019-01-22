package lk.ijse.fx.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.fx.business.BOFactory;
import lk.ijse.fx.business.custom.ManageOrdersBO;
import lk.ijse.fx.dto.OrderDTO;
import lk.ijse.fx.main.AppInitializer;
import lk.ijse.fx.view.util.OrderDetailTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViewOrderFormController {

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
    private JFXTextField txtEmployeeID;
    @FXML
    private JFXTextField txtItemCode;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXTextField txtUnitPrice;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private TableView<OrderDetailTM> tblOrderDetails;
    @FXML
    private AnchorPane root;

    private String orderid;
    private ManageOrdersBO manageOrdersBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_ORDERS);

    public void initialize(){

            tblOrderDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
            tblOrderDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
            tblOrderDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
            tblOrderDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
            tblOrderDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));


    }


    @FXML
    public void navigation(MouseEvent mouseEvent) throws IOException {
        AppInitializer.navigateToHome(root, (Stage) this.root.getScene().getWindow());

    }

    public void mainData(String orderid, int subTotal, LocalDate date,String dining,String employee,String table){

        this.orderid = orderid;
        txtOrderID.setText(orderid);
        lblSubTotal.setText(String.valueOf(subTotal));
        dtpDate.setValue(date);
        cmbDining.setValue(dining);
        txtEmployeeID.setText(employee);
        txtTableID.setText(table);
        fillData();
    }

    public void fillData(){

        List<OrderDTO> orderDTO = null;

        try {
            orderDTO = manageOrdersBO.findOrder(orderid);
            ObservableList<OrderDetailTM> tbldetails = FXCollections.observableArrayList();

            for (OrderDTO dto : orderDTO) {
                tbldetails.add(new OrderDetailTM(dto.getItemcode(),dto.getDescription(),
                        dto.getQuantity(),dto.getUnitPrice(),dto.getQuantity()*dto.getUnitPrice()));
            }
            tblOrderDetails.setItems(tbldetails);

        } catch (Exception e) {
            e.printStackTrace();
        }





    }

}
