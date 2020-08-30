/**
 * Class that implements runnable interface and creates a new thread affecting the own class.
 * @author Alexander Castman alca3002
 */
public class T2 implements Runnable{

	/**
	 * Thread that is used by T2.
	 */
	private Thread thread = new Thread(this);

	/**
	 * Boolean that contains the state of the thread. When set to false the thread stops.
	 */
	private boolean running;
	
	/**
	 * Constructor that starts the thread.
	 */
	public T2() {
		thread.start();
	}
	
	
	/**
	 * Method that every second prints at textline while the thread is active.
	 */
	@Override
	public void run() {
		
		running = true;
		
		while(running) {
			
			try {
				System.out.println("T2: Tråd 2");
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				
				running = false;
				
			}
			
		}
		
	}
	
	
	/**
	 * Method that can be use from outside the class to stop the thread.
	 */
	public void stopThread() {
		
		thread.interrupt();

	}

}
