package aireats.tools;

import java.util.List;

public class RestaurantConverter implements ObjectConverter {
    // columns: restaurant id, name, address, city, state, zip, latitude, longitude, stars, review_count, is_open
    @Override
    public Restaurant listToObject(List<String> strs) {
        String restaurantId = strs.get(0), name = strs.get(1), address = strs.get(2),
                city = strs.get(3), state = strs.get(4), zipcode = strs.get(5);

        Double latitude = Double.parseDouble(strs.get(6)),
                longitude = Double.parseDouble(strs.get(7)),
                stars = Double.parseDouble(strs.get(6));

        return new Restaurant(restaurantId, name, address, city, state, zipcode, latitude, longitude, stars);
    }
}
