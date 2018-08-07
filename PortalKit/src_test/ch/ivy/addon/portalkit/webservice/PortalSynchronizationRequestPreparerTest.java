package ch.ivy.addon.portalkit.webservice;

import junit.framework.Assert;

import org.junit.Test;

import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.domain.BusinessEntity;
import ch.ivy.ws.addon.CustomPropertyPair;
import ch.ivyteam.util.Property;

public class PortalSynchronizationRequestPreparerTest {

  @Test
  public void testConvertPortalEntityToRequestData() {
    PortalSynchronizationRequestPreparer preparer = new PortalSynchronizationRequestPreparer();
    BusinessEntity a = new Application();
    a.setId(1L);
    CustomPropertyPair result = preparer.convertPortalEntityToRequestData(a);
    Assert.assertEquals("AxonIvyPortal.Application.1", result.getKey());
  }

  @Test
  public void testConvertPropertyToRequestData() {
    PortalSynchronizationRequestPreparer preparer = new PortalSynchronizationRequestPreparer();
    String key = "AxonIvyPortal.Server.1";
    String value = "{incrementId : 1}";
    CustomPropertyPair result = preparer.convertPropertyToRequestData(new Property(key,value));
    Assert.assertEquals(key, result.getKey());
    Assert.assertEquals(value, result.getValue());
  }

}
