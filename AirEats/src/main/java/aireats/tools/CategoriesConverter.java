package aireats.tools;

import aireats.model.Categories;

import java.util.List;

public class CategoriesConverter implements ObjectConverter {

    // strs format: restaurant id, categories 1, 2, 3.....
    @Override
    public Categories listToObject(List<String> strs) {
        String restaurantId = strs.get(0);
        strs.remove(0);
        return new Categories(restaurantId, strs);
    }
}
