package aireats.tools;

import aireats.model.Hours;

import java.util.List;

public class HoursConverter implements ObjectConverter {

    // columns: business_id, monday, tuesday, wednesday, thursday, friday, saturday, sunday
    @Override
    public Hours listToObject(List<String> strs) {
        String restaurantId = strs.get(0), mon = strs.get(1), tues = strs.get(2), wed = strs.get(3),
                thurs = strs.get(4), fri = strs.get(5), sat = strs.get(6), sun = strs.get(7);

        return new Hours(restaurantId, mon, tues, wed, thurs, fri, sat, sun);
    }
}
