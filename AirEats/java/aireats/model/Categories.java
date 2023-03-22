package aireats.model;

import java.util.List;

public class Categories {
    protected String RestaurantId;
    protected List<String> CategoryList;

    public Categories(String restaurantId, List<String> categoryList) {
        super();
        RestaurantId = restaurantId;
        CategoryList = categoryList;
    }

    public String getRestaurantId() {
        return RestaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        RestaurantId = restaurantId;
    }

    public List<String> getCategoryList() {
        return CategoryList;
    }

    public void setCategoryList(List<String> categoryList) {
        CategoryList = categoryList;
    }

}
