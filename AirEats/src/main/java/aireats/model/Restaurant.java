package aireats.model;

/**
 * Administrators is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Persons}).
 */
public class Restaurant {
    private String restaurantId;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
    private Double latitude;
    private Double longitude;
    private Double stars;
    private Hours hours;
    private Attributes attributes;
    private Categories categories;

    public Restaurant(String restaurantId, String name, String address, String city, String state, String zip,
            Double latitude, Double longitude, Double stars) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.latitude = latitude;
        this.longitude = longitude;
        this.stars = stars;
    }

    public Restaurant(String restaurantId, String name, String address, String city, String state, String zip,
            Double latitude, Double longitude, Double stars, Hours hours, Attributes attributes,
            Categories categories) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.latitude = latitude;
        this.longitude = longitude;
        this.stars = stars;
        this.hours = hours;
        this.attributes = attributes;
        this.categories = categories;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getStars() {
        return stars;
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }

    public Hours getHours() {
        return hours;
    }

    public void setHours(Hours hours) {
        this.hours = hours;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Restaurant [restaurantId=" + restaurantId + ", name=" + name + ", address=" + address + ", city=" + city
                + ", state=" + state + ", zip=" + zip + ", latitude=" + latitude + ", longitude=" + longitude
                + ", stars=" + stars + ", hours=" + hours + ", attributes=" + attributes + ", categories=" + categories
                + "]";
    }
}