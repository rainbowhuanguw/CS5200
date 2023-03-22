package aireats.model;

import java.math.BigDecimal;

public class Airbnbs {
    protected String airbnbId;
    protected String name;
    protected String city;
    protected String neighborhood;
    protected String state;
    protected BigDecimal latitude;
    protected BigDecimal longitude;

    // Everything included
    public Airbnbs(String airbnbId, String name, String city, String neighborhood, String state,
                  BigDecimal latitude, BigDecimal longitude) {
        this.airbnbId = airbnbId;
        this.name = name;
        this.city = city;
        this.neighborhood = neighborhood;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    
    // PK only
    public Airbnbs(String airbnbId) {
    	this.airbnbId = airbnbId;
    }
    
    // Everything except PK
    public Airbnbs(String name, String city, String neighborhood, String state,
            BigDecimal latitude, BigDecimal longitude) {
    	this.name = name;
        this.city = city;
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

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}

