import java.util.concurrent.BlockingQueue;

public class TaskSource implements Runnable {

    private BlockingQueue<Task> queue;
    
    public TaskSource(BlockingQueue<Task> q){
        this.queue=q;
    }
    @Override
    public void run() {
        for(int i=0; i<20; i++){
        	Task task = new Task ("Task n. "+i);
            try {
				System.out.println("Time: "+ (System.currentTimeMillis()-Service.initTime) + ": "+ "Enqueueing "+task.getName());
                queue.put(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
				Thread.sleep(Service.taskIntroductionDelay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }
}