package lk.ijse.fx.business.custom.impl;

import lk.ijse.fx.business.Converter;
import lk.ijse.fx.business.custom.ManageMenuBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.custom.MenuDAO;
import lk.ijse.fx.dto.MenuDTO;

import java.util.List;

public class ManageMenuBOImpl implements ManageMenuBO {

    private MenuDAO menuDAO;

    public ManageMenuBOImpl() {
        menuDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MENU);
    }

    @Override
    public List<MenuDTO> getMenuItem() throws Exception {
        return menuDAO.findAll().map(Converter::<MenuDTO>getDTOList).get();
    }

    @Override
    public boolean createMenuItem(MenuDTO dto) throws Exception {
        return menuDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updateMenuItem(MenuDTO dto) throws Exception {
        return menuDAO.update(Converter.getEntity(dto));

    }

    @Override
    public boolean deleteMenuItem(String item_id) throws Exception {
        return menuDAO.delete(item_id);
    }

    @Override
    public MenuDTO findMenuItem(String item_id) throws Exception {
        return menuDAO.find(item_id).map(Converter::<MenuDTO>getDTO).orElse(null);

    }
}
