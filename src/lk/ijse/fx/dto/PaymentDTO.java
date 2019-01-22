package lk.ijse.fx.dto;

public class PaymentDTO extends SuperDTO{

    private String payment_id;
    private String order_id;
    private String payment_method;
    private int total;

    public PaymentDTO() {
    }

    public PaymentDTO(String payment_id, String order_id, String payment_method, int total) {
        this.setPayment_id(payment_id);
        this.setOrder_id(order_id);
        this.setPayment_method(payment_method);
        this.setTotal(total);
    }


    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "payment_id='" + payment_id + '\'' +
                ", order_id='" + order_id + '\'' +
                ", payment_method='" + payment_method + '\'' +
                ", total=" + total +
                '}';
    }
}
