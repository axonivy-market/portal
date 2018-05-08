package ch.ivy.addon.portalkit.loader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.eclipse.core.resources.IFolder;

import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.server.ServerFactory;

public class ResourceLoader {
  
  private IProcessModelVersion pmv;
  
  public ResourceLoader(IProcessModelVersion pmv) {
    this.pmv = pmv;
  }

  public Optional<Path> findResource(String relativeToWebContent) {
    return getWebContentFolder().map(f -> f.getFile(relativeToWebContent)).map(f -> f.getLocationURI())
        .map(u -> Paths.get(u)).filter(p -> Files.exists(p));
  }
  
  public Optional<Path> getPom() {
    return Optional.ofNullable(pmv.getProject().getFile("pom.xml")).map(f -> f.getLocationURI())
      .map(u -> Paths.get(u)).filter(p -> Files.exists(p));
  }

  private Optional<IFolder> getWebContentFolder() {
    try {
      return Optional.ofNullable(ServerFactory.getServer().getSecurityManager().executeAsSystem(
          () -> pmv.getProject().getFolder("webContent")));
    } catch (Exception failToGetWebContent) {
      throw new PortalException("Fail to reach webContent folder", failToGetWebContent);
    }
  }
}
