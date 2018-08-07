package ch.ivy.ws.addon.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.cm.IContentManagementSystem;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityManager;
import ch.ivyteam.ivy.security.SecurityManagerFactory;
import ch.ivyteam.ivy.server.ServerFactory;

/**
 *	 This class provides method for loading supported language which are defined inside an ivy application. 
 */
public class SupportedLanguageLoader {

	private static final String SUPPORTED_LANGUAGES_CMS_URI = "/AppInfo/SupportedLanguages";

	private String applicationName;

	public SupportedLanguageLoader(String applicationName) {
		this.applicationName = applicationName;
	}

	/**
	 * Loads supported language from an ivy application.
	 * @return {@link List} list of supported language
	 * @throws Exception
	 */
	public List<String> getSupportedLanguages() throws Exception {
		List<String> languages = null;
		ISecurityManager securityManager = SecurityManagerFactory
				.getSecurityManager();
		languages = securityManager.executeAsSystem(new CmsFinderCallable());
		return languages;
	}

	private class CmsFinderCallable implements Callable<List<String>> {

		@Override
		public List<String> call() throws Exception {
			Set<String> supportedLanguages = new HashSet<String>();
			for (IProcessModelVersion pmv : allReachablePmvs()) {
				IContentManagementSystem cms = Ivy.cms().getContentManagement().findCms(pmv);
				String cmsValue = cms.co(SUPPORTED_LANGUAGES_CMS_URI);
				List<String> languages = Arrays.asList(cmsValue.split(","));
				for (String language : languages) {
					if (!language.isEmpty()) {
						supportedLanguages.add(language);
					}
				}
			}
			return new ArrayList<String>(supportedLanguages);
		}

		private List<IProcessModelVersion> allReachablePmvs() {
			IApplication application = ServerFactory.getServer()
					.getApplicationConfigurationManager()
					.findApplication(applicationName);
			List<IProcessModelVersion> pmvs = new ArrayList<IProcessModelVersion>();
			for (IProcessModel pm : application.getProcessModels()) {
				pmvs.add(pm.getReleasedProcessModelVersion());
			}
			return pmvs;
		}
	}
}
