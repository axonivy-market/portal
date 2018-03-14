package ch.ivy.addon.portalkit.util;

import java.text.Collator;

import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.scripting.objects.List;
import ch.ivyteam.ivy.workflow.IProcessStart;

/**
 * Utility to sort process starts.
 *
 * @author maonguyen
 */
public class SortProcessStartsUtil {

	/** 
	 * Sorts a list of processStarts
	 * 
	 * @param list A list of processStarts to sort
	 * @param locale The locale used for sorting 
	 */
	public static void SortProcessStarts(List<IProcessStart> list, Locale locale) {
		final Collator collator = Collator.getInstance(locale);
		Collections.sort(list, new Comparator<IProcessStart>() {
			@Override
      public int compare(IProcessStart o1, IProcessStart o2) {
				try {
					String str1 = o1.getName();
					String str2 = o2.getName();
					if(str1 == null || str1.trim().length() == 0) str1 = o1.getUserFriendlyRequestPath();
					if(str2 == null || str2.trim().length() == 0) str2 = o2.getUserFriendlyRequestPath();
					return collator.compare(str1, str2);
				} catch (PersistencyException e) {
					Ivy.log().warn(e);
				}
				return 0;
			}
		});

	}
}
