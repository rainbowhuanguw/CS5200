package aireats.model;

import java.util.Arrays;
import java.util.List;

public class Categories {
    protected String RestaurantId;
    protected List<String> CategoryList;

    public Categories(String restaurantId, List<String> categoryList) {
        RestaurantId = restaurantId;
        CategoryList = categoryList;
    }

    public Categories(String restaurantId, String categoryListStr) {
        RestaurantId = restaurantId;
        CategoryList = Arrays.asList(categoryListStr.split(", "));
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

    public String getCategoryListStr() {
        return String.join(", ", CategoryList);
    }

    public void setCategoryList(List<String> categoryList) {
        CategoryList = categoryList;
    }

    @Override
    public String toString() {
        return "Categories [RestaurantId=" + RestaurantId + ", CategoryList=" + CategoryList + "]";
    }
}
