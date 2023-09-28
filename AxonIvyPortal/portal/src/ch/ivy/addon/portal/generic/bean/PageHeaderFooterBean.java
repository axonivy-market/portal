package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.axonivy.portal.components.service.IvyAdapterService;
import com.axonivy.portal.enums.PortalCustomSignature;

@ManagedBean
@RequestScoped
public class PageHeaderFooterBean implements Serializable {

  private static final long serialVersionUID = -5824689842763372385L;

  public String getHeader() {
    Map<String, Object> result = IvyAdapterService
        .startSubProcessInSecurityContext(PortalCustomSignature.GET_PAGE_HEADER_AND_FOOTER.getSignature(), null);
    return result != null ? (String) result.get("pageHeader") : null;
  }

  public String getFooter() {
    Map<String, Object> result = IvyAdapterService
        .startSubProcessInSecurityContext(PortalCustomSignature.GET_PAGE_HEADER_AND_FOOTER.getSignature(), null);
    return result != null ? (String) result.get("pageFooter") : null;
  }

}
