package main.java.org.halvors.Game.Server.thread;


public class SocketListenerThread implements Runnable {
	public SocketListenerThread() {
		
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
