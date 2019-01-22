package lk.ijse.fx.dao.custom.impl;

import lk.ijse.fx.dao.CrudUtil;
import lk.ijse.fx.dao.custom.PaymentDAO;
import lk.ijse.fx.entity.Payment;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public Optional<Payment> find(String paymentid) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Payment WHERE payment_id=?", paymentid);
        Payment payment = null;
        if (rst.next()) {
            payment = new Payment(rst.getString("payment_id"),
                    rst.getString("order_id"),
                    rst.getString("payment_method"),
                    rst.getInt("total"));
        }
        return Optional.ofNullable(payment);
    }

    @Override
    public Optional<List<Payment>> findAll() throws Exception {
        ArrayList<Payment> alpayments = new ArrayList<>();
        ResultSet rst = CrudUtil.<ResultSet>execute("SELECT * FROM Payment");
        while (rst.next()) {
            String paymentid = rst.getString(1);
            String orderid = rst.getString(2);
            String paymentmethod = rst.getString(3);
            int total = rst.getInt(4);

            Payment payment = new Payment(paymentid,orderid,paymentmethod,total);
            alpayments.add(payment);
        }
        return Optional.ofNullable(alpayments);
    }

    @Override
    public boolean save(Payment entity) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO Payment VALUES (?,?,?,?)",
                entity.getPayment_id(),entity.getOrder_id(),entity.getPayment_method(),entity.getTotal()) > 0;

    }

    @Override
    public boolean update(Payment entity) throws Exception {
       return false;
    }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.execute("DELETE FROM Payment WHERE payment_id = ?",key);
    }

    @Override
    public int count() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT COUNT(*) FROM Payment");
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }
}
