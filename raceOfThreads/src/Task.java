import java.util.ArrayList;

public class Task implements Runnable {
    private ArrayList<Integer> part;

    public Task(ArrayList<Integer> part) {
        this.part = part;
    }

    @Override
    public void run() {
        for (int number : part) {
            if (number % 2 == 0) {
                ResultCollector.evenNumbers.add(number);
            } else {
                ResultCollector.oddNumbers.add(number);
            }
        }
    }
}


