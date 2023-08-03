package ch.ivy.addon.portalkit.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.configuration.ExternalLink;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.util.RoleUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

public class ExternalLinkService extends JsonConfigurationService<ExternalLink> {
  private static final String EXTERNAL_LINKS = PortalVariable.EXTERNAL_LINK.key;
  private static ExternalLinkService instance;

  private ExternalLinkService() {}

  public static ExternalLinkService getInstance() {
    if (instance == null) {
      instance = new ExternalLinkService();
    }
    return instance;
  }
  
  public ExternalLink findExternalLinkByName(String externalLinkName) {
    if (StringUtils.isBlank(externalLinkName)) {
      return null;
    }
    return findAll().stream().filter(link -> externalLinkName.equals(link.getName())).findFirst().orElse(null);
  }

  public List<ExternalLink> filterPublicExternalLinksForIvySessionUser() {
    IUser sessionUser = Ivy.session().getSessionUser();
    return ExternalLinkService.getInstance().getPublicConfig().stream()
        .filter(externalLink -> RoleUtils.hasRolePermission(sessionUser.getRoles(), externalLink.getPermissions())
            || externalLink.getCreatorId() == sessionUser.getId())
        .collect(Collectors.toList());
  }

  public List<ExternalLink> filterPublicExternalLinksNotForIvySessionUser() {
    IUser sessionUser = Ivy.session().getSessionUser();
    return ExternalLinkService.getInstance().getPublicConfig().stream()
        .filter(externalLink -> !RoleUtils.hasRolePermission(sessionUser.getRoles(), externalLink.getPermissions())
            && externalLink.getCreatorId() != sessionUser.getId())
        .collect(Collectors.toList());
  }


  @Override
  public Class<ExternalLink> getType() {
    return ExternalLink.class;
  }

  @Override
  public String getConfigKey() {
    return EXTERNAL_LINKS;
  }

  @Override
  public List<ExternalLink> findAll() {
    return super.findAll();
  }
}
