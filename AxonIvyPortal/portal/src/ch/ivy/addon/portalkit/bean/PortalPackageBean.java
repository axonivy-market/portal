package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;

import ch.ivy.addon.portalkit.service.PortalPackageService;
import ch.ivy.addon.portalkit.service.PortalPackageService.ImportEntryResult;
import ch.ivy.addon.portalkit.service.PortalPackageService.PackagePreview;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class PortalPackageBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private final PortalPackageService service = new PortalPackageService();

  private PackagePreview preview;
  private byte[] pendingZipBytes;
  private List<ImportEntryResult> importResults;
  private List<PortalPackageService.ExportableFile> exportableFiles;

  public void prepareExportDialog() {
    try {
      exportableFiles = service.getExportableFiles();
    } catch (Exception e) {
      Ivy.log().error("Failed to load exportable files", e);
      exportableFiles = List.of();
    }
  }

  public StreamedContent exportPackage() {
    try {
      List<String> selected = exportableFiles == null ? List.of()
          : exportableFiles.stream()
              .filter(PortalPackageService.ExportableFile::isSelected)
              .map(PortalPackageService.ExportableFile::getFilename)
              .collect(java.util.stream.Collectors.toList());
      return service.exportPackage(selected);
    } catch (Exception e) {
      Ivy.log().error("Failed to export Portal package", e);
      return null;
    }
  }

  public boolean isAnyExportFileSelected() {
    return exportableFiles != null
        && exportableFiles.stream().anyMatch(PortalPackageService.ExportableFile::isSelected);
  }

  public List<PortalPackageService.ExportableFile> getExportableFiles() {
    return exportableFiles;
  }

  public void selectAllExportFiles() {
    if (exportableFiles != null) {
      exportableFiles.forEach(f -> f.setSelected(true));
    }
  }

  public void unselectAllExportFiles() {
    if (exportableFiles != null) {
      exportableFiles.forEach(f -> f.setSelected(false));
    }
  }

  public void handleFileSelect(FileUploadEvent event) {
    try {
      pendingZipBytes = event.getFile().getContent();
      preview = service.previewPackage(pendingZipBytes);
    } catch (Exception e) {
      Ivy.log().error("Failed to read uploaded package", e);
      pendingZipBytes = null;
      preview = null;
      addErrorMessage("Invalid package", "The uploaded file could not be read. Please ensure it is a valid zip archive.");
    }
  }

  public void confirmImport() {
    if (pendingZipBytes == null) {
      return;
    }
    try {
      importResults = service.importPackage(pendingZipBytes);
    } catch (Exception e) {
      Ivy.log().error("Failed to import Portal package", e);
    } finally {
      pendingZipBytes = null;
      preview = null;
    }
  }

  public boolean isHasAnySuccess() {
    return importResults != null
        && importResults.stream().anyMatch(r -> "SUCCESS".equals(r.getStatus()));
  }

  public List<String> getValidPreviewFiles() {
    return preview != null ? preview.getValidFiles() : List.of();
  }

  public List<String> getInvalidPreviewFiles() {
    return preview != null ? preview.getInvalidFiles() : List.of();
  }

  public boolean isFileSelected() {
    return preview != null;
  }

  public List<ImportEntryResult> getImportResults() {
    return importResults;
  }

  private void addErrorMessage(String summary, String detail) {
    FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
  }
}
