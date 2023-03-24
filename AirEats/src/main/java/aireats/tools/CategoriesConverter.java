package aireats.tools;

import aireats.model.Categories;

import java.util.ArrayList;
import java.util.List;

public class CategoriesConverter implements ObjectConverter {

    // strs format: restaurant id, categories 1, 2, 3.....
    @Override
    public Categories listToObject(List<String> strs) {
        String restaurantId = strs.get(0);

        List<String> categories = new ArrayList<>();
        for (int i = 1; i < strs.size(); i++) {
        	categories.add(strs.get(i));
        }
        
        return new Categories(restaurantId, categories);
    }
}
