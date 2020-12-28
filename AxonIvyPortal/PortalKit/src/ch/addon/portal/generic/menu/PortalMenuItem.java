package ch.addon.portal.generic.menu;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.menu.DefaultMenuItem;

import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivyteam.ivy.environment.Ivy;

public class PortalMenuItem extends DefaultMenuItem {

  private static final long serialVersionUID = 5232603726461037750L;

  /* Menu Parameters */
  public final static String MENU_KIND = "menuKind";
  public final static String MENU_URL = "menuUrl";
  public final static String TASK_ID = "taskId";
  public final static String IS_WORKING_ON_TASK = "isWorkingOnATask";
  /* Menu id format */
  public final static String MENU_ID_FORMAT = "menu-item-%s";
  public final static String SUB_MENU_ID_FORMAT = "sub-menu-item-%s";
  public final static String THIRD_PARTY_MENU_ID_FORMAT = "thirdparty-menu-item-%s";
  public final static String EXTERNAL_MENU_ID_FORMAT = "external-menu-item-%s";
  public final static String MENU_CLASS_FORMAT = "%s %s";

  public final static String ICON_POSITION = "right";
  public final static String MENU_CLASS_SUFFIX = "-menu-js";
  public final static String DEFAULT_MENU_PROCESS = "@this";
  public final static String DEFAULT_EXTERNAL_MENU_TARGET = "_blank";

  public static final String DEFAULT_MENU_COMMAND_METHOD = "#{menuView.onClickMenuItem}";
  public static final String DEFAULT_EXTERNAL_ON_CLICK_METHOD = "MainMenu.removeActiveOnExternalMenu()";

  public final static String BREADCRUMB_DESTINATION = "destination";
  public final static String DASHBOARD_PARAM = "isShowDashboard";
  public final static String DEFAULT_DASHBOARD_ICON = "si si-layout-dashboard";
  public final static String DASHBOARD = "/ch.ivy.addon.portalkit.ui.jsf/common/dashboard";

  private MenuKind menuKind;

  public PortalMenuItem(PortalMenuBuilder builder) {
    this.setValue(builder.name);
    this.setIcon(builder.icon);
    this.setIconPos(ICON_POSITION);

    this.setId(builder.id == null ? generateMenuId(builder.menuKind) : builder.id);
    this.setStyleClass(String.format(MENU_CLASS_FORMAT, builder.styleClass, builder.menuKind.name()));
    this.setContainerStyleClass(String.format(MENU_CLASS_FORMAT, builder.containerStyleClass, this.getId()));
    this.setUpdate(builder.update == null ? DEFAULT_MENU_PROCESS : builder.update);
    this.setProcess(builder.process == null ? DEFAULT_MENU_PROCESS : builder.process);
    this.setCommand(builder.commandMethod == null ? DEFAULT_MENU_COMMAND_METHOD : builder.commandMethod);

    this.setPartialSubmit(true);
    this.setImmediate(true);
    this.setGlobal(true);
    this.setDisabled(builder.disabled);
    this.setUrl(builder.url);

    if (builder.onClick == null && (builder.menuKind == MenuKind.EXTERNAL_LINK || builder.menuKind == MenuKind.THIRD_PARTY)) {
      this.setOnclick(DEFAULT_EXTERNAL_ON_CLICK_METHOD);
    }
    else {
      this.setOnclick(builder.onClick);
    }

    if (builder.target == null
        && (builder.menuKind == MenuKind.EXTERNAL_LINK || builder.menuKind == MenuKind.THIRD_PARTY)) {
      this.setTarget(DEFAULT_EXTERNAL_MENU_TARGET);
    } else {
      this.setTarget(builder.target);
    }

    generateMenuParams(builder);
  }

  public MenuKind getMenuKind() {
    return menuKind;
  }

  private void generateMenuParams(PortalMenuBuilder builder) {
    long workingTaskId = builder.workingTaskId == null ? Ivy.wfTask().getId() : builder.workingTaskId;
    this.setParam(TASK_ID, workingTaskId);
    this.setParam(IS_WORKING_ON_TASK, builder.isWorkingOnATask);
    this.setParam(MENU_KIND, builder.menuKind);
    this.setParam(MENU_URL, StringUtils.defaultIfEmpty(builder.url, EMPTY));

    Map<String, Object> customParams = builder.params;
    if (customParams == null) {
      return;
    }

    for (String key : customParams.keySet()) {
      this.setParam(key, customParams.get(key));
    }
  }

  private String generateMenuId(MenuKind menuKind) {
    String menuFormat = "%s";
    switch (menuKind) {
      case DASHBOARD:
        menuFormat = MENU_ID_FORMAT;
        break;
      case PROCESS:
      case TASK:
      case CASE:
      case STATISTICS:
      case CUSTOM:
        menuFormat = SUB_MENU_ID_FORMAT;
        break;
      case EXTERNAL_LINK:
        menuFormat = EXTERNAL_MENU_ID_FORMAT;
        break;
      case THIRD_PARTY:
        menuFormat = THIRD_PARTY_MENU_ID_FORMAT;
        break;
      default:
        break;
    }
    return String.format(menuFormat, menuKind);
  }

  public static class PortalMenuBuilder {
    private String id;
    private String name;
    private MenuKind menuKind;
    private String icon;
    private String styleClass;
    private String containerStyleClass;
    private String url;
    private String update;
    private String process;
    private String commandMethod;
    private Map<String, Object> params;
    private String onClick;
    private String target;
    private Long workingTaskId;
    private boolean isWorkingOnATask;
    private boolean disabled;

    public PortalMenuBuilder(String name, MenuKind menuKind, boolean isWorkingOnATask) {
      this.name = name;
      this.menuKind = menuKind;
      this.isWorkingOnATask = isWorkingOnATask;
    }

    public PortalMenuBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }

    public PortalMenuBuilder styleClass(String styleClass) {
      this.styleClass = styleClass;
      return this;
    }

    public PortalMenuBuilder containerStyleClass(String containerStyleClass) {
      this.containerStyleClass = containerStyleClass;
      return this;
    }

    public PortalMenuBuilder url(String url) {
      this.url = url;
      return this;
    }

    public PortalMenuBuilder update(String update) {
      this.update = update;
      return this;
    }

    public PortalMenuBuilder process(String process) {
      this.process = process;
      return this;
    }

    public PortalMenuBuilder commandMethod(String commandMethod) {
      this.commandMethod = commandMethod;
      return this;
    }

    public PortalMenuBuilder params(Map<String, Object> params) {
      this.params = params;
      return this;
    }

    public PortalMenuBuilder onClick(String onClick) {
      this.onClick = onClick;
      return this;
    }

    public PortalMenuBuilder target(String target) {
      this.target = target;
      return this;
    }

    public PortalMenuBuilder workingTaskId(Long workingTaskId) {
      this.workingTaskId = workingTaskId;
      return this;
    }
    
    public PortalMenuBuilder disabled(boolean disabled) {
      this.disabled = disabled;
      return this;
    }

    public PortalMenuItem build() {
      return new PortalMenuItem(this);
    }
  }
}

