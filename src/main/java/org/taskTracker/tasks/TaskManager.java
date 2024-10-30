package org.taskTracker.tasks;

import org.taskTracker.model.Epic;
import org.taskTracker.model.Subtask;
import org.taskTracker.model.Task;

import java.util.HashMap;
import java.util.List;

public interface TaskManager {
    HashMap<Integer, Task> getTaskTable();

    HashMap<Integer, Subtask> getSubTaskTable();

    HashMap<Integer, Epic> getEpicTaskTable();

    void printTaskTable();

    void printSubtaskTable();

    void printEpicTable();

    void createTask(Task task);

    Subtask createSubtask(Subtask subtask);

    void createEpic(Epic epic);

    Task returnTaskIdentification(int taskId);

    Subtask returnSubtaskIdentification(int subId);

    Epic returnEpicIdentification(int epicId);

    void removeTask();

    void removeEpic();

    void removeTaskIdentification(int taskId);

    void removeEpicIdentification(int epicId);

    void updateTask(Task task, int taskId);

    void updateEpic(Epic epic, int epicId);

    void taskStatusDone(int taskId);

    void subtaskStatusDone(int subtaskId);
    List<Task> history();

}
