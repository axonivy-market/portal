package ch.ivy.addon.portalkit.persistence.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.ExternalLink;
import ch.ivyteam.ivy.application.IApplication;

public class ExternalLinkDao extends AbstractDao<ExternalLink> {

  public ExternalLinkDao() {
    super();
  }
  
  public ExternalLinkDao(IApplication application) {
    super(application);
  }

  @ExecuteAsSystem
  public List<ExternalLink> findStartableLink(String userName) {
    return findAll().stream()
        .filter(externalLink -> StringUtils.equals(externalLink.getUsername(), userName) || externalLink.isPublic())
        .collect(Collectors.toList());
  }

  public void delete(long id) {
    ExternalLink persistedExternalLink = findById(id);
    delete(persistedExternalLink);
  }

}
