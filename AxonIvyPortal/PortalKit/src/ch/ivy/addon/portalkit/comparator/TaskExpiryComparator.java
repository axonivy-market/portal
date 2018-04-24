package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;
import java.util.Date;

import ch.ivy.addon.portalkit.bo.RemoteTask;

public class TaskExpiryComparator implements Comparator<RemoteTask> {

	@Override
	public int compare(RemoteTask firstTask, RemoteTask secondTask) {
		Date firstTaskExpiryTime = firstTask.getExpiryTimestamp();
		Date secondTaskExpiryTime = secondTask.getExpiryTimestamp();
		return compareTaskExpiryTime(firstTaskExpiryTime, secondTaskExpiryTime);
	}

	@SuppressWarnings("null")
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
