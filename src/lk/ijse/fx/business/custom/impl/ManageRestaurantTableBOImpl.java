package lk.ijse.fx.business.custom.impl;

import lk.ijse.fx.business.Converter;
import lk.ijse.fx.business.custom.ManageRestaurantTableBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.custom.RestaurantTableDAO;
import lk.ijse.fx.dto.RestaurantTableDTO;

import java.util.List;


public class ManageRestaurantTableBOImpl implements ManageRestaurantTableBO {

        private RestaurantTableDAO restaurantTableDAO;

    public ManageRestaurantTableBOImpl() {
        restaurantTableDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RESTAURANTTABLE);
    }

    @Override
    public List<RestaurantTableDTO> getTables() throws Exception {
        return restaurantTableDAO.findAll().map(Converter::<RestaurantTableDTO>getDTOList).get();
    }

    @Override
    public boolean createTable(RestaurantTableDTO dto) throws Exception {
        return restaurantTableDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updateTable(RestaurantTableDTO dto) throws Exception {
        return restaurantTableDAO.update(Converter.getEntity(dto));

    }

    @Override
    public boolean deleteTable(String item_id) throws Exception {
        return restaurantTableDAO.delete(item_id);
    }

    @Override
    public RestaurantTableDTO findTable(String item_id) throws Exception {
        return null;
    }
}
