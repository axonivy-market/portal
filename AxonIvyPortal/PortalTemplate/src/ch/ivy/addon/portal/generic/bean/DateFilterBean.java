package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.faces.bean.ManagedBean;

import org.apache.commons.lang3.time.DateUtils;

@ManagedBean(name="dateFilterBean")
public class DateFilterBean implements Serializable {
  private static final long serialVersionUID = 1L;

    public boolean filterByDate(Object value, Object filter, @SuppressWarnings("unused") Locale locale) {
        if (filter == null) {
            return true;
        }
        
        if (value == null) {
            return false;
        }
        
        return DateUtils.truncatedEquals((Date) filter, (Date) value, Calendar.DATE);
    }

}
