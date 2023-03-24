package aireats.model;

public class Recommendations {
    private Integer recommendationId;
    private String restaurantId;
    private String userId;

    public Recommendations(Integer recommendationId) {
        this.recommendationId = recommendationId;
    }

    public Recommendations(Integer recommendationId, String restaurantId, String userId) {
        this.recommendationId = recommendationId;
        this.restaurantId = restaurantId;
        this.userId = userId;
    }

    public Recommendations(String restaurantId, String userId) {
        this.restaurantId = restaurantId;
        this.userId = userId;
    }

    public Integer getRecommendationId() {
        return this.recommendationId;
    }

    public void setRecommendationId(Integer recommendationId) {
        this.recommendationId = recommendationId;
    }

    public String getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

	@Override
	public String toString() {
		return "Recommendations [recommendationId=" + recommendationId + ", restaurantId=" + restaurantId + ", userId="
				+ userId + "]";
	}
    

}
