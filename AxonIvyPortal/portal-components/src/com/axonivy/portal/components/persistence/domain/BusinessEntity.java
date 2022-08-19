package com.axonivy.portal.components.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.axonivy.portal.components.persistence.variable.PropertyKey;

public abstract class BusinessEntity {
  protected static final int PRIME_NUMBER = 31;
  protected Long id;

  @JsonIgnore
  private String propertyKey;

  @JsonIgnore
  private String entityIncreamentIdKey;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPropertyKey() {
    propertyKey = String.format(PropertyKey.ENTITY_PROPERTY_KEY, getEntityClassName(), this.id);
    return propertyKey;
  }

  public String getEntityIncreamentIdKey() {
    entityIncreamentIdKey = PropertyKey.ENTITY_INCREMENT_ID_KEY;
    return entityIncreamentIdKey;
  }

  private String getEntityClassName() {
    return this.getClass().getSimpleName();
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = PRIME_NUMBER * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    BusinessEntity other = (BusinessEntity) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }
}
