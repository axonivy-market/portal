package ch.ivy.addon.portalkit.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ch.ivyteam.ivy.environment.Ivy;

public class TaskInforActionService {

  public String prepareChangeDeadlineNoteContent(String fullNameOfUser, String userNameOfUser, Date datetime,
      Long taskId) {
    String formattedDate = formatDate(datetime);
    List<Object> parameters = Arrays.asList(fullNameOfUser, userNameOfUser, taskId.toString(), formattedDate);
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/setDeadlineNote", parameters);
  }
  
  public String prepareChangeDelayNoteContent(String fullNameOfUser, String userNameOfUser, Date datetime,
      Long taskId) {
    String formattedDate = formatDate(datetime);
    List<Object> parameters = Arrays.asList(fullNameOfUser, userNameOfUser, taskId.toString(), formattedDate);
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskDetails/setDelayTimestamp", parameters);
  }

  public String prepareChaneNameNoteContent(String changeBy, String newName, String oldName) {
    List<Object> parameters = Arrays.asList(changeBy, oldName, newName);
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/setNameNote", parameters);
  }

  private String formatDate(Date datetime) {
    String dateTimePattern =
        Ivy.cms().findContentObjectValue("/patterns/dateTimePattern", Locale.ENGLISH).getContentAsString();
    DateFormat formatter = new SimpleDateFormat(dateTimePattern);
    return formatter.format(datetime);
  }
}
