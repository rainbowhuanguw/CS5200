package aireats.tools;

import  aireats.dal.*;
import  aireats.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Inserter {
    private static final String delim = ",";
    private static final HostsConverter hostsConverter = new HostsConverter();
    private static final HostsDao<Hosts> hostsDao = HostsDao.getInstance();

    private static final YelpUsersConverter yelpUsersConverter = new YelpUsersConverter();
    private static final YelpUsersDao<YelpUsers> yelpUserDao = YelpUsersDao.getInstance();

    private static final CategoriesConverter categoriesConverter = new CategoriesConverter();
    private static final CategoriesDao<Categories> categoriesDao = CategoriesDao.getInstance();

    private static final TipsConverter tipsConverter = new TipsConverter();
    private static final TipsDao<Tips> tipsDao = TipsDao.getInstance();

    private static final HoursConverter hoursConverter = new HoursConverter();
    private static final HoursDao<Hours> hoursDao = HoursDao.getInstance();

    private static final RestaurantConverter restaurantConverter = new RestaurantConverter();
    private static final RestaurantsDao<Restaurant> restaurantDao = RestaurantsDao.getInstance();

    private static final ReviewConverter reviewConverter = new ReviewConverter();
    private static final ReviewsDao<Review> reviewsDao = ReviewsDao.getInstance();

    private static final AttributesConverter attributeConverter = new AttributesConverter();
    private static final AttributesDao<Attributes> attributesDao = AttributesDao.getInstance();

    private static final RecommendationConverter recommendationConverter = new RecommendationConverter();
    private static final RecommendationsDao<Recommendations> recommendationsDao = RecommendationsDao.getInstance();


    private static final AirbnbConverter airbnbConverter = new AirbnbConverter();
    private static final AirbnbsDao<Airbnbs> airbnbsDao = AirbnbsDao.getInstance();

    private static final String HOSTS_CSV = "data/hosts.csv", USERS_CSV = "data/users.csv",
            CATEGORIES_CSV = "data/category.csv", TIPS_CSV = "data/tip.csv",
            HOURS_CSV = "data/hours.csv", RESTAURANTS_CSV = "data/restaurants.csv",
            REVIEWS_CSV = "data/review_small.csv", ATTRIBUTES_CSV = "data/attributes_new.csv",
            RECOMMENDATIONS_CSV = "xxxxxx", AIRBNBS_CSV = "data/cleaned_airbnb_listings_usa.csv";

    private static final Map<String, ObjectConverter> converterMap = Map.of(
            HOSTS_CSV, hostsConverter,
            USERS_CSV, yelpUsersConverter,
            CATEGORIES_CSV, categoriesConverter,
            TIPS_CSV, tipsConverter,
            HOURS_CSV, hoursConverter,
            RESTAURANTS_CSV, restaurantConverter,
            REVIEWS_CSV, reviewConverter,
            ATTRIBUTES_CSV, attributeConverter,
            RECOMMENDATIONS_CSV, recommendationConverter,
            AIRBNBS_CSV, airbnbConverter
    );
    private static final Map<String, Dao> daoMap = Map.of(
            HOSTS_CSV, hostsDao,
            USERS_CSV, yelpUserDao,
            CATEGORIES_CSV, categoriesDao,
            TIPS_CSV, tipsDao,
            HOURS_CSV, hoursDao,
            RESTAURANTS_CSV, restaurantDao,
            REVIEWS_CSV, reviewsDao,
            ATTRIBUTES_CSV, attributesDao,
            RECOMMENDATIONS_CSV, recommendationsDao,
            AIRBNBS_CSV, airbnbsDao
    );

    private static void load(String csvPath) throws IOException {
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
    public static void main(String[] args) throws IOException, SQLException {
        // note: run create table statements before running the following

        // load files and populate tables
        load(USERS_CSV);
        load(RESTAURANTS_CSV);
        load(AIRBNBS_CSV);
        load(HOSTS_CSV);
        load(REVIEWS_CSV);
        load(TIPS_CSV);
        load(HOURS_CSV);
        load(ATTRIBUTES_CSV);
        load(CATEGORIES_CSV);
        load(RECOMMENDATIONS_CSV);

        // users
        String userId = "fJZO_skqpnhk1kvomI4dmA";
        YelpUsers yelpUsers = yelpUserDao.getYelpUserById(userId);
        System.out.println("find user by id:\n" + yelpUsers.toString());

        // restaurant
        String restaurantId = "0bPLkL0QhhPO5kt1_EXmNQ";
        Restaurant restaurant = restaurantDao.getRestaurantById(restaurantId);
        System.out.println("find restaurant by id:\n" + restaurant.toString());

        String restaurantName = "Denny's";
        List<Restaurant> dennys = restaurantDao.getRestaurantByName(restaurantName);
        System.out.println("find restaurant by name:\n" +
                dennys.stream().map(Object::toString).collect(Collectors.joining("\n")));

        double latitude = 34.15227, longitude = -118.10359, radius = 0.1;
        List<Restaurant> nearbys = restaurantDao.getNearbyRestaurants(latitude, longitude, radius);
        System.out.println("find nearby restaurant by name:\n" +
                nearbys.stream().map(Object::toString).collect(Collectors.joining("\n")));

        // aibnbs
        String airbnbId = "61153";
        Airbnbs airbnb = airbnbsDao.getAirbnbById(airbnbId);
        System.out.println("find airbnb by id:\n" + airbnb.toString());

        String airbnbName = "Hollywood  Hills Bedroom";
        List<Airbnbs> hollywoodRooms = airbnbsDao.getAirbnbByName(airbnbName);
        System.out.println("find airbnbs by name:\n" +
                hollywoodRooms.stream().map(Object::toString).collect(Collectors.joining("\n")));

        String city = "Seattle", state = "WA";
        List<Airbnbs> seattleAirbnbs = airbnbsDao.getAirbnbsByCityAndState(city, state);
        System.out.println("find airbnbs by city and state:\n" +
                seattleAirbnbs.stream().map(Object::toString).collect(Collectors.joining("\n")));

        // hosts
        int hostId = 481002317;
        Hosts hosts = hostsDao.getHostByHostId(hostId);
        System.out.println("find host by id:\n" + hosts.toString());

        // reviews
        String reviewId = "lUUhg8ltDsUZ9h0xnwY4Dg";
        Review review = reviewsDao.getReviewById(reviewId);
        System.out.println("find review by id:\n" + review.toString());

        // tips
        int tipId = 10;
        Tips tips = tipsDao.getTipsFromTipId(tipId);
        System.out.println("find tips by id:\n" + tips.toString());

        String tipUserId = "kjFgyrCvmVVGSlgWzRXILw";
        List<Tips> tipsByUser = tipsDao.getTipsFromUserId(tipUserId);
        System.out.println("find tips by user id:\n" +
                tipsByUser.stream().map(Object::toString).collect(Collectors.joining("\n")));


        List<Tips> tipsByRestaurant = tipsDao.getTipsFromRestaurantId(restaurantId);
        System.out.println("find tips by restaurant id:\n" +
                tipsByRestaurant.stream().map(Object::toString).collect(Collectors.joining("\n")));

        // hours
        Hours hours = hoursDao.getHoursByRestaurantId(restaurantId);
        System.out.println("find hours by restaurant id:\n" + hours.toString());

        // attributes
        Attributes attributes = attributesDao.getAttributesByRestaurantId(restaurantId);
        System.out.println("find attributes by restaurant id:\n" + attributes.toString());

        Attributes updatedAttributes = new Attributes(restaurantId, List.of("random", "placeholder", "attributes"));
        attributesDao.updateAttributes(updatedAttributes);
        System.out.println("update attributes: \n" + updatedAttributes.toString());
        attributesDao.updateAttributes(attributes); // IMPORTANT: restore data

        // categories
        Categories categories = categoriesDao.getCategoryByRestaurantId(restaurantId);
        System.out.println("find categories by restaurant id:\n" + categories.toString());

        List<String> newCategoriesStrs = List.of("random", "placeholder", "categories");
        List<String> originalCategories = categories.getCategoryList();
        Categories updatedCategories = categoriesDao.updateCategories(categories, newCategoriesStrs);
        System.out.println("update categories: \n" + updatedCategories.toString());
        categoriesDao.updateCategories(updatedCategories, originalCategories); // IMPORTANT: restore data

        // recommendations
        int recommendationId = 100;
        Recommendations recommendations = recommendationsDao.getRecommendationsFromRecommendationId(recommendationId);
        System.out.println("get recommendation by id: \n" + recommendations.toString());

        List<Recommendations> recommendationsFromUserId = recommendationsDao.getRecommendationsFromUserId(userId);
        System.out.println("get recommendation by user id: \n" +
                recommendationsFromUserId.stream().map(Object::toString).collect(Collectors.joining("\n")));

        List<Recommendations> recommendationsFromRestaurantId = recommendationsDao.getRecommendationsFromRestaurantId(userId);
        System.out.println("get recommendation by restaurant id: \n" +
                recommendationsFromRestaurantId.stream().map(Object::toString).collect(Collectors.joining("\n")));

    }

}