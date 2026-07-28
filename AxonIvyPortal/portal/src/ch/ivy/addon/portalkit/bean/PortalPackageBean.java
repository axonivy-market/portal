package ch.ivy.addon.portalkit.bean;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;

import com.axonivy.portal.components.util.FacesMessageUtils;

import ch.ivy.addon.portalkit.enums.PortalPackageFile;
import ch.ivy.addon.portalkit.service.PortalPackageService;
import ch.ivyteam.ivy.environment.Ivy;

@Named
@ViewScoped
public class PortalPackageBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private final PortalPackageService service = PortalPackageService.getInstance();

  public static class ImportEntryResult {
    public static ImportEntryResult valid(final String filename) {
      return new ImportEntryResult(filename, "VALID");
    }

    public static ImportEntryResult invalid(final String filename) {
      return new ImportEntryResult(filename, "INVALID");
    }

    public static ImportEntryResult success(final String filename) {
      return new ImportEntryResult(filename, "SUCCESS");
    }
    public static ImportEntryResult skipped(final String filename) {
      return new ImportEntryResult(filename, "SKIPPED");
    }

    public static ImportEntryResult failed(final String filename) {
      return new ImportEntryResult(filename, "FAILED");
    }

    private final String filename;

    private final String status;

    private ImportEntryResult(final String filename, final String status) {
      this.filename = filename;
      this.status = status;
    }

    public String getFilename() { return filename; }
    public String getStatus() { return status; }

    public String getIconClass() {
      return switch (status) {
        case "VALID", "SUCCESS" -> "ti-circle-check text-green-500";
        case "FAILED" -> "ti-circle-x portal-package-alert-danger-icon";
        default -> "ti-alert-triangle portal-package-alert-warning-icon";
      };
    }
  }

  private List<String> validPreviewFiles;
  private List<String> invalidPreviewFiles;
  private boolean fileSelected;
  private List<ImportEntryResult> importResults;
  private byte[] uploadedZipBytes;

  public StreamedContent exportPackage() {
    try {
      return service.exportPackage();
    } catch (final Exception e) {
      Ivy.log().error("Failed to export Portal package", e);
      return null;
    }
  }

  public void handleFileSelect(final FileUploadEvent event) {
    importResults = null;
    final List<String> valid = new ArrayList<>();
    final List<String> invalid = new ArrayList<>();
    try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(event.getFile().getContent()))) {
      final Set<String> seen = new LinkedHashSet<>();
      ZipEntry entry;
      while ((entry = zis.getNextEntry()) != null) {
        if (!entry.isDirectory()) {
          final String name = Paths.get(entry.getName()).getFileName().toString();
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
    } catch (final Exception e) {
      Ivy.log().error("Failed to read uploaded package", e);
      validPreviewFiles = null;
      invalidPreviewFiles = null;
      fileSelected = false;
      uploadedZipBytes = null;
      addMessage(FacesMessage.SEVERITY_ERROR,
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/PortalPackageManagement/InvalidPackageMessage"));
    }
  }

  public void confirmImport() {
    if (validPreviewFiles == null || uploadedZipBytes == null) {
      return;
    }
    final Map<String, Boolean> importStatus = service.importPackage(uploadedZipBytes);
    final List<ImportEntryResult> results = new ArrayList<>();
    validPreviewFiles.forEach(name -> results.add(
        Boolean.TRUE.equals(importStatus.get(name)) ? ImportEntryResult.success(name) : ImportEntryResult.failed(name)));
    invalidPreviewFiles.forEach(name -> results.add(ImportEntryResult.skipped(name)));
    importResults = results;
    final long successCount = results.stream().filter(r -> "SUCCESS".equals(r.getStatus())).count();
    final boolean importFullySuccessful = successCount == validPreviewFiles.size();
    if (importFullySuccessful) {
      addMessage(FacesMessage.SEVERITY_INFO,
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/PortalPackageManagement/ImportCompleteMessage"));
    } else {
      addMessage(FacesMessage.SEVERITY_ERROR,
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/PortalPackageManagement/ImportIncompleteMessage"));
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

  public List<ImportEntryResult> getPreviewEntries() {
    final List<ImportEntryResult> entries = new ArrayList<>();
    getValidPreviewFiles().forEach(name -> entries.add(ImportEntryResult.valid(name)));
    getInvalidPreviewFiles().forEach(name -> entries.add(ImportEntryResult.invalid(name)));
    return entries;
  }

  public boolean isFileSelected() {
    return fileSelected;
  }

  public List<ImportEntryResult> getImportResults() {
    return importResults;
  }

  private void addMessage(final FacesMessage.Severity severity, final String message) {
    FacesContext.getCurrentInstance().addMessage(null, FacesMessageUtils.sanitizedMessage(severity, message, null));
  }

  public List<PortalPackageFile> getPackageFiles() {
    return Arrays.asList(PortalPackageFile.values());
  }

  public String getAcceptedFileType() {
    return service.getAcceptedFileType();
  }
}
