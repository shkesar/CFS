
package com.github.shkesar.cfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static java.lang.System.out;

public class Simulator extends Thread {
    // Process containing multiple cores
    private Processor processor;

    private ArrayList<Process> processes;

    public Simulator() {
        // Makes a default processor @see Processor
        this(0, 0);
    }

    public Simulator(int quantumNumber, int size) {
        processes = new ArrayList<Process>();
        processor = new Processor(quantumNumber, size);
    }

    public void getUserInput(int minProcess) throws IOException {
        int count = 0;
        Process p;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		out.println("Enter the burst time of the processes : ");
        while(count != minProcess) {
            processes.add(new Process(Integer.parseInt(br.readLine())));
            count++;
        }

        processor.insertProcesses(processes);
    }

	// Just for testing purpose. Suit this to yourself
    private void insertPredefinedValues() {
        processes.add(new Process(8));
        processes.add(new Process(9));
        processes.add(new Process(7));
        processes.add(new Process(5));
        processes.add(new Process(11));

        processor.insertProcesses(processes);
    }

    @Override
    public void start() {
        run();
    }

    @Override
    public void run() {
        for(Core core : processor.getCores())
            new Thread(core).start();
    }

    public static void main(String args[]) throws IOException {
        Simulator sim = new Simulator();
        //sim.getUserInput(6);
        sim.insertPredefinedValues();
        sim.start();
    }
}
