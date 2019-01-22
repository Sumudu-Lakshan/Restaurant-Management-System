package lk.ijse.fx.entity;

public class RestaurantTable extends SuperEntity {

    private String table_number;
    private String table_name;
    private int seats;
    private String status;

    public RestaurantTable() {
    }

    public RestaurantTable(String table_number, String table_name, int seats, String status) {
        this.setTable_number(table_number);
        this.setTable_name(table_name);
        this.setSeats(seats);
        this.setStatus(status);
    }


    public String getTable_number() {
        return table_number;
    }

    public void setTable_number(String table_number) {
        this.table_number = table_number;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RestaurantTable{" +
                "table_number='" + table_number + '\'' +
                ", table_name='" + table_name + '\'' +
                ", seats=" + seats +
                ", status='" + status + '\'' +
                '}';
    }
}
