package lk.ijse.fx.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.fx.business.BOFactory;
import lk.ijse.fx.business.custom.ManageMenuBO;
import lk.ijse.fx.dto.MenuDTO;
import lk.ijse.fx.main.AppInitializer;
import lk.ijse.fx.view.util.MenuTM;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MenuItemFormController {

    @FXML
    private JFXTextField txtItemCode;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXTextField txtUnitPrice;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXComboBox<String> cmbMealType;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btnAdd;

    @FXML
    private Circle crlBack;
    @FXML
    private Circle crlNext;

    @FXML
    private TableView<MenuTM> tblMenuItem;

    private ManageMenuBO manageMenuItemBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_MENU);

    //=======================================Menu Item Initialize Method================================================//

    public void initialize(){

        tblMenuItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("item_code"));
        tblMenuItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblMenuItem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        tblMenuItem.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("meal_type"));

        ObservableList<String> mealTypes = cmbMealType.getItems();
        mealTypes.add("Starter");
        mealTypes.add("Main Dish");
        mealTypes.add("Drinks");

        List<MenuDTO> menuItemDB = null;
        try {
            menuItemDB = manageMenuItemBO.getMenuItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("error");

        ObservableList<MenuDTO> menuDTOS = FXCollections.observableArrayList(menuItemDB);

        System.out.println("where");

        ObservableList<MenuTM> tblItems = FXCollections.observableArrayList();

        System.out.println("why");

        for (MenuDTO menuDTO : menuDTOS) {
            tblItems.add(new MenuTM(menuDTO.getItem_code(), menuDTO.getDescription(), menuDTO.getUnit_price(), menuDTO.getMeal_type()));
        }

         tblMenuItem.setItems(tblItems);



        tblMenuItem.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MenuTM>() {
            @Override
            public void changed(ObservableValue<? extends MenuTM> observable, MenuTM oldValue, MenuTM selectedMenuItem) {
                if(selectedMenuItem == null){
                    return;
                }

                txtItemCode.setText(selectedMenuItem.getItem_code());
                txtDescription.setText(selectedMenuItem.getDescription());
                txtUnitPrice.setText(String.valueOf(selectedMenuItem.getUnit_price()));
                cmbMealType.setValue(selectedMenuItem.getMeal_type());
            }
        });
    }

    //=================================================================================================================//
    @FXML
    private void playMouseEnterAnimation(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() instanceof Circle){
            Circle circle = (Circle) mouseEvent.getSource();

            switch (circle.getId()){

                case "crlBack":
                    Tooltip.install(crlBack,new Tooltip("Go Back"));
                    break;

                case "crlNext":
                  //  Tooltip.install(crlNext,makeBubble(new Tooltip("Go Next")));
                    Tooltip.install(crlNext,new Tooltip("Go Next"));
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

    //=================================================================================================================//
    @FXML
    private void playMouseExitAnimation(MouseEvent mouseEvent) {

        if (mouseEvent.getSource() instanceof Circle){
            Circle circle = (Circle) mouseEvent.getSource();
            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), circle);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            circle.setEffect(null);

        }
    }

    //=================================================================================================================//
    @FXML
    private void navigation(MouseEvent mouseEvent) throws IOException {
        AppInitializer.navigateToHome(root, (Stage) this.root.getScene().getWindow());
    }

    //=================================================================================================================//
    @FXML
    private void add_OnAction(ActionEvent actionEvent) {
    }

    //=================================================================================================================//
    @FXML
    private void save_OnAction(ActionEvent actionEvent) {


        if (txtItemCode.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Order ID is empty", ButtonType.OK).showAndWait();
            txtItemCode.requestFocus();
            return;
        } else if (txtDescription.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Description is empty", ButtonType.OK).showAndWait();
            txtDescription.requestFocus();
            return;
        } else if (txtUnitPrice.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Unit price is empty", ButtonType.OK).showAndWait();
            txtUnitPrice.requestFocus();
            return;
        }else if(cmbMealType.getSelectionModel().isEmpty()){
                new Alert(Alert.AlertType.ERROR, "Select the meal type", ButtonType.OK).showAndWait();
                return;
            }

        if(tblMenuItem.getSelectionModel().isEmpty()) {

            // new item
            ObservableList<MenuTM> items = tblMenuItem.getItems();

            for (MenuTM item : items) {
                if (item.getItem_code().equals(txtItemCode.getText())) {
                    new Alert(Alert.AlertType.ERROR, "Duplicate Customer IDs are not allowed").showAndWait();
                    txtItemCode.requestFocus();
                    return;
                }
            }

            MenuTM menuTM = new MenuTM(txtItemCode.getText(), txtDescription.getText(), Integer.parseInt(txtUnitPrice.getText()), cmbMealType.getValue());
            tblMenuItem.getItems().add(menuTM);
            MenuDTO menuDTO = new MenuDTO(txtItemCode.getText(), txtDescription.getText(), Integer.parseInt(txtUnitPrice.getText()), cmbMealType.getValue());
            boolean result = false;

            try {
                result = manageMenuItemBO.createMenuItem(menuDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Item has been saved successfully", ButtonType.OK).showAndWait();
                tblMenuItem.scrollTo(menuTM);
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save the item", ButtonType.OK).showAndWait();

            }
        } else {
            MenuTM selectedItem = tblMenuItem.getSelectionModel().getSelectedItem();
            selectedItem.setDescription(txtDescription.getText());
            selectedItem.setUnit_price(Integer.parseInt(txtUnitPrice.getText()));
            selectedItem.setMeal_type(cmbMealType.getValue());
            tblMenuItem.refresh();

            boolean result = false;

            try {
                result = manageMenuItemBO.updateMenuItem(new MenuDTO(txtItemCode.getText(),
                        txtDescription.getText(),
                        Integer.parseInt(txtUnitPrice.getText()),cmbMealType.getValue()));

            } catch (Exception e) {
                e.printStackTrace();
            }

            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Item has been updated successfully", ButtonType.OK).showAndWait();

            }else {
                new Alert(Alert.AlertType.ERROR, "Failed to update the item", ButtonType.OK).showAndWait();

            }

        }
        reset();
    }

    private void reset() {
        txtItemCode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        cmbMealType.setValue("");
    }

    //=================================================================================================================//
    @FXML
    private void delete_OnAction(ActionEvent actionEvent) {

        Alert confirmMsg = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this item ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = confirmMsg.showAndWait();

        if (buttonType.get() == ButtonType.YES) {
            String selectedItem = tblMenuItem.getSelectionModel().getSelectedItem().getItem_code();

            tblMenuItem.getItems().remove(tblMenuItem.getSelectionModel().getSelectedItem());
            boolean result = false;
            try {
                result = manageMenuItemBO.deleteMenuItem(selectedItem);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!result){
                new Alert(Alert.AlertType.ERROR, "Failed to delete the item", ButtonType.OK).showAndWait();
            }else{
                reset();
            }
        }

    }

    //=================================================================================================================//
    @FXML
    private void edit_OnAction(ActionEvent actionEvent) {



    }
    //=================================================================================================================//

}
