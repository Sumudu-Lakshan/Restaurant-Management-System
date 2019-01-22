package lk.ijse.fx.dao.custom;

import lk.ijse.fx.dao.CrudDAO;
import lk.ijse.fx.entity.Payment;

public interface PaymentDAO extends CrudDAO <Payment,String> {

    int count() throws Exception;

}
