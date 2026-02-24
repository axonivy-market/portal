package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.axonivy.portal.components.service.DateTimeGlobalSettingService;

import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ApplicationScoped
public class FormattingNullWrapperBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final String DEFAULT_VALUE_IF_NULL = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");

  private String globalDateTimePattern;
  
  @PostConstruct
  public void init() {
    globalDateTimePattern = DateTimeGlobalSettingService.getInstance().getGlobalDateTimePattern();
  }
  
  public String format(Date date) {
    if (date == null) {
      return DEFAULT_VALUE_IF_NULL;
    }
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(globalDateTimePattern, Ivy.session().getFormattingLocale());
    return simpleDateFormat.format(date);
  }
}
