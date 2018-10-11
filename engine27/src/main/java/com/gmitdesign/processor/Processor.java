package com.gmitdesign.processor;
import com.gmitdesign.gears.*;
import java.util.*;
import java.util.concurrent.*;

public class Processor implements Runnable
{
	private Thread t = new Thread(this);
	private Gears gears;
	private boolean active;
	private List<ProcessorCommand> commands;
	private LinkedBlockingQueue<ProcessorCommand> commandQueue;
	
	public Processor(Gears gears){
		this.gears=gears;
		commands = new LinkedList<ProcessorCommand>();
		commandQueue = new LinkedBlockingQueue<ProcessorCommand>();
		t.start();
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public boolean isActive()
	{
		return active;
	}
	
	public void process(ProcessorCommand command){
		commandQueue.offer(command);
	}
	
	@Override
	public void run()
	{
	   while(active){
		  this.gears = CommandExecutor.execute(commandQueue.poll(),this.gears);
	   }
	}
	
	public static void main(String[] args){
	    System.out.println("fff");
		Processor p = new Processor(new Gears());
		p.process(new ProcessorCommand());
		p.process(new ProcessorCommand());
		p.process(new ProcessorCommand());
		
	}
}
