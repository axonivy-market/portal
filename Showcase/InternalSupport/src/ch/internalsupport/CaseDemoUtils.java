package ch.internalsupport;

import java.util.concurrent.Callable;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.SecurityManagerFactory;
import ch.ivyteam.ivy.workflow.ICase;

public class CaseDemoUtils {

	public static void destroyCase(final ICase iCase) {
		try {
			SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<Void>() {
				@Override
				public Void call() throws Exception {
					iCase.destroy();
					return null;
				}
			});
		} catch (Exception e) {
			Ivy.log().error(e);
		}
	}
}
