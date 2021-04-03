package project5;
import java.util.*;
/**
 * A comparator that sorts elements based on their mass
 * @author Richard
 *
 */
public class MassComparator implements Comparator<Meteorite>{
	/**
	 * Sort elements based on their mass. If an element has the same mass as another element, it will use the compareTo method to compare the two elements.
	 * @return an int of the mass of the first Meteorite subtracted by the mass of the second Meteorite
	 */
	@Override
	public int compare(Meteorite o1, Meteorite o2) {
		if(o1.getMass() == o2.getMass())
			return o1.compareTo(o2); //Uses the compareTo method in case both meteorites have the same mass
		else {
			return o1.getMass()- o2.getMass();
		}
	}
}
