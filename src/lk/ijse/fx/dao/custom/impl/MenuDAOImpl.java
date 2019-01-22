package lk.ijse.fx.dao.custom.impl;

import lk.ijse.fx.dao.CrudUtil;
import lk.ijse.fx.dao.custom.MenuDAO;
import lk.ijse.fx.entity.Menu;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuDAOImpl implements MenuDAO {
    @Override
    public Optional<Menu> find(String item_id) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Menu WHERE item_code=?", item_id);
        Menu m = null;
        if (rst.next()) {
            m = new Menu(rst.getString("item_code"),
                    rst.getString("description"),
                    rst.getInt("unit_price"),
                    rst.getString("meal_type"));
        }
        return Optional.ofNullable(m);
    }

    @Override
    public Optional<List<Menu>> findAll() throws Exception {

        ArrayList<Menu> allItems = new ArrayList<>();
        ResultSet rst = CrudUtil.<ResultSet>execute("SELECT * FROM Menu");
        while (rst.next()) {
            String code = rst.getString(1);
            String description = rst.getString(2);
            int unitprice = rst.getInt(3);
            String meal = rst.getString(4);

            Menu menu = new Menu(code,description,unitprice,meal);
            allItems.add(menu);
        }
        return Optional.ofNullable(allItems);
    }

    @Override
    public boolean save(Menu menu) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO Menu VALUES (?,?,?,?)",menu.getItem_code(),menu.getDescription(),menu.getUnit_price(),menu.getMeal_type()) > 0;
    }

    @Override
    public boolean update(Menu menu) throws Exception {
        return CrudUtil.<Integer>execute("UPDATE Menu SET description=?,unit_price=?,meal_type=? WHERE item_code=?",
                menu.getDescription(),menu.getUnit_price(),menu.getMeal_type(),menu.getItem_code()) > 0;
    }

    @Override
    public boolean delete(String item_code) throws Exception {
        return CrudUtil.<Integer>execute("DELETE FROM Menu WHERE item_code=?", item_code) > 0;
    }
}
