package ch.ivy.addon.portalkit.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ch.ivyteam.ivy.environment.Ivy;

public class TaskInforActionService {

  public String prepareChangeDeadlineNoteContent(String changeBy, Date datetime) {
    String formattedDate = formatDate(datetime);
    List<Object> parameters = Arrays.asList(changeBy, formattedDate);
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/setDeadlineNote", parameters);
  }

  private String formatDate(Date datetime) {
    String dateTimePattern = Ivy.cms()
        .findContentObjectValue("/ch.ivy.addon.portalkit.ui.jsf/common/dateTimePattern", Locale.ENGLISH)
        .getContentAsString();
    DateFormat formatter = new SimpleDateFormat(dateTimePattern);
    return formatter.format(datetime);
  }

}
