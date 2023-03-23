package aireats.model;

public class Airbnbs {
    protected String airbnbId;
    protected String name;
    protected String city;
    protected String neighborhood;
    protected String state;
    protected Double latitude;
    protected Double longitude;
    protected Integer hostId;

    // Everything included
    public Airbnbs(String airbnbId, String name, Integer hostId, String city, String neighborhood, String state,
                   Double latitude, Double longitude) {
        this.airbnbId = airbnbId;
        this.hostId = hostId;
        this.name = name;
        this.city = city;
        this.neighborhood = neighborhood;
        this.hostId = hostId;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public Airbnbs(String airbnbId) {
    	this.airbnbId = airbnbId;
    }

    // Everything except PK
    public Airbnbs(String name, Integer hostId, String city, String neighborhood, String state,
                   Double latitude, Double longitude) {
    	this.name = name;
        this.city = city;
        this.hostId = hostId;
        this.neighborhood = neighborhood;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAirbnbId() {
        return airbnbId;
    }

    public void setAirbnbId(String airbnbId) {
        this.airbnbId = airbnbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHostId() {
        return hostId;
    }

    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
}
