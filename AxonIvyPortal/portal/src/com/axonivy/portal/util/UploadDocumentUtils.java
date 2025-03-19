package com.axonivy.portal.util;

import static ch.ivy.addon.portal.chat.ChatReferencesContainer.log;

import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.virusscan.VirusException;
import org.primefaces.virusscan.VirusScanner;
import org.primefaces.virusscan.VirusScannerService;

import ch.ivy.addon.portalkit.document.DocumentDetector;
import ch.ivy.addon.portalkit.document.DocumentDetectorFactory;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.environment.Ivy;

public class UploadDocumentUtils {

  private static GlobalSettingService globalSettingService = GlobalSettingService.getInstance();
  private static final int MEGABYTE_IN_BYTE = 1048576;

  public static String validateUploadedFile(UploadedFile importFile) {
    String validateMessage = "";
    if (importFile == null || importFile.getSize() == 0) {
      validateMessage = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileEmptyMessage");
    } else if (enableVirusScannerForUploadedDocument() && isDocumentTypeHasVirus(importFile)) {
      validateMessage = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileContainVirus");
    } else if (enableScriptCheckingForUploadedDocument() && !isDocumentSafe(importFile)) {
      validateMessage = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileContainScript");
    } else if (!FilenameUtils.isExtension(importFile.getFileName(), "json")) {
      validateMessage = Ivy.cms().co("/Dialogs/components/CaseDocument/invalidFileMessage");
    }
    return validateMessage;
  }

  public static boolean enableVirusScannerForUploadedDocument() {
    return globalSettingService
        .findGlobalSettingValueAsBoolean(GlobalVariable.ENABLE_VIRUS_SCANNER_FOR_UPLOADED_DOCUMENT);
  }

  public static boolean enableScriptCheckingForUploadedDocument() {
    GlobalSettingService globalSettingService = GlobalSettingService.getInstance();
    String enableScriptCheckingForUploadedDocument =
        globalSettingService.findGlobalSettingValue(GlobalVariable.ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT);
    return Boolean.parseBoolean(enableScriptCheckingForUploadedDocument);
  }

  public static boolean isDocumentTypeHasVirus(UploadedFile uploadedFile) {
    VirusScannerService service = new VirusScannerService(VirusScanner.class.getClassLoader());
    try {
      service.performVirusScan(uploadedFile);
    } catch (VirusException e) {
      Ivy.log().error(e);
      return true;
    }
    return false;
  }

  public static boolean isDocumentSafe(UploadedFile uploadedFile) {
    if (uploadedFile != null) {
      DocumentDetectorFactory documentDetectorFactory = new DocumentDetectorFactory();
      DocumentDetector documentDetector = documentDetectorFactory
          .getDocumentDetector(FilenameUtils.getExtension(StringUtils.lowerCase(uploadedFile.getFileName())));
      if (documentDetector != null) {
        try {
          return documentDetector.isSafe(uploadedFile.getInputStream());
        } catch (IOException e) {
          Ivy.log().error(e);
          return false;
        }
      }
      // File type doesn't support for scanning inside script
      else
        return true;
    }
    return false;
  }

  public static Long getDocumentUploadSizeLimit() {
    String documentUploadSizeLimit =
        globalSettingService.findGlobalSettingValue(GlobalVariable.DOCUMENT_UPLOAD_SIZE_LIMIT);
    int uploadSizeLimitNumber = 0;
    try {
      if (StringUtils.isNotBlank(documentUploadSizeLimit)) {
        uploadSizeLimitNumber = Integer.parseInt(documentUploadSizeLimit);
      }
    } catch (NumberFormatException e) {
      log().error("Document size limit must be an Integer number, check Portal settings again.", e);
    }
    Long sizeLimitInByte = (long) (uploadSizeLimitNumber * MEGABYTE_IN_BYTE);
    return sizeLimitInByte;
  }

  public static Long getImageUploadSizeLimit() {
    String imageUploadSizeLimit = globalSettingService.findGlobalSettingValue(GlobalVariable.IMAGE_UPLOAD_SIZE_LIMIT);
    int uploadSizeLimitNumber = 0;
    try {
      if (StringUtils.isNotBlank(imageUploadSizeLimit)) {
        uploadSizeLimitNumber = Integer.parseInt(imageUploadSizeLimit);
      }
    } catch (NumberFormatException e) {
      log().error("Image size limit must be an Integer number, check Portal settings again.", e);
    }
    Long sizeLimitInByte = (long) (uploadSizeLimitNumber * MEGABYTE_IN_BYTE);
    return sizeLimitInByte;
  }
}
