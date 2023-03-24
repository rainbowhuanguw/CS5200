package aireats.tools;

import aireats.model.Review;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReviewConverter implements ObjectConverter {

    // column: review_id, user_id, business_id, stars, useful, funny, cool, text, date
    @Override
    public Review listToObject(List<String> strs) {
        String reviewId = strs.get(0), userId = strs.get(1), restaurantId = strs.get(2), text = strs.get(7);
        try {
        	int stars = Integer.parseInt(strs.get(3));
            Double useful = Double.parseDouble(strs.get(4)),
                    funny = Double.parseDouble(strs.get(5)),
                    cool = Double.parseDouble(strs.get(6));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime date = LocalDateTime.from(LocalDate.parse(strs.get(8), formatter));
            return new Review(reviewId, userId, restaurantId, stars, useful, funny, cool, text, date);
        } catch (Exception e) {
        	return null; 
        }
    }
}
