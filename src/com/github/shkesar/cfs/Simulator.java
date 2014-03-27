
package com.github.shkesar.cfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.out;

public class Simulator implements Runnable {
    final static private int QUANTUM_NUMBER = 4;
    private RBTree rbt;

    public Simulator() {
        rbt = new RBTree();
    }

    public void getUserInput(int minProcess) throws IOException {
        int count = 0;
        Process p;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		out.println("Enter the burst time of the processes : ");
        while(count != minProcess) {
            rbt.add(new Process(Integer.parseInt(br.readLine())));
            count++;
        }
    }

	// Just for testing purpose. Suit this to yourself
    private void insertPredefinedValues() {
        rbt.add(new Process(8));
        rbt.add(new Process(9));
        rbt.add(new Process(7));
        rbt.add(new Process(5));
        rbt.add(new Process(11));
    }

    @Override
    public void run() {
        while(!rbt.isEmpty()) {
            Process p = rbt.pollFirst();

            out.println(p);
            int timeToSpend = (p.getBurstTime() > QUANTUM_NUMBER) ? QUANTUM_NUMBER : (p.getBurstTime());
            try {
                Thread.sleep(timeToSpend * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(p.getBurstTime() > QUANTUM_NUMBER) {
                p.setElapsedProcessorTime(p.getElapsedProcessorTime()+timeToSpend);
                p.setBurstTime(p.getBurstTime() - timeToSpend);
                rbt.add(p);
            }
            else {
                p.setElapsedProcessorTime(p.getElapsedProcessorTime()+timeToSpend);
                p.setBurstTime(p.getBurstTime() - timeToSpend);
            }

            out.println(p + "\n");
        }
    }

    public static void main(String args[]) throws IOException {
        Simulator sim = new Simulator();
        //sim.getUserInput(6);
        sim.insertPredefinedValues();
        sim.run();
    }
}
