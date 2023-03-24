package aireats.tools;

import java.util.List;
import aireats.model.Recommendations;

public class RecommendationConverter implements ObjectConverter {

    // column : restaurantId, userId
    @Override
    public Recommendations listToObject(List<String> strs) {
        return new Recommendations(strs.get(0), strs.get(1));
    }
}
