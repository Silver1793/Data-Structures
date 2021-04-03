package project2;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class MeteroiteTester {
	public static void main(String[] args) {
		Location loc = new Location(89,21);
		Location loc2 = new Location (0.0 ,37.66667);
		Location input = new Location (0.0, 37.66667);
		MeteoriteList metList = new MeteoriteList();		
		Meteorite met1 = new Meteorite("a", 1);
		Meteorite met2 = new Meteorite("b", 2);
		Meteorite met3 = new Meteorite("c", 3);
		Meteorite met4 = new Meteorite("d", 1);
		Meteorite met5 = new Meteorite("e", 3);
		Meteorite met6 = new Meteorite("f", 9);
		Meteorite met7 = new Meteorite("g", 1);
		Meteorite met8 = new Meteorite("h", 2);
		Meteorite met9 = new Meteorite("a", 1);
		Meteorite met10 = new Meteorite("k", 10);
		
		DecimalFormat df = new DecimalFormat("#.#####");
		met1.setYear(1991);
		met1.setLocation(loc);
		met2.setLocation(loc2);
		met3.setLocation(loc);
		met4.setLocation(loc);
		met5.setLocation(loc);
		met6.setLocation(loc);
		
		met2.setYear(1990);
		met3.setYear(1980);
		met4.setYear(1990);
		met5.setYear(1970);
		met6.setYear(1990);
		
		
		//met9.setMass(300);
		met9.setYear(1980);
		//met9.setLocation(loc);
		
		met2.setMass(310);
		
		metList.add(met9);
		metList.add(met2);
		metList.add(met1);
		//metList.add(met3);
		//metList.add(met3);
		//metList.add(met4);
		
		//metLinkedList.add(met1);		metLinkedList.add(met2);
		//MeteoriteLinkedList metLinkedList = new MeteoriteLinkedList(metList);

		//metList.add(met9);
		//metList.add(met1);
		/*metLinkedList.add(met8);
		metLinkedList.add(met5);
		metLinkedList.add(met1);
		metLinkedList.add(met7);
		metLinkedList.add(met3);
		metLinkedList.add(met6);
		metLinkedList.add(met2);
		metLinkedList.add(met4);*/
		
		MeteoriteLinkedList metLinkedList = new MeteoriteLinkedList(metList);
		
		//System.out.println(loc2.getDistance(met8.getLocation()));
		
		//metLinkedList.remove("pine",1);
		//metLinkedList.remove("c",3);
		//metLinkedList.remove("a",1);
		//metLinkedList.remove("a",1);
		//metLinkedList.remove("d",1);
		System.out.println(metList.getByMass(10,5));
		//metLinkedList.remove("Richard", 1);
		//System.out.println();
		//for(Meteorite m: metList)
			//System.out.println(m);
		//System.out.println(metLinkedList);
		
		//metList.remove(met1);
		//System.out.println(metList);
		/*MeteroiteLinkedList linkedList = new MeteroiteLinkedList();
		File metFile = new File(args[0]);
		//Location loc = new Location(45, 45);
		MeteroiteList metList = new MeteroiteList();
		
		Scanner inMet = null;
		String ret = "";
		try {
			inMet = new Scanner(metFile);
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
		String line = inMet.nextLine();
		for(int i = 0; i < 2; i++)
		{
			line = inMet.nextLine();
		//System.out.println(splitCSVLine(line).get(1));
			Meteroite m = new Meteroite(splitCSVLine(line).get(0), Integer.parseInt(splitCSVLine(line).get(1)));
			Location loc = new Location(Double.parseDouble(splitCSVLine(line).get(7)), Double.parseDouble(splitCSVLine(line).get(8)));
			m.setMass((int)Double.parseDouble(splitCSVLine(line).get(4)));
			m.setYear(Integer.parseInt(splitCSVLine(line).get(6).substring(6, 10)));
			m.setLocation(loc);
			metList.add(m);			
		}
		System.out.println(Integer.parseInt(splitCSVLine(line).get(6).substring(6, 10)));*/
		
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

/*		Scanner userInput  = new Scanner (System.in ); 
		String userValue = "";
		MeteroiteList list = new MeteroiteList();
		MeteroiteLinkedList linkedList = new MeteroiteLinkedList();

		String line = null; 
		Scanner parseLine = null; 
		String name = null;
		int id = 0; 
		Meteroite current = null;
		
		Meteroite test = new Meteroite("Pine", 23);
		System.out.println(test.getMass());
		/*for(int i = 0; i < 3; i++)//while(inMet.hasNextLine())
		{
			line = inMet.nextLine(); 
			parseLine = new Scanner(line);
			parseLine.useDelimiter(", "); 
			name = parseLine.nextLine();
			name = name.substring(0, name.indexOf(","));
		System.out.println(name);	
		//current = new Meteroite(splitCSVLine(line).get(0), 1);
		}*/
		
		/*while(inMet.hasNextLine()) {
			try {
				line = inMet.nextLine();
				parseLine = new Scanner(line);
				parseLine.useDelimiter(", "); 
				//name = parseLine.next();
				//id = parseLine.nextInt();
				//splitCSVLine(line);
			} catch ( NoSuchElementException e) {
				System.err.println("This does not excits");
				continue;
			}
			try {
				for(int i = 0; i < splitCSVLine(line).size(); i++)
				{
				current = new Meteroite(splitCSVLine(line).get(i), id);
				list.add(current);
				}
			} catch (Exception e) {
				//nothing
			}
		}*/





/*do {
			System.out.println("Search the database by using one of the following quieries. \n"
					+ "To search for meteorite nearest to a given goe-location, enter \n"
					+ "location LATITUDE LONGITUDE \n"
					+ "To search for meteorites that fell in a given year, enter \n"
					+ "year YEAR \n"
					+ "To search for meteorites with weights MASS +/- 10 grams, enter \n"
					+ "mass MASS \n"
					+ "to finish the program, enter \n"
					+ "quit");
		}
			while(1!=1);//userValue != "quit");
			{
				System.out.println("What is your enquire");
			userValue = userInput.nextLine();
			//if(userValue.equalsIgnoreCase("quit"))
				//System.exit(0);
			if(userValue.substring(0,8).equalsIgnoreCase("LOCATION"))
					{
						int space = userValue.substring(9).indexOf(" ");
						double value1 = Double.parseDouble(userValue.substring(9, 9 + space));
						double value2 = Double.parseDouble(userValue.substring(10+space));
						Location loc = new Location(value1, value2);
						Meteroite m = metList.getByLocation(loc);
						System.out.println(metList.getByLocation(loc));
					}
			if(userValue.substring(0,4).equalsIgnoreCase("YEAR"))
			{
				int year = Integer.parseInt(userValue.substring(5));
				//linkedList = list.getByYear(year);
				System.out.println(metList.getByYear(year));
			}
				
			if(userValue.substring(0,4).equalsIgnoreCase("MASS"))
			{
				int mass = Integer.parseInt(userValue.substring(5));
				//linkedList.add(list.getByMass(mass, 10));
				System.out.println(linkedList);
			}
				System.out.println("This is not a valid enquire please try again");
		}*/
