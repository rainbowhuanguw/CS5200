package aireats.tools;

import aireats.dal.*;
import aireats.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Inserter {
	// split by comma but ignore commas inside quotes 
    private static final String delim = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
    
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

    private static final AirbnbConverter airbnbConverter = new AirbnbConverter();
    private static final AirbnbsDao<Airbnbs> airbnbsDao = AirbnbsDao.getInstance();

    private static final String DATA_ROOT = "src/main/java/aireats/test_data/";
    private static final String HOSTS_CSV = DATA_ROOT + "hosts.csv", 
    		USERS_CSV = DATA_ROOT + "users.csv",
            CATEGORIES_CSV = DATA_ROOT + "category.csv", 
            TIPS_CSV = DATA_ROOT + "tips.csv",
            HOURS_CSV = DATA_ROOT + "hours.csv", 
            RESTAURANTS_CSV = DATA_ROOT + "restaurants.csv",
            REVIEWS_CSV = DATA_ROOT + "reviews.csv",
            ATTRIBUTES_CSV = DATA_ROOT + "attributes.csv",
            AIRBNBS_CSV = DATA_ROOT + "airbnbs.csv";

    private static final Map<String, ObjectConverter> converterMap = Map.of(
            HOSTS_CSV, hostsConverter,
            USERS_CSV, yelpUsersConverter,
            CATEGORIES_CSV, categoriesConverter,
            TIPS_CSV, tipsConverter,
            HOURS_CSV, hoursConverter,
            RESTAURANTS_CSV, restaurantConverter,
            REVIEWS_CSV, reviewConverter,
            ATTRIBUTES_CSV, attributeConverter,
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
                Object ob = dao.create(converter.listToObject(strs));
                System.out.println("[inserted]  " + ob.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println();
    }
    
    public static void main(String[] args) throws IOException, SQLException {
        // note: run create table statements before running the following

        // load files and populate tables 
    	// do NOT populate recommendation table as it's for user input 
        load(USERS_CSV);
        load(RESTAURANTS_CSV);
        load(AIRBNBS_CSV);
        load(HOSTS_CSV);
        load(REVIEWS_CSV);
        load(TIPS_CSV);
        load(HOURS_CSV);
        load(ATTRIBUTES_CSV);
        load(CATEGORIES_CSV);

        // users
        String userId = "u1";
        YelpUsers yelpUsers = yelpUserDao.getYelpUserById(userId);
        System.out.println("find user by id:\n" + yelpUsers.toString());

        // restaurant
        String restaurantId = "rest1";
        Restaurant restaurant = restaurantDao.getRestaurantById(restaurantId);
        System.out.println("find restaurant by id:\n" + restaurant.toString());

        String restaurantName = "Denny's";
        List<Restaurant> dennys = restaurantDao.getRestaurantByName(restaurantName);
        System.out.println("find restaurant by name:\n" +
                dennys.stream().map(Object::toString).collect(Collectors.joining("\n")));

        double latitude = 34.15227, longitude = -118.10359, radius = 0.1;
        List<Restaurant> nearbys = restaurantDao.getNearbyRestaurants(latitude, longitude, radius);
        System.out.println("find nearby restaurant:\n" +
                nearbys.stream().map(Object::toString).collect(Collectors.joining("\n")));

        // aibnbs
        String airbnbId = "a1";
        Airbnbs airbnb = airbnbsDao.getAirbnbById(airbnbId);
        System.out.println("find airbnb by id:\n" + airbnb.toString());

        String airbnbName = "Zen Life at the Beach";
        List<Airbnbs> zenRooms = airbnbsDao.getAirbnbByName(airbnbName);
        System.out.println("find airbnbs by name:\n" +
        		zenRooms.stream().map(Object::toString).collect(Collectors.joining("\n")));

        String city = "Los Angeles", state = "CA";
        List<Airbnbs> laAirbnbs = airbnbsDao.getAirbnbsByCityAndState(city, state);
        System.out.println("find airbnbs by city and state:\n" +
                laAirbnbs.stream().map(Object::toString).collect(Collectors.joining("\n")));

        // hosts
        int hostId = 1;
        Hosts hosts = hostsDao.getHostByHostId(hostId);
        System.out.println("find host by id:\n" + hosts.toString());

        // reviews
        String reviewId = "r1";
        Review review = reviewsDao.getReviewById(reviewId);
        System.out.println("find review by id:\n" + review.toString());

        // tips
        int tipId = 1;
        Tips tips = tipsDao.getTipsFromTipId(tipId);
        System.out.println("find tips by id:\n" + tips.toString());

        String tipUserId = "u2";
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

        // categories
        Categories categories = categoriesDao.getCategoryByRestaurantId(restaurantId);
        System.out.println("find categories by restaurant id:\n" + categories.toString());
    }

}