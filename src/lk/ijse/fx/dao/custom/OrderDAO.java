package lk.ijse.fx.dao.custom;

import lk.ijse.fx.dao.CrudDAO;
import lk.ijse.fx.entity.Order;

public interface OrderDAO extends CrudDAO <Order,String> {

    int count() throws Exception;
}
