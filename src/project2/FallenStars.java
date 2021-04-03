package project2;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * The main method for project2. Reads an inputed file and gives errors if the file is unreadable or missing. 
 * Adds all the elements from the given file into an array list of type meteorite. Then asks the user to 
 * search through the list by entering a mass, year or location. Returns a linked list for elemetns within the same mass range +- 10,
 * a linked list for elements with the same inputed year or an element that is closest to the inputed location.
 * If the enquire is not valid, a message will show up saying it is not valid.
 * @author Richard
 *
 */
public class FallenStars {  

	public static void main(String[] args) {
		if(args.length == 0)
		{
			System.err.println("The program does not exitst");
			System.exit(1);
		}
		
		File metFile = new File(args[0]);
		
		if(!metFile.exists())
		{
			System.err.println("The file " + metFile.getAbsolutePath() + " Does not exist");
			System.exit(1);
		}
		
		if(!metFile.canRead())
		{
			System.err.println("The file " + metFile.getAbsolutePath() + " Cannot be opened for reading");
			System.exit(1);
		}
		
		Scanner inMet = null;
		
		try 
		{
			inMet = new Scanner(metFile);
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println("The file " + metFile.getAbsolutePath() + " cannot be opened for reading");
			System.exit(1);
		}
		
		MeteoriteList metList = new MeteoriteList();
		Location loc = null;
		
		String line = inMet.nextLine();
		//line = inMet.nextLine();
		while(inMet.hasNextLine()) 
		{
			line = inMet.nextLine();
				//splitCSVLine(line).get(0);
				//Integer.parseInt(splitCSVLine(line).get(1));
			Meteorite m = new Meteorite(splitCSVLine(line).get(0), Integer.parseInt(splitCSVLine(line).get(1)));
			
			try {
			 loc = new Location(Double.parseDouble(splitCSVLine(line).get(7)), Double.parseDouble(splitCSVLine(line).get(8)));
				m.setLocation(loc);

			}
			catch (NumberFormatException e)
			{
				m.setLocation(null);
				//continue;
			}
			catch(IllegalArgumentException e)
			{
				m.setLocation(null);
				//continue;
			}
			try {
				m.setMass(Integer.parseInt(splitCSVLine(line).get(4)));
			}
			catch(NumberFormatException e)
			{
				m.setMass(0);
				//continue;
			}
			try {
				String arr[] = splitCSVLine(line).get(6).split("/");
			m.setYear(Integer.parseInt(arr[2].substring(0,4)));
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				m.setYear(0);
				//continue;
			}
			catch(IllegalArgumentException e)
			{
				m.setYear(0);
				//continue;
			}
			//m.setLocation(null);
			metList.add(m);		
		}
		MeteoriteLinkedList linkedList = new MeteoriteLinkedList();
		//System.out.println(metList.get(1));
		//System.out.println(metList.size());
		Scanner userInput  = new Scanner (System.in ); 
		String userValue = "";
		/*
		 * 	Search the database by using one of the following queries.
	  To search for meteorite nearest to a given goe-location, enter
	        location LATITUDE LONGITUDE
	  To search for meteorites that fell in a given year, enter
	        year YEAR
	  To search for meteorites with weights MASS +/- 10 grams, enter
	        mass MASS
	  To finish the program, enter
	        quit

		 */
		System.out.println("Search the database by using one of the following queries."
				+ " \nTo search for meteorite nearest to a given goe-location, enter\n" + 
				"	location LATITUDE LONGITUDE"
				+ " \nTo search for meteorites that fell in a given year, enter\n" + 
				"	year YEAR" +
				" \nTo search for meteorites with weights MASS +/- 10 grams, enter\n" + 
				"	mass MASS"+
				"\nTo finish the program, enter\n" + 
				"	quit");
		do {
			System.out.println("\nPlease enter your enquire: ");
			userValue = userInput.nextLine();
			if(userValue.equalsIgnoreCase("quit"))
				System.exit(0);
			String arr[] = userValue.split(" ");
			
			if(arr[0].equalsIgnoreCase("mass"))
			{
				try {
				int mass = Integer.parseInt(arr[1]);
				if(metList.getByMass(mass,10) == null)
				{
					System.out.println("No elements were found for the inputted mass.");
					continue;
				}
				System.out.println(metList.getByMass(mass, 10));
				continue;
				}
				catch(NumberFormatException e)
				{
					System.err.println("Invalid input for mass");
					continue;
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					System.err.println("Invalid input for mass");
					continue;
				}
			}
			
			if(arr[0].equalsIgnoreCase("year"))
			{
				try {				
					int year = Integer.parseInt(arr[1]);
					if(metList.getByYear(year) == null)
					{
						System.out.println("No elements were found for the inputted year.");
						continue;
					}
					System.out.println(metList.getByYear(year));
					continue;
				}
				catch(NumberFormatException e)
				{
					System.err.println("Invalid input for year");
					continue;
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					System.err.println("Invalid input for year");
					continue;
				}
			}
			
			if(arr[0].equalsIgnoreCase("location"))
			{
				try
				{
				double value1 = Double.parseDouble(arr[1]);
				double value2 = Double.parseDouble(arr[2]);
				loc = new Location(value1, value2);
				System.out.println(metList.getByLocation(loc));
				}
				catch(NumberFormatException e)
				{
					System.err.println("Invalid inputs for location");
					continue;
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					System.err.println("Invalid input for location");
					continue;
				}
				catch(NullPointerException e) {
					System.err.println("list is null");
					continue;
				}
			}
			else
			{
				System.err.println("Invalid input, please try again");
			}
		}
		while(userValue != "quit");
		{
			//userInput.close();
		}
		System.exit(0);
	}
	
	
	public static ArrayList<String> splitCSVLine(String textLine){

		if (textLine == null ) return null;

		ArrayList<String> entries = new ArrayList<String>();
		int lineLength = textLine.length();
		StringBuffer nextWord = new StringBuffer();
		char nextChar;
		boolean insideQuotes = false;
		boolean insideEntry= false;

		// iterate over all characters in the textLine
		for (int i = 0; i < lineLength; i++) {
			nextChar = textLine.charAt(i);

			// handle smart quotes as well as regular quotes
			if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') {

				// change insideQuotes flag when nextChar is a quote
				if (insideQuotes) {
					insideQuotes = false;
					insideEntry = false;
				}
				else {
					insideQuotes = true;
					insideEntry = true;
				}
			}
			else if (Character.isWhitespace(nextChar)) {
				if ( insideQuotes || insideEntry ) {
					// add it to the current entry
					nextWord.append( nextChar );
				}
				else { // skip all spaces between entries
					continue;
				}
			}
			else if ( nextChar == ',') {
				if (insideQuotes){ // comma inside an entry
					nextWord.append(nextChar);
				}
				else { // end of entry found
					insideEntry = false;
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			}
			else {
				// add all other characters to the nextWord
				nextWord.append(nextChar);
				insideEntry = true;
			}

		}
		// add the last word ( assuming not empty )
		// trim the white space before adding to the list
		if (!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());
		}

		return entries;
	}

}
