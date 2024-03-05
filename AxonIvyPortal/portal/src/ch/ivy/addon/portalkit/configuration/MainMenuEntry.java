package ch.ivy.addon.portalkit.configuration;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.dto.DisplayName;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MainMenuEntry extends AbstractConfiguration {

	private List<DisplayName> names;
	private String icon;

	public List<DisplayName> getNames() {
		return names;
	}

	public void setNames(List<DisplayName> names) {
		this.names = names;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
