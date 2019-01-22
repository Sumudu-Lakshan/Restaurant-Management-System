package lk.ijse.fx.dao.custom.impl;

import lk.ijse.fx.dao.CrudUtil;
import lk.ijse.fx.dao.custom.RestaurantTableDAO;
import lk.ijse.fx.entity.RestaurantTable;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestaurantTableDAOImpl implements RestaurantTableDAO {
    @Override
    public Optional<RestaurantTable> find(String tablenumber) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM RestaurantTable WHERE table_number=?", tablenumber);
        RestaurantTable table = null;
        if (rst.next()) {
            table = new RestaurantTable(rst.getString("table_number"),
                    rst.getString("table_name"),
                    rst.getInt("seats"),
                    rst.getString("status"));
        }
        return Optional.ofNullable(table);
    }

    @Override
    public Optional<List<RestaurantTable>> findAll() throws Exception {
        ArrayList<RestaurantTable> altables = new ArrayList<>();
        ResultSet rst = CrudUtil.<ResultSet>execute("SELECT * FROM RestaurantTable");
        while (rst.next()) {
            String tableNumber = rst.getString(1);
            String tableName = rst.getString(2);
            int seats = rst.getInt(3);
            String status = rst.getString(4);

            RestaurantTable table = new RestaurantTable(tableNumber,tableName,seats,status);
            altables.add(table);
        }
        return Optional.ofNullable(altables);
    }

    @Override
    public boolean save(RestaurantTable entity) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO RestaurantTable VALUES (?,?,?,?)",
                entity.getTable_number(),entity.getTable_name(),entity.getSeats(),entity.getStatus()) > 0;
    }

    @Override
    public boolean update(RestaurantTable entity) throws Exception {
        return CrudUtil.<Integer>execute("UPDATE RestaurantTable SET table_name=?,seats=?,status=? WHERE table_number=?",
                entity.getTable_name(),entity.getSeats(),entity.getStatus(),entity.getTable_number()) > 0;
    }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.<Integer>execute("DELETE FROM RestaurantTable WHERE table_number = ?",key) >0;

    }
}
