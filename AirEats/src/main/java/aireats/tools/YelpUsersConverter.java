package aireats.tools;

import aireats.model.YelpUsers;

import java.sql.Timestamp;
import java.util.List;

public class YelpUsersConverter implements ObjectConverter {

    // strs column: user_id, name, review_count, yelping_since, useful, funny, cool, fans, average_stars,
    // compliment_hot, compliment_more, compliment_profile, compliment_cute, compliment_list, compliment_note,
    // compliment_plain, compliment_cool, compliment_funny, compliment_writer, compliment_photos
    @Override
    public YelpUsers listToObject(List<String> strs) {
        String userId = strs.get(0), username = strs.get(1);
        Integer reviewCount = Integer.parseInt(strs.get(2)),
                useful = Integer.parseInt(strs.get(4)),
                funny = Integer.parseInt(strs.get(5)),
                cool = Integer.parseInt(strs.get(6)),
                fans = Integer.parseInt(strs.get(7)),
                complimentHot = Integer.parseInt(strs.get(9)),
                complimentMore = Integer.parseInt(strs.get(10)),
                complimentProfile = Integer.parseInt(strs.get(11)),
                complimentCute = Integer.parseInt(strs.get(12)),
                complimentList = Integer.parseInt(strs.get(13)),
                complimentNote = Integer.parseInt(strs.get(14)),
                complimentPlain = Integer.parseInt(strs.get(15)),
                complimentCool = Integer.parseInt(strs.get(16)),
                complimentFunny = Integer.parseInt(strs.get(17)),
                complimentWriter = Integer.parseInt(strs.get(18)),
                complimentPhotos = Integer.parseInt(strs.get(19));
        Double averageStars = Double.parseDouble(strs.get(8));
        Timestamp since = Timestamp.valueOf(strs.get(3));

        return new YelpUsers(userId, username, reviewCount, since, useful, funny, cool, fans, averageStars,
                complimentHot, complimentMore, complimentProfile, complimentCute, complimentList, complimentNote,
                complimentPlain, complimentCool, complimentFunny, complimentWriter, complimentPhotos);
    }
}
