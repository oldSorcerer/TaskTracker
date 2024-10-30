package org.taskTracker.history;

import org.taskTracker.model.Task;

import java.util.List;

public interface HistoryManager {
    void add(Task task);

    void remove(int id);

    List<Task> getTasks();
}
