package org.sunrider.homework1;

import java.util.Arrays;

public class MyLinkedList<T> implements List<T> {

    Node<T> head;
    Node<T> tail;
    int size;

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;
        Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    public MyLinkedList() {
        head = new Node<>(null, null, null);
        tail = new Node<>(null, null, null);

        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    @Override
    public void add(T value) {

        Node<T> node = new Node<>(value, tail.prev, tail);

        if (head.next == tail)
            head.next = node;
        tail.prev.next = node;
        tail.prev = node;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Node<T> temp;
        if (index < size/2){
            temp = head.next;

            for (int i = 0; i < index; i++)
                temp = temp.next;

        }else {
            temp = tail.prev;
            for (int i = 0; i < size - index - 1; i++)
                temp = temp.prev;
        }

        Node<T> result = new Node<>(value, temp.prev, temp);
        temp.prev.next = result;
        temp.prev = result;
        size++;
    }

    @Override
    public void clear() {
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Node<T> temp;
        if (index < size/2){
            temp = head.next;
            for (int i = 0; i < index; i++){
                temp = temp.next;
            }
            return temp.data;
        }
        else {
            temp = tail.prev;
            for (int i = 0; i < size - index - 1; i++){
                temp = temp.prev;
            }
            return temp.data;
        }
    }

    @Override
    public void remove(T value) {
        Node<T> temp = head.next;
        for (int i = 0; i < size; i++){
            if (temp.data.equals(value)){
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;
                size--;
            }
            temp = temp.next;
        }
    }

    @Override
    public void sort() {

    }

    @Override
    public String toString() {
        T[] result = (T[]) new Object[size];
        Node<T> node = head.next;
        for (int i = 0; i < size; i++) {
            result[i] = node.data;
            node = node.next;
        }
        return Arrays.toString(result);
    }
}
