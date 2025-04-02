package org.sunrider.homework1;

import java.util.Arrays;

public class MyArrayList<T> implements List<T> {

    private T[] list;

    private int size = 10;
    private int currentIndex = 0;

    public MyArrayList() {
        list = (T[]) new Object[size];
    }

    @Override
    public void add(T value) {

        if (currentIndex == size) {
            extend();
        }

        list[currentIndex] = value;
        currentIndex++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index >= currentIndex)
            throw new IndexOutOfBoundsException();

        if (currentIndex + 1 >= size) {
            extend();
        }
        for (int i = currentIndex; i >= index; i--){
            list[i + 1] = list[i];
        }
        list[index] = value;
        currentIndex++;
    }

    @Override
    public void clear() {
        Arrays.fill(list, null);
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= currentIndex)
            throw new IndexOutOfBoundsException();
        return list[index];
    }

    @Override
    public void remove(T value) {
        for (int i = 0; i < currentIndex; i++) {
            if (list[i].equals(value)){
                for (int j = i; j < currentIndex; j++) {
                    list[j] = list[j + 1];
                }
                list[currentIndex] = null;
                currentIndex--;
                break;
            }
        }
    }

    @Override
    public void sort() {

    }

    private void extend(){
        size = (int) (size * 1.5) + 1;
        T[] tempList = (T[]) new Object[size];
        System.arraycopy(list, 0, tempList, 0, currentIndex);
        list = tempList;
    }

    @Override
    public String toString() {

        T[] tempList = (T[]) new Object[currentIndex];
        System.arraycopy(list, 0, tempList, 0, currentIndex);

        return Arrays.toString(tempList);
    }
}
