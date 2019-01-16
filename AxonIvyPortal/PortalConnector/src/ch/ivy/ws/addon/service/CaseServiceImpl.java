package ch.ivy.ws.addon.service;

import static ch.ivy.ws.addon.transformer.IvyCaseTransformer.transformToIvyCase;
import static ch.ivy.ws.addon.util.HiddenTasksCasesConfig.isHiddenTasksCasesExcluded;




import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;




import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

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
import ch.ivy.ws.addon.util.HiddenTasksCasesConfig;
import ch.ivy.ws.addon.util.SessionUtil;
import ch.ivy.ws.portaldata.model.CustomField;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.Binary;
import ch.ivyteam.ivy.scripting.objects.Recordset;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.INote;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.category.CategoryTree;
import ch.ivyteam.ivy.workflow.document.IDocument;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;




import com.google.gson.Gson;

public class CaseServiceImpl extends AbstractService implements ICaseService {

  @Override
  public CaseServiceResult setAdditionalProperties(final Long caseId,
      final List<IvyAdditionalProperty> additionalProperties) throws WSException {
    try {
      return executeAsSystem(() -> {
        CaseServiceResult result = new CaseServiceResult();
        List<WSException> errors = new ArrayList<WSException>();
        if (caseId != null) {
          CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);

          List<ICase> cases = null;
          try {
            cases = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getResults(query);
          } catch (Exception e) {
            // Wrong case id
            errors.add(createException(WSErrorType.WARNING, 10022, e, caseId));
          }

          if (CollectionUtils.isNotEmpty(cases) && cases.get(0) != null) {
            ICase c = cases.get(0);

            for (int i = 0; i < additionalProperties.size(); i++) {
              c.setAdditionalProperty(additionalProperties.get(i).getKey(), additionalProperties.get(i).getValue());
            }

          } else {
            // Wrong case id
            errors.add(createException(WSErrorType.WARNING, 10022, caseId));
          }
        } else {
          // No case id given
          errors.add(createException(WSErrorType.WARNING, 10022, ""));
        }
        result.setErrors(errors);

        return result;
      });
    } catch (Exception e) {
      if (e instanceof WSException) {
        throw (WSException) e;
      } else {
        throw new WSException(10000, e);
      }
    }
  }
  
  @Override
  public CaseServiceResult getAdditionalProperties(final Long caseId) throws WSException {
    try {
      return executeAsSystem(() -> {
        CaseServiceResult result = new CaseServiceResult();
        List<WSException> errors = new ArrayList<WSException>();

        if (caseId != null) {
          CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);

          List<ICase> cases = null;
          try {
            cases = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getResults(query);
          } catch (Exception e) {
            // Wrong case id
            errors.add(createException(WSErrorType.WARNING, 10022, e, caseId));          
            }

          if (cases != null && cases.size() > 0 && cases.get(0) != null) {
            ICase c = cases.get(0);
            List<String> additionalPropertyNames = c.getAdditionalPropertyNames();

            List<IvyAdditionalProperty> properties = new ArrayList<IvyAdditionalProperty>();
            for (String property : additionalPropertyNames) {
              IvyAdditionalProperty p = new IvyAdditionalProperty();
              p.setKey(property);
              p.setValue(c.getAdditionalProperty(property));
              properties.add(p);
            }
            result.setAddtionalProperties(properties);

          } else {
            // Wrong case id
            errors.add(createException(WSErrorType.WARNING, 10022, caseId));
          }
        } else {
          // No case id given
          errors.add(createException(WSErrorType.WARNING, 10022, ""));
        }

        result.setErrors(errors);

        return result;
      });
    } catch (Exception e) {
      if (e instanceof WSException) {
        throw (WSException) e;
      } else {
        throw new WSException(10000, e);
      }
    }
  }

  private Optional<ICase> findCaseById(final Optional<Integer> caseId) throws Exception {
    Callable<ICase> caseCallable = () -> {
      ICase ivyCase = null;
      if (caseId.isPresent()) {
        CaseQuery caseQuery = CaseQuery.create().where().caseId().isEqual(caseId.get());
        ivyCase = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getFirstResult(caseQuery);
      }
      return ivyCase;
    };

    ICase ivyCase = executeAsSystem(caseCallable);
    return Optional.ofNullable(ivyCase);
  }

  @Override
  public void destroyCase(final Integer caseId) throws WSException {
    try {
      executeAsSystem(() -> {
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
      return executeAsSystem(()->{
        Optional<ICase> optionalCase = findCaseById(Optional.ofNullable(caseId));
        if (optionalCase.isPresent()) {
          IvyCase ivyCase = caseTransformer.transform(optionalCase.get());
          caseServiceResult.setOneCase(ivyCase);
        } else {
          throw createException(WSErrorType.WARNING, 10022, caseId);
        }
        return caseServiceResult;
      });
    } catch (Exception exception) {
      throw createException(WSErrorType.WARNING, 10022, exception, caseId);
    }
  }

  @Override
  public NoteServiceResult findNotes(final Integer caseId, boolean excludeSystemNotes) throws WSException {
    try {
      return executeAsSystem(() -> {
        NoteServiceResult result = new NoteServiceResult();
        List<WSException> errors = new ArrayList<WSException>();

        if (caseId != null) {
          IvyNoteTransformer noteTransformer = new IvyNoteTransformer();
          CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);
          ICase c = null;
          try {
            c = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getResults(query).get(0);
          } catch (Exception e) {
            errors.add(createException(WSErrorType.WARNING, 10022, e, caseId.toString()));
          }

          if (c != null) {
            List<INote> notes = c.getNotes();
            if (excludeSystemNotes) {
              notes =
                  notes.stream().filter(n -> !StringUtils.equals(n.getWritterName(), ivySystemUserName()))
                      .collect(Collectors.toList());
            }
            result.setNotes(noteTransformer.transform(notes));
          }

        } else {
          errors.add(createException(WSErrorType.WARNING, 10028, ""));
        }

        result.setErrors(errors);

        return result;
      });
    } catch (Exception e) {
      throw new WSException(10003, e);
    }
  }

  private String ivySystemUserName() {
    return Ivy.session().getSecurityContext().getSystemUser().getName();
  }

  @Override
  public NoteServiceResult createNote(final String username, final Integer caseId, final String message)
      throws WSException {
    try {
      return executeAsSystem(() -> {
        NoteServiceResult result = new NoteServiceResult();
        List<WSException> errors = new ArrayList<WSException>();

        if (StringUtils.isBlank(username)) {
          // No user specified
          errors.add(new WSException(WSErrorType.WARNING, 10029));
        } else if (caseId == null) {
          // No CaseID specified
          errors.add(createException(WSErrorType.WARNING, 10034, ""));
        } else {
          IvyNoteTransformer noteTransformer = new IvyNoteTransformer();

          CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);
          ICase c = null;

          try {
            c = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getResults(query).get(0);
          } catch (Exception e) {
            // Wrong CaseID
            errors.add(createException(WSErrorType.WARNING, 10034, e, caseId.toString()));
          }

          if (c != null) {
            IWorkflowSession session = null;
            IApplication application = null;
            try {
              application = c.getApplication();
              session = findUserWorkflowSession(username, application);

              if (session != null) {
                INote note = c.createNote(session, message);
                result.setNewNote(noteTransformer.transform(note));
              }
            } catch (Exception e) {
              // Wrong Username
              errors.add(createException(WSErrorType.WARNING, 10029, e, username));
            } finally {
              if (session != null && application != null) {
                ISecurityContext securityContext = application.getSecurityContext();
                securityContext.destroySession(session.getIdentifier());
              }
            }
          }
        }

        result.setErrors(errors);

        return result;
      });
    } catch (Exception e) {
      throw new WSException(10004, e);
    }
  }

  @Override
  public CaseServiceResult findDocuments(final Integer caseId) throws WSException {
    try {
      return executeAsSystem(() -> {
          CaseServiceResult result = new CaseServiceResult();
          List<WSException> errors = new ArrayList<WSException>();

          if (caseId != null) {

            CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);
            ICase iCase = null;
            try {
              iCase = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getFirstResult(query);
            } catch (Exception e) {
              // Wrong CaseId
              errors.add(createException(WSErrorType.WARNING, 10022, e, caseId));
            }

            if (iCase != null) {
              try {
                IvyDocumentTransformer transformer = new IvyDocumentTransformer();
                result.setDocuments(transformer.transform(iCase.documents().getAll()));
              } catch (Exception e) {
                errors.add(createException(WSErrorType.WARNING, 10043, e, e.getMessage()));
              }
            }
          } else {
            // No caseId given
            errors.add(createException(WSErrorType.WARNING, 10028, ""));
          }
          result.setErrors(errors);

          return result;
      });
    } catch (Exception e) {
      throw new WSException(10005, e);
    }
  }

  @Override
  public CaseServiceResult uploadDocument(String username, Integer caseId, String documentName, Binary documentContent)
      throws WSException {
    try {
      return executeAsSystem(() -> {
        CaseServiceResult result = new CaseServiceResult();
        List<WSException> errors = new ArrayList<WSException>();

        if (caseId != null) {
          CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);
          ICase iCase = null;
          try {
            iCase = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getFirstResult(query);
          } catch (Exception e) {
            // Wrong CaseId
            errors.add(createException(WSErrorType.WARNING, 10022, e, caseId));
          }

          if (iCase != null) {
            try {
              IvyDocumentTransformer transformer = new IvyDocumentTransformer();
              IDocument document = iCase.documents().add(documentName).write().withContentFrom(documentContent);
              result.setDocument(transformer.transform(document));
              createNoteWhenUploadDocument(username, caseId, documentName);
            } catch (Exception e) {
              errors.add(createException(WSErrorType.WARNING, 10042, e, documentName));
            }
          }
        } else {
          // No caseId given
          errors.add(createException(WSErrorType.WARNING, 10028, ""));
        }
        result.setErrors(errors);

        return result;
      });
    } catch (Exception e) {
      throw new WSException(10005, e);
    }
  }

  private void createNoteWhenUploadDocument(String username, Integer caseId, String documentName)
      throws WSException {
    List<Object> parameter = Arrays.asList(username, documentName);
    String uploadDocumentMessage =
        Ivy.cms().co("/ch/ivy/addon/portalconnector/document/uploadDocumentNote", parameter);
    createNote(username, caseId, uploadDocumentMessage);
  }

  @Override
  public CaseServiceResult downloadDocument(Integer caseId, Integer documentId) throws WSException {
    try {
      return executeAsSystem(() -> {
        CaseServiceResult result = new CaseServiceResult();
        List<WSException> errors = new ArrayList<WSException>();

        if (caseId != null) {
          CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);
          ICase iCase = null;
          try {
            iCase = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getFirstResult(query);
          } catch (Exception e) {
            // Wrong CaseId
            errors.add(createException(WSErrorType.WARNING, 10022, e, caseId));
          }

          if (iCase != null) {
            try {
              result.setDocumentContent(iCase.documents().get(documentId).read().asBinary());
            } catch (Exception e) {
              errors.add(createException(WSErrorType.WARNING, 10043, e, e.getMessage()));
            }
          }
        } else {
          // No caseId given
          errors.add(createException(WSErrorType.WARNING, 10028, ""));
        }

        result.setErrors(errors);

        return result;
      });
    } catch (Exception e) {
      throw new WSException(10005, e);
    }
  }

  @Override
  public CaseServiceResult removeDocument(String userName, Integer caseId, Integer documentId) throws WSException {
    try {
      return executeAsSystem(() -> {
        CaseServiceResult result = new CaseServiceResult();
        List<WSException> errors = new ArrayList<WSException>();

        if (caseId != null) {
          CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);
          ICase iCase = null;
          try {
            iCase = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getFirstResult(query);
          } catch (Exception e) {
            // Wrong CaseId
            errors.add(createException(WSErrorType.WARNING, 10022, e, caseId));
          }

          if (iCase != null) {
            try {
              IDocument document = iCase.documents().get(documentId);
              iCase.documents().delete(documentId);
              if (document != null) {
                createNoteWhenDeleteDocument(userName, caseId, document);
              }
            } catch (Exception e) {
              errors.add(createException(WSErrorType.WARNING, 10043, e, e.getMessage()));
            }
          }
        } else {
          // No caseId given
          errors.add(createException(WSErrorType.WARNING, 10028, ""));
        }

        result.setErrors(errors);

        return result;
      });
    } catch (Exception e) {
      throw new WSException(10005, e);
    }
  }
  
  private void createNoteWhenDeleteDocument(String userName, Integer caseId, IDocument document)
      throws WSException {
    String documentName = document.getName();
    List<Object> parameter = Arrays.asList(userName, documentName);
    String removeDocumentMessage =
        Ivy.cms().co("/ch/ivy/addon/portalconnector/document/deleteDocumentNote", parameter);
    createNote(userName, caseId, removeDocumentMessage);
  }

  @Override
  public CaseServiceResult findCasesByCriteria(CaseSearchCriteria caseSearchCriteria, Integer startIndex, Integer count)
      throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return executeAsSystem(() -> {
        if (caseSearchCriteria.isEmpty()) {
          return result(noErrors());
        }

        CaseQuery caseQuery = createCaseQuery(caseSearchCriteria);
        queryExcludeHiddenCases(caseQuery, caseSearchCriteria.getInvolvedApplications());
        List<ICase> cases = executeCaseQuery(caseQuery, startIndex, count);
        
        List<IvyCase> ivyCases = new ArrayList<>();
        
        for (ICase iCase : cases) {
          IvyCase ivyCase = transformToIvyCase(iCase);
          final IApplication application = iCase.getApplication();
          final String involvedUsername = caseSearchCriteria.getInvolvedUsername();
          ivyCase.setCanDestroy(SessionUtil.doesUserHavePermission(application, involvedUsername, IPermission.CASE_DESTROY));
          ivyCase.setCanChangeDescription(SessionUtil.doesUserHavePermission(application, involvedUsername, IPermission.CASE_WRITE_DESCRIPTION));
          ivyCase.setCanChangeName(SessionUtil.doesUserHavePermission(application, involvedUsername, IPermission.CASE_WRITE_NAME));
          ivyCases.add(ivyCase);
        }
        
        return result(ivyCases, errors);
      });
    } catch (Exception e) {
      throw new WSException(10002, e);
    }
  }

  @Override
  public CaseServiceResult countCasesByCriteria(CaseSearchCriteria caseSearchCriteria) throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return executeAsSystem(() -> {
        if (caseSearchCriteria.isEmpty()) {
          return result(0, errors);
        }

        CaseQuery caseQuery = createCaseQuery(caseSearchCriteria);
        queryExcludeHiddenCases(caseQuery, caseSearchCriteria.getInvolvedApplications());
        long caseCount = countCases(caseQuery);
        
        return result(caseCount, errors);

      });
    } catch (Exception e) {
      throw new WSException(10016, e);
    }
  }

  @SuppressWarnings("static-access")
  @Override
  public CaseServiceResult findCategories(String jsonQuery, final String username, List<String> apps, String language)
      throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return executeAsSystem(() -> {
        CaseQuery caseQuery = Ivy.wf().getGlobalContext().getCaseQueryExecutor().createCaseQuery();
        if (StringUtils.isNotBlank(jsonQuery)) {
          caseQuery.fromJson(jsonQuery);
        }
        queryExcludeHiddenCases(caseQuery, apps);

        if (StringUtils.isNotBlank(username)) {
          AvailableAppsResult availableAppsResult = findAvailableApplicationsAndUsers(apps, username);
          caseQuery.where().and(queryForUsers(availableAppsResult.getUsers()))
              .and(queryForInvolvedApplications(availableAppsResult.getAvailableApps()));
        } else {
          caseQuery.where().and(queryForInvolvedApplications(apps));
        }
        caseQuery.where().and(
            queryForStates(Arrays.asList(CaseState.CREATED, CaseState.RUNNING, CaseState.DONE)));
        caseQuery.where().and().category().isNotNull().and().category().isNotEqual("Portal");

        CategoryTree categoryTree = CategoryTree.createFor(caseQuery);
        List<CategoryData> categories = new ArrayList<>();
        categoryTree.getAllChildren().forEach(category -> {
          CategoryData categoryData = new CategoryData();
          categoryData.setPath(category.getCategory().getPath(Locale.forLanguageTag(language)));
          categoryData.setRawPath(category.getRawPath());
          categories.add(categoryData);
        });
        return categoryResult(categories, errors);
      });
      
    } catch (Exception e) {
      throw new WSException(10016, e);
    }
  }

  @Override
  public CaseServiceResult analyzeCaseStateStatistic(CaseSearchCriteria caseSearchCriteria) throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return executeAsSystem(() -> {
        CaseQuery caseStateQuery = createCaseQuery(caseSearchCriteria);
        queryExcludeHiddenCases(caseStateQuery, caseSearchCriteria.getInvolvedApplications());

        caseStateQuery.aggregate().countRows().groupBy().state().orderBy().state();

        Recordset recordSet = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getRecordset(caseStateQuery);
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

        return result(caseStateStatistic, errors);
      });
    } catch (Exception e) {
      throw new WSException(10051, e);
    }
  }

  @Override
  public CaseServiceResult analyzeElapsedTimeByCaseCategory(CaseSearchCriteria caseSearchCriteria) throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return executeAsSystem(() -> {
        CaseQuery elapsedTimeQuery = createCaseQuery(caseSearchCriteria);
        queryExcludeHiddenCases(elapsedTimeQuery, caseSearchCriteria.getInvolvedApplications());

        elapsedTimeQuery.where().and().businessRuntime().isNotNull();
        elapsedTimeQuery.aggregate().avgBusinessRuntime().groupBy().category();

        Recordset recordSet = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getRecordset(elapsedTimeQuery);
        HashMap<String, Long> recordMap = new HashMap<String, Long>();
        if (recordSet != null) {
          recordSet.getRecords().forEach(
              record -> {
                String categoryName = record.getField("CATEGORY").toString();
                BigDecimal averageElapsedTime =
                    Optional.ofNullable((BigDecimal) record.getField("AVGBUSINESSRUNTIME")).orElse(
                        new BigDecimal(0));
                long averageElapsedTimeValue = averageElapsedTime.longValue();
                recordMap.put(categoryName, averageElapsedTimeValue);
              });
        }

        ElapsedTimeStatistic elapsedTimeStatistic = new ElapsedTimeStatistic();
        Gson gsonConverter = new Gson();
        String json = "";
        if (recordMap.size() != 0) {
          json = gsonConverter.toJson(recordMap);
        }
        elapsedTimeStatistic.setResult(json);

        return result(elapsedTimeStatistic, errors);
      });
    } catch (Exception e) {
      throw new WSException(10052, e);
    }
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
    List<ICase> cases = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getResults(query, startIndex, count);
    return cases;
  }

  private long countCases(CaseQuery query) {
    return Ivy.wf().getGlobalContext().getCaseQueryExecutor().getCount(query);
  }

  private CaseQuery createCaseQuery(CaseSearchCriteria caseSearchCriteria) throws Exception {
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
      executeAsSystem(() -> {
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

  private void queryExcludeHiddenCases(CaseQuery query, List<String> apps) {
    if (isHiddenTasksCasesExcluded(apps)){
      String customField = HiddenTasksCasesConfig.getHiddenTasksCasesField(apps);
      switch (customField.toLowerCase()){
        case CustomField.CUSTOM_VARCHAR_FIELD1:
          query.where().and().customVarCharField1().isNull();
          break;
        case CustomField.CUSTOM_VARCHAR_FIELD2:
          query.where().and().customVarCharField2().isNull();
          break;
        case CustomField.CUSTOM_VARCHAR_FIELD3:
          query.where().and().customVarCharField3().isNull();
          break;
        case CustomField.CUSTOM_VARCHAR_FIELD4:
          query.where().and().customVarCharField4().isNull();
          break;
        case CustomField.CUSTOM_VARCHAR_FIELD5:
          query.where().and().customVarCharField5().isNull();
          break;
        case CustomField.CUSTOM_DECIMAL_FIELD1:
          query.where().and().customDecimalField1().isNull();
          break;
        case CustomField.CUSTOM_DECIMAL_FIELD2:
          query.where().and().customDecimalField2().isNull();
          break;
        case CustomField.CUSTOM_DECIMAL_FIELD3:
          query.where().and().customDecimalField3().isNull();
          break;
        case CustomField.CUSTOM_DECIMAL_FIELD4:
          query.where().and().customDecimalField4().isNull();
          break;
        case CustomField.CUSTOM_DECIMAL_FIELD5:
          query.where().and().customDecimalField5().isNull();
          break;
        case CustomField.CUSTOM_TIMESTAMP_FIELD1:
          query.where().and().customTimestampField1().isNull();
          break;
        case CustomField.CUSTOM_TIMESTAMP_FIELD2:
          query.where().and().customTimestampField2().isNull();
          break;
        case CustomField.CUSTOM_TIMESTAMP_FIELD3:
          query.where().and().customTimestampField3().isNull();
          break;
        case CustomField.CUSTOM_TIMESTAMP_FIELD4:
          query.where().and().customTimestampField4().isNull();
          break;
        case CustomField.CUSTOM_TIMESTAMP_FIELD5:
          query.where().and().customTimestampField5().isNull();
          break;  
        default: 
          query.where().and().additionalProperty("HIDE").isNull();
          break;
      }
    }
  }

}
