package com.beyondcoding.marathon.domain;

public class Runner {

    private String name;

    private Integer minutes;

    public Runner() {
    }

    public Runner(String name) {
        this.name = name;
    }

    public Runner(String name, Integer minutes) {
        this.name = name;
        this.minutes = minutes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    @Override
    public String toString() {
        return "Runner{" +
                "name='" + name + '\'' +
                ", minutes=" + minutes +
                '}';
    }
}
