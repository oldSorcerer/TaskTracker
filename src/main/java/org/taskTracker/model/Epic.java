package org.taskTracker.model;

import java.util.List;

public class Epic extends Task {
    public Epic(String name, List<Subtask> description) {
        super(name, description);
    }

    @Override
    public TypeTasks getType() {
        return TypeTasks.EPIC;
    }

    @Override
    public List<Subtask> getDescription() {
        return (List<Subtask>) super.getDescription();
    }

}
