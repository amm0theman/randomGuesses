package gatewayBlockchain;

import java.io.IOException;
import java.util.Scanner;

public class CloserThread implements Runnable {

	Racer[] racers;
	boolean forced2Quit = false;
	
	CloserThread(Racer[] _racers) {
		racers = _racers;
	}
	
	@Override
	public void run() {
		Scanner myscan = new Scanner(System.in);
		System.out.println("Enter anything to quit threads early");
		try {
		myscan.nextLine();
		}
		catch(Exception e) {
			forced2Quit = true;
		}
		if(forced2Quit == false)
			for(int i = 0; i < racers.length; i++) {
				racers[i].quitOut = true;
			}
	}
	
	
}
