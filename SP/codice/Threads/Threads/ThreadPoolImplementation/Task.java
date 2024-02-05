public class Task implements Runnable {
    private String name;
    
    public Task(String str){
        this.name=str;
    }

    public String getName() {
        return name;
    }
    public void run() {
   	 	System.out.println("Time: "+ (System.currentTimeMillis()-Service.initTime) + ": "+ name + " in Worker " + Thread.currentThread().getName() + " Starting");
    	try {
			Thread.sleep(Service.taskDuration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
   	 	System.out.println("Time: "+ (System.currentTimeMillis()-Service.initTime) + ": "+ name + " in Worker " + Thread.currentThread().getName() + " Done");
    }
}
