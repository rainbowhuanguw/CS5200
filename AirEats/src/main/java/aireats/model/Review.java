package aireats.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Review {

	protected String reviewId;
    protected String userId;
    protected String restaurantId;
    protected int stars;
    protected Double useful;
    protected Double funny;
    protected Double cool;
    protected String content;

	protected LocalDateTime date;

    public Review(String reviewId, String userId, String restaurantId, int stars, Double useful, Double funny, Double cool,
				  String content, LocalDateTime date) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.stars = stars;
        this.useful = useful;
        this.funny = funny;
        this.cool = cool;
        this.content = content;
        this.date = date;
    }

	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public double getUseful() {
		return useful;
	}

	public void setUseful(Double useful) {
		this.useful = useful;
	}

	public double getFunny() {
		return funny;
	}

	public void setFunny(Double funny) {
		this.funny = funny;
	}

	public double getCool() {
		return cool;
	}

	public void setCool(Double cool) {
		this.cool = cool;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
    
    @Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", userId=" + userId + ", restaurantId=" + restaurantId + ", stars="
				+ stars + ", useful=" + useful + ", funny=" + funny + ", cool=" + cool + ", content=" + content
				+ ", date=" + date + "]";
	}

}