package lk.ijse.fx.view.util;

import java.time.LocalDate;

public class OrderTM {
    private String order_id;
    private LocalDate date;
    private String dining;
    private String table_number;
    private String employee_id;
    private int total;

    public OrderTM() {
    }


    public OrderTM(String order_id, LocalDate date, String dining, String table_number, String employee_id, int total) {
        this.order_id = order_id;
        this.date = date;
        this.dining = dining;
        this.table_number = table_number;
        this.employee_id = employee_id;
        this.total = total;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderTM{" +
                "order_id='" + order_id + '\'' +
                ", date=" + date +
                ", dining='" + dining + '\'' +
                ", table_number='" + table_number + '\'' +
                ", employee_id='" + employee_id + '\'' +
                ", total=" + total +
                '}';
    }
}
