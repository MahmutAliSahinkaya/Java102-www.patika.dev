import java.util.ArrayList;

public class NumberDivider {
    public static ArrayList<ArrayList<Integer>> divideArrayList(ArrayList<Integer> numbers, int parts) {
        int size = numbers.size() / parts;
        int begin = 0;
        int end = size;
        ArrayList<ArrayList<Integer>> dividedNumbers = new ArrayList<>();

        for (int i = 0; i < parts; i++) {
            ArrayList<Integer> part = new ArrayList<>(numbers.subList(begin, end));
            dividedNumbers.add(part);
            begin = end;
            end += size;
        }

        return dividedNumbers;
    }
}


