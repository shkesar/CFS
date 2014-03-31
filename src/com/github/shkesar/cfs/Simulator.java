package com.github.shkesar.cfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simulator
{
  private Processor processor;

  public Simulator(int quantumNumber, int size)
  {
    processor = new Processor(quantumNumber, size);
  }

  private void start()
  {
    ExecutorService service = Executors.newFixedThreadPool(processor.getNumberOfCores());
    processor.getCores().stream().forEach(service::execute);
    service.shutdown();
  }

  private void getBurstTimeOfProcessesFromUser()
  {
    List<Process> processes = new ArrayList<>();
    try (Scanner scanner = new Scanner(System.in))
    {
      System.out.print("Enter burst times of any number of processes separated by space: ");

      processInput(processes, scanner.nextLine());

      processor.queueProcesses(processes);
    }
  }

  private void processInput(List<Process> processes, String line)
  {
    try (Scanner scanner = new Scanner(line))
    {
      while (scanner.hasNextInt())
      {
        processes.add(new Process(scanner.nextInt()));
      }
    }
  }

  /*
  private void addTestProcesses()
  {
    List<Process> processes = new ArrayList<>();
    Random random = new Random(System.currentTimeMillis());

    for (int i = 0; i < 5; i++)
    {
      processes.add(new Process(random.nextInt(10) + 1));
    }

    processor.queueProcesses(processes);
  }
  */

  public static void main(String args[])
  {
    Simulator simulator = new Simulator(4, 2);

    simulator.getBurstTimeOfProcessesFromUser();
    //simulator.addTestProcesses();

    simulator.start();
  }
}
