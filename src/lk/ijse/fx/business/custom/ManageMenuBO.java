package lk.ijse.fx.business.custom;

import lk.ijse.fx.business.SuperBO;
import lk.ijse.fx.dto.MenuDTO;

import java.util.List;

public interface ManageMenuBO extends SuperBO {

    List<MenuDTO> getMenuItem() throws Exception;

    boolean createMenuItem(MenuDTO dto) throws Exception;

    boolean updateMenuItem(MenuDTO dto) throws Exception;

    boolean deleteMenuItem(String item_id) throws Exception;

    MenuDTO findMenuItem(String item_id) throws Exception;
}
