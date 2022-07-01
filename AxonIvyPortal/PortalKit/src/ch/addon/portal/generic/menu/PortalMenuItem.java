package ch.addon.portal.generic.menu;

import static java.util.Objects.isNull;
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
  public final static String WORKING_ON_TASK = "isWorkingOnATask";
  /* Menu id format */
  public final static String MENU_ID_FORMAT = "menu-item-%s";
  public final static String SUB_MENU_ID_FORMAT = "sub-menu-item-%s";
  public final static String THIRD_PARTY_MENU_ID_FORMAT = "thirdparty-menu-item-%s";
  public final static String EXTERNAL_MENU_ID_FORMAT = "external-menu-item-%s";
  public final static String MENU_CLASS_FORMAT = "%s %s";
  public final static String MENU_CLASS_SUFFIX = "-menu-js";
  
  public final static String DEFAULT_ICON_POSITION = "right";
  public final static String DEFAULT_MENU_PROCESS = "@this";
  public final static String DEFAULT_EXTERNAL_MENU_TARGET = "_blank";

  public static final String DEFAULT_MENU_COMMAND_METHOD = "#{menuView.onClickMenuItem}";
  public static final String DEFAULT_EXTERNAL_ON_CLICK_METHOD = "MainMenu.removeActiveOnExternalMenu()";
  public static final String DEFAULT_ON_CLICK_PATTERN = "onClickMenuItem(this, %s, %s);";
  public static final String MENU_ITEM_ID_FORMAT = "%s_%s";

  public final static String BREADCRUMB_DESTINATION = "destination";
  public final static String DEFAULT_DASHBOARD_ICON = "si si-layout-dashboard";

  private MenuKind menuKind;

  public PortalMenuItem() { }

  public PortalMenuItem(PortalMenuBuilder builder) {
    this.setId(String.format(MENU_ITEM_ID_FORMAT, builder.menuKind.toString(), builder.menuIndex));
    this.setValue(builder.name);
    this.setIcon(builder.icon);
    this.setIconPos(DEFAULT_ICON_POSITION);
    String containerClassMenuId = isNull(builder.id) ? generateMenuId(builder.menuKind) : builder.id;
    this.setStyleClass(String.format(MENU_CLASS_FORMAT, StringUtils.defaultIfEmpty(builder.styleClass, EMPTY), builder.menuKind.name()));
    this.setContainerStyleClass(String.format(MENU_CLASS_FORMAT, StringUtils.defaultIfEmpty(builder.containerStyleClass, EMPTY), containerClassMenuId));
    this.setUpdate(isNull(builder.update) ? DEFAULT_MENU_PROCESS : builder.update);
    this.setProcess(isNull(builder.process) ? DEFAULT_MENU_PROCESS : builder.process);
    this.setCommand(isNull(builder.commandMethod) ? DEFAULT_MENU_COMMAND_METHOD : builder.commandMethod);

    this.setPartialSubmit(true);
    this.setImmediate(true);
    this.setGlobal(true);
    this.setDisabled(builder.disabled);
    this.setUrl(builder.url);

    var isOpenOnNewTab = builder.menuKind == MenuKind.EXTERNAL_LINK || builder.menuKind == MenuKind.THIRD_PARTY;
    var onClick = String.format(DEFAULT_ON_CLICK_PATTERN, builder.isWorkingOnATask, isOpenOnNewTab);
    this.setOnclick(onClick.concat(isNull(builder.onClick) ? "" : builder.onClick));

    this.setTarget(builder.target);
    if (isNull(builder.target) && (builder.menuKind == MenuKind.EXTERNAL_LINK || builder.menuKind == MenuKind.THIRD_PARTY)) {
      this.setTarget(DEFAULT_EXTERNAL_MENU_TARGET);
    }

    if (builder.isCleanParams) {
      this.setParams(null);
    } else {
      generateMenuParams(builder);
      this.setUrl(null);
    }
  }

  public MenuKind getMenuKind() {
    return menuKind;
  }

  private void generateMenuParams(PortalMenuBuilder builder) {
    long workingTaskId = isNull(builder.workingTaskId) ? Ivy.wfTask().getId() : builder.workingTaskId;
    this.setParam(TASK_ID, workingTaskId);
    this.setParam(WORKING_ON_TASK, builder.isWorkingOnATask);
    this.setParam(MENU_KIND, builder.menuKind);
    this.setParam(MENU_URL, StringUtils.defaultIfEmpty(builder.url, EMPTY));

    Map<String, Object> customParams = builder.params;
    if (isNull(customParams)) {
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
    private boolean isCleanParams;
    private int menuIndex;

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

    public PortalMenuBuilder cleanParam(boolean isCleanParams) {
      this.isCleanParams = isCleanParams;
      return this;
    }

    public PortalMenuBuilder menuIndex(int menuIndex) {
      this.menuIndex = menuIndex;
      return this;
    }

    public PortalMenuItem build() {
      return new PortalMenuItem(this);
    }
  }
}
