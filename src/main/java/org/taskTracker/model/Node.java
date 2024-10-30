package org.taskTracker.model;



public class Node<T> {
    public T data;
    public Node<T> next;
    public Node<T> prew;

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prew = null;
    }

}
