package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portalkit.dto.DashboardWidget;
import ch.ivy.addon.portalkit.dto.WidgetSample;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.loader.ResourceLoader;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.application.property.ICustomProperty;
import ch.ivyteam.ivy.environment.Ivy;

@ViewScoped
@ManagedBean
public class DashboardBean implements Serializable {

  private static final long serialVersionUID = -4224901891867040688L;
  private List<DashboardWidget> widgets;
  private List<WidgetSample> samples;
  private String user;
  private ObjectMapper mapper;

  @PostConstruct
  public void init() {
    widgets = new ArrayList<>();
    mapper = new ObjectMapper();
    samples = List.of(taskSample(), caseSample(), statisticSample(), processSample());
    user = Ivy.session().getSessionUserName();
    List<ICustomProperty> properties = Ivy.wf().getApplication().customProperties().findAllStartingWith("dashboard.widgets." + user);
    try {
      widgets = defaultWidgets();
      /*
       * if (CollectionUtils.isNotEmpty(properties)) { for (ICustomProperty property :
       * properties) { widgets.add(mapper.readValue(property.getValue(),
       * DashboardWidget.class)); } } else { widgets = defaultWidgets(); }
       */
    } catch (IOException e) {
    }
  }
  
  private List<DashboardWidget> defaultWidgets() throws IOException {
    ILibrary portalStyleLib = Ivy.wf().getApplication().findReleasedLibrary(PortalLibrary.PORTAL_STYLE.getValue());
    ResourceLoader loader = new ResourceLoader(portalStyleLib.getProcessModelVersion());
    Optional<Path> path = loader.getWidgetConfiguration();
    String read = String.join("\n", Files.readAllLines(path.get()));
    return Arrays.asList(mapper.readValue(read, DashboardWidget[].class));
  }

  public List<DashboardWidget> getWidgets() {
    return widgets;
  }
  
  public List<WidgetSample> getSamples() {
    return samples;
  }
  
  public void save() throws JsonParseException, JsonMappingException, IOException {
    Map<String, String> requestParamMap = getRequestParameterMap();
    String nodes = Optional.ofNullable(requestParamMap.get("nodes")).orElse(StringUtils.EMPTY);
    List<DashboardWidget> widgets = Arrays.asList(mapper.readValue(nodes, DashboardWidget[].class));
    for (DashboardWidget widget : widgets) {
      DashboardWidget updatedWidget = this.widgets.get(this.widgets.indexOf(widget));
      updatedWidget.setX(widget.getX());
      updatedWidget.setY(widget.getY());
      updatedWidget.setWidth(widget.getWidth());
      updatedWidget.setHeight(widget.getHeight());
      Ivy.wf().getApplication().customProperties().property("dashboard.widgets." + user + "." + widget.getId()).setValue(mapper.writeValueAsString(updatedWidget));
    }
  }
  
  public void saveWidget(DashboardWidget widget) throws JsonProcessingException {
    Ivy.wf().getApplication().customProperties().property("dashboard.widgets." + user + "." + widget.getId()).setValue(mapper.writeValueAsString(widget));
  }
  
  public void restore() throws IOException {
    List<ICustomProperty> properties = Ivy.wf().getApplication().customProperties().findAllStartingWith("dashboard.widgets." + user);
    for (ICustomProperty property : properties) {
      Ivy.wf().getApplication().customProperties().delete(property.getName());
    }
    widgets = defaultWidgets();
  }
  
  private Map<String, String> getRequestParameterMap() {
    Map<String, String> requestParamMap =
        FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    return requestParamMap;
  }
  
  private WidgetSample taskSample() {
    return new WidgetSample("Task List", DashboardWidgetType.TASK, "task-widget-prototype.png");
  }
  
  private WidgetSample caseSample() {
    return new WidgetSample("Case List", DashboardWidgetType.CASE, "case-widget-prototype.png");
  }
  
  private WidgetSample statisticSample() {
    return new WidgetSample("Statistic Widget", DashboardWidgetType.STATISTIC, "statistic-widget-prototype.png");
  }
  
  private WidgetSample processSample() {
    return new WidgetSample("Process List", DashboardWidgetType.PROCESS, "process-widget-prototype.png");
  }
}
