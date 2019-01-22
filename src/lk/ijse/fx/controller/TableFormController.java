package lk.ijse.fx.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.fx.business.BOFactory;
import lk.ijse.fx.business.custom.ManageRestaurantTableBO;
import lk.ijse.fx.dto.RestaurantTableDTO;
import lk.ijse.fx.main.AppInitializer;
import lk.ijse.fx.view.util.EmployeeTM;
import lk.ijse.fx.view.util.RestaurantTableTM;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class TableFormController {

    @FXML
    private JFXTextField txtTableNumber;
    @FXML
    private JFXTextField txtTableName;
    @FXML
    private JFXTextField txtSeats;
    @FXML
    private JFXButton btnAddNew;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<RestaurantTableTM> tblRestTable;
    @FXML
    private JFXTextField txtStatus;
    @FXML
    private AnchorPane root;

    private ManageRestaurantTableBO manageRestaurantTableBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_RESTAURATNTTABLE);

    public void initialize(){

        tblRestTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("table_number"));
        tblRestTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("table_name"));
        tblRestTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("seats"));
        tblRestTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("status"));

        List<RestaurantTableDTO> restTableDB = null;

        try {
            restTableDB = manageRestaurantTableBO.getTables();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ObservableList<RestaurantTableDTO> restaurantTableDTOS = FXCollections.observableArrayList(restTableDB);
        ObservableList<RestaurantTableTM> restTbl = FXCollections.observableArrayList();

        for (RestaurantTableDTO rs : restaurantTableDTOS) {
            restTbl.add(new RestaurantTableTM(rs.getTable_number(),rs.getTable_name(),rs.getSeats(),rs.getStatus()));
        }
        tblRestTable.setItems(restTbl);

        tblRestTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RestaurantTableTM>() {
            @Override
            public void changed(ObservableValue<? extends RestaurantTableTM> observable, RestaurantTableTM oldValue, RestaurantTableTM selectedTable) {
                if(selectedTable == null){
                    return;
                }

                txtTableNumber.setText(selectedTable.getTable_number());
                txtTableName.setText(selectedTable.getTable_name());
                txtSeats.setText(String.valueOf(selectedTable.getSeats()));
                txtStatus.setText(selectedTable.getStatus());

            }
        });

    }

    @FXML
    private void navigation(MouseEvent mouseEvent) throws IOException {
        AppInitializer.navigateToHome(root, (Stage) this.root.getScene().getWindow());
    }
    @FXML
    private void addNew_OnAction(ActionEvent actionEvent) {
    }
    @FXML
    private void save_OnAction(ActionEvent actionEvent) {

        if (txtTableNumber.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Table ID is empty", ButtonType.OK).showAndWait();
            txtTableNumber.requestFocus();
            return;
        } else if (txtTableName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Table name is empty", ButtonType.OK).showAndWait();
            txtTableName.requestFocus();
            return;
        } else if (txtSeats.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Seats are empty", ButtonType.OK).showAndWait();
            txtSeats.requestFocus();
            return;
        }else if (txtSeats.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Status is empty", ButtonType.OK).showAndWait();
            txtStatus.requestFocus();
            return;
        }

        if(tblRestTable.getSelectionModel().isEmpty()){
            ObservableList<RestaurantTableTM> rstTbl = tblRestTable.getItems();

            for (RestaurantTableTM restaurantTableTM : rstTbl) {
                if (restaurantTableTM.getTable_number().equals(txtTableNumber.getText())) {
                    new Alert(Alert.AlertType.ERROR, "Duplicate Table IDs are not allowed").showAndWait();
                    txtTableNumber.requestFocus();
                    return;
                }
            }

            RestaurantTableTM restaurantTableTM = new RestaurantTableTM(txtTableNumber.getText(), txtTableName.getText(),
                    Integer.parseInt(txtSeats.getText()), txtStatus.getText());
            tblRestTable.getItems().add(restaurantTableTM);

            RestaurantTableDTO restaurantTableDTO = new RestaurantTableDTO(txtTableNumber.getText(), txtTableName.getText(),
                    Integer.parseInt(txtSeats.getText()), txtStatus.getText());

            boolean result = false;

            try {
                result = manageRestaurantTableBO.createTable(restaurantTableDTO);
                System.out.println("in try result value is "+ result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Table has been saved successfully", ButtonType.OK).showAndWait();
                tblRestTable.scrollTo(restaurantTableTM);
            }else {
                new Alert(Alert.AlertType.ERROR, "Failed to save the table", ButtonType.OK).showAndWait();

            }
        } else {
            RestaurantTableTM selectedItem = tblRestTable.getSelectionModel().getSelectedItem();
            selectedItem.setTable_name(txtTableName.getText());
            selectedItem.setStatus(txtStatus.getText());
            selectedItem.setSeats(Integer.parseInt(txtSeats.getText()));

            tblRestTable.refresh();

            boolean result = false;

            try {
                result = manageRestaurantTableBO.updateTable(new RestaurantTableDTO(txtTableNumber.getText(), txtTableName.getText(),
                        Integer.parseInt(txtSeats.getText()), txtStatus.getText()));

            } catch (Exception e) {
                e.printStackTrace();
            }

            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Table has been updated successfully", ButtonType.OK).showAndWait();

            }else {
                new Alert(Alert.AlertType.ERROR, "Failed to update the table", ButtonType.OK).showAndWait();

            }

        }

    }
    @FXML
    private void delete_OnAction(ActionEvent actionEvent) {
        Alert confirmMsg = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this item ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = confirmMsg.showAndWait();

        if (buttonType.get() == ButtonType.YES) {
            String selectedTable = tblRestTable.getSelectionModel().getSelectedItem().getTable_number();

            tblRestTable.getItems().remove(tblRestTable.getSelectionModel().getSelectedItem());
            boolean result = false;
            try {
                result = manageRestaurantTableBO.deleteTable(selectedTable);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!result){
                new Alert(Alert.AlertType.ERROR, "Failed to delete the table", ButtonType.OK).showAndWait();
            }else{

            }
        }
    }
}

