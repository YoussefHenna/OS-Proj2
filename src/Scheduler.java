import java.util.*;

public class Scheduler {


    /*
        HOW TO IMPLEMENT

        You have a list of pairs(map entries) in each method which are randomly generated

        each pair has a getKey() and getValue() method
            getKey -- returns the arrival time as integer
            getValue -- returns the actual process

        each process has
            getTimeTilCompletion() -- gets the time a process needs to complete (ex: 5, 4)
            getRemainingExecutionTime() -- gets the remaining time till process completes
            start() -- starts the process (should only be called once, then pause/resume as much as you want)
            pause() -- pauses the process
            resume() -- resumes the process
            isPaused() -- returns boolean if paused
            getId() -- returns id of process

        When a process is running it will automatically print 'Process {id} running for one time unit' for every time unit
        that passes in which it is not paused.

        When you pause it, it will stop until resumed.

        To find out when the process is done:
            getRemainingExecutionTime() == 0

        Your implementation should basically just be when to pause and when to resume
         You can skip calling start() if you want, and just use resume() right away

     */
    public static void scheduleFirstComeFirstServed(AbstractMap<Integer,Process> processes) {
        System.out.println("Scheduling using First Come First Served");
        //IMPLEMENT HERE
    }

    public static void scheduleShortestJobFirst(AbstractMap<Integer,Process> processes) {
        System.out.println("Scheduling using Shortest Job First");
        //IMPLEMENT HERE
    }

    public static void scheduleRoundRobin(AbstractMap<Integer,Process> processes) {
        Random rand = new Random();
        int min = OperatingSystem.TIME_UNIT;
        int max = 4 * OperatingSystem.TIME_UNIT;
        int timeSlice = rand.nextInt(max - min) + min;

        System.out.println("Scheduling using Round Robin with time slice: " + timeSlice);

        int currentTime = 0;
        int currentRunningTimeUnits = 0;
        Queue<Process> currentlyRunning = new LinkedList<>();
        int numComplete = 0;
        int initSize = processes.size();


        do {

            for (Map.Entry<Integer,Process> pair : processes.entrySet()) {
                if (pair.getKey() == currentTime) {
                    currentlyRunning.add(pair.getValue());
                    processes.remove(pair);
                }
            }
            Process current = currentlyRunning.peek();
            if (current != null) {
                if (currentRunningTimeUnits >= timeSlice) {
                    current.pause();
                    if (current.getRemainingExecutionTime() != 0) {
                        currentlyRunning.add(currentlyRunning.poll());
                    } else {
                        numComplete++;
                        currentlyRunning.poll();
                    }
                    Process newCurrent = currentlyRunning.peek();
                    if (newCurrent != null) {
                        newCurrent.resume();
                    }
                    currentRunningTimeUnits = 0;
                } else if (currentRunningTimeUnits == 0) {
                    current.start();
                }
            }
            currentTime += OperatingSystem.TIME_UNIT;
            currentRunningTimeUnits += OperatingSystem.TIME_UNIT;
            try {
                Thread.sleep(OperatingSystem.TIME_UNIT_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (numComplete < initSize);
    }

}
