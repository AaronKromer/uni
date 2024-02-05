import java.io.IOException;
import java.lang.System;
import java.util.Random;
import java.text.SimpleDateFormat; 
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ThreadPoolServer{
	
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
			

	class HandleRequestThread implements Runnable {
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

	long go(int taskNumber, int iterationNumber, int sleepTime, int poolSize){
		Thread[] threads = new Thread[taskNumber];
		ExecutorService pool = Executors.newFixedThreadPool(poolSize);
		for (int threadIndex = 0; threadIndex < taskNumber; threadIndex++){
			threads[threadIndex]= new Thread(new HandleRequestThread(iterationNumber, sleepTime));
		}
		long m1 = System.currentTimeMillis();
		for (int threadIndex=0; threadIndex < taskNumber ; threadIndex++) {
			pool.execute(threads[threadIndex]);
		}
		pool.shutdown();
		try {
			if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
				pool.shutdownNow();
			}
		} catch (InterruptedException ex) {
			pool.shutdownNow();
			Thread.currentThread().interrupt();
		}
		long m2 = System.currentTimeMillis() - m1;
		return m2;
	}		
}
