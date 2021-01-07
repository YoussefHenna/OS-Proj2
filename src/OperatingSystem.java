import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class OperatingSystem {
    //Time unit used for dividing time
    public static final int TIME_UNIT = 1;

    //Time in milliseconds per time unit
    public static final int TIME_UNIT_MS = 100;


    //Returns a list of pairs(map entries)
    //For each pair: key(first) is time of arrival, value(second) is the process itself
    //Everything is random
    public static AbstractMap<Integer,Process> getProcesses(){
         AbstractMap<Integer,Process> processes = new HashMap<Integer,Process>();
        Random rand = new Random();
        int min = 3; int max = 8;
        int numOfProc = rand.nextInt(max - min) + min;

        for(int i = 0; i < numOfProc; i++){
            min = 4; max = 20;
            Process proc = new Process(i,rand.nextInt(max - min) + min);

            max = 10;
            int timeOfArrival = rand.nextInt(max); //from 0 - 10
            processes.put(timeOfArrival, proc);
        }

        System.out.println("Processes Generated: ");
        for(Map.Entry<Integer,Process> pair: processes.entrySet()){
            Process p = pair.getValue();
            System.out.println("Process "+ p.getId() + ":");
            System.out.println("\tArriving at: "+pair.getKey());
            System.out.println("\tExecution time units: "+p.getTimeTilCompletion());
        }
        System.out.println();
        System.out.println();
        return processes;
    }


    //We have to generate a new set of processes for each scheduler, because process is consumed in single scheduler
    public static void main(String[] args) {
        System.out.println("------------------------------------------------------------------");
        System.out.println("--------------------------ROUND ROBIN-----------------------------");
        System.out.println("------------------------------------------------------------------");
        AbstractMap<Integer,Process> processesForRR  = getProcesses();
        Scheduler.scheduleRoundRobin(processesForRR);


        System.out.println("------------------------------------------------------------------");
        System.out.println("------------------FIRST COME FIRST SERVED-------------------------");
        System.out.println("------------------------------------------------------------------");
        AbstractMap<Integer,Process> processesForFCFS  = getProcesses();
        Scheduler.scheduleFirstComeFirstServed(processesForFCFS);


        System.out.println("------------------------------------------------------------------");
        System.out.println("-----------------------SHORTEST JOB FIRST-------------------------");
        System.out.println("------------------------------------------------------------------");
        AbstractMap<Integer,Process> processesForSJF  = getProcesses();
        Scheduler.scheduleShortestJobFirst(processesForSJF);


    }


}
