package aireats.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Administrators is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Persons}).
 */
public class Reviews {
    protected String reviewId;
    protected String userId;
    protected String restaurantId;
    protected int stars;
    protected BigDecimal useful;
    protected BigDecimal funny;
    protected BigDecimal cool;
    protected String content;
    protected Date date;

    public Reviews(String reviewId, String userId, String restaurantId, int stars, BigDecimal useful, BigDecimal funny, BigDecimal cool, String content, Date date) {
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

	public BigDecimal getUseful() {
		return useful;
	}

	public void setUseful(BigDecimal useful) {
		this.useful = useful;
	}

	public BigDecimal getFunny() {
		return funny;
	}

	public void setFunny(BigDecimal funny) {
		this.funny = funny;
	}

	public BigDecimal getCool() {
		return cool;
	}

	public void setCool(BigDecimal cool) {
		this.cool = cool;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

    
}