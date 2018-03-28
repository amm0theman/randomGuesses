package gatewayBlockchain;

import java.util.Random;

public class Racer implements Runnable {

	//racer is trying to guess this member
	volatile int answer;
	int guess;
	volatile Random rnd;
	volatile long numGuess=0;
	static volatile boolean quitOut;
	
	Racer(int _answer)
	{
		answer = _answer;
		quitOut = false;
		rnd = new Random();		
		rnd.setSeed(System.currentTimeMillis());
	}
	
	//if the guess was right return trues else return false
	public boolean isRight(int guess) {
		if (guess == answer) {
			return true;
		}
		return false;
	}
	
	@Override
	public void run() {
		boolean guessing = true;
		while(guessing) {
			guess = rnd.nextInt();
			guess = guess % 1000000000;
			numGuess++;
			
		    /*try {
				Thread.sleep(1);//to slow me down
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/ 
			
			if(quitOut)
			{
				System.out.println("After "+numGuess+" guesses I was told to quit.  Dying.");
				break;
			}
			synchronized (this) { 
				if(isRight(guess))
				{
					quitOut = true;
					System.out.println("After "+numGuess+" guesses I got the answer! ("+guess+").  Quitting.");
					guessing = false;
				} 
			}
		}
	}
}
