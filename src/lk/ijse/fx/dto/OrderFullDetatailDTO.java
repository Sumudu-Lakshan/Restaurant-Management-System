package lk.ijse.fx.dto;

import java.time.LocalDate;

public class OrderFullDetatailDTO extends SuperDTO{

    private String order_id;
    private LocalDate date;
    private String dining;
    private String table;
    private String waiter;
    private int subTotal;

    public OrderFullDetatailDTO() {
    }

    public OrderFullDetatailDTO(String order_id, LocalDate date, String dining, String table, String waiter, int subTotal) {
        this.order_id = order_id;
        this.date = date;
        this.dining = dining;
        this.table = table;
        this.waiter = waiter;
        this.subTotal = subTotal;
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

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getWaiter() {
        return waiter;
    }

    public void setWaiter(String waiter) {
        this.waiter = waiter;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }
}
