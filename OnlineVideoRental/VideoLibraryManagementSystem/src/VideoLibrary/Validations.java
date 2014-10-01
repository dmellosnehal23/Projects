package VideoLibrary;
import java.util.regex.*;

public class Validations {

			protected boolean validString(String input) {
			boolean state = false;
			//String rg = "^([a-zA-Z])$";
			String rg="^[a-zA-Z]+$";
			try {
				Pattern pt = Pattern.compile(rg);
				if (pt.matcher(input).matches()) {
					state = true;
					System.out.println("Server Side:True String");
				}
				else{
					System.out.println("Server Side:False String");
				}
			} catch (Exception e) {
				//System.out.println("Server Side:False String");
				e.printStackTrace();
			}
			return state;
		}
		
		protected boolean validInt(int input1) {
			boolean state = false;
			String input=String.valueOf(input1);
			String rg = "^([0-9])+$";
			try {
				Pattern pt = Pattern.compile(rg);
				if (pt.matcher(input).matches()) {
					state = true;
					System.out.println("Server Side:True Int");
				}
				else{
					System.out.println("Server Side:False Integer");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return state;
		}
		
		public boolean validNumericString(String input) {
			boolean state = false;
			String rg="^[a-zA-Z0-9]+$";
			try {
				Pattern pt = Pattern.compile(rg);
				if (pt.matcher(input).matches()) {
					state = true;
					System.out.println("Server Side:True NumericString");
				}
				else{
					System.out.println("Server Side:False NumericString");
				}
			} catch (Exception e) {
				//System.out.println("Server Side:False String");
				e.printStackTrace();
			}
			return state;
		}

		protected boolean validZipCode(long input1) {
			boolean state = false;
			//String rg = "^([0-9]{3}-[0-9]{2}-[0-9]{4})$";
			String input=String.valueOf(input1);
			String rg = "^([0-9]{5}|[0-9]{9})$";
			//String rg = "^\\d{5}(-\\d{4})?$";
			try {
				
				Pattern pt = Pattern.compile(rg);
				if (pt.matcher(input).matches()) {
					state = true;
					System.out.println("Server Side:True zipcode");
				}
				else{
					System.out.println("Server Side:False ZipCode");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return state;
		}

		protected boolean validSSN(long input1) {
			boolean state = false;
			String input=String.valueOf(input1);
			String rg = "^([0-9]{9})+$";
			try {
				Pattern pt = Pattern.compile(rg);
				if (pt.matcher(input).matches()) {
					state = true;
					System.out.println("Server Side:True MembershipID");
				}
				else{
					System.out.println("Server Side:False MembershipID");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return state;
		}
		
		protected boolean validEmail(String input) {
			boolean state = false;
			
			//String rg = " ^\b[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,4}\b+$ ";
			//String rg=".+@.+\\.[a-z]+";
			String rg1="[a-zA-Z0-9_]";
			String rg=rg1 + "+@" + rg1 + "+\\." + rg1 + "+";
			try {
				Pattern pt = Pattern.compile(rg);
				if (pt.matcher(input).matches()) {
					state = true;
					System.out.println("Server Side:True Email");
				}
				else{
					System.out.println("Server Side:False EmailID");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return state;
		}

	}

