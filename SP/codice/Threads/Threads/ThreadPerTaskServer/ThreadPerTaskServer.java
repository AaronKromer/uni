import java.io.IOException;
import java.lang.System;
import java.util.Random;
import java.util.*;

class ThreadPerTaskServer{

	void handleRequest(int iterationNumber, int sleepTime, int fakeParam) {
		int accumulator = 0;
		for (int i=0; i < iterationNumber; i++) {
			for (int j=0; j<1000; j++) {
				accumulator+=(i*j);
				if (accumulator > 1000000000)
					accumulator = accumulator/1000;
				if (accumulator == fakeParam) {
					System.out.println(accumulator);
				}
			}
		}	
		if (sleepTime != 0) {
			try {
				Thread.sleep(sleepTime);
			} catch (Exception e) {
				System.out.println("Errror in sleep");
				System.exit(-1);
			}
		}		
	}
			
	public class HandleRequestThread implements Runnable {
	  private int iterationNumber;
	  private int sleepTime;
      public HandleRequestThread(int iterationNumber, int sleepTime) {
         this.iterationNumber = iterationNumber;
		 this.sleepTime = sleepTime;
      }
      public void run() {
          handleRequest(iterationNumber, sleepTime, -1);
      }
	}
      
   long go(int taskNumber, int iterationNumber, int sleepTime){
		Thread[] threads = new Thread[taskNumber];
		for (int threadIndex=0; threadIndex < taskNumber ; threadIndex++) {
			threads[threadIndex]= new Thread(new HandleRequestThread(iterationNumber, sleepTime));
		}
		long m1 = System.currentTimeMillis();
		for (int threadIndex=0; threadIndex < taskNumber ; threadIndex++) {
			threads[threadIndex].start();
		}
		try {
			for (Thread thread : threads) {
				thread.join();
			}
		} catch (InterruptedException e) {
			System.out.println("Error");;
			System.exit(-1);
		}		
		long m2 = System.currentTimeMillis() - m1;
		return m2;
	}
}
