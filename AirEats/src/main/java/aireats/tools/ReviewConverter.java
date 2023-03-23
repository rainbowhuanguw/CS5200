package aireats.tools;
<<<<<<< HEAD
=======

import aireats.model.Review;
>>>>>>> cbd3e85 (corrected import paths to start from AirEats)

import aireats.model.Review;
import java.sql.Timestamp;
import java.util.List;

public class ReviewConverter implements ObjectConverter {

    // column: review_id, user_id, business_id, stars, useful, funny, cool, text, date
    @Override
    public Review listToObject(List<String> strs) {
        String reviewId = strs.get(0), userId = strs.get(1), restaurantId = strs.get(2), text = strs.get(7);

        int stars = Integer.parseInt(strs.get(3));
        Double useful = Double.parseDouble(strs.get(4)),
                funny = Double.parseDouble(strs.get(5)),
                cool = Double.parseDouble(strs.get(6));
        Timestamp date = Timestamp.valueOf(strs.get(8));
        return new Review(reviewId, userId, restaurantId, stars, useful, funny, cool, text, date);
    }
}
