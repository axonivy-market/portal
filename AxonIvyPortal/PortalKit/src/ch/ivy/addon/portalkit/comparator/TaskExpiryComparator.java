package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;
import java.util.Date;

import ch.ivyteam.ivy.workflow.ITask;

public class TaskExpiryComparator implements Comparator<ITask> {

	@Override
	public int compare(ITask firstTask, ITask secondTask) {
		Date firstTaskExpiryTime = firstTask.getExpiryTimestamp();
		Date secondTaskExpiryTime = secondTask.getExpiryTimestamp();
		return compareTaskExpiryTime(firstTaskExpiryTime, secondTaskExpiryTime);
	}

	private int compareTaskExpiryTime(Date firstExpiryTime, Date secondExpiryTime) {
		if(firstExpiryTime == null && secondExpiryTime == null) {
			return 0;
		}
		if(firstExpiryTime == null && secondExpiryTime != null) {
			return 1;
		}
		if(firstExpiryTime != null && secondExpiryTime == null) {
			return -1;
		}
		return firstExpiryTime.compareTo(secondExpiryTime);
	}
	
}
