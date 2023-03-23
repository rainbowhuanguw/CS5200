package aireats.tools;

import aireats.dal.Dao;
import aireats.dal.HostsDao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CSVReader {

    private static final String delim = ",";
    private static final HostsConverter hostsConverter = new HostsConverter();
    private static final HostsDao hostsDao = HostsDao.getInstance();
    private static final YelpUsersConverter yelpUsersConverter = new YelpUsersConverter();
    private static final CategoriesConverter categoriesConverter = new CategoriesConverter();
    private static final TipsConverter tipsConverter = new TipsConverter();
    private static final HoursConverter hoursConverter = new HoursConverter();
    private static final RestaurantConverter restaurantConverter = new RestaurantConverter();
    private static final ReviewConverter reviewConverter = new ReviewConverter();
    private static final AttributesConverter attributeConverter = new AttributesConverter();
    private static final RecommendationConverter recommendationConverter = new RecommendationConverter();

    private static final Map<String, ObjectConverter> converterMap = Map.of(
            "src/data/hosts.csv", hostsConverter,
            "users.csv", yelpUsersConverter,
            "categories.csv", categoriesConverter,
            "tips.csv", tipsConverter,
            "hours.csv", hoursConverter,
            "restaurant.csv", restaurantConverter,
            "review.csv", reviewConverter,
            "attributes.csv", attributeConverter,
            "recommendation.csv", recommendationConverter
    );
    private static final Map<String, Dao> daoMap = Map.of(
            "src/data/hosts.csv", hostsDao
    );


    public static void read(String csvPath) throws IOException {
        String line;
        BufferedReader br = new BufferedReader(new FileReader(csvPath));
        ObjectConverter converter = converterMap.get(csvPath);
        Dao dao = daoMap.get(csvPath);
        boolean isHeader = true;

        while ((line = br.readLine()) != null) {
            List<String> strs = List.of(line.split(delim));
            // skip header
            if (isHeader) {
                isHeader = false;
                continue;
            }
            try {
                dao.create(converter.listToObject(strs));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}