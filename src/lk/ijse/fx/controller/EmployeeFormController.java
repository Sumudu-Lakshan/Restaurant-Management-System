package lk.ijse.fx.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.fx.business.BOFactory;
import lk.ijse.fx.business.custom.ManageEmployeeBO;
import lk.ijse.fx.dto.EmployeeDTO;
import lk.ijse.fx.main.AppInitializer;
import lk.ijse.fx.view.util.EmployeeTM;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class EmployeeFormController {


    @FXML
    private TableView<EmployeeTM> tblEmployee;
    @FXML
    private JFXTextField txtEmpId;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtAge;
    @FXML
    private JFXTextField txtMobile;
    @FXML
    private RadioButton rdbMale;
    @FXML
    private RadioButton rdbFemale;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXComboBox<String> cmbJob;
    @FXML
    private AnchorPane root;

    private ManageEmployeeBO manageEmployeeBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_EMPLOYEE);
//======================================Employee Appinitalizer====================================================//
    public void initialize(){

        tblEmployee.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        tblEmployee.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("employee_name"));
        tblEmployee.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblEmployee.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("age"));
        tblEmployee.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tblEmployee.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("job"));
        tblEmployee.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("gender"));

        ObservableList<String> joblist = cmbJob.getItems();
        joblist.add("Waiter");
        joblist.add("Chef");
        joblist.add("Cashier");

        List<EmployeeDTO> employeeDB = null;

        try {
            employeeDB = manageEmployeeBO.getEmployee();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ObservableList<EmployeeDTO> employeeDTOS = FXCollections.observableArrayList(employeeDB);
        ObservableList<EmployeeTM> tblEmpl = FXCollections.observableArrayList();

        for (EmployeeDTO e : employeeDTOS) {
            tblEmpl.add(new EmployeeTM(e.getEmployee_id(),e.getEmployee_name(),e.getAddress(),e.getAge(),e.getMobile(),
                    e.getJob(),e.getGender()));
        }

        tblEmployee.setItems(tblEmpl);

        tblEmployee.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EmployeeTM>() {
            @Override
            public void changed(ObservableValue<? extends EmployeeTM> observable, EmployeeTM oldValue, EmployeeTM selectedemployee) {
                if(selectedemployee == null){
                    return;
                }

                txtEmpId.setText(selectedemployee.getEmployee_id());
                txtName.setText(selectedemployee.getEmployee_name());
                txtAddress.setText(selectedemployee.getAddress());
                txtAge.setText(String.valueOf(selectedemployee.getAge()));
                txtMobile.setText(selectedemployee.getMobile());
                cmbJob.setValue(selectedemployee.getJob());
                if(selectedemployee.getGender().equals("Male")){
                    rdbMale.setSelected(true);
                    rdbFemale.setSelected(false);
                    return;
                }
                if(selectedemployee.getGender().equals("Female")){
                    rdbMale.setSelected(false);
                    rdbFemale.setSelected(true);
                    return;
                }


            }
        });

    }

    //==================================================================================================================//
    @FXML
    private void navigation(MouseEvent mouseEvent) throws IOException {
        AppInitializer.navigateToHome(root, (Stage) this.root.getScene().getWindow());

    }
    @FXML
    private void addNew_OnAction(ActionEvent actionEvent) {
    }
    @FXML
    private void save_OnAction(ActionEvent actionEvent) {


        if (txtEmpId.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Employee ID is empty", ButtonType.OK).showAndWait();
            txtEmpId.requestFocus();
            return;
        } else if (txtName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Employee name is empty", ButtonType.OK).showAndWait();
            txtName.requestFocus();
            return;
        } else if (txtAddress.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Address is empty", ButtonType.OK).showAndWait();
            txtAddress.requestFocus();
            return;
        } else if (txtAge.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Age is empty", ButtonType.OK).showAndWait();
            txtAge.requestFocus();
            return;
        } else if (txtMobile.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Mobile Number is empty", ButtonType.OK).showAndWait();
            txtMobile.requestFocus();
            return;
        } else if (cmbJob.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Select the Job type", ButtonType.OK).showAndWait();
            return;
        } else if (!rdbMale.isSelected() && !rdbFemale.isSelected()) {
            new Alert(Alert.AlertType.ERROR, "Select the Gender", ButtonType.OK).showAndWait();
            return;
        }
        System.out.println("after methods");

        String gender = "";

        System.out.println("after gender");

        if (rdbMale.isSelected()) {
            gender = "Male";
            System.out.println("male selected");
        } else {
            gender = "Female";
            System.out.println("female selected");
        }

        if(tblEmployee.getSelectionModel().isEmpty()){
            ObservableList<EmployeeTM> empltbl = tblEmployee.getItems();

            for (EmployeeTM employeeTM : empltbl) {
                if (employeeTM.getEmployee_id().equals(txtEmpId.getText())) {
                    new Alert(Alert.AlertType.ERROR, "Duplicate Emoloyee IDs are not allowed").showAndWait();
                    txtEmpId.requestFocus();
                    return;
                }
            }

            EmployeeTM employeeTM = new EmployeeTM(txtEmpId.getText(), txtName.getText(), txtAddress.getText(), Integer.parseInt(txtAge.getText()), txtMobile.getText(), cmbJob.getValue(),gender);
            tblEmployee.getItems().add(employeeTM);
            EmployeeDTO employeeDTO = new EmployeeDTO(txtEmpId.getText(), txtName.getText(), txtAddress.getText(), Integer.parseInt(txtAge.getText()), txtMobile.getText(), cmbJob.getValue(),gender);

           boolean result = false;

            try {
                result = manageEmployeeBO.createEmployee(employeeDTO);
                System.out.println("in try resulte value is "+ result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Employee has been saved successfully", ButtonType.OK).showAndWait();
                tblEmployee.scrollTo(employeeTM);
            }else {
                new Alert(Alert.AlertType.ERROR, "Failed to save the Employee", ButtonType.OK).showAndWait();

            }
        } else {
            EmployeeTM selectedItem = tblEmployee.getSelectionModel().getSelectedItem();
            selectedItem.setEmployee_name(txtName.getText());
            selectedItem.setAddress(txtAddress.getText());
            selectedItem.setAge(Integer.parseInt(txtAge.getText()));
            selectedItem.setMobile(txtMobile.getText());
            selectedItem.setGender(gender);
            selectedItem.setJob(cmbJob.getValue());
            tblEmployee.refresh();

            boolean result = false;

            try {
                result = manageEmployeeBO.updateEmployee(new EmployeeDTO(txtEmpId.getText(), txtName.getText(), txtAddress.getText(),
                        Integer.parseInt(txtAge.getText()), txtMobile.getText(), cmbJob.getValue(),gender));

            } catch (Exception e) {
                e.printStackTrace();
            }

            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Employee has been updated successfully", ButtonType.OK).showAndWait();

            }else {
                new Alert(Alert.AlertType.ERROR, "Failed to update the employee", ButtonType.OK).showAndWait();

            }

        }
        }

    @FXML
    private void delete_OnAction(ActionEvent actionEvent) {
        Alert confirmMsg = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this item ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = confirmMsg.showAndWait();

        if (buttonType.get() == ButtonType.YES) {
            String selectedEmployee = tblEmployee.getSelectionModel().getSelectedItem().getEmployee_id();

            tblEmployee.getItems().remove(tblEmployee.getSelectionModel().getSelectedItem());
            boolean result = false;
            try {
                result = manageEmployeeBO.deleteEmployee(selectedEmployee);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!result){
                new Alert(Alert.AlertType.ERROR, "Failed to delete the employee", ButtonType.OK).showAndWait();
            }

        }

        txtName.clear();
        txtAddress.clear();
        txtEmpId.clear();
        txtMobile.clear();
        txtAge.clear();
        cmbJob.setValue(cmbJob.getPromptText());
        rdbMale.setSelected(false);
        rdbFemale.setSelected(false);

    }
}
