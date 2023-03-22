package aireats.models;

public class Recommendations {
    private int recommendationsId;
    private string restaurantId;
    private string userId;

    public Recommendations(int recommendationsId) {
        this.recommendationsId = recommendationsId;
    }

    public Recommendations(int recommendationsId, string restaurantId, string userId) {
        this.recommendationsId = recommendationsId;
        this.restaurantId = restaurantId;
        this.userId = userId;
    }

    public Recommendations(string restaurantId, string userId) {
        this.restaurantId = restaurantId;
        this.userId = userId;
    }

    public int getRecommendationsId() {
        return this.recommendationsId;
    }

    public void setRecommendationsId(int recommendationsId) {
        this.recommendationsId = recommendationsId;
    }

    public string getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(string restaurantId) {
        this.restaurantId = restaurantId;
    }

    public string getUserId() {
        return this.userId;
    }

    public void setUserId(string userId) {
        this.userId = userId;
    }

}
