package ch.ivy.addon.portalkit.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.domain.BusinessEntity;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.property.ICustomProperties;
import ch.ivyteam.ivy.application.property.ICustomProperty;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IWorkflowContext;
import ch.ivyteam.util.Property;

@SuppressWarnings("unchecked")
@RunWith(PowerMockRunner.class)
@PrepareForTest(AbstractDao.class)
public class AbstractDaoTest {

  private AbstractDao<BusinessEntity> abstractDao;

  @Before
  public void setup() {
    abstractDao = PowerMockito.mock(AbstractDao.class);
  }

  @Test
  public void testGetAllPortalDataProperties() throws Exception {
    final String PORTAL_PROPERTY_START = "AxonIvyPortal";
    ICustomProperty customProperty1 = mock(ICustomProperty.class);
    ICustomProperty customProperty2 = mock(ICustomProperty.class);

    List<ICustomProperty> customProperties = new ArrayList<>();
    customProperties.add(customProperty1);
    customProperties.add(customProperty2);
    when(abstractDao, "findAllStartingWithPrefix", PORTAL_PROPERTY_START).thenReturn(customProperties);
    when(abstractDao, "getCustomPropertyName", customProperty1).thenReturn("key1");
    when(abstractDao, "getCustomPropertyName", customProperty2).thenReturn("key2");
    when(abstractDao, "getCustomPropertyValue", customProperty1).thenReturn("valueOfKey1");
    when(abstractDao, "getCustomPropertyValue", customProperty2).thenReturn("valueOfKey2");
    when(abstractDao.getAllPortalDataProperties()).thenCallRealMethod();
    Property expectedProperty1 = new Property("key1", "valueOfKey1");
    Property expectedProperty2 = new Property("key2", "valueOfKey2");

    List<Property> actualProperties = abstractDao.getAllPortalDataProperties();

    assertEquals(2, actualProperties.size());
    assertEquals(expectedProperty1, actualProperties.get(0));
    assertEquals(expectedProperty2, actualProperties.get(1));
  }

  @Test
  public void testFindByIdWithoutRelationship() throws Exception {
    Long entityId = 1L;
    String jsonData = "{\"id\":1}";
    final String ENTITY_PROPERTY_KEY = "AxonIvyPortal.Test.1";
    ICustomProperty customProperty = mock(ICustomProperty.class);
    BusinessEntity entity = getNewBusinessEntity(entityId);

    when(abstractDao, "getEntityPropertyKey", entityId).thenReturn(ENTITY_PROPERTY_KEY);
    when(abstractDao, "findPropertyByKey", ENTITY_PROPERTY_KEY).thenReturn(customProperty);
    when(abstractDao, "getCustomPropertyValue", customProperty).thenReturn(jsonData);
    when(abstractDao, "parseJsonToObject", jsonData).thenReturn(entity);
    when(abstractDao.findByIdWithoutRelationship(entityId)).thenCallRealMethod();

    BusinessEntity actualBusinessEntity = abstractDao.findByIdWithoutRelationship(1L);
    assertEquals(entity, actualBusinessEntity);
  }


  @Test
  public void testFindById() throws Exception {
    Long entityId = 1L;
    BusinessEntity entity = getNewBusinessEntity(entityId);
    when(abstractDao.findByIdWithoutRelationship(entityId)).thenReturn(entity);
    when(abstractDao.findById(entityId)).thenCallRealMethod();

    assertEquals(entityId, abstractDao.findById(entityId).getId());
  }


  @Test
  public void testFindAll() throws Exception {
    final String PROPERTY_PREFIX_KEY = "AxonIvyPortal.Test";
    when(abstractDao, "getPropertyPrefixKey").thenReturn(PROPERTY_PREFIX_KEY);

    ICustomProperty customProperty1 = mock(ICustomProperty.class);
    ICustomProperty customProperty2 = mock(ICustomProperty.class);

    List<ICustomProperty> customProperties = new ArrayList<>();
    customProperties.add(customProperty1);
    customProperties.add(customProperty2);
    when(abstractDao, "findAllStartingWithPrefix", PROPERTY_PREFIX_KEY).thenReturn(customProperties);

    BusinessEntity entity1 = getNewBusinessEntity(1L);
    BusinessEntity entity2 = getNewBusinessEntity(2L);

    List<BusinessEntity> businessEntities = new ArrayList<BusinessEntity>();
    businessEntities.add(entity1);
    businessEntities.add(entity2);

    String propertyValue1 = "{\"id\":1}";
    String propertyValue2 = "{\"id\":2}";

    when(abstractDao, "getCustomPropertyValue", customProperty1).thenReturn(propertyValue1);
    when(abstractDao, "getCustomPropertyValue", customProperty2).thenReturn(propertyValue2);
    when(abstractDao, "parseJsonToObject", propertyValue1).thenReturn(entity1);
    when(abstractDao, "parseJsonToObject", propertyValue2).thenReturn(entity2);
    when(abstractDao.findAll()).thenCallRealMethod();

    List<BusinessEntity> actualEntities = abstractDao.findAll();
    assertEquals(2, actualEntities.size());
    assertEquals(entity1, actualEntities.get(0));
    assertEquals(entity2, actualEntities.get(1));
  }

  @Test
  public void testParseJsonToObject() throws Exception {
    AbstractDao<Application> abstractDao = mock(AbstractDao.class);
    String jsonData = "{\"id\":51}";
    when(abstractDao, "determineEntityType").thenReturn(Application.class);
    when(abstractDao, "parseJsonToObject", jsonData).thenCallRealMethod();
    BusinessEntity businessEntity = Whitebox.invokeMethod(abstractDao, "parseJsonToObject", jsonData);

    assertEquals(Long.valueOf(51L), businessEntity.getId());
  }
  
  @Test
  public void testParseObjectToJson() throws Exception{
    String expectedResult = "{\"id\":51}";
    BusinessEntity businessEntity = new BusinessEntity() {};
    businessEntity.setId(51L);
    when(abstractDao, "parseObjectToJson", businessEntity).thenCallRealMethod();
    String actualResult = Whitebox.invokeMethod(abstractDao, "parseObjectToJson", businessEntity);
    assertEquals(expectedResult, actualResult);
  }

  @Test
  public void testSaveNewEntity() throws Exception {
    BusinessEntity businessEntity = new BusinessEntity() {};
    BusinessEntity expectEntity = getNewBusinessEntity(1L);

    String entityValue = "{\"id\":51}";

    when(abstractDao, "saveNewData", businessEntity).thenReturn(entityValue);
    when(abstractDao, "parseJsonToObject", entityValue).thenReturn(expectEntity);
    when(abstractDao, "isAddMode", businessEntity).thenReturn(true);
    when(abstractDao.save(businessEntity)).thenCallRealMethod();

    assertEquals(expectEntity, abstractDao.save(businessEntity));
    PowerMockito.verifyPrivate(abstractDao, Mockito.times(1)).invoke("saveNewData", businessEntity);
    PowerMockito.verifyPrivate(abstractDao, Mockito.times(0)).invoke("modifyData", businessEntity);
  }

  @Test
  public void testUpdateOldEntity() throws Exception {
    BusinessEntity businessEntity = getNewBusinessEntity(1L);
    String entityValue = "{\"id\":51}";

    when(abstractDao, "modifyData", businessEntity).thenReturn(entityValue);
    when(abstractDao, "parseJsonToObject", entityValue).thenReturn(businessEntity);
    when(abstractDao, "isAddMode", businessEntity).thenReturn(false);
    when(abstractDao.save(businessEntity)).thenCallRealMethod();

    assertEquals(businessEntity, abstractDao.save(businessEntity));
    PowerMockito.verifyPrivate(abstractDao, Mockito.times(0)).invoke("saveNewData", businessEntity);
    PowerMockito.verifyPrivate(abstractDao, Mockito.times(1)).invoke("modifyData", businessEntity);
  }
  
  

  @Test
  public void testCheckAddMode() throws Exception {
    BusinessEntity businessEntity = new BusinessEntity() {};
    when(abstractDao, "isAddMode", businessEntity).thenCallRealMethod();
    boolean isAddMode = Whitebox.invokeMethod(abstractDao, "isAddMode", businessEntity);

    assertEquals(true, isAddMode);
  }

  @Test
  public void testCheckUpdateMode() throws Exception {
    BusinessEntity businessEntity = getNewBusinessEntity(1L);
    when(abstractDao, "isAddMode", businessEntity).thenCallRealMethod();
    boolean isAddMode = Whitebox.invokeMethod(abstractDao, "isAddMode", businessEntity);

    assertEquals(false, isAddMode);
  }

  @Test
  public void testSaveAll() {
    BusinessEntity businessEntity1 = new BusinessEntity() {};
    BusinessEntity businessEntity2 = new BusinessEntity() {};
    List<BusinessEntity> businessEntities = new ArrayList<BusinessEntity>();
    businessEntities.add(businessEntity1);
    businessEntities.add(businessEntity2);

    BusinessEntity expectedBusinessEntity1 = getNewBusinessEntity(1L);
    BusinessEntity expectedBusinessEntity2 = getNewBusinessEntity(2L);
    List<BusinessEntity> expectedBusinessEntities = new ArrayList<BusinessEntity>();
    expectedBusinessEntities.add(expectedBusinessEntity1);
    expectedBusinessEntities.add(expectedBusinessEntity2);

    when(abstractDao.save(businessEntity1)).thenReturn(expectedBusinessEntity1);
    when(abstractDao.save(businessEntity2)).thenReturn(expectedBusinessEntity2);

    when(abstractDao.saveAll(businessEntities)).thenCallRealMethod();

    List<BusinessEntity> actualBusinessEntities = abstractDao.saveAll(businessEntities);
    assertEquals(2, actualBusinessEntities.size());
    assertEquals(expectedBusinessEntity1, actualBusinessEntities.get(0));
    assertEquals(expectedBusinessEntity2, actualBusinessEntities.get(1));
  }
  
  @Test
  public void testDeleteProperty() {
    ICustomProperties customProperties = mock(ICustomProperties.class);
    IApplication mockApplication = mock(IApplication.class);
    String deletePropertyKey = "AxonIvyPortal.BusinessEntity.1";
    BusinessEntity deleteEntity = getNewBusinessEntity(1L);
    when(mockApplication.customProperties()).thenReturn(customProperties);
    
    AbstractDao<BusinessEntity> dao = new AbstractDao<BusinessEntity>(mockApplication){};
    
    dao.delete(deleteEntity);
    
    Mockito.verify(customProperties).delete(deletePropertyKey);
  }
  
  @Test
  public void testGetPropertyPrefixKey() throws Exception{
    String expectedPropertyPrefixKey = "AxonIvyPortal.Test.";
    when(abstractDao, "getEntityClassName").thenReturn("Test");
    when(abstractDao, "getPropertyPrefixKey").thenCallRealMethod();
    String actualPropertyPrefixKey = Whitebox.invokeMethod(abstractDao, "getPropertyPrefixKey");
    assertEquals(expectedPropertyPrefixKey, actualPropertyPrefixKey);
  }
  
  @Test
  public void testGetEntityClassName() throws Exception{
    String expectedClassName = "Application";
    when(abstractDao, "determineEntityType").thenReturn(Application.class);
    when(abstractDao, "getEntityClassName").thenCallRealMethod();
    String actualClassName = Whitebox.invokeMethod(abstractDao, "getEntityClassName");
    assertEquals(expectedClassName, actualClassName);
  }
  
  @Test
  @PrepareForTest({Ivy.class,IApplication.class})
  public void testDetermineEntityType() throws Exception{
    PowerMockito.mockStatic(Ivy.class);
    IApplication iApplication = mock(IApplication.class);
    mockStatic(IApplication.class);
    when(IApplication.current()).thenReturn(iApplication);
    
    AbstractDao<BusinessEntity> abstractDao = new AbstractDao<BusinessEntity>() {};
    
    Class<?> actualEntityType = Whitebox.invokeMethod(abstractDao, "determineEntityType");
    assertEquals(BusinessEntity.class, actualEntityType);
  }

  private BusinessEntity getNewBusinessEntity(Long entityId) {
    BusinessEntity entity = new BusinessEntity() {};
    entity.setId(entityId);
    return entity;
  }
}
