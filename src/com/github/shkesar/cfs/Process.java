
package com.github.shkesar.cfs;

public class Process implements Comparable<Process> {
    static private char JOB_ID = 'A';
    private String name;
    private int elapsedProcessorTime = 0;
    private int burstTime;

    public static String genProcessName() {
        return "Job " + JOB_ID++;
    }

    public Process(String name, int burstTime) {
        this.name = name;
        this.burstTime = burstTime;
    }

    public Process(int burstTime) {
        this(genProcessName(), burstTime);
    }

    public Process() {
        this(genProcessName(), 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getElapsedProcessorTime() {
        return elapsedProcessorTime;
    }

    public void setElapsedProcessorTime(int elapsedProcessorTime) {
        this.elapsedProcessorTime = elapsedProcessorTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    @Override
    public int compareTo(Process process) {
        return -(process.elapsedProcessorTime+process.getName().hashCode()) +
                (this.elapsedProcessorTime+this.getName().hashCode());
    }

    @Override
    public String toString() {
        return this.name + " - \tCurrent Burst time : " + this.burstTime +
                "\tProcessor elapsed time : " + this.elapsedProcessorTime;
    }
}
