package ch.ivy.addon.portalkit.taskfilter.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskCompletedDateFilter extends TaskFilter {
	private Date fromCompletedDate;
	private Date toCompletedDate;

	@Override
	public String label() {
		StringBuilder sb = new StringBuilder();
		sb.append(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/completedOn")).append(" (")
				.append(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/filter/from")).append("/")
				.append(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/filter/to")).append(")");
		return sb.toString();
	}

	@Override
	public String value() {
		DateFormat formatter = new SimpleDateFormat(DateTimeGlobalSettingService.getInstance().getDateTimePattern(), Ivy.session().getContentLocale());
		if (fromCompletedDate != null && toCompletedDate != null) {
			return String.format(DASH, formatter.format(fromCompletedDate), formatter.format(toCompletedDate));
		}

		if (fromCompletedDate != null) {
			return String.format(GREATER_EQUAL, formatter.format(fromCompletedDate));
		}

		if (toCompletedDate != null) {
			return String.format(LESS_EQUAL, formatter.format(toCompletedDate));
		}
		return ALL;
	}

	@Override
	public TaskQuery buildQuery() {
		if (fromCompletedDate == null && toCompletedDate == null) {
			return null;
		}

		TaskQuery query = TaskQuery.create();
		if (fromCompletedDate != null) {
			query.where().endTimestamp().isGreaterOrEqualThan(fromCompletedDate);
		}

		if (toCompletedDate != null) {
			query.where().endTimestamp().isLowerOrEqualThan(toCompletedDate);
		}
		return query;
	}

	@Override
	public void resetValues() {
		fromCompletedDate = null;
		toCompletedDate = null;
	}

	@Override
	public void validate() {
		if (fromCompletedDate != null && toCompletedDate != null
				&& (fromCompletedDate.compareTo(toCompletedDate) > 0)) {
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage("advanced-filter-error-messages",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/dateFromBiggerThanTo"), null));
		}
	}

	public Date getFromCompletedDate() {
		return fromCompletedDate;
	}

	public Date getToCompletedDate() {
		return toCompletedDate;
	}

	public void setToCompletedDate(Date toCompletedDate) {
		this.toCompletedDate = toCompletedDate;
	}

	public void setFromCompletedDate(Date fromCompletedDate) {
		this.fromCompletedDate = fromCompletedDate;
	}


}
