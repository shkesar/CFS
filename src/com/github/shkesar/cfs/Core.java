package com.github.shkesar.cfs;

import static java.lang.System.out;

public class Core extends Thread {

    private RBTree rbt;

    private Processor processor;

    private int totalBurstTime = 0;

    public Core(Processor processor) {
        this.processor = processor;
        this.rbt = new RBTree();
    }

    public void add(Process process) {
        totalBurstTime += process.getBurstTime();
        rbt.add(process);
    }

    public void remove(Process process) {
        totalBurstTime -= process.getBurstTime();
        rbt.remove(process);
    }

    public int getTotalBurstTime() {
        return totalBurstTime;
    }

    @Override
    public void start() {
        run();
    }

    @Override
    public void run() {
        while(!rbt.isEmpty()) {
            Process p = rbt.pollFirst();
            int QUANTUM_NUMBER = processor.getQuantum_number();

            out.println(this.toString() + p + " active");
            int timeToSpend = (p.getBurstTime() > QUANTUM_NUMBER) ? QUANTUM_NUMBER : (p.getBurstTime());
            try {
                Thread.sleep(timeToSpend * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            totalBurstTime -= timeToSpend;

            if(p.getBurstTime() > QUANTUM_NUMBER) {
                p.setElapsedProcessorTime(p.getElapsedProcessorTime()+timeToSpend);
                p.setBurstTime(p.getBurstTime() - timeToSpend);
                rbt.add(p);
            }
            else {
                p.setElapsedProcessorTime(p.getElapsedProcessorTime()+timeToSpend);
                p.setBurstTime(p.getBurstTime() - timeToSpend);
            }

            out.println(this.toString() + p + " deactive \n");
        }
    }

    @Override
    public String toString() {
        return "Core " + processor.getID(this) + " ";
    }
}
