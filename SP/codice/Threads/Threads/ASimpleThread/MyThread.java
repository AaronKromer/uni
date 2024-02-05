public class MyThread extends Thread {
	long initTime;
	MyThread(long t){
		this.initTime=t;
	}
	
	public void run (){
		String threadName = Thread.currentThread().getName(); 
		for (int i=0; i < 5; i++) {
			try {
				Thread.currentThread().sleep(1000);
				System.out.println(threadName + " at t= " + (System.currentTimeMillis()-initTime));
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}
}
