package com.github.shkesar.cfs;

import java.util.TreeSet;

public class Core implements Runnable
{
  private TreeSet<Process> processes;
  private Processor processor;
  private int totalBurstTime;

  public Core(Processor processor)
  {
    this.processor = processor;
    processes = new TreeSet<>();
  }

  public void add(Process process)
  {
    totalBurstTime += process.getBurstTime();
    processes.add(process);
  }

  public void remove(Process process)
  {
    totalBurstTime -= process.getBurstTime();
    processes.remove(process);
  }

  public int getTotalBurstTime()
  {
    return totalBurstTime;
  }

  @Override
  public void run()
  {
    while (!processes.isEmpty())
    {
      final Process process = processes.pollFirst();
      final int quantumNumber = processor.getQuantumNumber();

      System.out.println(toString() + process + " Active");

      int timeToSpend = (process.getBurstTime() > quantumNumber) ? quantumNumber : process.getBurstTime();
      runProcess(timeToSpend);

      totalBurstTime -= timeToSpend;

      process.setElapsedProcessTime(process.getElapsedProcessTime() + timeToSpend);
      process.setBurstTime(process.getBurstTime() - timeToSpend);

      if (process.getBurstTime() > quantumNumber)
      {
        processes.add(process);
      }

      System.out.println(toString() + process + " Deactive");
    }
  }

  // The process runs, ain't it?
  private void runProcess(int timeToSpend)
  {
    try
    {
      Thread.sleep(timeToSpend * 1000);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public String toString()
  {
    return "[Core: " + processor.getCoreID(this) + "]";
  }
}
