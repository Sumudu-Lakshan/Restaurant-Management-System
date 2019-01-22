package lk.ijse.fx.entity;


import java.time.LocalDate;

public class Order extends SuperEntity{

    private String order_id;
    private LocalDate date;
    private String table_number;
    private String employee_id;
    private String dining;


    public Order() {
    }

    public Order(String order_id, LocalDate date, String table_number, String employee_id, String dining) {
        this.order_id = order_id;
        this.date = date;
        this.table_number = table_number;
        this.employee_id = employee_id;
        this.dining = dining;
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

    @Override
    public String toString() {
        return "Order{" +
                "order_id='" + order_id + '\'' +
                ", date=" + date +
                ", table_number='" + table_number + '\'' +
                ", employee_id='" + employee_id + '\'' +
                ", dining='" + dining + '\'' +
                '}';
    }
}
