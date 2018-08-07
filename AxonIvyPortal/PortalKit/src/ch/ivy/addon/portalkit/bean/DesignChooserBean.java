package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
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
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.primefaces.event.FileUploadEvent;

import ch.ivy.addon.portalkit.loader.ResourceLoader;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class DesignChooserBean {

  private final static String PORTALSTYLE_LIBRARY = "ch.ivyteam.ivy.project.portal:portalStyle";
  private static final String COLORS_LESS_PATH = "/resources/less/colors.less";
  private static final String CUSTOMIZATION_LESS_PATH = "/resources/less/customization.less";
  private static final String HOME_LOGO_CMS = "/images/logo/CorporateLogo";
  private static final String LOGIN_LOGO_CMS = "/images/logo/loginLogo";
  private static final String MAIN_COLOR_ATTRIBUTE = "@menu-color: ";
  private static final String BACKGROUND_COLOR_ATTRIBUTE = "@body-background-color: ";
  private static final String ANY_CHARACTERS_REGEX = "([^']*)";
  private static final String SEMICOLON = ";";
  private static final String HASH_SIGN = "#";
  private static final String MAIN_COLOR_PATTERN = MAIN_COLOR_ATTRIBUTE + ANY_CHARACTERS_REGEX;
  private static final String BACKGROUND_COLOR_PATTERN = BACKGROUND_COLOR_ATTRIBUTE + ANY_CHARACTERS_REGEX;

  private String mainColor;
  private String backgroundColor;
  private ResourceLoader loader;

  @PostConstruct
  public void init() {
    try {
      initWebContentLoader();
      mainColor = retrieveMainColorFromFile();
      backgroundColor = retrieveBackgroundColorFromFile();
    } catch (IOException e) {
      Ivy.log().error("Can't retrieve colors from less file", e);
    }
  }

  public void uploadLoginLogo(FileUploadEvent event) throws IOException {
    uploadLogo(event, LOGIN_LOGO_CMS);
  }

  public void uploadHomeLogo(FileUploadEvent event) throws IOException {
    uploadLogo(event, HOME_LOGO_CMS);
  }

  public void applyNewColors() throws IOException, MavenInvocationException {
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
      compileThemeLess();
    }
  }
  
  private void compileThemeLess() throws MavenInvocationException {
    Optional<Path> portalStylePom = loader.getPom();
    if (portalStylePom.isPresent()) {
      InvocationRequest request = new DefaultInvocationRequest();
      request.setPomFile(portalStylePom.get().toFile());
      request.setGoals(Collections.singletonList("lesscss:compile"));
      
      Invoker invoker = new DefaultInvoker();
      invoker.execute(request);
    }
  }

  private void uploadLogo(FileUploadEvent event, String cms) throws IOException {
    InputStream inputStream = event.getFile().getInputstream();
    Ivy.cms().findContentObjectValue(cms, Locale.ENGLISH).setContent(inputStream, 0, null);
  }

  private String retrieveMainColorFromFile() throws IOException {
    return retrieveColorValueFromLessFile(MAIN_COLOR_PATTERN);
  }

  private String retrieveBackgroundColorFromFile() throws IOException {
    return retrieveColorValueFromLessFile(BACKGROUND_COLOR_PATTERN);
  }

  private String retrieveColorValueFromLessFile(String pattern) throws IOException {
    String color = retrieveColorValueFromLessFile(pattern, CUSTOMIZATION_LESS_PATH);
    if (StringUtils.isBlank(color)) {
      color = retrieveColorValueFromLessFile(pattern, COLORS_LESS_PATH);
    }
    return color;
  }

  private String retrieveColorValueFromLessFile(String pattern, String resource) throws IOException {
    String color = StringUtils.EMPTY;
    Optional<Path> path = loader.findResource(resource);
    if (path.isPresent()) {
      try (Stream<String> lines = Files.lines(path.get())) {
        Pattern p = Pattern.compile(pattern);
        Optional<Matcher> matcher = lines.map(p::matcher).filter(Matcher::matches).findFirst();
        if (matcher.isPresent()) {
          String colorLine = matcher.get().group(1);
          color = colorLine.substring(colorLine.indexOf(": ") + 2, colorLine.length() - 1);
        }
      }
    }
    return color;
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
}
