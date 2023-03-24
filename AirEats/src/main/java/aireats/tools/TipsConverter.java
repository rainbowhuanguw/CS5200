package aireats.tools;

import aireats.model.Tips;

import java.sql.Timestamp;
import java.util.List;

public class TipsConverter implements ObjectConverter {
    @Override
    // columns: user_id, business_id, compliment_count, date, text
    public Tips listToObject(List<String> strs) {
        String userId = strs.get(0), restaurantId = strs.get(1), text = strs.get(4);
        int complimentCount = Integer.parseInt(strs.get(2));
        Timestamp date = Timestamp.valueOf(strs.get(3));

        return new Tips(userId, restaurantId, complimentCount, date, text);
    }
}
