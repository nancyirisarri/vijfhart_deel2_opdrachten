import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

class VerjaardagApp {

	public static void main(String args[]) throws ParseException {
		
		Date dateIn = new Date();
		String errorMessage = String.format("Please pass the date with format dd-MM-yyyy.\nInput was: %s.\nTry again.", Arrays.toString(args));
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			dateIn = formatter.parse(args[0]);
		} catch (ParseException e) {
			System.out.printf(errorMessage);
			System.exit(1);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.printf(errorMessage);
			System.exit(2);
		}
		
		Calendar calIn = Calendar.getInstance();
		calIn.setTime(dateIn);
		
		Locale locNL = new Locale("NL");
		
		for(int i = 1; i <= 10; i++) {
			System.out.println(calIn.get(Calendar.YEAR) + " " + 
			  calIn.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locNL));
			calIn.add(Calendar.YEAR, 1);
		}
	}
}