import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class PriemGetal extends RecursiveAction {
	private static final int THRESHOLD = 20;
	private int start;
	private int end;

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.printf("Please pass two numbers to evaluate if each number in that range is a prime with ForkJoinPool threading.\nInput was: %s.\nTry again.", Arrays.toString(args));
			System.exit(1);
		}
		
		PriemGetal task = new PriemGetal(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		ForkJoinPool fjPool = new ForkJoinPool();
		fjPool.invoke(task);
		fjPool.shutdown();
	}

	public PriemGetal(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public void compute() {
		if (end - start <= THRESHOLD) {
			for (int cnt = start; cnt <= end; cnt++) {
				boolean isPriem = isPriem(cnt);
				if (isPriem) {
					System.out.println(cnt + " Priemgetal");
				} else {
					System.out.println(cnt + " Geen priemgetal");
				}
			}
		} else {
			System.out.println("forking");
			int halfWay = ((end - start) / 2) + start;
			PriemGetal task1 = new PriemGetal(halfWay, end);
			// task1.fork();
			PriemGetal task2 = new PriemGetal(start, halfWay);
			// task2.compute();
			// task1.join();
			invokeAll(task1, task2);
		}
	}

	public static boolean isPriem(int getal) {
		boolean returnValue = true;
		int wortel = new Double(Math.sqrt(getal)).intValue();
		if (getal % 2 == 0 & getal > 2) {
			returnValue = false;
		} else {
			int deler = 3;
			while (deler <= wortel) {
				if (getal % deler == 0) {
					returnValue = false;
					break;
				} else {
					returnValue = true;
					break;
				}
			}
		}
		return returnValue;
	}
}