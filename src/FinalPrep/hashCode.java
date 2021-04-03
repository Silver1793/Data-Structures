package FinalPrep;

public class hashCode {

}
/* Returns a hash code for this string. The hash code for a String  object is computed as
*      s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
* using integer arithmetic, where s[i] is the i'th character of the string,
* n is the length of the string, and ^ indicates exponentiation.
* (The hash value of the empty string is zero.)
* @return  a hash code value for this object.
*/
// This is for Strings
public int hashCode() {
  int h = hash;
  if (h == 0 && value.length > 0) {
      char val[] = value;
      for (int i = 0; i < value.length; i++) {
          h = 31 * h + val[i];
      }
      hash = h;
  }
  return h;
}
//This is for Doubles
/**
 * Returns a hash code for a double} value.
 *
 * @param value the value to hash
 * @return a hash code value for a {@code double} value.
 */
public static int hashCode(double value) {
    long bits = doubleToLongBits(value);
    return (int)(bits ^ (bits >>> 32));
}

