import org.apache.commons.cli.*;
@SuppressWarnings( "deprecation" )
public class MTSMain {
    public static void main(String[] args) throws Exception {

		int taskNumber = 10;
		int iterationNumber = 1000000;
		int poolSize=8;
		int sleepTime = 0;
		
		Options options = new Options();
		options.addOption("t", true, "Workload (Task Number)");
		options.addOption("i", true, "Processing intensity (Iteration number)");
		options.addOption("s", true, "Sleeping intensity (Sleep time)");
		options.addOption("p", true, "Thread pool size");
		
        CommandLineParser parser = new DefaultParser();
		
        try {
			CommandLine cmd = parser.parse(options, args);
			if(cmd.hasOption("t")) {
				taskNumber=Integer.parseInt(cmd.getOptionValue ("t"));
			}
			if(cmd.hasOption("i")) {
				iterationNumber=Integer.parseInt(cmd.getOptionValue ("i"));
			}
			if(cmd.hasOption("s")) {
				sleepTime=Integer.parseInt(cmd.getOptionValue ("s"));
			}
			if(cmd.hasOption("p")) {
				poolSize=Integer.parseInt(cmd.getOptionValue ("p"));
			}
			if(cmd.hasOption("h")) {
				System.out.println("Usage: " + "-n number of tasks -l task length -p thread pool size -w wait time -v verbose");
				System.exit(0);
			}
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
		ThreadPoolServer s = new ThreadPoolServer(); 
		long elapsed_time = s.go(taskNumber, iterationNumber, sleepTime, poolSize);
		System.out.println(taskNumber + " " + poolSize + " " + sleepTime + " " + elapsed_time + " " + ((float) elapsed_time/ (float) taskNumber));
    }
}