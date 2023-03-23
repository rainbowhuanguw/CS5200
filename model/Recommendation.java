package aireats.models;

public class Recommendation {
    private int recommendationId;
    private string restaurantId;
    private string userId;

    public Recommendation(int recommendationId) {
        this.recommendationId = recommendationId;
    }

    public Recommendation(int recommendationId, string restaurantId, string userId) {
        this.recommendationId = recommendationId;
        this.restaurantId = restaurantId;
        this.userId = userId;
    }

    public Recommendation(string restaurantId, string userId) {
        this.restaurantId = restaurantId;
        this.userId = userId;
    }

    public int getRecommendationId() {
        return this.recommendationId;
    }

    public void setRecommendationId(int recommendationId) {
        this.recommendationId = recommendationId;
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
