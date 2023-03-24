package aireats.model;

import java.util.Date;

public class Tips {
	private Integer tipId;
	private String userId;
	private String restaurantId;
	private Integer complimentCount;
	private Date date;
	private String context;

	public Tips(Integer tipId) {
		this.tipId = tipId;
	}

	public Tips(Integer tipId, String userId, String restaurantId, Integer complimentCount, Date date,
			String context) {
		this.tipId = tipId;
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.complimentCount = complimentCount;
		this.date = date;
		this.context = context;
	}

	public Tips(String userId, String restaurantId, Integer complimentCount, Date date, String context) {
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.complimentCount = complimentCount;
		this.date = date;
		this.context = context;
	}

	public Integer getTipId() {
		return this.tipId;
	}

	public void setTipId(Integer tipId) {
		this.tipId = tipId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRestaurantId() {
		return this.restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Integer getComplimentCount() {
		return this.complimentCount;
	}

	public void setComplimentCount(Integer complimentCount) {
		this.complimentCount = complimentCount;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

}