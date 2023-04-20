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
    	if (restaurantId == null) {
    		return "";
    	}
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
    	if (name == null) {
    		return "";
    	}
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
    	if (address == null) {
    		return "";
    	}
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
    	if (city == null) {
    		return "";
    	}
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
    	if (state == null) {
    		return "";
    	}
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
    	if (zip == null) {
    		return "";
    	}
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
    	if (stars == null) {
    		return 0.0;
    	}
        return stars;
    }
    
    public String getStarsString() {
    	if (stars == null) {
    		return "0.0";
    	}
        return stars.toString();
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }

    public Hours getHours() {
        return hours;
    }
    
    public String getHoursString() {
    	if (hours == null) {
    		return "";
    	}
        return "" + hours;
    }

    public void setHours(Hours hours) {
        this.hours = hours;
    }

    public Attributes getAttributes() {
        return attributes;
    }
    
    public String getAttributesString() {
    	if (attributes == null) {
    		return "";
    	}
        return "" + attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public Categories getCategories() {
        return categories;
    }
    
    public String getCategoryString() {
    	if (categories == null) {
    		return "";
    	}
    	return "" + categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public String getDescription() {
    	return  "Stars: " + stars + "; " + 
    			"Category: " + categories + "; " + 
    			"Address: " + address + ", " + city + ", " + state + " " + zip + "; " + 
    		    "Hours: " + hours + "; " + 
    			"Other attributes: " + attributes;
    }

    @Override
    public String toString() {
        return "Restaurant [restaurantId=" + restaurantId + ", name=" + name + ", address=" + address + ", city=" + city
                + ", state=" + state + ", zip=" + zip + ", latitude=" + latitude + ", longitude=" + longitude
                + ", stars=" + stars + ", hours=" + hours + ", attributes=" + attributes + ", categories=" + categories
                + "]";
    }
}