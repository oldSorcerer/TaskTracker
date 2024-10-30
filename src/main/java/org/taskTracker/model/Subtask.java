package org.taskTracker.model;




public class Subtask extends Task {

    @Override
    public TypeTasks getType() {
        return TypeTasks.SUBTASK;
    }

    public Subtask(String name, String description) {
        super(name, description);
    }

}
