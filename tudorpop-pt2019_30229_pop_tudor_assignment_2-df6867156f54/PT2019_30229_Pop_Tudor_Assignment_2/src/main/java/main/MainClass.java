package main;

import core.Task;
import core.TaskSolver;
import guiPackage.*;
import guiPackage.SetupWindow;
import simulator.Simulator;

public class MainClass {
	public static void main(String[] args) {
		
		//Simulator sim =new Simulator(4, 0.5f, 2f, 0.5f, 2f,15f);
		//sim.start();
		
		try {
			SetupWindow window = new SetupWindow();
			window.perform();
		}catch(Exception e) {
			System.out.println("Outstanding move");
		}
		
	}
}
