package lk.ijse.fx.dao.custom.impl;

import lk.ijse.fx.dao.CrudUtil;
import lk.ijse.fx.dao.custom.QueryDAO;
import lk.ijse.fx.entity.CustomEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public List<CustomEntity> findFullyDetailOfOrder() throws Exception {
        ResultSet rst = CrudUtil.<ResultSet>execute("SELECT o.order_id,o.date,o.dining,o.table_number,o.employee_id,p.total from" +
                " `order`as o inner join payment p on o.order_id = p.order_id");

        List<CustomEntity> al = new ArrayList<>();

        while (rst.next()){
            CustomEntity customEntity = new CustomEntity(rst.getString(1),
                    rst.getDate(2).toLocalDate(),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6));

            al.add(customEntity);
        }
            return al;
    }

    @Override
    public List<CustomEntity> findOrderWithItemDetails(String orderid) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT m.item_code,m.description,m.unit_price,orderdetail.qty from menu as m " +
                "inner join orderdetail on m.item_code = orderdetail.item_code where order_id = ?",orderid);

        List<CustomEntity> orderlist = new ArrayList<>();

        while (rst.next()){

            CustomEntity customEntity = new CustomEntity(rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4));

            orderlist.add(customEntity);
        }

        return orderlist;
    }
}
