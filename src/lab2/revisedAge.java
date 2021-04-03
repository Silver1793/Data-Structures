package lab2;
import java.util.Scanner;

public class revisedAge {
	Scanner in = new Scanner(System.in);
	int num = 0;
	String name = null;
	public void setAge()
	{
		//int num = 0;
		System.out.println("Enter your age a positive integer");
			 num = in.nextInt();
			if(num < 0 || num > 120)
			{
				System.out.println("Please enter an ager between 0 and 120");
				setAge();
			}
			//return num;
	}
	public void setName()
	{
		//String name = null;
		int count = 0;
		System.out.println("Enter your name");
			String input = in.nextLine();
			name = in.nextLine();
			for(int i = 0; i < name.length(); i++)
			{
				if(Character.isDigit(name.charAt(i)))
					count += Integer.parseInt(name.substring(i,i+1));
			}
			if(count != 0)
			{
				System.out.println("Please enter a name without numbers");
				setName();
			}
		//return name;
	}
	public int getAge()
	{
		return num;
	}
	public String getName()
	{
		return name;
	}

	public void printString()
	{
		System.out.println(getName() + " is " + getAge() + " years old");
	}
	
	public static void main(String[] args) {
		revisedAge revised = new revisedAge();
		revised.setAge();
		revised.setName();
		revised.printString();
		
    }
}