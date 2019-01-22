package lk.ijse.fx.entity;

public class OrderDetail extends SuperEntity{

    private OrderDetailPK orderDetailPK;
    private int qty;
    private int unit_price;

    public OrderDetail() {
    }

    public OrderDetail(OrderDetailPK orderDetailPK, int qty, int unit_price) {
        this.setOrderDetailPK(orderDetailPK);
        this.setQty(qty);
        this.setUnit_price(unit_price);
    }

    public OrderDetail(String order_id,String item_code,int qty,int unit_price){
        this.setOrderDetailPK(new OrderDetailPK(order_id,item_code));
        this.setQty(qty);
        this.setUnit_price(unit_price);

    }


    public OrderDetailPK getOrderDetailPK() {
        return orderDetailPK;
    }

    public void setOrderDetailPK(OrderDetailPK orderDetailPK) {
        this.orderDetailPK = orderDetailPK;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(int unit_price) {
        this.unit_price = unit_price;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailPK=" + orderDetailPK +
                ", qty=" + qty +
                ", unit_price=" + unit_price +
                '}';
    }
}
