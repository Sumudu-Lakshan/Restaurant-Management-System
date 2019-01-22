package lk.ijse.fx.dao.custom.impl;

import lk.ijse.fx.dao.CrudUtil;
import lk.ijse.fx.dao.custom.OrderDetailDAO;
import lk.ijse.fx.entity.OrderDetail;
import lk.ijse.fx.entity.OrderDetailPK;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public Optional<OrderDetail> find(OrderDetailPK key) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM OrderDetail WHERE order_id=? AND item_code=?",
                key.getOrder_id(),key.getItem_code());
        OrderDetail od = null;
        if (rst.next()) {
            String orderId = rst.getString("order_id");
            String itemCode = rst.getString("item_code");
            int qty = rst.getInt("qty");
            int unitPrice = rst.getInt("unit_price");
            od = new OrderDetail(orderId, itemCode, qty, unitPrice);
        }
        return Optional.ofNullable(od);
    }

    @Override
    public Optional<List<OrderDetail>> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM OrderDetail");
        List<OrderDetail> allOrderDetails = new ArrayList<>();

        if (rst.next()) {
            String orderId = rst.getString("order_id");
            String itemCode = rst.getString("item_code");
            int qty = rst.getInt("qty");
            int unitPrice = rst.getInt("unit_price");
            OrderDetail orderDetail = new OrderDetail(orderId, itemCode, qty, unitPrice);
            allOrderDetails.add(orderDetail);
        }

        return Optional.ofNullable(allOrderDetails);
    }

    @Override
    public boolean save(OrderDetail entity) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO OrderDetail VALUES (?,?,?,?)",
                entity.getOrderDetailPK().getOrder_id(), entity.getOrderDetailPK().getItem_code(), entity.getQty(), entity.getUnit_price()) > 0;
    }

    @Override
    public boolean update(OrderDetail entity) throws Exception {
        return CrudUtil.<Integer>execute("UPDATE OrderDetail SET qty=?, unit_price=? WHERE order_id=? AND item_code=?",
                entity.getQty(), entity.getUnit_price(), entity.getOrderDetailPK().getOrder_id(), entity.getOrderDetailPK().getItem_code()) > 0;
    }

    @Override
    public boolean delete(OrderDetailPK key) throws Exception {
        return CrudUtil.<Integer>execute("DELETE FROM OrderDetail WHERE order_id=? AND item_code=?",
                key.getOrder_id(), key.getItem_code()) > 0;
    }
}
