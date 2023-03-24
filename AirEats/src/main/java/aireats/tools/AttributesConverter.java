package aireats.tools;

import aireats.model.Attributes;

import java.util.ArrayList;
import java.util.List;

public class AttributesConverter implements ObjectConverter {

    // columns : restaurant id, attributes 1,2,3
    @Override
    public Attributes listToObject(List<String> strs) {
        String restaurantId = strs.get(0);
        List<String> attributes = new ArrayList<>();
        for (int i = 1; i < strs.size(); i++) {
        	attributes.add(strs.get(i));
        }
        return new Attributes(restaurantId, strs);
    }
}
