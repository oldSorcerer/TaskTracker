package org.taskTracker.model;


import java.util.Objects;

public class Task {

    private String name;
    private Object description;
    private Integer identificationNumber = -1;
    private Status status = Status.NEW;
    private final TypeTasks type = TypeTasks.TASK;


    public Task(String name, Object description) {
        this.name = name;
        this.description = description;
    }

    public Task() {

    }

    public TypeTasks getType() {
        return type;
    }

    public String getName() {
        return name;
    }


    public Object getDescription() {
        return description;
    }


    public Integer getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(int identificationNumber) {
        this.identificationNumber = identificationNumber;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {

        return type + " №" + identificationNumber + ": " + name + '\n' +
                "Описание: " + description + '\n' +
                "Статус: " + status + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(identificationNumber, task.identificationNumber) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return identificationNumber.hashCode();
    }
}


