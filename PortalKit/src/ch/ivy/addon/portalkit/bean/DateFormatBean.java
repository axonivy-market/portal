package ch.ivy.addon.portalkit.bean;

import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ApplicationScoped
public class DateFormatBean {

  public String format(Date date) {
    if (Objects.isNull(date)) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
    }
    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Ivy.session().getContentLocale());
    return dateFormat.format(date);
  }
}
