package aireats.tools;

import aireats.model.Attributes;

import java.util.List;

public class AttributesConverter implements ObjectConverter {

    // columns : restaurant id, attributes 1,2,3
    @Override
    public Attributes listToObject(List<String> strs) {
        String restaurantId = strs.get(0);
        strs.remove(0);
        return new Attributes(restaurantId, strs);
    }
}
