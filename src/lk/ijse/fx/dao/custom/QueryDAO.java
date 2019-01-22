package lk.ijse.fx.dao.custom;

import lk.ijse.fx.dao.SuperDAO;
import lk.ijse.fx.entity.CustomEntity;

import java.util.List;
import java.util.Optional;

public interface QueryDAO extends SuperDAO {

    //Optional<List<CustomEntity>>findFullyDetailOfOrder() throws Exception;

    List<CustomEntity> findFullyDetailOfOrder() throws Exception;

    List<CustomEntity> findOrderWithItemDetails(String orderid) throws Exception;

}
