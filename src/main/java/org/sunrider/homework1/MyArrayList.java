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
            size = (int) (size * 1.5) + 1;
            T[] tempList = (T[]) new Object[size];
            System.arraycopy(list, 0, tempList, 0, currentIndex);
            list = tempList;
        }

        list[currentIndex] = value;
        currentIndex++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index >= currentIndex)
            throw new IndexOutOfBoundsException();
        list[index] = value;
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

    @Override
    public String toString() {

        T[] tempList = (T[]) new Object[currentIndex];
        System.arraycopy(list, 0, tempList, 0, currentIndex);

        return Arrays.toString(tempList);
    }
}
