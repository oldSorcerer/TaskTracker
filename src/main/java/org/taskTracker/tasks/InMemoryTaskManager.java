package org.taskTracker.tasks;

import org.taskTracker.history.HistoryManager;
import org.taskTracker.model.Epic;
import org.taskTracker.model.Subtask;
import org.taskTracker.model.Task;

import java.util.*;


import static org.taskTracker.model.Status.DONE;
import static org.taskTracker.model.Status.IN_PROGRESS;

public class InMemoryTaskManager implements TaskManager {

    public HashMap<Integer, Task> taskTable = new HashMap<>();
    public HashMap<Integer, Subtask> subTaskTable = new HashMap<>();
    public HashMap<Integer, Epic> epicTable = new HashMap<>();
    private int number = 1;
    public HistoryManager historyManager = Managers.getDefaultHistory();


    @Override
    public HashMap<Integer, Task> getTaskTable() {
        return taskTable;
    }

    @Override
    public HashMap<Integer, Subtask> getSubTaskTable() {
        return subTaskTable;
    }

    @Override
    public HashMap<Integer, Epic> getEpicTaskTable() {
        return epicTable;
    }

    @Override
    public void printTaskTable() {      // Вывод списка задач +
        for (Map.Entry<Integer, Task> entry : taskTable.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    @Override
    public void printSubtaskTable() {     // Вывод списка подзадач +
        for (Map.Entry<Integer, Subtask> entry : subTaskTable.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    @Override
    public void printEpicTable() {         // Вывод списка эпиков +
        for (Map.Entry<Integer, Epic> entry : epicTable.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    @Override
    public void createTask(Task task) {// создание задачи +
        task.setIdentificationNumber(number);
        taskTable.put(task.getIdentificationNumber(), task);
        number++;
    }

    @Override
    public Subtask createSubtask(Subtask subtask) {    // создание подзадачи +
        subtask.setIdentificationNumber(number);
        subTaskTable.put(number, subtask);
        number++;
        return subtask;
    }

    @Override
    public void createEpic(Epic epic) {     // создание эпика
        epic.setIdentificationNumber(number);
        epicTable.put(number, epic);
        number++;
    }

    @Override
    public Task returnTaskIdentification(int taskId) { // получение задачи по идентификатору +
        if (taskTable.get(taskId) == null) {
            System.out.println("Задачи с таким номером нет");
            return null;
        }
        historyManager.add(taskTable.get(taskId)); //================
        return taskTable.get(taskId);
    }

    @Override
    public Subtask returnSubtaskIdentification(int subId) { // получение подзадачи по идентификатору +
        if (subTaskTable.get(subId) == null) {
            System.out.println("Задачи с таким номером нет");
            return null;
        }
        historyManager.add(subTaskTable.get(subId)); //=============
        return subTaskTable.get(subId);
    }

    @Override
    public Epic returnEpicIdentification(int epicId) {   // получение эпика по идентификатору +
        if (epicTable.get(epicId) == null) {
            System.out.println("Задачи с таким номером нет");
            return null;
        }
        historyManager.add(epicTable.get(epicId)); //===============
        return epicTable.get(epicId);
    }

    @Override
    public void removeTask() {     // удаление всех задач +
        taskTable.clear();
    }

    @Override
    public void removeEpic() {     // удаление всех эпиков +
        epicTable.clear();
        subTaskTable.clear();
    }

    @Override
    public void removeTaskIdentification(int taskId) {
        taskTable.remove(taskId);
    }

    @Override
    public void removeEpicIdentification(int epicId) { //удаление эпика по индексу +
        for (Subtask x : epicTable.get(epicId).getDescription()) {
            subTaskTable.remove(x.getIdentificationNumber());
        }
        epicTable.remove(epicTable.get(epicId).getIdentificationNumber());
    }

    @Override
    public void updateTask(Task task, int taskId) {   // обновление задачи
        task.setStatus(IN_PROGRESS);
        task.setIdentificationNumber(taskId);
        taskTable.put(task.getIdentificationNumber(), task);
    }


//    public void updateSubtask(int subId) {       //  обновление подзадачи изи сделать
//
//    }

    @Override
    public void updateEpic(Epic epic, int epicId) {   // обновление эпика
        for (Subtask x : epicTable.get(epicId).getDescription()) {
            subTaskTable.remove(x.getIdentificationNumber());
        }
        for (Subtask x : epic.getDescription()) {
            x.setStatus(IN_PROGRESS);
        }
        epic.setIdentificationNumber(epicId);
        epic.setStatus(IN_PROGRESS);
        epicTable.put(epicId, epic);
    }

    @Override
    public void taskStatusDone(int taskId) {        //завершение Task
        taskTable.get(taskId).setStatus(DONE);
    }

    @Override
    public void subtaskStatusDone(int subtaskId) {      // завершение подзадач с эпиком
        subTaskTable.get(subtaskId).setStatus(DONE);
        epicStatusDone();
    }

    public void epicStatusDone() {          // завершение эпика
        for (Map.Entry<Integer, Epic> entry : epicTable.entrySet()) {
            if (entry.getValue().getDescription().stream().allMatch(x -> x.getStatus() == DONE)) {
                entry.getValue().setStatus(DONE);
            }
        }
    }

    @Override
    public List<Task> history() {
        return historyManager.getTasks();
    }


}
