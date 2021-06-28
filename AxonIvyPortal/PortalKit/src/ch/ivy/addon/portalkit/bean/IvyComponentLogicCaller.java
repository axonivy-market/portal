package ch.ivy.addon.portalkit.bean;

import java.lang.reflect.Field;

import javax.el.ELContext;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.ClassUtils;

import ch.ivyteam.ivy.environment.Ivy;

public class IvyComponentLogicCaller<T> {

  private static boolean isCorePre7024;

  @SuppressWarnings("unchecked")
  public T invokeComponentLogic(String componentId, String methodSignature, Object[] paramValues) {
    FacesContext fc = FacesContext.getCurrentInstance();
    UIComponent component = fc.getViewRoot().findComponent(componentId);
    if (component == null) {
      throw new RuntimeException("ivy component with ID " + componentId + " does not exist!");
    }

    ELContext el = fc.getELContext();
    Application app = fc.getApplication();
    MethodExpression elExpression =
        app.getExpressionFactory().createMethodExpression(el, methodSignature, Object.class,
            ClassUtils.toClass(paramValues));
    setTargetComponent(elExpression, component);

    Ivy.log().info("invoking: " + elExpression);
    return (T) elExpression.invoke(el, paramValues);
  }

  /**
   * hack to ensure that the el-expression will runn on the give ivy component!
   * 
   * @param componentMethod
   * @param component
   */
  private void setTargetComponent(MethodExpression componentMethod, UIComponent component) {
    try {
      if (isCorePre7024) {
        setTargetComponentPreCore7024(componentMethod, component);
        return;
      }
      try {
        setTargetComponentPostCore7024(componentMethod, component);
      }
      catch(NoSuchFieldException ex) {
        setTargetComponentPreCore7024(componentMethod, component);
        isCorePre7024 = true;
      }
    } catch (Exception ex) {
      Ivy.log().error(ex);
    }
  }

  private void setTargetComponentPostCore7024(MethodExpression componentMethod, UIComponent component) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    Field cc = componentMethod.getClass().getDeclaredField("compositeComponentId");
    cc.setAccessible(true);
    cc.set(componentMethod, component.getId());
  }

  private void setTargetComponentPreCore7024(MethodExpression componentMethod, UIComponent component) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    Field cc = componentMethod.getClass().getDeclaredField("compositeComponent");
    cc.setAccessible(true);
    cc.set(componentMethod, component);
  }
}
