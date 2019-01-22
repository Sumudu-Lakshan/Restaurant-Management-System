package lk.ijse.fx.entity;

import java.time.LocalDate;

public class CustomEntity {

    private String order_id;
    private LocalDate date;
    private String dining;
    private String table_number;
    private String employee_id;
    private int subTotal;

    private String item_id;
    private String description;
    private int quantity;
    private int unit_price;

    public CustomEntity() {
    }

    public CustomEntity(String order_id, LocalDate date, String dining, String table_number, String employee_id, int subTotal) {
        this.order_id = order_id;
        this.date = date;
        this.dining = dining;
        this.table_number = table_number;
        this.employee_id = employee_id;
        this.subTotal = subTotal;
    }

    public CustomEntity(String item_id, String description, int quantity, int unit_price) {
        this.setItem_id(item_id);
        this.setDescription(description);
        this.setQuantity(quantity);
        this.setUnit_price(unit_price);
    }


    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
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

    public int getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(int unit_price) {
        this.unit_price = unit_price;
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

    public String getDining() {
        return dining;
    }

    public void setDining(String dining) {
        this.dining = dining;
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

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }
}
