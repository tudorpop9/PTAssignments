package core;

import java.util.Comparator;

public class TaskSolverComparator implements Comparator<TaskSolver>{

	public synchronized int compare(TaskSolver o1, TaskSolver o2) {
		if(o1.getWaitingTime() == o2.getWaitingTime()) {
			
			if(o1.getSolverNumber() < o2.getSolverNumber()) {
				return -1;
			}else if (o1.getSolverNumber() > o2.getSolverNumber()) {
				return 1;
			}else {
					return 0;
			}
		}else if (o1.getWaitingTime() < o2.getWaitingTime()) {
			return -1;
		}else {
			return 1;
		}
	}
	
}
