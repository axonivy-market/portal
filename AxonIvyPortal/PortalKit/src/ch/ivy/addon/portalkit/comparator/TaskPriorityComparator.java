package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;

import ch.ivy.addon.portalkit.bo.RemoteTask;

public class TaskPriorityComparator implements Comparator<RemoteTask>{

	@Override
	public int compare(RemoteTask firstTask, RemoteTask secondTask) {
		return firstTask.getPriority().compareTo(secondTask.getPriority());
	}

}
