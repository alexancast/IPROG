/**
 * Subclass to Thread.
 * @author Alexander Castman alca3002
 */
public class T1 extends Thread{

	/**
	 * Boolean that contains the state of the thread. When set to false the thread stops.
	 */
	private boolean running;
	
	/**
	 * Method that every second prints at textline while the thread is active.
	 */
	@Override
	public void run() {
		
		running = true;
		
		while(running) {
			
			try {
				System.out.println("T1: Tråd 1");
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				
				System.err.println("Thread interrupted");
				
			}
			
		}
		
		
	}
	
	/**
	 * Method that can be used to stop the thread from outside the class.
	 */
	public void stopThread() {
		running = false;
	}
	
}
