package ch.ivy.addon.portalkit.loader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.security.exec.Sudo;

public class ResourceLoader {

  private IProcessModelVersion pmv;

  public ResourceLoader(IProcessModelVersion pmv) {
    this.pmv = pmv;
  }

  public Optional<Path> findResource(String relativeToWebContent) {
    return getWebContentFolder().map(f -> f.getFile(relativeToWebContent)).map(IFile::getLocationURI)
        .map(Paths::get).filter(Files::exists);
  }

  public Optional<Path> getPom() {
    return Optional.ofNullable(pmv.getProject().getFile("pom.xml")).map(IFile::getLocationURI)
        .map(Paths::get).filter(Files::exists);
  }

  private Optional<IFolder> getWebContentFolder() {
    try {
      return Optional.ofNullable(Sudo.call(() -> pmv.getProject().getFolder("webContent")));
    } catch (Exception failToGetWebContent) {
      throw new PortalException("Fail to reach webContent folder", failToGetWebContent);
    }
  }
}
