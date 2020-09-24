package ch.ivy.addon.portalkit.bean;

import java.lang.reflect.Field;

import javax.el.ELContext;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.ClassUtils;

import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.environment.Ivy;

public class IvyComponentLogicCaller<T> {

  @SuppressWarnings("unchecked")
  public T invokeComponentLogic(String componentId, String methodSignature, Object[] paramValues) {
    FacesContext fc = FacesContext.getCurrentInstance();
    UIComponent component = fc.getViewRoot().findComponent(componentId);
    if (component == null) {
      throw new PortalException("ivy component with ID " + componentId + " does not exist!");
    }

    ELContext el = fc.getELContext();
    Application app = fc.getApplication();
    MethodExpression elExpression =
        app.getExpressionFactory().createMethodExpression(el, methodSignature, Object.class,
            ClassUtils.toClass(paramValues));
    setTargetComponent(elExpression, component);

    Ivy.log().debug("invoking: " + elExpression);
    return (T) elExpression.invoke(el, paramValues);
  }

  /**
   * hack to ensure that the el-expression will run on the give ivy component!
   * 
   * @param componentMethod
   * @param component
   */
  private void setTargetComponent(MethodExpression componentMethod, UIComponent component) {
    try {
      Field cc = componentMethod.getClass().getDeclaredField("compositeComponent");
      cc.setAccessible(true);
      cc.set(componentMethod, component);
    } catch (Exception ex) {
      Ivy.log().error(ex);
    }
  }

}
