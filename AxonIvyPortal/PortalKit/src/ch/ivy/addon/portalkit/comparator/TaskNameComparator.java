package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;

import ch.ivy.addon.portalkit.bo.RemoteTask;

public class TaskNameComparator implements Comparator<RemoteTask> {

    @Override
    public int compare(RemoteTask firstTask, RemoteTask secondTask) {
	return firstTask.getName().compareTo(secondTask.getName());
    }

}
