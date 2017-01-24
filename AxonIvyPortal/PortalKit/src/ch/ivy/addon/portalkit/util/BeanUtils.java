package ch.ivy.addon.portalkit.util;

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import ch.ivyteam.ivy.environment.Ivy;

public final class BeanUtils {
  /**
   * Utility class should not be instantiable
   */
  private BeanUtils() {
  }

  /**
   * Invoke method of another bean from which bean called this method by method
   * expression string</br> Exp: #{logic.plusTwoRealNumber}
   * 
   * @param methodEL
   *          method expression string
   * @param parameters
   *          array of parameter of method
   * @return return value
   * @throws ELException
   *           if method expression string is invalid
   * @throws ClassCaseException
   *           if return type is wrong
   */
  @SuppressWarnings("unchecked")
  public static <E> E invokeBeanMethodViaMethodExpression(String methodExpresstion, Object... params) {

    FacesContext facesContext = FacesContext.getCurrentInstance();
    ELContext elContext = facesContext.getELContext();
    Application application = facesContext.getApplication();
    ExpressionFactory expressionFactory = application.getExpressionFactory();
    MethodExpression methodExpression = expressionFactory.createMethodExpression(elContext, methodExpresstion, null,
        getClassParameters(params));
    E returnData;
    try {
      returnData = (E) methodExpression.invoke(elContext, params);
    } catch (ELException elException) {
      Ivy.log().error("Error calling " + methodExpresstion, elException);
      throw new ELException("Error calling " + methodExpresstion, elException);
    } catch (ClassCastException ex) {
      Ivy.log().error("The data type of return value is incorrect", ex);
      throw ex;
    }
    return returnData;
  }

  private static Class<?>[] getClassParameters(Object[] params) {
    Class<?>[] results = new Class<?>[params.length];
    for (int i = 0; i < params.length; i++) {
      results[i] = params[i].getClass();
    }
    return results;
  }
}
