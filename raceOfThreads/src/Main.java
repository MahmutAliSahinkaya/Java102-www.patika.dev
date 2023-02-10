import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            numbers.add(i);
        }

        ArrayList<ArrayList<Integer>> dividedNumbers = NumberDivider.divideArrayList(numbers, 4);

        Thread t1 = new Thread(new Task(dividedNumbers.get(0)));
        Thread t2 = new Thread(new Task(dividedNumbers.get(1)));
        Thread t3 = new Thread(new Task(dividedNumbers.get(2)));
        Thread t4 = new Thread(new Task(dividedNumbers.get(3)));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Even Numbers: " + ResultCollector.evenNumbers);
        System.out.println("Odd Numbers: " + ResultCollector.oddNumbers);
    }
}




