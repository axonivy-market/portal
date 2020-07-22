package ch.ivy.addon.portalkit.persistence.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
  public List<ExternalLink> findStartableLink(Long userId) {
    return Optional.ofNullable(findAll()).orElse(new ArrayList<>()).stream()
        .filter(externalLink -> Optional.ofNullable(externalLink.getCreatorId()).orElse(-1L).longValue() == userId.longValue() || externalLink.isPublic())
        .collect(Collectors.toList());
  }

  public void delete(long id) {
    ExternalLink persistedExternalLink = findById(id);
    delete(persistedExternalLink);
  }

}
