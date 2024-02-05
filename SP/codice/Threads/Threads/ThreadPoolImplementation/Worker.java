import java.util.concurrent.BlockingQueue;

public class Worker implements Runnable{

private BlockingQueue<Task> queue;
    public Worker (BlockingQueue<Task> q){
        this.queue=q;
    }

    @Override
    public void run() {
        try{
        	Task task;
            while(true) {
            	task = queue.take();
            	task.run();
            }
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}