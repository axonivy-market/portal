package ch.ivy.addon.portalkit.service;


import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.business.data.store.BusinessDataRepository;
import ch.ivyteam.ivy.environment.Ivy;

public abstract class AbstractExpressService<T> {

  public T findById(String id) {
    return repo().find(id, getType());
  }

  public BusinessDataInfo<T> save(T object) {
    if (object == null) {
      return null;
    }
    return repo().save(object);
  }

  public void delete(String id) {
    T object = findById(id);
    if (object == null) {
      return;
    }
    repo().delete(object);
  }

  public abstract Class<T> getType();

  protected BusinessDataRepository repo() {
    return Ivy.repo();
  }

}
