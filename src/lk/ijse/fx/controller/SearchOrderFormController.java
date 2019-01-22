package lk.ijse.fx.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import lk.ijse.fx.business.BOFactory;
import lk.ijse.fx.business.custom.ManageOrdersBO;
import lk.ijse.fx.business.custom.ManagePaymentBO;
import lk.ijse.fx.dto.OrderFullDetatailDTO;
import lk.ijse.fx.main.AppInitializer;
import lk.ijse.fx.view.util.OrderTM;

import java.io.IOException;
import java.util.List;

public class SearchOrderFormController {

    @FXML
    private JFXTextField txtOrderID;
    @FXML
    private TableView<OrderTM> tblSearchOrderTable;
    @FXML
    private AnchorPane root;
    @FXML
    private Circle crlBack;
    @FXML
    private Circle crlNext;

    private ObservableList<OrderTM> olOrders;

    private ManageOrdersBO manageOrdersBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_ORDERS);
    private ManagePaymentBO managePaymentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_PAYMENT);

    public void initialize(){

        tblSearchOrderTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("order_id"));
        tblSearchOrderTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblSearchOrderTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("dining"));
        tblSearchOrderTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("table_number"));
        tblSearchOrderTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        tblSearchOrderTable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("total"));

        List<OrderFullDetatailDTO> orderDB = null;

        try {
            orderDB = manageOrdersBO.getOrdersWithEmployeeAndTableAndTotals();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //ObservableList<OrderFullDetatailDTO> orderFullDetatailDTOS = FXCollections.observableArrayList(orderDB);
            olOrders = FXCollections.observableArrayList();

        for (OrderFullDetatailDTO o : orderDB) {
            olOrders.add(new OrderTM(o.getOrder_id(),o.getDate(),o.getDining(),o.getTable(),o.getWaiter(),o.getSubTotal()));
        }
        tblSearchOrderTable.setItems(olOrders);


    }

    @FXML
    private void navigation(MouseEvent mouseEvent) throws IOException {
        AppInitializer.navigateToHome(root, (Stage) this.root.getScene().getWindow());
    }
    @FXML
    private void playMouseEnterAnimation(MouseEvent mouseEvent) {
    }
    @FXML
    private void playMouseExitAnimation(MouseEvent mouseEvent) {
    }

    @FXML
    private void txtOrderId_OnKeyReleased(KeyEvent keyEvent) {

        ObservableList<OrderTM> templist = FXCollections.observableArrayList();
        for (OrderTM olOrder : olOrders) {
            if(olOrder.getOrder_id().startsWith(txtOrderID.getText())){
                templist.add(olOrder);

            }
        }
        tblSearchOrderTable.setItems(templist);
    }

    public void tblOrder_OnClicked(MouseEvent mouseEvent) throws IOException {

        if(mouseEvent.getClickCount() == 2){

            OrderTM selectedItem = tblSearchOrderTable.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/ijse/fx/view/ViewOrderForm.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            ViewOrderFormController controller = fxmlLoader.getController();

            controller.mainData(selectedItem.getOrder_id(),selectedItem.getTotal(),
                    selectedItem.getDate(),selectedItem.getDining().toString(),selectedItem.getEmployee_id(),selectedItem.getTable_number());

            Scene scene = new Scene(root);
            ((Stage)tblSearchOrderTable.getScene().getWindow()).setScene(scene);



        }
    }
}
