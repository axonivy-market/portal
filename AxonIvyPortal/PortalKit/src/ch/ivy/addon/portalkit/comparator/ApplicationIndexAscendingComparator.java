package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;

import ch.ivy.addon.portalkit.persistence.domain.Application;

public class ApplicationIndexAscendingComparator implements Comparator<Application> {

	@Override
	public int compare(Application firstApplication, Application secondApplication) {
		return firstApplication.getMenuOrdinal().compareTo(secondApplication.getMenuOrdinal());
	}
}
