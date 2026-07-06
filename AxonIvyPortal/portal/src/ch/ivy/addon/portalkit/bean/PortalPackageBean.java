package ch.ivy.addon.portalkit.bean;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;

import ch.ivy.addon.portalkit.service.PortalPackageService;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class PortalPackageBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private final PortalPackageService service = new PortalPackageService();

  public static class ImportEntryResult {
    private final String filename;
    private final String status;

    private ImportEntryResult(String filename, String status) {
      this.filename = filename;
      this.status = status;
    }

    public static ImportEntryResult success(String filename) {
      return new ImportEntryResult(filename, "SUCCESS");
    }

    public static ImportEntryResult skipped(String filename) {
      return new ImportEntryResult(filename, "SKIPPED");
    }

    public static ImportEntryResult failed(String filename) {
      return new ImportEntryResult(filename, "FAILED");
    }

    public String getFilename() { return filename; }
    public String getStatus() { return status; }
  }

  private List<String> validPreviewFiles;
  private List<String> invalidPreviewFiles;
  private boolean fileSelected;
  private List<ImportEntryResult> importResults;
  private byte[] uploadedZipBytes;

  public StreamedContent exportPackage() {
    try {
      return service.exportPackage();
    } catch (Exception e) {
      Ivy.log().error("Failed to export Portal package", e);
      return null;
    }
  }

  public void handleFileSelect(FileUploadEvent event) {
    importResults = null;
    List<String> valid = new ArrayList<>();
    List<String> invalid = new ArrayList<>();
    try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(event.getFile().getContent()))) {
      Set<String> seen = new LinkedHashSet<>();
      ZipEntry entry;
      while ((entry = zis.getNextEntry()) != null) {
        if (!entry.isDirectory()) {
          String name = Paths.get(entry.getName()).getFileName().toString();
          if (seen.add(name) && name.endsWith(".json")) {
            if (service.getFileDescriptions().containsKey(name)) {
              valid.add(name);
            } else {
              invalid.add(name);
            }
          }
        }
        zis.closeEntry();
      }
      validPreviewFiles = valid;
      invalidPreviewFiles = invalid;
      fileSelected = true;
      uploadedZipBytes = event.getFile().getContent();
    } catch (Exception e) {
      Ivy.log().error("Failed to read uploaded package", e);
      validPreviewFiles = null;
      invalidPreviewFiles = null;
      fileSelected = false;
      uploadedZipBytes = null;
      addErrorMessage("Invalid package", "The uploaded file could not be read. Please ensure it is a valid zip archive.");
    }
  }

  public void confirmImport() {
    if (validPreviewFiles == null || uploadedZipBytes == null) {
      return;
    }
    Map<String, Boolean> importStatus = service.importPackage(uploadedZipBytes);
    List<ImportEntryResult> results = new ArrayList<>();
    validPreviewFiles.forEach(name -> results.add(
        Boolean.TRUE.equals(importStatus.get(name)) ? ImportEntryResult.success(name) : ImportEntryResult.failed(name)));
    invalidPreviewFiles.forEach(name -> results.add(ImportEntryResult.skipped(name)));
    importResults = results;
    long successCount = results.stream().filter(r -> "SUCCESS".equals(r.getStatus())).count();
    boolean importFullySuccessful = successCount == validPreviewFiles.size();
    if (importFullySuccessful) {
      addInfoMessage("Import complete", "All files were imported successfully.");
    } else {
      addErrorMessage("Import incomplete", "Some files could not be imported. Check the server log for details.");
    }
    validPreviewFiles = null;
    invalidPreviewFiles = null;
    uploadedZipBytes = null;
    fileSelected = false;
  }

  public void resetImportState() {
    validPreviewFiles = null;
    invalidPreviewFiles = null;
    fileSelected = false;
    uploadedZipBytes = null;
    importResults = null;
  }

  public boolean isHasAnySuccess() {
    return importResults != null
        && importResults.stream().anyMatch(r -> "SUCCESS".equals(r.getStatus()));
  }

  public List<String> getValidPreviewFiles() {
    return validPreviewFiles != null ? validPreviewFiles : List.of();
  }

  public List<String> getInvalidPreviewFiles() {
    return invalidPreviewFiles != null ? invalidPreviewFiles : List.of();
  }

  public boolean isFileSelected() {
    return fileSelected;
  }

  public List<ImportEntryResult> getImportResults() {
    return importResults;
  }

  private void addErrorMessage(String summary, String detail) {
    FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
  }

  private void addInfoMessage(String summary, String detail) {
    FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
  }
}
