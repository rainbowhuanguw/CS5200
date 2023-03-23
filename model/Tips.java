package aireats.model;

import java.util.Date;

public class Recommendations {
	private int tipId;
	private string userId;
	private string restaurantId;
	private int complimentCount;
	private Date date;
	private string context;

	public Recommendations(int tipId) {
		this.tipId = tipId;
	}

	public Recommendations(int tipId, string userId, string restaurantId, int complimentCount, Date date,
			string context) {
		this.tipId = tipId;
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.complimentCount = complimentCount;
		this.date = date;
		this.context = context;
	}

	public Recommendations(string userId, string restaurantId, int complimentCount, Date date, string context) {
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.complimentCount = complimentCount;
		this.date = date;
		this.context = context;
	}

	public int getTipId() {
		return this.tipId;
	}

	public void setTipId(int tipId) {
		this.tipId = tipId;
	}

	public string getUserId() {
		return this.userId;
	}

	public void setUserId(string userId) {
		this.userId = userId;
	}

	public string getRestaurantId() {
		return this.restaurantId;
	}

	public void setRestaurantId(string restaurantId) {
		this.restaurantId = restaurantId;
	}

	public int getComplimentCount() {
		return this.complimentCount;
	}

	public void setComplimentCount(int complimentCount) {
		this.complimentCount = complimentCount;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public string getContext() {
		return this.context;
	}

	public void setContext(string context) {
		this.context = context;
	}

}