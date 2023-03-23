package aireats.tools;

import aireats.model.Recommendation;

import java.util.List;

public class RecommendationConverter implements ObjectConverter {

    // column : restaurantId, userId
    @Override
    public Recommendation listToObject(List<String> strs) {
        return new Recommendation(strs.get(0), strs.get(1));
    }
}
