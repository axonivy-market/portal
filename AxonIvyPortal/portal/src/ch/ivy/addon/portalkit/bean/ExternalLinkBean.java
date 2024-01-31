package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;

import com.axonivy.portal.util.ExternalLinkUtils;

import ch.ivy.addon.portalkit.bo.ExternalLinkProcessItem;
import ch.ivy.addon.portalkit.configuration.ExternalLink;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.Protocol;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.service.ExternalLinkService;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.util.Pair;

@ManagedBean
@ViewScoped
public class ExternalLinkBean implements Serializable {

  private static final long serialVersionUID = 4772777911430826945L;
  private ExternalLink externalLink;
  private ExternalLinkService externaLinkService;
  
  @PostConstruct
  public void init() {
    externaLinkService = ExternalLinkService.getInstance();
  }
  
  public void addNewExternalLink(String clientId) {
    externalLink = new ExternalLink();
    externalLink.setIcon(ExternalLinkProcessItem.DEFAULT_ICON);
    PrimeFaces.current().resetInputs(clientId + ":add-external-link-form");
  }

  public ExternalLink saveNewExternalLink() {
    IUser sessionUser = Ivy.session().getSessionUser();
    externalLink.setCreatorId(sessionUser.getId());
    externalLink.setSecurityMemberId(sessionUser.getSecurityMemberId());
    String processLink = correctLink(externalLink.getLink());
    externalLink.setLink(processLink);

    externaLinkService.save(externalLink);
    return externalLink;
  }

  public boolean hasPublicLinkCreationPermission() {
    return PermissionUtils.checkPublicLinkCreationPermission();
  }
  
  public String correctLink(String link) {
    String processLink = link.trim();
    if (!isValidProcessLink(processLink)) {
      processLink = Protocol.HTTP.getValue() + processLink;
    }
    return processLink;
  }

  public void handleImageUpload(FileUploadEvent event) {
    removeImage();
    Pair<String, String> imageInfo = ExternalLinkUtils.handleImageUpload(event);
    externalLink.setImageLocation(imageInfo.getLeft());
    externalLink.setImageType(imageInfo.getRight());
  }
  
  public void removeImage() {
    if (StringUtils.isNoneBlank(externalLink.getImageLocation())) {
      ExternalLinkUtils.removeImage(externalLink.getImageLocation(), externalLink.getImageType());
      externalLink.setImageLocation(null);
      externalLink.setImageType(null);
    }
  }

  private boolean isValidProcessLink(String processLink) {
    String linkInLowerCase = processLink.toLowerCase();
    return linkInLowerCase.startsWith(Protocol.HTTP.getValue())
        || linkInLowerCase.startsWith(Protocol.HTTPS.getValue()) || linkInLowerCase.startsWith("/");
  }
  
  public void startExternalLink(ExternalLink selectedExternalLink) throws IOException {
    FacesContext.getCurrentInstance().getExternalContext().redirect(selectedExternalLink.getLink());
  }

  public ExternalLink getExternalLink() {
    return externalLink;
  }

  public void setExternalLink(ExternalLink externalLink) {
    this.externalLink = externalLink;
  }
  
  public void updateNameByLocale() {
    String currentName = LanguageUtils.getLocalizedName(externalLink.getNames(), externalLink.getName());
    initMultipleLanguagesForLinkName(currentName);
    String currentLanguage = UserUtils.getUserLanguage();
    Optional<DisplayName> optional = externalLink.getNames().stream()
        .filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage())).findFirst();
    if (optional.isPresent()) {
      optional.get().setValue(currentName);
    }
  }
  
  private void initMultipleLanguagesForLinkName(String currentName) {
    Map<String, DisplayName> mapLanguage = externalLink
                                            .getNames()
                                            .stream()
                                            .collect(Collectors.toMap(o -> o.getLocale().toLanguageTag(), o -> o));
    List<String> supportedLanguages = LanguageService.newInstance().findUserLanguages().getIvyLanguage().getSupportedLanguages();
    for (String language : supportedLanguages) {
      DisplayName localeLanguage = mapLanguage.get(language);
      if (localeLanguage == null) {
        DisplayName displayName = new DisplayName();
        displayName.setLocale(Locale.forLanguageTag(language));
        displayName.setValue(currentName);
        externalLink.getNames().add(displayName);
      } else if (StringUtils.isBlank(localeLanguage.getValue())) {
        localeLanguage.setValue(currentName);
      }
    }
  }
  
  public void updateDescriptionByLocale() {
    String currentDescription = LanguageUtils.getLocalizedName(externalLink.getDescriptions(), externalLink.getDescription());
    initMultipleLanguagesForLinkDescription(currentDescription);
    String currentLanguage = UserUtils.getUserLanguage();
    Optional<DisplayName> optional = externalLink.getDescriptions().stream()
        .filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage())).findFirst();
    if (optional.isPresent()) {
      optional.get().setValue(currentDescription);
    }
  }
  
  private void initMultipleLanguagesForLinkDescription(String currentDescription) {
    Map<String, DisplayName> mapLanguage = externalLink
                                            .getDescriptions()
                                            .stream()
                                            .collect(Collectors.toMap(o -> o.getLocale().toLanguageTag(), o -> o));
    List<String> supportedLanguages = LanguageService.newInstance().findUserLanguages().getIvyLanguage().getSupportedLanguages();
    for (String language : supportedLanguages) {
      DisplayName localeLanguage = mapLanguage.get(language);
      if (localeLanguage == null) {
        DisplayName displayName = new DisplayName();
        displayName.setLocale(Locale.forLanguageTag(language));
        displayName.setValue(currentDescription);
        externalLink.getDescriptions().add(displayName);
      } else if (StringUtils.isBlank(localeLanguage.getValue())) {
        localeLanguage.setValue(currentDescription);
      }
    }
  }
}
