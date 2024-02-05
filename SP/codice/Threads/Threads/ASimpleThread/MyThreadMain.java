public class MyThreadMain {
	public static void main(String[] args) {
		MyThreadMain myThreadMain = new MyThreadMain();
		myThreadMain.go();
	}
void go(){
	long initTime = System.currentTimeMillis() ;
	for (int i = 0; i < 5; i++) {
				System.out.println("Generating Thread n. "+ i + " at t="+(System.currentTimeMillis()-initTime));
				MyThread myThread= new MyThread(initTime);
				myThread.start();
			}		
	}
}
