package lk.ijse.fx.entity;

public class OrderDetailPK {

    private String order_id;
    private String item_code;

    public OrderDetailPK() {
    }

    public OrderDetailPK(String order_id, String item_code) {
        this.setOrder_id(order_id);
        this.setItem_code(item_code);
    }


    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    @Override
    public String toString() {
        return "OrderDetailPK{" +
                "order_id='" + order_id + '\'' +
                ", item_code='" + item_code + '\'' +
                '}';
    }
}
