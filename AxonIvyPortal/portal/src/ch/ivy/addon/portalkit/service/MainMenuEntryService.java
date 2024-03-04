package ch.ivy.addon.portalkit.service;

import java.util.List;
import java.util.Locale;

import ch.ivy.addon.portalkit.configuration.MainMenuEntry;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.util.DisplayNameAdaptor;
import ch.ivyteam.ivy.environment.Ivy;

public class MainMenuEntryService extends JsonConfigurationService<MainMenuEntry> {
	private static final String MAIN_MENU_ENTRY = PortalVariable.MAIN_MENU_ENTRY.key;

	public MainMenuEntryService() {
	}

	@Override
	public Class<MainMenuEntry> getType() {
		return MainMenuEntry.class;
	}

	@Override
	public String getConfigKey() {
		return MAIN_MENU_ENTRY;
	}

	private MainMenuEntry getMainMenuDashboard() {
		List<MainMenuEntry> mainMenuDashboardList = getPublicConfig();
		return mainMenuDashboardList.stream().findFirst().orElse(null);
	}

	public String getDisplayNameInCurrentLocale() {
		Locale currentLocale = Ivy.session().getContentLocale();
		DisplayNameAdaptor displayNameAdaptor = new DisplayNameAdaptor(getMainMenuDashboard().getDisplayName(),currentLocale);
		return displayNameAdaptor.getDisplayNameAsString();
	}
	
	public String getMenuIcon() {
		return getMainMenuDashboard().getMenuIcon();
	}
}
