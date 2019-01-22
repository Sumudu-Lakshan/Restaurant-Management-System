package lk.ijse.fx.business.custom;

import lk.ijse.fx.business.SuperBO;
import lk.ijse.fx.dao.SuperDAO;
import lk.ijse.fx.dto.RestaurantTableDTO;

import java.util.List;

public interface ManageRestaurantTableBO extends SuperBO {
    List<RestaurantTableDTO> getTables() throws Exception;

    boolean createTable(RestaurantTableDTO dto) throws Exception;

    boolean updateTable(RestaurantTableDTO dto) throws Exception;

    boolean deleteTable(String table_number) throws Exception;

    RestaurantTableDTO findTable(String table_number) throws Exception;
}
