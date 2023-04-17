import java.util.Random;

/**
 * Lab 9: Thread Barrier
 *
 * Main class
 *
 */
public class Main {

    private static int threadCount = 20 ;
    private static final int barrierSize = 10 ;
    private static final int sleepTime = 500 ;

    /**
     * Main program
     *    Create a barrier
     *    Create multiple instances of Process and run them in threads.
     *	 threadCount should be provided as command line argument when you run Main
     * @param args
     */
    public static void main(String[] args) {

        // Create a random source for randomly setting the sleep time of the
        //  process instances
        Random r = new Random() ;
        threadCount = Integer.parseInt(args[0]);

        // Print out the number of threads
        System.out.println("Number of threads = " + threadCount);

        // Create the barrier
        Barrier barrier = new Barrier(barrierSize) ;

        // Create and start the process threads
        // There are threadCount threads

        for (int i = 0; i < threadCount; i++) {
            Process process = new Process(barrier, i+1, r.nextInt(sleepTime));
            Thread t = new Thread(process);
            t.start();
        }

    }
}
