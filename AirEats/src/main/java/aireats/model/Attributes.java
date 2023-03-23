package aireats.model;

import java.util.List;

public class Attributes {
	public String restaurantId;
	public List<String> attributes;

	public Attributes(String restaurantId, List<String> attributes) {
		this.restaurantId = restaurantId;
		this.attributes = attributes;
	}

	public Attributes(String restaurantId, String attributesStr) {
		this.restaurantId = restaurantId;
		this.attributes = Arrays.asList(attributesStr.split(", "));
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
	 * @return the attributes
	 */
	public List<String> getAttributes() {
		return attributes;
	}

	public String getAttributesStr() {
		return String.join(", ", attributes);
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public String toString() {
		return "Attributes [restaurantId=" + RestaurantId + ",
		attributes=" + attributes + "]";
	}
}