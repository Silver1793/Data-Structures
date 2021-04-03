package project5;

import java.util.Comparator;
/**
 * A comparator used to sort elements based on their year
 * @author Richard
 *
 */
public class YearComparator implements Comparator<Meteorite>{
	/**
	 * Compares elements based on their year. If their year is the same it will use the compareTo method to compare the two elements
	 */
	@Override
	public int compare(Meteorite o1, Meteorite o2) {
		if(o1.getYear() == o2.getYear())
			return o1.compareTo(o2);
		else
		return o1.getYear()-(o2.getYear());
	}
} 