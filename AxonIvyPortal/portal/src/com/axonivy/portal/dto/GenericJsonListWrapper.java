package com.axonivy.portal.dto;

import java.util.List;

/**
 * Concrete, type-erasure-safe {@link JsonListWrapper} used when the wrapped
 * element type is only known at runtime via a {@code Class<T>} parameter
 * (e.g. {@code BusinessEntityConverter}'s generic list conversion methods).
 *
 * Unlike the abstract {@link JsonListWrapper}, Jackson can instantiate this
 * class directly when given a {@code JavaType} built with
 * {@code TypeFactory.constructParametricType(GenericJsonListWrapper.class, elementClass)}.
 *
 * @param <T> the type of elements in the wrapped list
 */
public class GenericJsonListWrapper<T> extends JsonListWrapper<T> {

  public GenericJsonListWrapper() {
    super();
  }

  public GenericJsonListWrapper(String version, List<T> items) {
    super(version, items);
  }
}
