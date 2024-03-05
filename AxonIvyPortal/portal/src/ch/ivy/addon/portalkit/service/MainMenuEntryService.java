package ch.ivy.addon.portalkit.service;

import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;

import ch.ivy.addon.portalkit.configuration.MainMenuEntry;
import ch.ivy.addon.portalkit.dto.DisplayName;
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

	private MainMenuEntry getMainMenuEntry() {
		List<MainMenuEntry> mainMenuDashboardList = getPublicConfig();
		return mainMenuDashboardList.stream().findFirst().orElse(null);
	}

	public String getNameInCurrentLocale() {
		Locale currentLocale = Ivy.session().getContentLocale();
		MainMenuEntry mainMenuEntry = getMainMenuEntry();

		if (mainMenuEntry != null) {
			String displayName = getNameFollowingLocale(mainMenuEntry.getNames(), currentLocale);
			DisplayNameAdaptor displayNameAdaptor = new DisplayNameAdaptor(displayName, currentLocale);
			return displayNameAdaptor.getDisplayNameAsString();
		}
		return "";
	}

	public String getMenuIcon() {
		MainMenuEntry mainMenuEntry = getMainMenuEntry();
		if (mainMenuEntry != null) {
			return mainMenuEntry.getIcon();
		}
		return "";
	}

	private String getNameFollowingLocale(List<DisplayName> displayNameList, Locale locale) {
		List<Locale> localeList = displayNameList.stream().map(DisplayName::getLocale).toList();

		if (CollectionUtils.isNotEmpty(displayNameList) && isContainsLocale(localeList, locale)) {
			return displayNameList.stream().filter(item -> item.getLocale().equals(locale)).map(DisplayName::getValue)
					.findAny().orElse(null);
		}
		return "";
	}

	private boolean isContainsLocale(List<Locale> localeList, Locale locale) {
		return localeList.contains(locale);
	}
}
