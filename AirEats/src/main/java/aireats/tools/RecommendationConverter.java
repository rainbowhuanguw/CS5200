package aireats.tools;

import java.util.List;

public class RecommendationConverter implements ObjectConverter {

    // column : restaurantId, userId
    @Override
    public Recommendation listToObject(List<String> strs) {
        return new Recommendation(strs.get(0), strs.get(1));
    }
}
