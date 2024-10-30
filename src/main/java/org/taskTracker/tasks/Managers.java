package org.taskTracker.tasks;

import org.taskTracker.history.HistoryManager;
import org.taskTracker.history.InMemoryHistoryManager;
import org.taskTracker.tasks.InMemoryTaskManager;
import org.taskTracker.tasks.TaskManager;

public class Managers {

    public TaskManager getDefault() {
        return new InMemoryTaskManager();
    }
    static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }

}
