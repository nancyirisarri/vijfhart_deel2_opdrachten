import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

class LeesWerknemers {
	public static void main(String args[]) {
		File file = new File("j_werknemers.csv");

		Locale locNL = new Locale("NL");
		NumberFormat nf = NumberFormat.getInstance(locNL);

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNext()) {
				String curr = scanner.next();
				String[] currArray = curr.split(",");
				System.out.printf("%-12s", currArray[0]);
				System.out.printf("%-12s", currArray[1]);
				System.out.printf("%-8s", nf.format(Float.parseFloat(currArray[2])));
				System.out.printf("%-2d\n", Integer.parseInt(currArray[3]));
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}
}