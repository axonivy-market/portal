package ch.ivy.addon.portalkit.util;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.environment.Ivy;


/**
 * @author lptchi
 *
 */
@ManagedBean (name = "urlHelper")
@ApplicationScoped
public class UrlHelper implements Serializable {
  private static final long serialVersionUID = 1L;

	public static String findCreateExpressWorkflowStartLink() throws Exception {
		ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
		return collector.findCreateExpressWorkflowStartLink();
	}
}
