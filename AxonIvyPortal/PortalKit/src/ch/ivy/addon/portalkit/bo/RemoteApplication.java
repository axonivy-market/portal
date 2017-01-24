package ch.ivy.addon.portalkit.bo;

import java.io.File;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;

import ch.ivyteam.api.IvyScriptVisibility;
import ch.ivyteam.api.PublicAPI;
import ch.ivyteam.db.jdbc.DatabaseConnectionConfiguration;
import ch.ivyteam.ivy.application.ActivityOperationState;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.ExternalDatabaseConfigurationAccessFlag;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IExternalDatabaseConfiguration;
import ch.ivyteam.ivy.application.IExternalDatabaseDefaultConfiguration;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.application.calendar.IBusinessCalendar;
import ch.ivyteam.ivy.application.calendar.IBusinessCalendarSettings;
import ch.ivyteam.ivy.application.property.ICustomProperties;
import ch.ivyteam.ivy.application.restricted.IDefaultGlobalVariable;
import ch.ivyteam.ivy.application.restricted.IDefaultWebService;
import ch.ivyteam.ivy.application.restricted.IEnvironment;
import ch.ivyteam.ivy.application.restricted.IGlobalVariable;
import ch.ivyteam.ivy.application.restricted.IWebService;
import ch.ivyteam.ivy.application.value.QualifiedVersion;
import ch.ivyteam.ivy.application.value.WebServiceAuthentication;
import ch.ivyteam.ivy.event.ISystemEventListener;
import ch.ivyteam.ivy.event.SystemEvent;
import ch.ivyteam.ivy.event.SystemEventCategory;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.security.IEMailNotificationSettings;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityDescriptor;
import ch.ivyteam.ivy.security.ISession;
import ch.ivyteam.ivy.system.IProperty;

/**
 * Bean for application.
 *
 * @author maonguyen
 */
@SuppressWarnings("restriction")
public class RemoteApplication implements IApplication {

  private String name;

  /**
   * Constructor
   *
   */
  public RemoteApplication() {
    super();
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void activate() throws PersistencyException {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void deactivate() throws PersistencyException {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public ActivityOperationState getActivityOperationState() throws PersistencyException {
    return null;
  }

  @Override
  public String getActivityOperationStateText() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public ActivityState getActivityState() throws PersistencyException {
    return null;
  }

  @Override
  public String getActivityStateText() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void lock() throws PersistencyException {

  }

  @Override
  public Object getAdapter(Class arg0) {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public Object getAttribute(String attributeName) {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public Set<String> getAttributeNames() {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public Object removeAttribute(String attributeName) {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public Object setAttribute(String attributeName, Object attributeValue) {
    return null;
  }

  @Override
  public List<IProperty> getConfigurationProperties() throws PersistencyException {
    return null;
  }

  @Override
  public IProperty getConfigurationProperty(String arg0) throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void addSystemEventListener(EnumSet<SystemEventCategory> arg0, ISystemEventListener arg1) {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void removeSystemEventListener(EnumSet<SystemEventCategory> arg0, ISystemEventListener arg1) {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public DispatchStatus sendSystemEvent(SystemEvent<?> arg0) {
    return null;
  }

  @Override
  public IEnvironment createEnvironment(String arg0, String arg1, IEnvironment arg2) throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public IExternalDatabaseDefaultConfiguration createExternalDatabaseConfiguration(String userFriendlyName,
      DatabaseConnectionConfiguration connectionConfiguration,
      EnumSet<ExternalDatabaseConfigurationAccessFlag> accessFlags, int maxConnections) throws PersistencyException {
    return null;
  }

  @Override
  public IDefaultGlobalVariable createGlobalVariable(String arg0, String arg1, String arg2) throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public IProcessModel createProcessModel(String processModelName, String description) throws PersistencyException {
    return null;
  }

  @Override
  public IDefaultWebService createWebService(String arg0, String arg1, String arg2, String arg3, String arg4,
      boolean arg5, int arg6, String arg7, String arg8, int arg9, String arg10, String arg11, String arg12)
      throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.HIDDEN)
  public void deleteAllExternalDatabaseConfigurations() {

  }

  @Override
  public void deleteEnvironment(String arg0) throws PersistencyException {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void deleteExternalDatabaseConfiguration(String userFriendlyName) throws PersistencyException {

  }

  @Override
  public void deleteGlobalVariable(String arg0) throws PersistencyException {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void deleteProcessModel(String processModelName) throws PersistencyException {

  }

  @Override
  public void deleteWebService(String arg0) throws PersistencyException {

  }

  @Override
  public IExternalDatabaseDefaultConfiguration findDefaultExternalDatabaseConfiguration(String arg0)
      throws PersistencyException {
    return null;
  }

  @Override
  public IDefaultGlobalVariable findDefaultGlobalVariable(String arg0) throws PersistencyException {
    return null;
  }

  @Override
  public IDefaultWebService findDefaultWebService(String arg0) throws PersistencyException {
    return null;
  }

  @Override
  public IEnvironment findEnvironment(String arg0) throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public IExternalDatabaseConfiguration findExternalDatabaseConfiguration(String userFriendlyName)
      throws PersistencyException {
    return null;
  }

  @Override
  public IExternalDatabaseConfiguration findExternalDatabaseConfiguration(String arg0, ISession arg1)
      throws PersistencyException {
    return null;
  }

  @Override
  public IGlobalVariable findGlobalVariable(String arg0) throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public IProcessModel findProcessModel(String processModelName) throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public IProcessModel findProcessModel(Object identifier) throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public IProcessModelVersion findProcessModelVersion(String processModelVersionUrl) throws PersistencyException {
    return null;
  }

  @Override
  public IProcessModelVersion findProcessModelVersion(Object arg0) throws PersistencyException {
    return null;
  }

  @Override
  public IWebService findWebService(String arg0) throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public String getActiveEnvironment() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public IEnvironment getActualEnvironment() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.HIDDEN)
  public IEnvironment getActualEnvironment(ISession session) throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public IBusinessCalendarSettings getBusinessCalendarSettings() {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public Locale getDefaultEMailLanguage() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public IEMailNotificationSettings getDefaultEMailNotifcationSettings() throws PersistencyException {
    return null;
  }

  @Override
  public List<IExternalDatabaseConfiguration> getDefaultExternalDatabaseConfigurations() throws PersistencyException {
    return null;
  }

  @Override
  public List<IGlobalVariable> getDefaultGlobalVariables() throws PersistencyException {
    return null;
  }

  @Override
  public List<IWebService> getDefaultWebServices() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public String getDescription() throws PersistencyException {
    return null;
  }

  @Override
  public List<IEnvironment> getEnvironments() throws PersistencyException {
    return null;
  }

  @Override
  public List<IEnvironment> getEnvironmentsSortedByName() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public List<IExternalDatabaseConfiguration> getExternalDatabaseConfigurations() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public File getFileArea() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public String getFileDirectory() throws PersistencyException {
    return null;
  }

  @Override
  public List<IGlobalVariable> getGlobalVariables() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public long getId() {
    return 0;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.HIDDEN)
  @Deprecated
  public int getIdentifier() {
    return 0;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public List<ILibrary> getLibraries() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public String getName() throws PersistencyException {
    return name;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public String getOwnerName() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public List<IProcessModel> getProcessModels() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public List<IProcessModel> getProcessModelsSortedByName() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public ISecurityContext getSecurityContext() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public ISecurityDescriptor getSecurityDescriptor() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public File getSessionFileArea() throws PersistencyException {
    return null;
  }

  @Override
  public File getSessionFileArea(ISession arg0) throws PersistencyException {
    return null;
  }

  @Override
  public List<IWebService> getWebServices() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  @Deprecated
  public boolean isDefaultFileDirectory() throws PersistencyException {
    return false;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public boolean isSystem() throws PersistencyException {
    return false;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void setActiveEnvironment(String environment) throws PersistencyException, NoSuchElementException {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void setBusinessCalendar(IBusinessCalendar calendar) {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void setDefaultEMailLanguage(Locale emailLanguage) throws PersistencyException {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void setDefaultEMailNotifcationSettings(IEMailNotificationSettings settings) throws PersistencyException {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void setDescription(String description) throws PersistencyException {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void setFileDirectory(String directory) throws PersistencyException {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void setOwnerName(String ownerName) throws PersistencyException {

  }

  /**
   * Sets the name
   *
   * @param name
   *          The name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public ICustomProperties customProperties() {
    return null;
  }

  @Override
  public IDefaultWebService createWebService(String generationIdentifier, String name, String description, String tag,
      String wsdlurl, boolean transportSession, int sessionHandlingMode, String webServiceFramework,
      String generationProperties, int generationPropertiesVersion, WebServiceAuthentication authentication)
      throws PersistencyException {
    return null;
  }

  @Override
  public boolean isSystemProvided() throws PersistencyException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public ILibrary findLibrary(String requiredLibraryId,
		QualifiedVersion minVersion, QualifiedVersion maxVersion)
		throws PersistencyException {
	// TODO Auto-generated method stub
	return null;
  }

}
