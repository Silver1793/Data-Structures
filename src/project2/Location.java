package project2;
import java.lang.Math;
/**
 * Represents a location that takes in a longitude and a latitude and can return a distance between two coordinates. Also sees 
 * if two comparing objects are equal or not in regards of their location.
 * @author Richard
 * @version 1.49
 * @since 1.0
 */
public class Location {
	
	private double longitude, latitude;
	
	/**
	 * Creates a Location using the latitude and longitude given
	 * 
	 * @throws IllegalArgumentException if latitude is greater than 90 or less than -90 or if longitude is greater than 180 or less than -180
	 * @param latitude Takes in a double which will be used to set the latitude
	 * @param longitude Takes in a double which will be used to set the longitude
	 */ 
	public Location(double latitude, double longitude) 
	{
		this.longitude = longitude;
		this.latitude = latitude;
		if(latitude > 90 || latitude < -90)
			throw new IllegalArgumentException("Illegal parameter, Latitude must be between -90 and 90");
		if(longitude > 180 || longitude < -180)
			throw new IllegalArgumentException("Illegal parameter, Longitude must be between -180 and 180");
	}
	/**
	 * Gets the distance between this Location and the given parameters Location.
	 * 
	 * @throws IllegalArgumentException if the given parameter is null. Then prints out the statement "Invalid input")
	 * @param loc takes in a location parameter that will be used to get the latitude and longitude that will be used for finding the distance of
	 * @return Returns the distance between the two locations that were accessed
	 */
	public double getDistance(Location loc) 
	{
		if(loc == (null))
			throw new IllegalArgumentException("Invalid input");
		double dLat = Math.toRadians(loc.getLatitude() - this.getLatitude());
		double dLon = Math.toRadians(loc.getLongitude() - this.getLongitude());
		
		double lat1 = Math.toRadians(this.getLatitude());
		double lat2 = Math.toRadians(loc.getLatitude());
		
		double a = Math.pow(Math.sin(dLat/2), 2) + Math.pow(Math.sin(dLon /2),2)*
				Math.cos(lat1)*Math.cos(lat2);
		double rad= 6371;
		double c = 2*Math.asin(Math.sqrt(a));
		return rad * c;
		
	}
	/**
	 * Gets a latitude value
	 * 
	 * @return the latitude value
	 */
	public double getLatitude()
	{
		return latitude;
	}
	/**
	 * Gets a longitude value
	 * 
	 * @return a longitude value;
	 */
	public double getLongitude()
	{
		return longitude;
	}
	/**
	 * The equals method first checks to see if the parameter given is null. If it is the method returns false.
	 * Then it sets a Location variable to the object inputed
	 * The method compares the latitude and longitude of this object to the inputed object by seeing if the
	 * absolute value after subtracting both latitudes from each other and both longitudes from each other is 0.
	 * 
	 * @param o an object that will be used to get the latitude and longitude from.
	 * @return false if the two values are not equal and returns true if the two values are equal
	 */
	@Override
	public boolean equals(Object o)
	{
		if(o == null)
			return false;
		if(!(o instanceof Location))
			return false;
		Location eLoc = (Location) o;
		if(Math.abs(getLatitude() - eLoc.getLatitude()) < 0.00001 
				&& Math.abs(getLongitude() - eLoc.getLongitude()) < 0.00001)
			return true;
		return false;
	}
}
