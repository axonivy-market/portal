package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;

import ch.ivyteam.ivy.workflow.ITask;

public class TaskPriorityComparator implements Comparator<ITask>{

	@Override
	public int compare(ITask firstTask, ITask secondTask) {
		return firstTask.getPriority().compareTo(secondTask.getPriority());
	}

}
