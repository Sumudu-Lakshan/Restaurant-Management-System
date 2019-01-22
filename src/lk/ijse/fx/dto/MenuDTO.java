package lk.ijse.fx.dto;

public class MenuDTO extends SuperDTO {

    private String item_code;
    private String description;
    private int unit_price;
    private String meal_type;

    public MenuDTO() {
    }

    public MenuDTO(String item_code, String description, int unit_price, String meal_type) {
        this.item_code = item_code;
        this.description = description;
        this.unit_price = unit_price;
        this.meal_type = meal_type;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(int unit_price) {
        this.unit_price = unit_price;
    }

    public String getMeal_type() {
        return meal_type;
    }

    public void setMeal_type(String meal_type) {
        this.meal_type = meal_type;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "item_code='" + item_code + '\'' +
                ", description='" + description + '\'' +
                ", unit_price=" + unit_price +
                ", meal_type='" + meal_type + '\'' +
                '}';
    }
}
