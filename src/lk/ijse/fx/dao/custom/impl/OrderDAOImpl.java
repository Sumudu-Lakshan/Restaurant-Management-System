package lk.ijse.fx.dao.custom.impl;

import lk.ijse.fx.dao.CrudUtil;
import lk.ijse.fx.dao.custom.OrderDAO;
import lk.ijse.fx.entity.Order;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public Optional<Order> find(String orderid) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Order WHERE order_id=?", orderid);
        Order order = null;
        if (rst.next()) {
            order = new Order(rst.getString("order_id"),
                    rst.getDate("date").toLocalDate(),
                    rst.getString("table_number"),
                    rst.getString("employee_id"),
                    rst.getString("dining"));
        }
        return Optional.ofNullable(order);
    }

    @Override
    public Optional<List<Order>> findAll() throws Exception {
        ArrayList<Order> allOrders = new ArrayList<>();
        ResultSet rst = CrudUtil.<ResultSet>execute("SELECT * FROM Order");
        while (rst.next()) {
            String id = rst.getString(1);
            Date date = rst.getDate(2);
            String tableid = rst.getString(3);
            String employeeid = rst.getString(4);
            String dining = rst.getString(5);

            Order order = new Order(id,date.toLocalDate(),tableid,employeeid,dining);
            allOrders.add(order);
        }
        return Optional.ofNullable(allOrders);

    }

    @Override
    public boolean save(Order order) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO `Order` VALUES (?,?,?,?,?)",
                order.getOrder_id(),order.getDate(),
                order.getTable_number(),order.getEmployee_id(),order.getDining()) > 0;

    }

    @Override
    public boolean update(Order order) throws Exception {
        return CrudUtil.<Integer>execute("UPDATE Order SET date=?,table_number=?,employee_id=?,dining=? WHERE order_id=?",
                order.getDate(),order.getTable_number(),order.getEmployee_id(),order.getDining(),order.getOrder_id()) > 0;

    }

    @Override
    public boolean delete(String order_id) throws Exception {
        return CrudUtil.<Integer>execute("DELETE FROM Order WHERE order_id=?", order_id) > 0;
    }

    @Override
    public int count() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT COUNT(*) FROM `Order`");
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }
}
