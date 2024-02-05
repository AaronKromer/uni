import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.cli.*;

@SuppressWarnings( "deprecation" )
public class Service {
	public static long initTime;
	public static int nWorkers;
	public static int taskDuration;
	public static int taskIntroductionDelay;
	
	@SuppressWarnings( "deprecation" )
    public static void main (String[] args) {  	
		Options options = new Options();
		options.addOption("p", true, "Thread Pool Size2");
		options.addOption("d", true, "Task Duration");
		options.addOption("i", true, "Task Introduction Delay");
        CommandLineParser parser = new DefaultParser();
		int nWorkers = 5;
		taskDuration = 2500;
		taskIntroductionDelay = 1000;
        try {
			CommandLine cmd = parser.parse(options, args);
			if(cmd.hasOption("p")) {
				nWorkers=Integer.parseInt(cmd.getOptionValue ("p"));
			}
			if(cmd.hasOption("d")) {
				taskDuration = Integer.parseInt(cmd.getOptionValue ("d"));
			}			
			if(cmd.hasOption("i")) {
				taskIntroductionDelay = Integer.parseInt(cmd.getOptionValue ("i"));
			}
		} catch (ParseException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
		
		System.out.println ("-p " + nWorkers + " -d " + taskDuration  + " -i " + taskIntroductionDelay);
		initTime = System.currentTimeMillis();
		BlockingQueue<Task> queue = new ArrayBlockingQueue<>(10);
		TaskSource taskSource = new TaskSource (queue);
		Worker worker = new Worker (queue);
		
        for (int i = 0; i < nWorkers; i++) {
			new Thread(worker).start();
		}
		new Thread(taskSource).start();
		
    }

}