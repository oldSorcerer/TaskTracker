package org.taskTracker.history;

import org.taskTracker.history.HistoryManager;
import org.taskTracker.model.Node;
import org.taskTracker.model.Task;

import java.util.ArrayList;
import java.util.HashMap;


public class InMemoryHistoryManager implements HistoryManager {

    public Node<Task> head;
    public Node<Task> tail;
    private HashMap<Integer, Node<Task>> hashMap = new HashMap<>();


    @Override
    public void add(Task task) {
        if (hashMap.containsKey(task.getIdentificationNumber())) {
            removeNode(hashMap.get(task.getIdentificationNumber()));
            hashMap.remove(task.getIdentificationNumber());
        }
        hashMap.put(task.getIdentificationNumber(), linkLast(task));
    }

    @Override
    public void remove(int id) {

    }

    private void removeNode(Node<Task> node) {
        if (node == tail) {
            Node<Task> prew = tail.prew;
            prew.next = null;
            tail = prew;
        } else if (node == head) {
            Node<Task> prew = head.next;
            prew.prew = null;
            head = prew;
        } else {
            Node<Task> left = node.prew;
            Node<Task> right = node.next;
            left.next = right;
            right.prew = left;
        }
    }

    @Override
    public ArrayList<Task> getTasks() {
        Node<Task> current = head;
        ArrayList<Task> tasks = new ArrayList<>();
        while (current != null) {
            tasks.add(current.data);
            current = current.next;
        }
        return tasks;
    }


    private Node<Task> linkLast(Task task) {
        Node<Task> node = new Node<>(task);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
            node.prew = tail;
        }
        tail = node;
        return node;
    }
}
