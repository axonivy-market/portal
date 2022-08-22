package com.axonivy.portal.components.util;

import java.util.List;

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
   * @param methodExpresstion 
   * @param params 
   * @return return value
   * @throws ELException
   *           if method expression string is invalid
   * @throws ClassCastException
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
  
  public static MethodExpression createCompleteMethod(String methodName) {
    @SuppressWarnings("rawtypes")
    Class<List> retType = List.class;
    Class<?>[] paramTypes = { String.class };
    MethodExpression autocompleteMethod = createMethodExpression(methodName, retType, paramTypes);
    return autocompleteMethod;
  }

  private static MethodExpression createMethodExpression(String methodExpression, Class<?> expectedReturnType,
      Class<?>[] expectedParamTypes) {
    FacesContext context = FacesContext.getCurrentInstance();
    return context.getApplication().getExpressionFactory().createMethodExpression(context.getELContext(),
        methodExpression, expectedReturnType, expectedParamTypes);
  }
}
