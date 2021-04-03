package lab2;
import java.util.Scanner;

//By Richard Li, Anjun Rajpal, Macky Dancy
//The goal of this project it to take in an integer as an age and then a word as a name and then print out
//the persons name along with their age. Their name should not have numbers and the age should not be 
//less than 0 or exceed 120.
public class Age {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter your age (a positive integer): ");
		
		
		if(!in.hasNextInt())//Checks to see if input is an integer
		{
			System.out.println("Input not an int");
			System.exit(0);
		}
		String num = in.nextLine();
		if(num.indexOf(' ') != -1)
		{
			System.out.println("Please enter one number");
			System.exit(0);
		}
		
		//String num = in.nextLine();
		/*if(num.indexOf(" ") == -1)
		{
			System.out.println("Please enter one numer");
		}*/
		
		if(Integer.parseInt(num) < 0 || Integer.parseInt(num) > 120 ) { //Checks to see if input is within bounds of 0 and 120
			System.out.println("Number is out of bounds(not in the range of 0-120)");

		}else{//prompts user for name if conditions for the age input is met
			int count = 1;
			System.out.println("Enter your name: ");
			//in.nextLine();
			String name = in.nextLine();


			while(count > 0)//Creates a loop to see if string has any numbers in it
			{
				count = 0;
				for(int i = 0; i < name.length(); i++)//Goes through the string letter by letter
				{
					if(Character.isDigit(name.charAt(i)))
						count += Integer.parseInt(name.substring(i,i+1));//If character is able to turn into an int, it will be added
				}

				if(count == 0)//If no characters were added, it breaks out of the loop
				{
					break;
				}else //prompts message and terminates program if numbers are detected to be in the string
				{
					System.out.println("Numbers are present in the name input");
					System.exit(0);

				}

                
			}

			System.out.println(name + " is " + num + " years old." );//Prints out the name with the age
			in.close();//Closes the program
		}


	}
}
/*package lab2;
import java.util.Scanner;
import java.util.*;

public class Age {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your age (a positive integer): ");
        	
        	while(in.hasNextInt() == false)
        	{
        		System.out.println("This is not a valid age");
        		in.nextLine();
        	}
        	int num = in.nextInt();
        while(num <= 0 || num >= 120) {
            System.out.println("Number is out of bounds. Enter your age(a positive integer): ");
            while(in.hasNextInt() == false)
        	{
            	in.next();
        		System.out.println("This is not a valid age");
        		in.nextLine();
        	}
            num = in.nextInt();
        }//Checks to see if the input number is within the specified range.
        
        int count = 1;
        System.out.println("Enter your name: ");
        in.nextLine();
        String name = in.nextLine();
        while(count > 0)
        {
        	count = 0;
        for(int i = 0; i < name.length(); i++)
        {
        	if(Character.isDigit(name.charAt(i)))
        		count += Integer.parseInt(name.substring(i,i+1));
        }
        	if(count == 0)
        	{
        		break;
        	}
        	else 
        	{
                System.out.println("Please remove numbers from your name ");
                //String input = in.nextLine();
                name = in.nextLine();
			}
        }
        
        System.out.println(name + " is " + num + " years old." );
        in.close();
    }
}*/
/*
String input for age error (wrong input type)
Enter multpile numbers(make sure to only enter one number)
Cannot have decimals
*/