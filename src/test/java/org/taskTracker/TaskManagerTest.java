package org.taskTracker;

import junit.framework.TestCase;
import org.taskTracker.tasks.Managers;
import org.taskTracker.tasks.TaskManager;


public class TaskManagerTest extends TestCase {  // создаем объект менеджера для пользователя
       private final Managers managers = new Managers();
       public TaskManager taskManager = managers.getDefault();
}