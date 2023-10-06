package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.axonivy.portal.components.service.IvyAdapterService;
import com.axonivy.portal.enums.PortalCustomSignature;

import ch.ivy.addon.portalkit.support.HtmlParser;

@ManagedBean
@RequestScoped
public class PageHeaderFooterBean implements Serializable {

  private static final long serialVersionUID = -5824689842763372385L;

  private String header;
  private String footer;

  @PostConstruct
  public void init() {
    Map<String, Object> mapHeaderFooter = IvyAdapterService
        .startSubProcessInSecurityContext(PortalCustomSignature.GET_PAGE_HEADER_AND_FOOTER.getSignature(), null);
    this.header = mapHeaderFooter != null ? HtmlParser.sanitize((String) mapHeaderFooter.get("pageHeader")) : null;
    this.footer = mapHeaderFooter != null ? HtmlParser.sanitize((String) mapHeaderFooter.get("pageFooter")) : null;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public void setFooter(String footer) {
    this.footer = footer;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

}
