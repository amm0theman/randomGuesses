package gatewayBlockchain;

import java.util.Random;
import java.util.Scanner;
import java.lang.Math.*;

@SuppressWarnings("unused")
public class RacerDriver {

	public static void main(String[] args) {
		Random rnd = new Random(System.currentTimeMillis());
		Integer answer = Math.abs(rnd.nextInt() % 1000000000);
		Racer[] racers = new Racer[4];
		Thread[] racerThreads = new Thread[4];
		Thread quitThread = new Thread();
		
		for (int i = 0; i < racerThreads.length; i++) {
			racers[i] = new Racer(answer);
			racerThreads[i] = new Thread(racers[i]);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		CloserThread closerThread = new CloserThread(racers);
		quitThread = new Thread(closerThread);
		
		for (int i = 0; i < racerThreads.length; i++) {
			racerThreads[i].start();
		}
		quitThread.start();
		
		
		
		/*
		synchronized (racers[1]) {
			System.out.println("Gotchya");
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Releasing thread");
		}
		*/
		
		Scanner myscan = new Scanner(System.in);
		
		for (int i = 0; i < racerThreads.length; i++) {
			try {
				racerThreads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//All my racers are done, I'm ready to exit
		//Note - this is nuclear war to my CloserThread.
		quitThread.interrupt();
		try {
			quitThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
