package org.taskTracker.tasks;

import org.taskTracker.model.Epic;
import org.taskTracker.model.Subtask;
import org.taskTracker.model.Task;
import org.taskTracker.model.TypeTasks;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileBackedTasksManager extends InMemoryTaskManager {   //класс сохранения


    @Override
    public void createTask(Task task) {
        super.createTask(task);
        save();
    }


    @Override
    public void createEpic(Epic epic) {
        super.createEpic(epic);
        save();
    }

    @Override
    public void removeTask() {
        super.removeTask();
        save();
    }

    @Override
    public void removeEpic() {
        super.removeEpic();
        save();
    }

    @Override
    public void removeTaskIdentification(int taskId) {
        super.removeTaskIdentification(taskId);
        save();
    }

    @Override
    public void removeEpicIdentification(int epicId) {
        super.removeEpicIdentification(epicId);
        save();
    }

    @Override
    public void updateTask(Task task, int taskId) {
        super.updateTask(task, taskId);
        save();
    }

    @Override
    public void updateEpic(Epic epic, int epicId) {
        super.updateEpic(epic, epicId);
        save();
    }

    @Override
    public void taskStatusDone(int taskId) {
        super.taskStatusDone(taskId);
        save();
    }

    @Override
    public void subtaskStatusDone(int subtaskId) {
        super.subtaskStatusDone(subtaskId);
        save();
    }

    @Override
    public List<Task> history() {
        super.history();
        save();
        return super.history();
    }

    public void save() {   // метод сохранения

    }

    public String toString(Task task) {  // из Обьекта в строку
        String result;
        if (task.getType().equals(TypeTasks.EPIC)) {
            result = '\n' + task.getIdentificationNumber() +
                    ";" + task.getType() +
                    ";" + task.getName() +
                    ";" + task.getStatus() + ";";
            List<Subtask> subtasks = (List<Subtask>) task.getDescription();
            for (Subtask x : subtasks) {
                result += x.getIdentificationNumber() +
                        ";" + x.getType() +
                        ";" + x.getName() +
                        ";" + x.getStatus() +
                        ";" + x.getDescription() + ";";
            }
        } else {
            result = '\n' + task.getIdentificationNumber() +
                    ";" + task.getType() +
                    ";" + task.getName() +
                    ";" + task.getStatus() +
                    ";" + task.getDescription();
        }
        return result;
    }

    public Task fromString(String taskString) {  // из строки в объект
        int id;
        String name;
        Object description;
        String[] builder = taskString.split(";");
        return null;
    }

}
