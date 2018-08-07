package ch.ivy.addon.portalkit.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivy.ws.addon.IvyTask;
import ch.ivyteam.ivy.environment.Ivy;

public class TaskInforActionService {

	public String prepareChangeDeadlineNoteContent(String fullNameOfUser, String userNameOfUser, Date datetime, Long taskId) {
		String formattedDate = formatDate(datetime);
		List<Object> parameters = Arrays.asList(fullNameOfUser, userNameOfUser, taskId.toString(), formattedDate);
		return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/setDeadlineNote", parameters);
	}
	
	public String prepareChaneNameNoteContent(String changeBy, String newName, String oldName) {
		List<Object> parameters = Arrays.asList(changeBy, oldName, newName);
		String co = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/setNameNote", parameters);
		return co;
	}

	private String formatDate(Date datetime) {
		String dateTimePattern =
				Ivy.cms().findContentObjectValue("/patterns/dateTimePattern", Locale.ENGLISH)
						.getContentAsString();
		DateFormat formatter = new SimpleDateFormat(dateTimePattern);
		return formatter.format(datetime);
	}

	public IvyTask prepareDataToSaveTask(RemoteTask task) {
		IvyTask ivyTask = new IvyTask();
		ivyTask.setId(task.getId());
		ivyTask.setPriority(task.getPriority().name());
		Calendar cal = Calendar.getInstance();
		if (task.getExpiryTimestamp() != null) {
			cal.setTime(task.getExpiryTimestamp());
			ivyTask.setExpireTimestamp(cal);
		} else {
			ivyTask.setExpireTimestamp(null);
		}
		ivyTask.setDescription(emptyIfNull(task.getDescription()));
		ivyTask.setName(emptyIfNull(task.getName()));
		return ivyTask;
	}

	private static String emptyIfNull(String string) {
		return Objects.toString(string, "");
	}
}
