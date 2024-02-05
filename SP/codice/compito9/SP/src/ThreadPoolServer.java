import java.io.IOException;
import java.lang.System;
import java.util.Random;
import java.text.SimpleDateFormat; 
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ThreadPoolServer{
	public ExecutorService pool;
	public ThreadPoolServer(int poolSize) {
		 this.pool = setupPool(poolSize);
      }

	void handleRequest(Request request, Response response, int sleepTime) {
		if (request.getUri() != null) {
			if (request.getUri().startsWith("/servletrepository")) {
				MyServletProcessor processor = new MyServletProcessor();
				processor.process(request, response);
			}
			else {
				MyStaticResourceProcessor processor = new MyStaticResourceProcessor();
				processor.process(request, response);
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
	  private Request request;
	  private Response response;
	  private int sleepTime;
      public HandleRequestThread(Request request, Response response, int sleepTime) {
		 this.request = request;
		 this.response = response;
         this.sleepTime = sleepTime;
      }
      public void run() {
		  handleRequest( request,  response,  sleepTime);
      }
	}
	ExecutorService setupPool(int poolSize){
		ExecutorService pool = Executors.newFixedThreadPool(poolSize);
		return pool;

	}
	void shutdownPool(ExecutorService pool){
		pool.shutdown();
	}
	void go(Request request, Response response, int sleepTime){
		pool.execute(new Thread(new HandleRequestThread(request,response, sleepTime)));
	}		
}
