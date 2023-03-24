package aireats.tools;

import aireats.model.Airbnbs;

import java.util.List;

public class AirbnbConverter implements ObjectConverter {
    // column: id, name, host_id, host_name, neighborhood_group, neighborhood, latitude, longitude, room_type, price,
    // minimum_nights, number_of_reviews, review_per_month, calculated_host_listing_count, availability_365,
    // number_of_reviews_itm, license, state, city
    @Override
    public Airbnbs listToObject(List<String> strs) {
        String airbnbId = strs.get(0), name = strs.get(1), neighborHood = strs.get(5),
                state = strs.get(18), city = strs.get(19);
        try {
        	Integer hostId = Integer.parseInt(strs.get(2));
            Double latitude = Double.parseDouble(strs.get(6)), longitude = Double.parseDouble(strs.get(7));
            return new Airbnbs(airbnbId, hostId, name, city, neighborHood, state, latitude, longitude);
        } catch (Exception e) {
        	return null;
        }
    }
}
