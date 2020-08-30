import java.util.concurrent.TimeUnit;

/**
 * Program that creates 2 threades, one by using Runnable interface and one by extending Thread class.
 * @author Alexander Castman alca3002
 */

public class Program {

	/**
	 * Method that creates 2 threads and starts and stops them in intervals.
	 */
	public void run() {
		
		
		try {
		
			
			T1 thread1 = new T1();
			thread1.start();

			TimeUnit.SECONDS.sleep(5);
			
			T2 thread2 = new T2();
			
			
			TimeUnit.SECONDS.sleep(5);
			
			thread1.stopThread();
			
			TimeUnit.SECONDS.sleep(5);
			
			thread2.stopThread();
			
		
			
			
		
		
		
		} catch (InterruptedException e) {
		
			System.err.println("Error: Sleep interrupted");
		}
		
		
	}

	/**
	 * Method that runs when the program starts.
	 * @param args
	 */
	public static void main(String[] args) {
		
		Program program = new Program();
		program.run();
		
	}
	
}
