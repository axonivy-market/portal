package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;

import ch.ivy.addon.portalkit.loader.ResourceLoader;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.Binary;
import ch.ivyteam.ivy.scripting.objects.File;

import com.inet.lib.less.Less;

@ManagedBean
@ViewScoped
public class DesignChooserBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private final static String PORTALSTYLE_LIBRARY = "ch.ivyteam.ivy.project.portal:portalStyle";
  private static final String VARIABLES_LESS_PATH = "/resources/less/variables.less";
  private static final String CUSTOMIZATION_LESS_PATH = "/resources/less/customization.less";
  private static final String THEME_LESS_PATH = "/resources/less/theme.less";
  private static final String THEME_CSS_PATH = "/resources/css/theme.min.css";
  private static final String HOME_LOGO_CMS = "/images/logo/CorporateLogo";
  private static final String LOGIN_LOGO_CMS = "/images/logo/loginLogo";
  private static final String MAIN_COLOR_ATTRIBUTE = "@menu-color: ";
  private static final String BACKGROUND_COLOR_ATTRIBUTE = "@body-background-color: ";
  private static final String ANY_CHARACTERS_REGEX = "([^']*)";
  private static final String SEMICOLON = ";";
  private static final String HASH_SIGN = "#";
  private static final String MAIN_COLOR_PATTERN = MAIN_COLOR_ATTRIBUTE + ANY_CHARACTERS_REGEX;
  private static final String BACKGROUND_COLOR_PATTERN = BACKGROUND_COLOR_ATTRIBUTE + ANY_CHARACTERS_REGEX;
  private static final String HOME_LOGO_HEIGHT_ATTRIBUTE = "@home-logo-height:";
  private static final String LOGIN_LOGO_HEIGHT_ATTRIBUTE = "@login-logo-height:";
  private static final String HOME_LOGO_HEIGHT_PATTERN = HOME_LOGO_HEIGHT_ATTRIBUTE + ANY_CHARACTERS_REGEX;
  private static final String LOGIN_LOGO_HEIGHT_PATTERN = LOGIN_LOGO_HEIGHT_ATTRIBUTE + ANY_CHARACTERS_REGEX;
  private static final String LOGIN_LOGO = "LoginLogo.png";
  private static final String HOME_LOGO = "HomeLogo.png";

  private String mainColor;
  private String backgroundColor;
  private boolean uploadedHomeLogo; 
  private long homeLogoHeight;
  private boolean uploadedLoginLogo;
  private long loginLogoHeight;
  
  private ResourceLoader loader;
  private transient InputStream homeLogoStream;
  private transient InputStream loginLogoStream;

  @PostConstruct
  public void init() {
    try {
      initWebContentLoader();
      uploadedHomeLogo = false;
      uploadedLoginLogo = false;
      mainColor = retrieveMainColorFromFile();
      backgroundColor = retrieveBackgroundColorFromFile();
      String regex = "(?<=[0-9])(?=[^0-9])";
      String[] homeLogoHeightStyle = retrieveHomeLogoHeightFromFile().split(regex);
      if (homeLogoHeightStyle.length > 0){
        homeLogoHeight = Long.parseLong(homeLogoHeightStyle[0]);
      }
      String[] loginLogoHeightStyle = retrieveLoginLogoHeightFromFile().split(regex);
      if (loginLogoHeightStyle.length > 0){
        loginLogoHeight = Long.parseLong(loginLogoHeightStyle[0]);
      }
    } catch (IOException e1) {
      Ivy.log().error("Can't retrieve colors from less file", e1);
    } catch (NumberFormatException e2) {
      Ivy.log().error("Can't retrieve colors from less file", e2);
    }
  }
  
  
  public String getLoginLogoImage() throws IOException {
    return Ivy.html().fileref(new File(LOGIN_LOGO, true));
  }

  public String getHomeLogoImage() throws IOException {
    return Ivy.html().fileref(new File(HOME_LOGO, true));
  }

  public void uploadLoginLogo(FileUploadEvent event) throws IOException {
    uploadedLoginLogo = true;
    loginLogoStream = event.getFile().getInputstream();
    File file = new File(LOGIN_LOGO, true);
    Binary content = new Binary(event.getFile().getContents());
    file.writeBinary(content);
  }

  public void uploadHomeLogo(FileUploadEvent event) throws IOException {
    uploadedHomeLogo = true;
    homeLogoStream = event.getFile().getInputstream();
    File file = new File(HOME_LOGO, true);
    Binary content = new Binary(event.getFile().getContents());
    file.writeBinary(content);
  }
  
  public void apply() throws IOException {
    applyNewColors();
    if (loginLogoStream != null) {
      uploadLogo(loginLogoStream, LOGIN_LOGO_CMS);
    }
    if (homeLogoStream != null) {
      uploadLogo(homeLogoStream, HOME_LOGO_CMS);
    }
    scaleLogo();
    compileThemeLess();
  }
  
  private void scaleLogo() throws IOException {
    Optional<Path> path = loader.findResource(CUSTOMIZATION_LESS_PATH);
    if (path.isPresent()) {
      try (Stream<String> lineStream = Files.lines(path.get())) {
        String newLoginLogoHeight = LOGIN_LOGO_HEIGHT_ATTRIBUTE + " " + loginLogoHeight + "px" + SEMICOLON;
        String newHomeLogoHeight = HOME_LOGO_HEIGHT_ATTRIBUTE + " " + homeLogoHeight + "px" + SEMICOLON;
        
        List<String> lines = lineStream.map(line -> line.replaceAll(LOGIN_LOGO_HEIGHT_PATTERN, newLoginLogoHeight))
                  .map(line -> line.replaceAll(HOME_LOGO_HEIGHT_PATTERN, newHomeLogoHeight)).collect(Collectors.toList());
        if (!lines.stream().anyMatch(Pattern.compile(LOGIN_LOGO_HEIGHT_PATTERN).asPredicate())) {
          lines.add(newLoginLogoHeight);
        }
        if (!lines.stream().anyMatch(Pattern.compile(HOME_LOGO_HEIGHT_PATTERN).asPredicate())) {
          lines.add(newHomeLogoHeight);
        }
        Files.write(path.get(), lines);
      }
    }
  }
  
  private void applyNewColors() throws IOException {
    Optional<Path> path = loader.findResource(CUSTOMIZATION_LESS_PATH);
    if (path.isPresent()) {
      try (Stream<String> lineStream = Files.lines(path.get())) {
        String newMainColor = MAIN_COLOR_ATTRIBUTE + HASH_SIGN + mainColor + SEMICOLON;
        String newBackgroundColor = BACKGROUND_COLOR_ATTRIBUTE + HASH_SIGN + backgroundColor + SEMICOLON;
        
        List<String> lines = lineStream.map(line -> line.replaceAll(MAIN_COLOR_PATTERN, newMainColor))
                  .map(line -> line.replaceAll(BACKGROUND_COLOR_PATTERN, newBackgroundColor)).collect(Collectors.toList());
        if (!lines.stream().anyMatch(Pattern.compile(MAIN_COLOR_PATTERN).asPredicate())) {
          lines.add(newMainColor);
        }
        if (lines.stream().filter(Pattern.compile(BACKGROUND_COLOR_PATTERN).asPredicate()).count() == 1) {
          lines.add(newBackgroundColor);
        }
        Files.write(path.get(), lines);
      }
    }
  }
  
  private void compileThemeLess() throws IOException {
    Optional<Path> themeLessFilePath = loader.findResource(THEME_LESS_PATH);
    Optional<Path> themeFilePath = loader.findResource(THEME_CSS_PATH);

    if (themeLessFilePath.isPresent()) {
      String compiledContent = Less.compile(themeLessFilePath.get().toFile(), true);
      if (themeFilePath.isPresent() && StringUtils.isNotBlank(compiledContent)) {
        List<String> content = new ArrayList<>();
        content.add(compiledContent);
        Files.write(themeFilePath.get(), content);
      }
    }
  }

  private void uploadLogo(InputStream is, String cms) {
    Ivy.cms().findContentObjectValue(cms, Locale.ENGLISH).setContent(is, 0, null);
  }
  
  private String retrieveLoginLogoHeightFromFile() throws IOException {
    return retrieveStyleValueFromLessFile(LOGIN_LOGO_HEIGHT_PATTERN);
  }

  private String retrieveHomeLogoHeightFromFile() throws IOException {
    return retrieveStyleValueFromLessFile(HOME_LOGO_HEIGHT_PATTERN);
  }

  private String retrieveMainColorFromFile() throws IOException {
    return retrieveStyleValueFromLessFile(MAIN_COLOR_PATTERN);
  }

  private String retrieveBackgroundColorFromFile() throws IOException {
    return retrieveStyleValueFromLessFile(BACKGROUND_COLOR_PATTERN);
  }

  private String retrieveStyleValueFromLessFile(String pattern) throws IOException {
    String styleValue = retrieveStyleValueFromLessFile(pattern, CUSTOMIZATION_LESS_PATH);
    if (StringUtils.isBlank(styleValue)) {
      styleValue = retrieveStyleValueFromLessFile(pattern, VARIABLES_LESS_PATH);
    }
    return styleValue;
  }
  
  private String retrieveStyleValueFromLessFile(String pattern, String resource) throws IOException {
    String styleValue = StringUtils.EMPTY;
    Optional<Path> path = loader.findResource(resource);
    if (path.isPresent()) {
      try (Stream<String> lines = Files.lines(path.get())) {
        Pattern p = Pattern.compile(pattern);
        Optional<Matcher> matcher = lines.map(p::matcher).filter(Matcher::matches).findFirst();
        if (matcher.isPresent()) {
          String colorLine = matcher.get().group(1);
          styleValue = colorLine.substring(colorLine.indexOf(": ") + 2, colorLine.length() - 1);
        }
      }
    }
    return styleValue;
  }

  private void initWebContentLoader() {
    if (loader == null) {
      for (IProcessModel pm : Ivy.request().getApplication().getProcessModels()) {
        IProcessModelVersion releasedPmv = pm.getReleasedProcessModelVersion();
        if (releasedPmv != null) {
          String libraryId = releasedPmv.getLibrary().getId();
          if (PORTALSTYLE_LIBRARY.equals(libraryId)) {
            loader = new ResourceLoader(releasedPmv);
          }
        }
      }
    }
  }
  
  public String getMainColor() {
    return mainColor;
  }

  public void setMainColor(String mainColor) {
    this.mainColor = mainColor;
  }

  public String getBackgroundColor() {
    return backgroundColor;
  }

  public void setBackgroundColor(String backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  public long getHomeLogoHeight() {
    return homeLogoHeight;
  }

  public void setHomeLogoHeight(long homeLogoHeight) {
    this.homeLogoHeight = homeLogoHeight;
  }

  public long getLoginLogoHeight() {
    return loginLogoHeight;
  }

  public void setLoginLogoHeight(long loginLogoHeight) {
    this.loginLogoHeight = loginLogoHeight;
  }
  
  public boolean isUploadedHomeLogo() {
    return uploadedHomeLogo;
  }

  public void setUploadedHomeLogo(boolean uploadedHomeLogo) {
    this.uploadedHomeLogo = uploadedHomeLogo;
  }

  public boolean isUploadedLoginLogo() {
    return uploadedLoginLogo;
  }

  public void setUploadedLoginLogo(boolean uploadedLoginLogo) {
    this.uploadedLoginLogo = uploadedLoginLogo;
  }
}