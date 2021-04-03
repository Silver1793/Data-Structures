package project6;
import java.util.*; 

/**
 * Provides comparison of String objects by length as the primary key 
 * and then according to their natural ordering as the secondary key.
 */
public class LengthComparator implements Comparator<String> {
    public int compare(String obj1, String obj2) {
        if (obj1.length() == obj2.length() )
            return obj1.compareTo(obj2); 
        else 
            return obj1.length() - obj2.length(); 
    }
}