package com.github.shkesar.cfs;

public class Process implements Comparable<Process>
{
  private static char PROCESS_ID = 'A';

  private String name;
  private int elapsedProcessTime;
  private int burstTime;

  public static String generateProcessName()
  {
    return String.valueOf(PROCESS_ID++);
  }

  public Process(int burstTime)
  {
    this(generateProcessName(), burstTime);
  }

  public Process(String name, int burstTime)
  {
    this.name = name;
    this.burstTime = burstTime;
  }

  public int getElapsedProcessTime()
  {
    return elapsedProcessTime;
  }

  public void setElapsedProcessTime(int elapsedProcessTime)
  {
    this.elapsedProcessTime = elapsedProcessTime;
  }

  public int getBurstTime()
  {
    return burstTime;
  }

  public void setBurstTime(int burstTime)
  {
    this.burstTime = burstTime;
  }

  @Override
  public int compareTo(Process process)
  {
    return elapsedProcessTime + name.hashCode() -
      process.elapsedProcessTime - process.name.hashCode();
  }

  @Override
  public String toString()
  {
    return "[Process: " + name + ", Burst Time: " + burstTime +
      ", Elapsed Process Time: " + elapsedProcessTime + "]";
  }
}
