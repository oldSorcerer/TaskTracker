package org.taskTracker;

import org.junit.Assert;
import org.taskTracker.model.Epic;
import org.taskTracker.model.Status;
import org.taskTracker.model.Subtask;
import org.taskTracker.model.Task;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class TaskTest extends TaskManagerTest {


    public void testCreateTask() {      // создание задач
        Task task = new Task("Аптека", "Парацетамол, Аспирин");
        Task task1 = new Task("Магазин", "Колбаса, сыр");
        Task task2 = new Task("Улица", "Погулять с собакой");
        taskManager.createTask(task);
        taskManager.createTask(task1);
        taskManager.createTask(task2);
        assertNotNull(taskManager.getTaskTable());
        Assert.assertNotEquals(task, task1);
        Assert.assertNotEquals(task, task2);
        Assert.assertEquals(task.hashCode(), taskManager.getTaskTable().get(task.getIdentificationNumber()).hashCode());
        assertEquals(3, taskManager.getTaskTable().size());
        taskManager.printTaskTable();
    }

    public void testCreateEpic() {          // создание эпиков
        Epic epic = new Epic("Глобус", Arrays.asList(
                taskManager.createSubtask(new Subtask("Аптека", "Таблетки, Аспирин")),
                taskManager.createSubtask(new Subtask("Продукты", "Колбаса, сыр")),
                taskManager.createSubtask(new Subtask("Овощи", "Помидор, Оругцы"))));

        Epic epic1 = new Epic("Свидание", Collections.singletonList( // неизм. список тк 1 элемент
                taskManager.createSubtask(new Subtask("Вечер", "Платье, туфли"))));

        taskManager.createEpic(epic);
        taskManager.createEpic(epic1);
        assertNotNull(taskManager.getSubTaskTable());
        assertNotNull(taskManager.getEpicTaskTable());
        assertEquals("Колличество эпиков больше", 2, taskManager.getEpicTaskTable().size());
        assertEquals("Колличество подзадач больше", 4, taskManager.getSubTaskTable().size());
        taskManager.printEpicTable();
    }


    public void fullRemoveTaskEpicTest() {      // полное удаление всех задач
        taskManager.removeTask();
        taskManager.removeEpic();
        assertEquals(0, taskManager.getTaskTable().size());
        assertEquals(0, taskManager.getEpicTaskTable().size());
        assertEquals(0, taskManager.getSubTaskTable().size());
        System.out.println("----ЗАВЕРШЕНИЕ полного удаления-------");
    }


    public void searchTaskEpicTest() {      //поиск задач
        assertNull(taskManager.returnTaskIdentification(5));
        assertNull(taskManager.returnSubtaskIdentification(50));
        assertNull(taskManager.returnEpicIdentification(40));
        assertEquals(taskManager.returnTaskIdentification(1), taskManager.getTaskTable().get(1));
        assertEquals(taskManager.returnEpicIdentification(8), taskManager.getEpicTaskTable().get(8));
        assertEquals(taskManager.returnSubtaskIdentification(7), taskManager.getSubTaskTable().get(7));
        System.out.println("----ЗАВЕРШЕНИЕ поиска задач-------");
    }


    public void removeTaskEpicIdentification() {        // удаление по id
        int i = taskManager.getSubTaskTable().size();
        taskManager.removeTaskIdentification(12);
        taskManager.removeEpicIdentification(18);
        assertNull(taskManager.returnTaskIdentification(12));
        assertNull(taskManager.returnEpicIdentification(18));
        assertNotEquals(taskManager.getSubTaskTable().size(), i);
        taskManager.printTaskTable();
        taskManager.printEpicTable();
        System.out.println("----ЗАВЕРШЕНИЕ удаления по id-------");
    }


    public void updateTaskEpicTest() {      // обновление задач
        Task oldTask = taskManager.returnTaskIdentification(10);
        Epic oldEpic = taskManager.returnEpicIdentification(17);
        taskManager.updateTask(new Task("Кинотеатр", "Выбрать фильм"), 10);
        taskManager.updateEpic(new Epic("Ашан", Arrays.asList(
                        taskManager.createSubtask(new Subtask("Хозтовары", "Мыло, Шапмунь")),
                        taskManager.createSubtask(new Subtask("Фрукты", "Бананы, апельсины"))))
                , 17);
        assertNotEquals(oldTask, taskManager.returnTaskIdentification(10));
        assertNotEquals(oldEpic, taskManager.returnEpicIdentification(17));
        taskManager.printTaskTable();
        taskManager.printEpicTable();
        taskManager.printSubtaskTable();
        System.out.println("----ЗАВЕРШЕНИЕ обновления задач-------");
    }


    public void updateStatus() {     //завершение задач
        taskManager.taskStatusDone(10);
        assertEquals(taskManager.returnTaskIdentification(10).getStatus(), Status.DONE);
        taskManager.printTaskTable();
        taskManager.printEpicTable();
        System.out.println("----ЗАВЕРШЕНИЕ статуса Task-------");
    }


    public void testIdEpic() {      //завершение Epic
        taskManager.subtaskStatusDone(19);
        taskManager.subtaskStatusDone(20);
        taskManager.printSubtaskTable();
        taskManager.printEpicTable();
        assertEquals(taskManager.returnEpicIdentification(17).getStatus(), Status.DONE);
        System.out.println("----ЗАВЕРШЕНИЕ статуса epic-------");
    }

    public void testHistory(){   // сохранение истории просмотров
        taskManager.returnTaskIdentification(1);
        taskManager.returnTaskIdentification(2);
        taskManager.returnTaskIdentification(3);
        taskManager.returnTaskIdentification(2);
        taskManager.returnTaskIdentification(2);
        taskManager.returnEpicIdentification(9);
        taskManager.returnEpicIdentification(9);
        taskManager.returnTaskIdentification(1);
    }
}