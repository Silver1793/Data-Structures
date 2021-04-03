package project5;

import java.text.DecimalFormat;

import org.hamcrest.core.IsInstanceOf;
/**
 * Represents a Meteorite that has a name and id. Also sets the mass, year and location for the meteorite. 
 * @author Richard
 * @version 1.49
 * @since 1.00
 */
public class Meteorite implements Comparable<Meteorite>{
	private String name;
	private int id;
	private int year, mass;
	private Location location;
	
	/**
	 * Creates a meteorite with a name and id using the given parameters
	 * Also instantiates the variables mass and year to 0
	 * 
	 * @param name a string parameter which is what this name's value will be set to
	 * @param id a int parameter which is what this id's value will be set to
	 */
	public Meteorite(String name, int id)
	{
		if(id <= 0 || name.equals(null) || name.equals(""))
			throw new IllegalArgumentException("ID must be greater than 0 and name must have an input");
		this.name = name;
		this.id = id;
		mass = 0;
		year = 0;
		
	}
	/**
	 * Gets the id of the meteorite object
	 * 
	 * @return an int id
	 */
	public int getId()
	{
		return id;
	}
	/**
	 * Gets the name of the meteorite object
	 * 
	 * @return a string name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * Sets this classes year value to the parameter
	 * 
	 * @throws IllegalArgumentException if the input is greater than 2020(the current year) or less than 0 
	 * 		   and will print out "Please enter a year that is a positive integer and less than the current year".
	 * @param year is an int which will be what this year's set to
	 */
	public void setYear(int year)
	{
		if(year < 0 || year >= 2020)
		throw new IllegalArgumentException("Please enter a year that is a positive integer and less than the current year");
		this.year = year;
	}
	/**
	 * Sets this classes mass value to the parameter
	 * 
	 * @param mass
	 * @throws IllegalArugmentException
	 */
	public void setMass(int mass)
	{
		if(mass < 0)
			throw new IllegalArgumentException("Please enter a mass that is a positive integer");
		this.mass = mass;
	}
	/**
	 * Sets this classes location value to the parameters
	 * 
	 * @param loc is a variable of Location
	 */
	public void setLocation(Location loc)
	{
		this.location = loc;
	}
	/**
	 * Gets this classes year value
	 * 
	 * @return an int representing the year value
	 */
	public int getYear()
	{
		return this.year;
	}
	/**
	 * gets this classes mass value
	 * 
	 * @return an int representing mass
	 */
	public int getMass()
	{
		return this.mass;
	}
	/**
	 * Gets this objects location value
	 * 
	 * @return a location representing location
	 */
	public Location getLocation()
	{
		return this.location;
	}
	/**
	 * Checks if an object is equal to a meteorite by seeing if the value for the names and ID are equal 
	 * Casts the object parameter to meteorite so that it is comparable to other meteorite objects
	 * 
	 * @return false if the meteorite name or id is not equal to the parameter objects name and id
	 * 		   true is the meteorites name and id are equal to the objects name and id
	 * @param o an object that will be casted to a meteorite
	 */
	@Override
	public boolean equals(Object o)
	{
		if(o == null)
			return false;
		if(!(o instanceof Meteorite))
			return false;
		Meteorite eMet = (Meteorite) o;
		if(this.name.equalsIgnoreCase(eMet.name))
			if(this.id == eMet.id)
				return true;
		return false;
			
	}
	/**
	 * The method returns a toString for every meteorite object so that it is printed out in a certain way
	 * 
	 * @return a string of the name, id, year, mass and coordinates formated with spaces inbetween each other
	 */
	public String toString()
	{
		String lat = "";
		String lon = "";
		String mass = "";
		String year = "";
		DecimalFormat df = new DecimalFormat("#0.00000");
		if(location != null)
		{
			lat = "" + df.format(location.getLatitude());
			lon = "" + df.format(location.getLongitude());
		}
		if(getMass() != 0)
		{
			mass = getMass() + "";
		}
		if(getYear() != 0)
		{
			year = getYear() + "";
		}
		return String.format("%-20s %4s %4s %6s %10s %10s", name, id, year, mass, lat, lon);//return name + " " + id + " " + getMass() + " " + getYear() + " " + getLocation(); // " LATITUDE: " + location.getLatitude() + " LONGITUDE: " + location.getLongitude();//location.getLatitude() + " " + location.getLongitude(); 
	}
	/**
	 * Compares two meteorite objects to see if they are equal or if there is one that is greater than the other by comparing the meteorites name and id
	 * 
	 * @param o takes in a meteorite object that will be compared to in the method
	 * @return -1 if the meteorite being compared to is less than the meteorite that is comparing
	 * 		    1 if the meteorite being compared to is more than the meteorite that is comparing
	 * 		    0 if the two meteorite objects have the same id and name
	 */
	@Override
	public int compareTo(Meteorite o) 
	{
		int comp = this.name.compareToIgnoreCase(o.name);
		if(comp < 0)
			return -1;
		if(comp > 0)
			return 1;
		if(comp == 0)
			comp = this.id - o.id;
		if(comp < 0)
			return -1;
		if(comp > 0)
			return 1;
		return 0;
	}

}