package ch.ivy.ws.addon.service;

import static ch.ivy.ws.addon.transformer.IvyCaseTransformer.transformToIvyCase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.Callable;

import org.apache.commons.lang.StringUtils;

import ch.ivy.ws.addon.CategoryData;
import ch.ivy.ws.addon.WSErrorType;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.bo.AvailableAppsResult;
import ch.ivy.ws.addon.bo.CaseServiceResult;
import ch.ivy.ws.addon.bo.NoteServiceResult;
import ch.ivy.ws.addon.transformer.IvyCaseTransformer;
import ch.ivy.ws.addon.transformer.IvyDocumentTransformer;
import ch.ivy.ws.addon.transformer.IvyNoteTransformer;
import ch.ivy.ws.addon.types.CaseStateStatistic;
import ch.ivy.ws.addon.types.ElapsedTimeStatistic;
import ch.ivy.ws.addon.types.IvyAdditionalProperty;
import ch.ivy.ws.addon.types.IvyApplication;
import ch.ivy.ws.addon.types.IvyCase;
import ch.ivy.ws.addon.util.IvyExecutor;
import ch.ivy.ws.addon.util.JsonConverterUtils;
import ch.ivy.ws.addon.util.SessionUtil;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.Binary;
import ch.ivyteam.ivy.scripting.objects.Recordset;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.INote;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.category.CategoryTree;
import ch.ivyteam.ivy.workflow.document.IDocument;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

public class CaseServiceImpl extends AbstractService implements ICaseService {

  @Override
  public CaseServiceResult setAdditionalProperties(final Long caseId,
      final List<IvyAdditionalProperty> additionalProperties) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<CaseServiceResult>() {
        @Override
        public CaseServiceResult call() throws Exception {
          CaseServiceResult result = new CaseServiceResult();
          result.setErrors(new ArrayList<>());
          if (caseId != null) {
            CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);
            ICase iCase = null;
            try {
              iCase = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getFirstResult(query);
              setAdditionalProperties(additionalProperties, iCase);
            } catch (Exception e) {
              // Wrong case id
              result.getErrors().add(new WSException(WSErrorType.WARNING, 10022, e, Arrays.asList(caseId), null));
            }
          } else {
            // No case id given
            result.getErrors().add(new WSException(WSErrorType.WARNING, 10022, new ArrayList<>(), null));
          }
          return result;
        }
      });
    } catch (WSException e) {
      throw e;
    } catch (Exception ex) {
      throw new WSException(10000, ex);
    }
  }

  private void setAdditionalProperties(final List<IvyAdditionalProperty> additionalProperties, ICase iCase) {
    for (int i = 0; i < additionalProperties.size(); i++) {
      iCase.setAdditionalProperty(additionalProperties.get(i).getKey(), additionalProperties.get(i).getValue());
    }
  }

  @Override
  public CaseServiceResult getAdditionalProperties(final Long caseId) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<CaseServiceResult>() {
        @Override
        public CaseServiceResult call() throws Exception {
          CaseServiceResult result = new CaseServiceResult();
          result.setErrors(new ArrayList<>());
          if (caseId == null) {
            result.getErrors().add(new WSException(WSErrorType.WARNING, 10022, new ArrayList<>(), null));
            return result;
          }
          CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);

          ICase iCase = null;
          try {
            iCase = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getFirstResult(query);
            result.setAddtionalProperties(getAdditionalProperties(iCase));
          } catch (Exception e) {
            // Wrong case id
            result.getErrors().add(new WSException(WSErrorType.WARNING, 10022, e, Arrays.asList(caseId), null));
          }
          return result;
        }
      });
    } catch (WSException e) {
      throw e;
    } catch (Exception ex) {
      throw new WSException(10000, ex);
    }
  }

  private List<IvyAdditionalProperty> getAdditionalProperties(ICase iCase) {
    List<String> additionalPropertyNames = iCase.getAdditionalPropertyNames();

    List<IvyAdditionalProperty> properties = new ArrayList<>();
    for (String property : additionalPropertyNames) {
      IvyAdditionalProperty p = new IvyAdditionalProperty();
      p.setKey(property);
      p.setValue(iCase.getAdditionalProperty(property));
      properties.add(p);
    }
    return properties;
  }

  private Optional<ICase> findCaseById(final Optional<Integer> caseId) {
    Callable<ICase> caseCallable = () -> {
      ICase ivyCase = null;
      if (caseId.isPresent()) {
        CaseQuery caseQuery = CaseQuery.create().where().caseId().isEqual(caseId.get());
        ivyCase = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getFirstResult(caseQuery);
      }
      return ivyCase;
    };

    ICase ivyCase = IvyExecutor.executeAsSystem(caseCallable);
    return Optional.ofNullable(ivyCase);
  }

  @Override
  public void destroyCase(final Integer caseId) throws WSException {
    try {
      ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        ICase ivyCase = findCaseById(Optional.ofNullable(caseId)).get();
        ivyCase.destroy();
        return Void.class;
      });
    } catch (Exception e) {
      throw new WSException(10000, e);
    }
  }

  @Override
  public CaseServiceResult findCase(final Integer caseId) throws WSException {
    CaseServiceResult caseServiceResult = new CaseServiceResult();
    IvyCaseTransformer caseTransformer = new IvyCaseTransformer();
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<CaseServiceResult>() {
        @Override
        public CaseServiceResult call() throws Exception {
          Optional<ICase> optionalCase = findCaseById(Optional.ofNullable(caseId));
          if (optionalCase.isPresent()) {
            IvyCase ivyCase = caseTransformer.transform(optionalCase.get());
            caseServiceResult.setOneCase(ivyCase);
          } else {
            List<Object> userTextParams = new ArrayList<>();
            userTextParams.add(caseId);
            throw new WSException(WSErrorType.WARNING, 10022, userTextParams, null);
          }
          return caseServiceResult;
        }
      });
    } catch (Exception exception) {
      List<Object> userTextParams = new ArrayList<>();
      userTextParams.add(caseId);
      throw new WSException(WSErrorType.WARNING, 10022, exception, userTextParams, null);
    }
  }

  @Override
  public NoteServiceResult findNotes(final Integer caseId) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<NoteServiceResult>() {
        @Override
        public NoteServiceResult call() throws Exception {
          NoteServiceResult result = new NoteServiceResult();
          result.setErrors(new ArrayList<>());

          if (caseId != null) {
            findNotes(caseId, result);
          } else {
            result.getErrors().add(new WSException(WSErrorType.WARNING, 10028, new ArrayList<>(), null));
          }
          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10003, e);
    }
  }

  private void findNotes(final Integer caseId, NoteServiceResult result) {
    CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);
    ICase c = null;
    try {
      c = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getResults(query).get(0);
    } catch (Exception e) {
      result.getErrors().add(new WSException(WSErrorType.WARNING, 10022, e, Arrays.asList(caseId), null));
    }
    if (c != null) {
      result.setNotes(new IvyNoteTransformer().transform(c.getNotes()));
    }
  }

  @Override
  public NoteServiceResult createNote(final String username, final Integer caseId, final String message)
      throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<NoteServiceResult>() {
        @Override
        public NoteServiceResult call() throws Exception {
          NoteServiceResult result = new NoteServiceResult();
          result.setErrors(new ArrayList<>());
          if (StringUtils.isBlank(username)) {
            // No user specified
            result.getErrors().add(new WSException(WSErrorType.WARNING, 10029));
          } else if (caseId == null) {
            // No CaseID specified
            result.getErrors().add(new WSException(WSErrorType.WARNING, 10034, new ArrayList<>(), null));
          } else {
            createNote(username, message, result, caseId);
          }
          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10004, e);
    }
  }

  private void createNote(final String username, final String message, NoteServiceResult result, Integer caseId) {
    CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);
    ICase iCase = null;

    try {
      iCase = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getResults(query).get(0);
    } catch (Exception e) {
      // Wrong CaseID
      result.getErrors().add(new WSException(WSErrorType.WARNING, 10034, e, Arrays.asList(caseId), null));
    }
    if (iCase != null) {
      IWorkflowSession session = null;
      IApplication application = null;
      try {
        application = iCase.getApplication();
        session = findUserWorkflowSession(username, application);
        if (session != null) {
          INote note = iCase.createNote(session, message);
          result.setNewNote(new IvyNoteTransformer().transform(note));
        }
      } catch (Exception e) {
        // Wrong Username
        result.getErrors().add(new WSException(WSErrorType.WARNING, 10029, e, Arrays.asList(username), null));
      } finally {
        if (session != null && application != null) {
          application.getSecurityContext().destroySession(session.getIdentifier());
        }
      }
    }
  }

  @Override
  public CaseServiceResult findDocuments(final Integer caseId) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<CaseServiceResult>() {
        @Override
        public CaseServiceResult call() throws Exception {
          CaseServiceResult result = new CaseServiceResult();
          result.setErrors(new ArrayList<>());

          if (caseId != null) {

            findDocuments(caseId, result);
          } else {
            // No caseId given
            result.getErrors().add(new WSException(WSErrorType.WARNING, 10028, new ArrayList<>(), null));
          }
          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10005, e);
    }
  }

  private void findDocuments(final Integer caseId, CaseServiceResult result) {
    CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);
    ICase iCase = null;
    try {
      iCase = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getFirstResult(query);
    } catch (Exception e) {
      // Wrong CaseId
      result.getErrors().add(new WSException(WSErrorType.WARNING, 10022, e, Arrays.asList(caseId), null));
    }

    if (iCase != null) {
      try {
        IvyDocumentTransformer transformer = new IvyDocumentTransformer();
        result.setDocuments(transformer.transform(iCase.documents().getAll()));
      } catch (Exception e) {
        result.getErrors().add(new WSException(WSErrorType.WARNING, 10043, e, Arrays.asList(e.getMessage()), null));
      }
    }
  }

  @Override
  public CaseServiceResult uploadDocument(String username, Integer caseId, String documentName, Binary documentContent)
      throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<CaseServiceResult>() {
        @Override
        public CaseServiceResult call() throws Exception {
          CaseServiceResult result = new CaseServiceResult();
          result.setErrors(new ArrayList<>());
          if (caseId != null) {
            uploadDocument(username, caseId, documentName, documentContent, result);
          } else {
            // No caseId given
            result.getErrors().add(new WSException(WSErrorType.WARNING, 10028, new ArrayList<>(), null));
          }
          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10005, e);
    }
  }

  private void uploadDocument(String username, Integer caseId, String documentName, Binary documentContent,
      CaseServiceResult result) {
    CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);
    ICase iCase = null;
    try {
      iCase = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getFirstResult(query);
    } catch (Exception e) {
      // Wrong CaseId
      result.getErrors().add(new WSException(WSErrorType.WARNING, 10022, e, Arrays.asList(caseId), null));
    }
    if (iCase != null) {
      try {
        IDocument document = iCase.documents().add(documentName).write().withContentFrom(documentContent);
        result.setDocument(new IvyDocumentTransformer().transform(document));
        createNoteWhenUploadDocument(username, caseId, documentName);
      } catch (Exception e) {
        result.getErrors().add(new WSException(WSErrorType.WARNING, 10042, e, Arrays.asList(documentName), null));
      }
    }
  }

  private void createNoteWhenUploadDocument(String username, Integer caseId, String documentName) throws WSException {
    List<Object> parameter = Arrays.asList(username, documentName);
    String uploadDocumentMessage = Ivy.cms().co("/ch/ivy/addon/portalconnector/document/uploadDocumentNote", parameter);
    createNote(username, caseId, uploadDocumentMessage);
  }

  @Override
  public CaseServiceResult downloadDocument(Integer caseId, Integer documentId) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<CaseServiceResult>() {
        @Override
        public CaseServiceResult call() throws Exception {
          CaseServiceResult result = new CaseServiceResult();
          result.setErrors(new ArrayList<>());

          if (caseId != null) {
            downloadDocument(caseId, documentId, result);
          } else {
            // No caseId given
            result.getErrors().add(new WSException(WSErrorType.WARNING, 10028, new ArrayList<>(), null));
          }
          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10005, e);
    }
  }

  private void downloadDocument(Integer caseId, Integer documentId, CaseServiceResult result) {
    CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);
    ICase iCase = null;
    try {
      iCase = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getFirstResult(query);
    } catch (Exception e) {
      // Wrong CaseId
      result.getErrors().add(new WSException(WSErrorType.WARNING, 10022, e, Arrays.asList(caseId), null));
    }
    if (iCase != null) {
      try {
        result.setDocumentContent(iCase.documents().get(documentId).read().asBinary());
      } catch (Exception e) {
        result.getErrors().add(new WSException(WSErrorType.WARNING, 10043, e, Arrays.asList(e.getMessage()), null));
      }
    }
  }

  @Override
  public CaseServiceResult removeDocument(String userName, Integer caseId, Integer documentId) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<CaseServiceResult>() {
        @Override
        public CaseServiceResult call() throws Exception {
          CaseServiceResult result = new CaseServiceResult();
          result.setErrors(new ArrayList<>());
          if (caseId == null) {
            result.getErrors().add(new WSException(WSErrorType.WARNING, 10028, new ArrayList<>(), null));
            return result;
          }
          removeDocument(userName, caseId, documentId, result);
          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10005, e);
    }
  }

  private void removeDocument(String userName, Integer caseId, Integer documentId, CaseServiceResult result) {
    CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);
    ICase iCase = null;
    try {
      iCase = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getFirstResult(query);
    } catch (Exception e) {
      // Wrong CaseId
      result.getErrors().add(new WSException(WSErrorType.WARNING, 10022, e, Arrays.asList(caseId), null));
    }
    if (iCase != null) {
      try {
        iCase.documents().delete(documentId);
        IDocument document = iCase.documents().get(documentId);
        if (document != null) {
          createNoteAfterRemoveDocument(userName, caseId, document);
        }
      } catch (Exception e) {
        result.getErrors().add(new WSException(WSErrorType.WARNING, 10043, e, Arrays.asList(e.getMessage()), null));
      }
    }
  }

  private void createNoteAfterRemoveDocument(String userName, Integer caseId, IDocument document) throws WSException {
    String documentName = document.getName();
    List<Object> parameter = Arrays.asList(userName, documentName);
    String removeDocumentMessage = Ivy.cms().co("/ch/ivy/addon/portalconnector/document/deleteDocumentNote", parameter);
    createNote(userName, caseId, removeDocumentMessage);
  }

  @Override
  public CaseServiceResult findCasesByCriteria(CaseSearchCriteria caseSearchCriteria, Integer startIndex, Integer count)
      throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {

        if (caseSearchCriteria.isEmpty()) {
          return result(noErrors());
        }

        CaseQuery caseQuery = createCaseQuery(caseSearchCriteria);
        queryExcludeHiddenCases(caseQuery);
        List<ICase> cases = executeCaseQuery(caseQuery, startIndex, count);
        List<IvyCase> ivyCases = new ArrayList<>();

        for (ICase iCase : cases) {
          IvyCase ivyCase = transformToIvyCase(iCase);
          checkPermission(caseSearchCriteria, iCase, ivyCase);
          ivyCases.add(ivyCase);
        }

        return result(ivyCases, errors);
      });
    } catch (Exception e) {
      throw new WSException(10002, e);
    }
  }

  private void checkPermission(CaseSearchCriteria caseSearchCriteria, ICase iCase, IvyCase ivyCase) {
    ivyCase.setCanDestroy(SessionUtil.doesUserHavePermission(iCase.getApplication(),
        caseSearchCriteria.getInvolvedUsername(), IPermission.CASE_DESTROY));
    ivyCase.setCanChangeDescription(SessionUtil.doesUserHavePermission(iCase.getApplication(),
        caseSearchCriteria.getInvolvedUsername(), IPermission.CASE_WRITE_DESCRIPTION));
    ivyCase.setCanChangeName(SessionUtil.doesUserHavePermission(iCase.getApplication(),
        caseSearchCriteria.getInvolvedUsername(), IPermission.CASE_WRITE_NAME));
    ivyCase.setCanUploadDeleteDocument(checkCanUserUploadDeleteDocument(caseSearchCriteria, iCase));
  }

  private boolean checkCanUserUploadDeleteDocument(CaseSearchCriteria caseSearchCriteria, ICase iCase) {
    return SessionUtil.doesUserHavePermission(iCase.getApplication(), caseSearchCriteria.getInvolvedUsername(),
        IPermission.DOCUMENT_WRITE)
        || SessionUtil.doesUserHavePermission(iCase.getApplication(), caseSearchCriteria.getInvolvedUsername(),
            IPermission.DOCUMENT_OF_INVOLVED_CASE_WRITE);
  }

  @Override
  public CaseServiceResult countCasesByCriteria(CaseSearchCriteria caseSearchCriteria) throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        if (caseSearchCriteria.isEmpty()) {
          return result(0, errors);
        }

        CaseQuery caseQuery = createCaseQuery(caseSearchCriteria);
        queryExcludeHiddenCases(caseQuery);
        long caseCount = countCases(caseQuery);
        return result(caseCount, errors);

      });
    } catch (Exception e) {
      throw new WSException(10016, e);
    }
  }

  @Override
  public CaseServiceResult findCategories(String jsonQuery, final String username, List<String> apps, String language)
      throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        CaseQuery caseQuery = buildQuery(jsonQuery, username, apps);
        CategoryTree categoryTree = CategoryTree.createFor(caseQuery);
        List<CategoryData> categories = getCategoryList(language, categoryTree);
        return categoryResult(categories, errors);
      });
    } catch (Exception e) {
      throw new WSException(10016, e);
    }
  }

  private CaseQuery buildQuery(String jsonQuery, final String username, List<String> apps) {
    CaseQuery caseQuery = Ivy.wf().getGlobalContext().getCaseQueryExecutor().createCaseQuery();
    if (StringUtils.isNotBlank(jsonQuery)) {
      CaseQuery.fromJson(jsonQuery);
    }
    queryExcludeHiddenCases(caseQuery);

    if (username != null && !StringUtils.isEmpty(username)) {
      AvailableAppsResult availableAppsResult = findAvailableApplicationsAndUsers(apps, username);
      caseQuery.where().and(queryForUsers(availableAppsResult.getUsers()))
          .and(queryForInvolvedApplications(availableAppsResult.getAvailableApps()));
    } else {
      caseQuery.where().and(queryForInvolvedApplications(apps));
    }
    caseQuery.where().and(queryForStates(Arrays.asList(CaseState.CREATED, CaseState.RUNNING, CaseState.DONE)));
    caseQuery.where().and().category().isNotNull().and().category().isNotEqual("Portal");
    return caseQuery;
  }

  private List<CategoryData> getCategoryList(String language, CategoryTree categoryTree) {
    List<CategoryData> categories = new ArrayList<>();
    categoryTree.getAllChildren().forEach(category -> {
      CategoryData categoryData = new CategoryData();
      categoryData.setPath(category.getCategory().getPath(Locale.forLanguageTag(language)));
      categoryData.setRawPath(category.getRawPath());
      categories.add(categoryData);
    });
    return categories;
  }

  @Override
  public CaseServiceResult analyzeCaseStateStatistic(CaseSearchCriteria caseSearchCriteria) throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        CaseQuery caseStateQuery = createCaseQuery(caseSearchCriteria);
        queryExcludeHiddenCases(caseStateQuery);

        caseStateQuery.aggregate().countRows().groupBy().state().orderBy().state();

        Recordset recordSet = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getRecordset(caseStateQuery);
        CaseStateStatistic caseStateStatistic = createCaseStateStatistic(recordSet);

        return result(caseStateStatistic, errors);
      });
    } catch (Exception e) {
      throw new WSException(10051, e);
    }
  }

  private CaseStateStatistic createCaseStateStatistic(Recordset recordSet) {
    CaseStateStatistic caseStateStatistic = new CaseStateStatistic();
    if (recordSet != null) {
      recordSet.getRecords().forEach(record -> {
        int state = Integer.parseInt(record.getField("STATE").toString());
        long numberOfCases = Long.parseLong(record.getField("COUNT").toString());
        if (state == CaseState.DONE.intValue()) {
          caseStateStatistic.setDone((numberOfCases));
        } else if (state == CaseState.CREATED.intValue()) {
          caseStateStatistic.setCreated(numberOfCases);
        } else if (state == CaseState.DESTROYED.intValue()) {
          caseStateStatistic.setFailed(numberOfCases);
        } else if (state == CaseState.RUNNING.intValue()) {
          caseStateStatistic.setRunning(numberOfCases);
        }
      });
    }
    return caseStateStatistic;
  }

  @Override
  public CaseServiceResult analyzeElapsedTimeByCaseCategory(CaseSearchCriteria caseSearchCriteria) throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        CaseQuery elapsedTimeQuery = createCaseQuery(caseSearchCriteria);
        queryExcludeHiddenCases(elapsedTimeQuery);

        elapsedTimeQuery.where().and().businessRuntime().isNotNull();
        elapsedTimeQuery.aggregate().avgBusinessRuntime().groupBy().category();

        Recordset recordSet = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getRecordset(elapsedTimeQuery);
        HashMap<String, Long> recordMap = getCategoryToAverageElapsedTimeMap(recordSet);

        ElapsedTimeStatistic elapsedTimeStatistic = new ElapsedTimeStatistic();
        elapsedTimeStatistic.setResult(JsonConverterUtils.mapToJson(recordMap));

        return result(elapsedTimeStatistic, errors);
      });
    } catch (Exception e) {
      throw new WSException(10052, e);
    }
  }

  private HashMap<String, Long> getCategoryToAverageElapsedTimeMap(Recordset recordSet) {
    HashMap<String, Long> recordMap = new HashMap<>();
    if (recordSet != null) {
      recordSet.getRecords().forEach(
          record -> {
            String categoryName = record.getField("CATEGORY").toString();
            BigDecimal averageElapsedTime =
                Optional.ofNullable((BigDecimal) record.getField("AVGBUSINESSRUNTIME")).orElse(new BigDecimal(0));
            long averageElapsedTimeValue = averageElapsedTime.longValue();
            recordMap.put(categoryName, averageElapsedTimeValue);
          });
    }
    return recordMap;
  }

  private CaseServiceResult result(List<WSException> errors) {
    CaseServiceResult result = new CaseServiceResult();
    result.setErrors(errors);
    return result;
  }

  private CaseServiceResult result(List<IvyCase> ivyCases, List<WSException> errors) {
    CaseServiceResult result = new CaseServiceResult();
    result.setCases(ivyCases);
    result.setErrors(errors);
    return result;
  }

  private CaseServiceResult result(long caseCount, List<WSException> errors) {
    CaseServiceResult result = new CaseServiceResult();
    result.setCaseCount(caseCount);
    result.setErrors(errors);
    return result;
  }

  private CaseServiceResult result(CaseStateStatistic caseStateStatistic, List<WSException> errors) {
    CaseServiceResult result = new CaseServiceResult();
    result.setCaseStateStatistic(caseStateStatistic);
    result.setErrors(errors);
    return result;
  }

  private CaseServiceResult result(ElapsedTimeStatistic elapsedTimeStatistic, List<WSException> errors) {
    CaseServiceResult result = new CaseServiceResult();
    result.setElapsedTimeStatistic(elapsedTimeStatistic);
    result.setErrors(errors);
    return result;
  }

  private CaseServiceResult categoryResult(List<CategoryData> categories, List<WSException> errors) {
    CaseServiceResult result = new CaseServiceResult();
    result.setCategories(categories);
    result.setErrors(errors);
    return result;
  }

  private static List<WSException> noErrors() {
    return Collections.emptyList();
  }

  private static CaseQuery queryForUsers(List<IUser> users) {
    CaseQuery caseQuery = CaseQuery.create();
    users.forEach(user -> caseQuery.where().or().isInvolved(user));
    return caseQuery;
  }

  private CaseQuery queryForStates(List<CaseState> states) {
    CaseQuery stateFieldQuery = CaseQuery.create();
    IFilterQuery filterQuery = stateFieldQuery.where();
    for (CaseState state : states) {
      filterQuery.or().state().isEqual(state);
    }
    return stateFieldQuery;
  }

  private List<ICase> executeCaseQuery(CaseQuery query, Integer startIndex, Integer count) {
    return Ivy.wf().getGlobalContext().getCaseQueryExecutor().getResults(query, startIndex, count);
  }

  private long countCases(CaseQuery query) {
    return Ivy.wf().getGlobalContext().getCaseQueryExecutor().getCount(query);
  }

  private CaseQuery createCaseQuery(CaseSearchCriteria caseSearchCriteria) {
    CaseQuery finalQuery = CaseQuery.fromJson(caseSearchCriteria.getJsonQuery());

    if (caseSearchCriteria.isBusinessCase()) {
      finalQuery.where().and().isBusinessCase();
    } else if (caseSearchCriteria.isTechnicalCase()) {
      finalQuery.where().and().isNotBusinessCase().and().businessCaseId()
          .isEqual(caseSearchCriteria.getBusinessCaseId());
    }

    if (caseSearchCriteria.hasInvolvedUsername() && !caseSearchCriteria.isIgnoreInvolvedUser()) {
      List<String> involvedApplications = caseSearchCriteria.getInvolvedApplications();
      String involvedUsername = caseSearchCriteria.getInvolvedUsername();

      AvailableAppsResult availableAppsResult =
          findAvailableApplicationsAndUsers(involvedApplications, involvedUsername);
      finalQuery.where().and(queryForUsers(availableAppsResult.getUsers()))
          .and(queryForInvolvedApplications(availableAppsResult.getAvailableApps()));
    } else if (caseSearchCriteria.hasInvolvedApplications()) {
      finalQuery.where().and(queryForInvolvedApplications(caseSearchCriteria.getInvolvedApplications()));
    }

    return finalQuery;
  }

  private CaseQuery queryForInvolvedApplications(List<String> apps) {
    List<IvyApplication> applications = WsServiceFactory.getApplicationService().getApplicationsBy(apps);
    CaseQuery caseQuery = CaseQuery.create();
    applications.forEach(app -> caseQuery.where().or().applicationId().isEqual(app.getId()));
    return caseQuery;
  }

  @Override
  public void saveCase(IvyCase ivyCase) throws WSException {
    try {
      ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        CaseQuery query = CaseQuery.create().where().caseId().isEqual(ivyCase.getId());
        ICase icase = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getFirstResult(query);
        icase.setName(ivyCase.getName());
        icase.setDescription(ivyCase.getDescription());

        return null;
      });
    } catch (Exception e) {
      throw new WSException(e);
    }
  }

  private void queryExcludeHiddenCases(CaseQuery query) {
    List<ICase> hiddenCases =
        executeCaseQuery(CaseQuery.create().where().additionalProperty("HIDE").isNotNull(), 0, -1);

    hiddenCases.forEach(hiddenCase -> query.where().and().caseId().isNotEqual(hiddenCase.getId()));
  }

}
