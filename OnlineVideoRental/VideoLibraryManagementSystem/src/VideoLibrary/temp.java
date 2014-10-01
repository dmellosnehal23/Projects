package VideoLibrary;
import VideoLibrary.Validations;
public class temp {
		
	public static void main(String args[])
	{
		Validations obj=new Validations();
		int inte=10;
		String name="abhi1";
		boolean validString=obj.validString("abhi1");
				
		if(obj.validString(name)&&obj.validInt(inte))
		{
			System.out.println("in if");
		}
		boolean validInt=obj.validInt(123);
		
		
		boolean validZipCode=obj.validZipCode(12345);
		
		
		boolean validSSN=obj.validSSN(123456789);
		
		
		boolean validEmail=obj.validEmail("a@a.com");
		
	}
	
}

