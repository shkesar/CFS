package com.github.shkesar.cfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Processor {

    // Default Quantum Number : the number of seconds that a single task executes
    private int quantum_number = 4;
    // Default number of cores
    private int size = 2;

    // Collection of cores in a processor
    private ArrayList<Core> cores;

    public Processor() {
        this(0, 0);
    }

    public Processor(int quantum_number, int size) {
        if(quantum_number > 0)
            this.quantum_number = quantum_number;
        if(size > 0)
            this.size = size;
        init();
    }

    public void init() {
        cores = new ArrayList<Core>();
        for(int i = 0; i < size; i++)
            cores.add(new Core(this));
    }

    public void insertProcesses(ArrayList<Process> processes) {
        for(Process process : processes)
            add(process);
    }

    /*Inserts a process into a core based on some parameter
    which is currently the total burst time of the processes*/
    public void add(Process p) {
        Core lessUsedCore = getLessUsedCore();
        lessUsedCore.add(p);
    }


    private Core getLessUsedCore() {
        if(cores.size() > 1) {
            return Collections.min(cores, new Comparator<Core>() {
                @Override
                public int compare(Core core1, Core core2) {
                    return core1.getTotalBurstTime() - core2.getTotalBurstTime();
                }
            });
        }
        return cores.get(0);
    }

    // Integer ID attached to a core
    public int getID(Core core) {
        return cores.indexOf(core);
    }

    public int getQuantum_number() {
        return quantum_number;
    }

    public int getSize() {
        return size;
    }

    public ArrayList<Core> getCores() {
        return cores;
    }
}
