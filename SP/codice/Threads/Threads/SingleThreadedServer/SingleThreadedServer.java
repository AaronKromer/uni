import java.io.IOException;
import java.lang.System;
import java.util.Random;

class SingleThreadedServer{
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
	
	long go(int taskNumber, int iterationNumber, int sleepTime){
		long m1 = System.currentTimeMillis();
		for (int i = 0; i < taskNumber; i++){				
			handleRequest(iterationNumber, sleepTime, -1);		
		}
		long m2 = System.currentTimeMillis() - m1;
		return m2;
		
	}		
}
