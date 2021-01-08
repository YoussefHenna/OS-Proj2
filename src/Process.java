//Imitates a process, can be paused and resumed
public class Process {
    private final int id;
    private final int timeTilCompletion;
    private int timeRan = 0;
    private boolean isPaused = true;
    private Thread thread;

    /**
     * @param id                id of the process, used to identify which process is running
     * @param timeTilCompletion multiple of TIME_UNIT to which the process needs until completion
     */
    public Process(int id, int timeTilCompletion) {
        this.id = id;
        this.timeTilCompletion = timeTilCompletion;
        thread = new Thread(this::run);
    }

    private void run() {
        while (timeRan < timeTilCompletion && !isPaused) {
            try {
                System.out.println(id + " ran for one time unit");
                timeRan += OperatingSystem.TIME_UNIT;
                Thread.sleep(OperatingSystem.TIME_UNIT_MS + 20);//20 second period so scheduler reschedule

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public boolean isPaused() {
        return isPaused;
    }

    public int getId() {
        return id;
    }

    public int getTimeTilCompletion() {
        return timeTilCompletion;
    }

    public int getRemainingExecutionTime() {
        return timeTilCompletion - timeRan;
    }

    public void start() {
        thread.start();
    }

    public void pause() {
        isPaused = true;
    }

    public void resume() {
        isPaused = false;
        thread = new Thread(this::run);
        start();
    }

    @Override
    public String toString() {
        return "Process{" +
                "id=" + id +
                ", isPaused=" + isPaused +
                '}';
    }
}
