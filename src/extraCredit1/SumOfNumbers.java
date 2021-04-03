package extraCredit1;

public class SumOfNumbers {

	public static int SumOfNumbers(String str) 
	{
		int num = 0;
		int count = 0;
		int sum = 0;
		for(int i = 0; i < str.length(); i++)
		{
			if(Character.isDigit(str.charAt(i)))
			{
				for(int j = i; j < str.length(); j++)
				{
					if(!Character.isDigit(str.charAt(j)))///11 22 33
					{
						break;
					}
					else 
					{
						count++;
					}
				}
				if(Character.isDigit(str.charAt(i)) && count < str.length())
					sum += Integer.parseInt(str.substring(i, count));
				i += count;
			}
		}
		return sum;
	}
	
	public static void main(String[] args)
	{
		String str = "rr1";
		System.out.println(SumOfNumbers(str));
		//System.out.println(str.charAt(2));
		//ss223xx
	}

}
