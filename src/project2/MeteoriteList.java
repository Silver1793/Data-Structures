package project2;
import java.util.ArrayList;

/**
 * A program that represents a Meteorite list. Can get elements in the list by mass withen a certain range, by year for the specific year or 
 * by finding the closest meteorite object given a location.
 * @author Richard
 * @version 1.59
 * @since 1.00
 */
public class MeteoriteList extends ArrayList<Meteorite>
{
	//MeteoriteList metList;
	
	/**
	 * Creates a meteorite list without taking any parameters. 
	 * Instantiates a meteorite linked list to this class
	 */
	public MeteoriteList()
	{
		//metList = this;
	}
	/**
	 * Gets all of the elements that have a mass within the parameter of delta.
	 * Adds all of these elements to a linked list.
	 * 
	 * 
	 * @param mass an int value that will be used to check if an object has a mass close to the parameter
	 * @param delta and int value that will be the range of acceptable masses from the mass parameter
	 * @return a linked list of all of the valid elements that have masses withen the delta parameter starting from the mass parameter
	 * 		   If no elements were added the method will return null.
	 * @throws IllegalArgumentException if the mass parameter is less than 0 or if the delta parameter is less than or equal to 0
	 * 		   it will print out "This input for mass is not valid".
	 */
	public MeteoriteLinkedList getByMass(int mass, int delta)
	{
		MeteoriteLinkedList metLinkedList = new MeteoriteLinkedList();
		int count = 0;
		if(mass < 0 || delta <= 0)
			throw new IllegalArgumentException("This input for mass is not valid");
		if(this.size() == 0) {
			return null;
		}
		for(int i = 0; i < this.size(); i++) {			
		if(this.get(i).getMass() <= mass + delta && this.get(i).getMass() >= mass - delta && this.get(i).getMass() != 0)
		{
			metLinkedList.add(this.get(i));
			count++;
		}
		}
		if(count == 0)
			return null;
		return metLinkedList;
	}
	/**
	 * Goes through a list and sees which objects coordinates is closest the the ones in the parameter
	 * Gets the index of the element that is closest in the list
	 * 
	 * @param loc an object of Location that will be used to find the distance between the loc parameter and the objects location
	 * @return the element that is closest to the coordinates provided
	 * @throws IllegalArugmentExcepption if the loc parameter is null the exception will be thrown and 
	 * 		   the statement "location is null" will be printed
	 */
	public Meteorite getByLocation(Location loc) 
	{
		if(loc == (null))
			throw new IllegalArgumentException("location is null");
		if(this.size() == 0) {
			return null;
		}
		int count = 0;
		int k =0; 
		double total = 0;
		while(this.get(k).getLocation() == null){
			k++;
		}
		total = loc.getDistance(this.get(k).getLocation());
		for(int i = 0; i < this.size(); i++)
		{
			//try {
			if(this.get(i).getLocation() == (null))
			{
				continue;
			}
			else if(total > loc.getDistance(this.get(i).getLocation()))
			{
				total = loc.getDistance(this.get(i).getLocation());
				count = i;
			}
			/*}
			catch(IllegalArgumentException e)
			{
				continue;
			}*/
		}
		return this.get(count);
	}
	/**
	 * Gets all the elements that have the same year as the year parameter and adds them to a linked list
	 * @param year this is the only parameter in the getByYear method
	 * @throw IllegalArgumentException if the year parameter is less than or equal to 0 or if the year parameter is greater than the current year
	 * 		  prints out the statement "This is an invalid input for year".
	 * @return A linked list with all of the elements that have the same year as the parameter
	 * 		   If no elements were added, null will be returned
	 */
	public MeteoriteLinkedList getByYear(int year)
	{
		int count = 0;
		MeteoriteLinkedList metLinkedList = new MeteoriteLinkedList();
		if(year <= 0)
			throw new IllegalArgumentException("This is an invalid input for year");
		if(this.size() == 0) {
			return null;
		}
		for(int i = 0; i < this.size(); i++)
		{
			if(this.get(i).getYear() == year) //&& metList.get(i).getYear() != 0)
			{
				metLinkedList.add(this.get(i));
				count++;
			}
		}
		if(count == 0)
			return null;
		return metLinkedList;
	}
	
}
