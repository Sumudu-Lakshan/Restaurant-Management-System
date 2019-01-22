package lk.ijse.fx.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO extends SuperDTO{

    private String order_id;
    private LocalDate date;
    private String table_number;
    private String employee_id;
    private String dining;

    private String itemcode;
    private String description;
    private int quantity;
    private int unitPrice;

    private List<OrderDTO> orderDetails = new ArrayList<>();
    private String subTotal;

    public List<OrderDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public OrderDTO() {
    }

    public OrderDTO(String order_id, LocalDate date, String table_number, String employee_id, String dining, String itemcode, String description, int quantity, int unitPrice) {
        this.order_id = order_id;
        this.date = date;
        this.table_number = table_number;
        this.employee_id = employee_id;
        this.dining = dining;
        this.itemcode = itemcode;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;

    }

    public OrderDTO(String order_id, LocalDate date, String table_number, String employee_id, String dining,String subTotal) {
        this.order_id = order_id;
        this.date = date;
        this.table_number = table_number;
        this.employee_id = employee_id;
        this.dining = dining;
        this.subTotal = subTotal;
    }

    public OrderDTO(String order_id, LocalDate date, String table_number, String employee_id, String dining,List<OrderDTO> orderDetails) {
        this.order_id = order_id;
        this.date = date;
        this.table_number = table_number;
        this.employee_id = employee_id;
        this.dining = dining;
        this.orderDetails = orderDetails;
    }

    public OrderDTO(String itemcode, String description, int quantity, int unitPrice) {
        this.itemcode = itemcode;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTable_number() {
        return table_number;
    }

    public void setTable_number(String table_number) {
        this.table_number = table_number;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getDining() {
        return dining;
    }

    public void setDining(String dining) {
        this.dining = dining;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }
}
