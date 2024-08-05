package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.util.FacesMessageUtils;

import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.SortDirection;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.ivydata.dto.IvyNotificationChannelDTO;
import ch.ivy.addon.portalkit.ivydata.dto.IvyNotificationChannelSubcriptionDTO;
import ch.ivy.addon.portalkit.ivydata.dto.IvyNotificationEventDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.UserSettingService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.notification.channel.NotificationSubscription;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;

@ManagedBean
@ViewScoped
public class UserProfileBean implements Serializable {
  private static final String DEFAULT_OPTION = "/ch.ivy.addon.portalkit.ui.jsf/MyProfile/defaultOption";
  private static final long serialVersionUID = 4952280551311826903L;
  private static final String DEFAULT = UserSettingService.DEFAULT;

  public UserProfileBean() {}

  private ISecurityMember subscriber;
  private ISecurityContext securityContext;

  private List<IvyNotificationEventDTO> events;
  private List<IvyNotificationChannelDTO> channels;

  public void init() {
    subscriber = Ivy.session().getSessionUser();
    securityContext = ISecurityContext.current();
    onloadChannel();
  }

  public void saveHomepage(String homepageName) {
    IUser user = Ivy.session().getSessionUser();
    if (StringUtils.isBlank(homepageName)) {
      user.removeProperty(UserProperty.HOMEPAGE);
    }
    user.setProperty(UserProperty.HOMEPAGE, homepageName);
  }

  public String getDisplayNameOfTaskSortField(String sortField) {
    List<String> sortFieldNames = Stream.of(TaskSortField.values()).map(Enum::name).collect(Collectors.toList());

    if (StringUtils.equals(sortField, DEFAULT)) {
      GlobalSettingService globalSettingService = GlobalSettingService.getInstance();
      String defaultSortField = globalSettingService
          .findGlobalSettingByGlobalVariable(GlobalVariable.DEFAULT_SORT_FIELD_OF_TASK_LIST).getValue();

      String displaySortField =
          sortFieldNames.contains(defaultSortField) ? TaskSortField.valueOf(defaultSortField).getLabel()
              : defaultSortField;
      return getDefaultSelection(displaySortField);
    }

    return sortFieldNames.contains(sortField) ? TaskSortField.valueOf(sortField).getLabel() : sortField;
  }

  public String getDisplayNameOfCaseSortField(String sortField) {
    List<String> sortFieldNames = Stream.of(CaseSortField.values()).map(Enum::name).collect(Collectors.toList());
    if (StringUtils.equals(sortField, DEFAULT)) {
      GlobalSettingService globalSettingService = GlobalSettingService.getInstance();
      String defaultSortField = globalSettingService
          .findGlobalSettingByGlobalVariable(GlobalVariable.DEFAULT_SORT_FIELD_OF_CASE_LIST).getValue();

      String displaySortField =
          sortFieldNames.contains(defaultSortField) ? CaseSortField.valueOf(defaultSortField).getLabel()
              : defaultSortField;
      return getDefaultSelection(displaySortField);
    }

    return sortFieldNames.contains(sortField) ? CaseSortField.valueOf(sortField).getLabel() : sortField;
  }

  public String getDisplayNameOfTaskSortDirection(String sortDirection) {
    List<String> sortDirectionNames = Stream.of(SortDirection.values()).map(Enum::name).collect(Collectors.toList());
    if (StringUtils.equals(sortDirection, DEFAULT)) {
      GlobalSettingService globalSettingService = GlobalSettingService.getInstance();
      String defaultDirection = globalSettingService
          .findGlobalSettingByGlobalVariable(GlobalVariable.DEFAULT_SORT_DIRECTION_OF_TASK_LIST).getValue();

      String displayDirection =
          sortDirectionNames.contains(defaultDirection) ? SortDirection.valueOf(defaultDirection).getLabel() : "";
      return getDefaultSelection(displayDirection);
    }

    return sortDirectionNames.contains(sortDirection) ? SortDirection.valueOf(sortDirection).getLabel() : "";
  }

  public String getDisplayNameOfCaseSortDirection(String sortDirection) {
    List<String> sortDirectionNames = Stream.of(SortDirection.values()).map(Enum::name).collect(Collectors.toList());
    if (StringUtils.equals(sortDirection, DEFAULT)) {
      GlobalSettingService globalSettingService = GlobalSettingService.getInstance();
      String defaultDirection = globalSettingService
          .findGlobalSettingByGlobalVariable(GlobalVariable.DEFAULT_SORT_DIRECTION_OF_CASE_LIST).getValue();

      String displayDirection =
          sortDirectionNames.contains(defaultDirection) ? SortDirection.valueOf(defaultDirection).getLabel() : "";
      return getDefaultSelection(displayDirection);
    }


    return sortDirectionNames.contains(sortDirection) ? SortDirection.valueOf(sortDirection).getLabel() : "";
  }

  private String getDefaultSelection(String sortFieldName) {
    return Ivy.cms().co(DEFAULT_OPTION, Arrays.asList(sortFieldName));
  }

  public void onloadChannel() {
    events = IvyNotificationEventDTO.all();
    channels = IvyNotificationChannelDTO.all(subscriber, securityContext);
  }

  public void resetAllChannel() {
    channels.forEach(this::resetChannel);
    onloadChannel();
    addMessage(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/MyProfile/notificationChannelsReset"));
  }

  private void resetChannel(IvyNotificationChannelDTO channel) {
    channel.getSubscriptions().forEach(
        (event, subscription) -> subscription.setState(IvyNotificationChannelSubcriptionDTO.State.USE_DEFAULT));
    saveChannel(channel);
  }

  public void saveAllChannel() {
    channels.forEach(this::saveChannel);
  }

  private void saveChannel(IvyNotificationChannelDTO channel) {
    channel.getSubscriptions().entrySet().forEach(eventSubscription -> {
      var subscription = NotificationSubscription.of(subscriber, channel.getChannel(), eventSubscription.getKey());
      subscription.state(eventSubscription.getValue().getState().toDbState());
    });
  }

  private void addMessage(String msg) {
    FacesContext.getCurrentInstance().addMessage("notification-Message", FacesMessageUtils.sanitizedMessage(msg));
  }

  public List<IvyNotificationChannelDTO> getChannels() {
    return channels;
  }

  public List<IvyNotificationEventDTO> getEvents() {
    return events;
  }

  public Boolean canAccessProcessList() {
    return PermissionUtils.checkAccessFullProcessListPermission();
  }

  public Boolean canAccessTaskList() {
    return PermissionUtils.checkAccessFullTaskListPermission();
  }

  public Boolean canAccessCaseList() {
    return PermissionUtils.checkAccessFullCaseListPermission();
  }
}
