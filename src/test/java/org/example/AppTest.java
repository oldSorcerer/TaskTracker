package org.example;

import org.junit.Test;
import org.taskTracker.*;

public class AppTest {
    TaskTest taskTest = new TaskTest();

    @Test
    public void fullTest() {  //тест всех методов
        taskTest.testCreateTask();
        taskTest.testCreateEpic();
        taskTest.searchTaskEpicTest();
        taskTest.fullRemoveTaskEpicTest();
        taskTest.testCreateTask();
        taskTest.testCreateEpic();
        taskTest.removeTaskEpicIdentification();
        taskTest.updateTaskEpicTest();
        taskTest.updateStatus();
        taskTest.testIdEpic();
    }

    @Test
    public void fullTestHistory() { // история тест
        taskTest.testCreateTask();
        taskTest.testCreateEpic();
        taskTest.testHistory();
        System.out.println("------Завершение истории -------");
        taskTest.taskManager.history().forEach(System.out::println);
    }
}
