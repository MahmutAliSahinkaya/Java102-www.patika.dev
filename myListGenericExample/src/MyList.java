import java.util.Arrays;

public class MyList<T> {
    private int capacity =10;
    private T[] array;
    private int index=0;
    private T[] tempArray;
    private int size;


    public MyList() {
        this.array = (T[]) new Object[capacity];
    }

    public MyList(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];

    }

    public int size() {
        int count = 0;
        for (T t : array)
            if (t != null)
                count++;
        return count;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public T[] getArray() {
        return array;
    }
    public void setArray(T[] array) {
        this.array = array;
    }

    public void add(T data) {
        if (size() > getCapacity()) {
            this.tempArray = this.array;
            setCapacity(getCapacity() * 2);
            this.array = (T[]) new Object[getCapacity()];
            this.array = Arrays.copyOf(tempArray, getCapacity());
        }
        this.array[size()] = data;
    }
    public T get(int index)   {
    if(index < 0 || index>size()){
        return null;
    }else{
        return this.array[index];
    }

}

    public T remove(int index) {
        if (size() < index || index < 0) {
            return null;
        }
        T[] temp = this.array;
        this.array[index] = null;
        for (int i = index; i < size(); i++) {
            if (size() - i == 1)
                this.array[i] = null;
            else
                this.array[i] = temp[i + 1];
        }
        return this.array[index];

    }

    public T set(int index, T data) {
        if (size() < index || index < 0) {
            return null;
        }
        this.array[index] = data;
        return this.array[index];


    }
    @Override
    public String toString() {
        if (size() > 0) {
            StringBuilder str = new StringBuilder("[");
            for (int i = 0; i < size(); i++) {
                if (i == (size() - 1)) {
                    str.append(array[i]).append("]");
                } else str.append(array[i]).append(",");

            }
            return str.toString();
        }
        return "[]";
    }

    public int indexOf(T data) {
        int index = -1;
        for (int i = 0; i < size(); i++) {
            if (array[i] == data) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int lastIndexOf(T data) {
        int index = -1;
        for (int i = size() - 1; i >= 0; i--) {
            if (array[i] == data) {
                index = i;
                break;
            }
        }
        return index;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T[] toArray() {
        return this.array;
    }

    public void clear() {
        this.array = (T[]) new Object[10];
    }

    public MyList<T> subList(int start, int finish) {
        MyList<T> newList = new MyList<>((finish - start + 1));
        for (int i = start; i <= finish; i++) {
            newList.add(this.array[i]);
        }
        return newList;
    }

    public boolean contains(T data) {
        for (int i = 0; i < size(); i++) {
            if (array[i] == data)
                return true;
        }
        return false;
    }
}

