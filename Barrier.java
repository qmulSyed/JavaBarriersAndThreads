
/**
 * Lab 9: Thread Barrier
 * Barrier Class
 *
 * Processes join the barrier and are held until barrierSize processes have
 * joined.
 *
 *
 */
public class Barrier {

    /**
     * Size of the barrier, which is the minimum number of processes to proceed.
     */
    private int barrierSize;
    int numProcesses = 0;
    int val = 0;


    /**
     * Create a barrier of a given size
     *
     * @param size
     */
    public Barrier(int size) {
        barrierSize = size;
        System.out.println("Barrier size = " + barrierSize);
    }

    /**
     * Processes join at barrier and either wait or are allowed past.
     *
     * @param p The process joining
     */
    public void joinBarrier(Process p) {
        System.out.println(p.getName() + " waiting on barrier");

        synchronized (p) {
            p.numQueue = numProcesses; // Queue number of process (Doesnt use java data structure)
            numProcesses = numProcesses + 1; // Increment counter for processes in barrier
        }

        while(numProcesses <= barrierSize){
            // Wait
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // Wait for ms (depends on queue number so FIFO) (Doesnt use java data structure)
        try {
            Thread.sleep(p.numQueue*100); // Wait for time depending on queue order of process
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Barrier passed
        System.out.println(p.getName() + " passed the barrier");
    }
}
