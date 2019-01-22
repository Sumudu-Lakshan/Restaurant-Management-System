package lk.ijse.fx.view.util;

public class MenuTM {

    private String item_code;
    private String description;
    private int unit_price;
    private String meal_type;

    public MenuTM() {
    }

    public MenuTM(String item_code, String description, int unit_price, String meal_type) {
        this.setItem_code(item_code);
        this.setDescription(description);
        this.setUnit_price(unit_price);
        this.setMeal_type(meal_type);
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
        return "MenuTM{" +
                "item_code='" + item_code + '\'' +
                ", description='" + description + '\'' +
                ", unit_price=" + unit_price +
                ", meal_type='" + meal_type + '\'' +
                '}';
    }
}
