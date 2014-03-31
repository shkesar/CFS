package com.github.shkesar.cfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Processor
{
  private ArrayList<Core> cores;
  private int quantumNumber;

  public Processor(int quantumNumber, int numberOfCores)
  {
    this.quantumNumber = quantumNumber;

    cores = new ArrayList<>(numberOfCores);

    IntStream.range(0, numberOfCores).forEach(n -> cores.add(new Core(this)));
  }

  public void queueProcesses(List<Process> processes)
  {
    processes.forEach(this::add);
  }

  public void add(Process process)
  {
    getLessUsedCore().add(process);
  }

  private Core getLessUsedCore()
  {
    return Collections.min(cores, (x, y) -> x.getTotalBurstTime() - y.getTotalBurstTime());
  }

  public int getCoreID(Core core)
  {
    return cores.indexOf(core) + 1;
  }

  public int getQuantumNumber()
  {
    return quantumNumber;
  }

  public int getNumberOfCores()
  {
    return cores.size();
  }

  public ArrayList<Core> getCores()
  {
    return cores;
  }
}
