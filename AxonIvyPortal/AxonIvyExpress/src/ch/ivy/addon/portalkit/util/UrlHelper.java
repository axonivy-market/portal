package ch.ivy.addon.portalkit.util;

import java.util.concurrent.Callable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.sun.tools.classfile.StackMapTable_attribute.append_frame;

import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.request.IHttpRequest;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.security.SecurityManagerFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


/**
 * @author lptchi
 *
 */
@ManagedBean (name = "urlHelper")
@ApplicationScoped
public class UrlHelper {
	//"http://localhost:8081/ivy/pro/designer/GenericApplicationPortal/14DE256AA74C60D7/documentFileUpload.ivp";
	private static String urlDocumentPage = "";	
	/**
	 * @return String url of document file upload page
	 */
	public static String getUrlDocumentPage() {
		try {
			return  ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<String>() {
							public String call() throws Exception {
					if (urlDocumentPage != ""){
						return urlDocumentPage;
					}
					
					if (Ivy.session().getStartableProcessStarts() != null && Ivy.session().getStartableProcessStarts().size() > 0){
						for (IProcessStart item : Ivy.session().getStartableProcessStarts()){
							if (item.getFullRequestPath().contains("14DE256AA74C60D7/documentFileUpload.ivp")){
								urlDocumentPage = item.getFullRequestPath();
								break;
							}
						}
					}
					return urlDocumentPage;
				}
			  });
			  
		} catch (Exception e) {
			Ivy.log().error(e);
		}
		return null;
	}
	
	/**
	 * Get full request path of requestID.
	 * 
	 * @param requestID 
	 * @return full request URL.
	 */
	public static String getUrlByRequestID(final String requestID) {
		try {
			  return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<String>() {
				public String call() throws Exception {
					if (Ivy.session().getStartableProcessStarts() != null && Ivy.session().getStartableProcessStarts().size() > 0){
						for (IProcessStart item : Ivy.session().getStartableProcessStarts()){
							if (item.getFullRequestPath().contains(requestID)){
								FacesContext facesContext = FacesContext.getCurrentInstance(); 
								if (facesContext != null) {
									ExternalContext externalContext = facesContext.getExternalContext();
									HttpServletRequest req = (HttpServletRequest) externalContext.getRequest();
									String requestContextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
									String url = req.getRequestURL().toString();
									String host = url.substring(0, url.length() - req.getRequestURI().length());
									return host + requestContextPath + "/pro/" + item.getFullRequestPath();
								}
								break;
							}
						}
					}
					return "";
				}
			});
						  
		} catch (Exception e) {
			Ivy.log().error(e);
		}
		return null;
	}
	
	private static final Map<String, String> links = new LinkedHashMap<>();

	
	/**
	 * Returns full link using process start signature, e.g. myProcessStart()
	 * if there are process starts with same signature, it returns the first one
	 * Based on: http://answers.axonivy.com/questions/1061/get-link-to-process-of-another-project
	 * @param signature
	 * @param params - url parameters
	 * @return string with full link
	 */
	public static String getFullLinkBySignature(String signature, String... params) {
		String tmpLink;
		tmpLink = links.get(signature);
		String result;
		if (tmpLink != null && !tmpLink.isEmpty()){
			result = tmpLink;
		}
		else {
			try {
				result = SecurityManagerFactory.getSecurityManager().executeAsSystem(() -> {
					String link = null;
					if (Ivy.request() instanceof IHttpRequest){
						Set<IProcessStart> processStarts = Ivy.wf().findProcessStartsBySignature(signature);
						if(processStarts!=null&&!processStarts.isEmpty()){
							if(processStarts.size() > 1) {
								Ivy.log().error("Multiple ({1}) Process Starts with signature {0} found : {2}",signature,processStarts.size(),processStarts);
							}
							IProcessStart processStart = processStarts.iterator().next();
							String fullRequestPath = processStart.getFullRequestPath();
							//if(((IHttpRequest) Ivy.request()).getHttpServletRequest().getContextPath().isEmpty()){
							//	Ivy.var().get("ivy.var.de_eon_working_context_url");
							//}else{
								link = ((IHttpRequest) Ivy.request()).getHttpServletRequest().getContextPath() + "/pro/"+fullRequestPath;								
							//}
							
							
							links.put(signature, link);
						} else {
							Ivy.log().error("Process Start with signature {0} not found ",signature);
						}
					}
					return link;
				});
			} catch (Exception e) {
				Ivy.log().error("Error while getFullLinkBySignature", e);
				return null;
			}
		}
		if(params!=null &&params.length>0 &&result!=null){
			String paramsStr = String.join("&", params);
			result = result + "?"+ paramsStr;
		}
		return result;
	}
	
	public static String findCreateExpressWorkflowStartLink() throws Exception {
		ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
		return collector.findCreateExpressWorkflowStartLink();
	}
}
