package com.axonivy.portal.components.persistence.dao;

import java.lang.reflect.Method;

import com.axonivy.portal.components.persistence.domain.BusinessEntity;
import com.axonivy.portal.components.persistence.exception.DaoException;
import com.axonivy.portal.components.util.IvyExecutor;

import ch.ivyteam.ivy.application.IApplication;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

public class ExecuteAsSystemDecorator {

  private ExecuteAsSystemDecorator() {}

  @SuppressWarnings("unchecked")
  public static <T extends BusinessEntity> AbstractDao<T> decorate(AbstractDao<T> abstractDao, IApplication application) {

    try {
      ProxyFactory factory = new ProxyFactory();
      factory.setSuperclass(abstractDao.getClass());

      return (AbstractDao<T>) factory.create(new Class[] {IApplication.class}, new Object[] {application},
          new SystemInvocationHandler());
    } catch (Exception exception) {
      throw new DaoException("Cannot create proxy for AbstractDao class", exception);
    }
  }

  private static class SystemInvocationHandler implements MethodHandler {
    @Override
    public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
      if (thisMethod.getAnnotation(ExecuteAsSystem.class) != null) {
        return IvyExecutor.executeAsSystem(() -> proceed.invoke(self, args));
      } else {
        return proceed.invoke(self, args);
      }
    }
  }
}
