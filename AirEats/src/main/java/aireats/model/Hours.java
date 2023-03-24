package aireats.model;

public class Hours {
	protected String restaurantId;
	protected String monday;
	protected String tuesday;
	protected String wednesday;
	protected String thursday;
	protected String friday;
	protected String saturday;
	protected String sunday;
	
	public Hours(String restaurantId, String monday, String tuesday, String wednesday, String thursday, String friday,
			String saturday, String sunday) {
		this.restaurantId = restaurantId;
		this.monday = monday;
		this.tuesday = tuesday;
		this.wednesday = wednesday;
		this.thursday = thursday;
		this.friday = friday;
		this.saturday = saturday;
		this.sunday = sunday;
	}

	/**
	 * @return the restaurantId
	 */
	public String getRestaurantId() {
		return restaurantId;
	}

	/**
	 * @param restaurantId the restaurantId to set
	 */
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	/**
	 * @return the monday
	 */
	public String getMonday() {
		return monday;
	}

	/**
	 * @param monday the monday to set
	 */
	public void setMonday(String monday) {
		this.monday = monday;
	}

	/**
	 * @return the tuesday
	 */
	public String getTuesday() {
		return tuesday;
	}

	/**
	 * @param tuesday the tuesday to set
	 */
	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}

	/**
	 * @return the wednesday
	 */
	public String getWednesday() {
		return wednesday;
	}

	/**
	 * @param wednesday the wednesday to set
	 */
	public void setWednesday(String wednesday) {
		this.wednesday = wednesday;
	}

	/**
	 * @return the thursday
	 */
	public String getThursday() {
		return thursday;
	}

	/**
	 * @param thursday the thursday to set
	 */
	public void setThursday(String thursday) {
		this.thursday = thursday;
	}

	/**
	 * @return the friday
	 */
	public String getFriday() {
		return friday;
	}

	/**
	 * @param friday the friday to set
	 */
	public void setFriday(String friday) {
		this.friday = friday;
	}

	/**
	 * @return the saturday
	 */
	public String getSaturday() {
		return saturday;
	}

	/**
	 * @param saturday the saturday to set
	 */
	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}

	/**
	 * @return the sunday
	 */
	public String getSunday() {
		return sunday;
	}

	/**
	 * @param sunday the sunday to set
	 */
	public void setSunday(String sunday) {
		this.sunday = sunday;
	}

	@Override
	public String toString() {
		return "Hours [restaurantId=" + restaurantId + ", monday=" + monday + ", tuesday=" + tuesday + ", wednesday="
				+ wednesday + ", thursday=" + thursday + ", friday=" + friday + ", saturday=" + saturday + ", sunday="
				+ sunday + "]";
	}
	
	
}