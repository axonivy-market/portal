package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ApplicationScoped
public class FormattingNullWrapperBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final String DEFAULT_VALUE_IF_NULL = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
  
  public String format(Date date) {
    if (date == null) {
      return DEFAULT_VALUE_IF_NULL;
    }
    DateTimeGlobalSettingService dateTimePatternService = new DateTimeGlobalSettingService();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateTimePatternService.getGlobalSettingPattern());
    return simpleDateFormat.format(date);
  }
}
