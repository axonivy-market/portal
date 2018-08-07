package ch.ivy.ws.addon.service;

import static ch.ivy.ws.addon.transformer.IvyCaseTransformer.transformToIvyCase;
import static ch.ivy.ws.addon.transformer.IvyCaseTransformer.transformToIvyCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.math.NumberUtils;

import ch.ivy.ws.addon.WSErrorType;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.AvailableAppsResult;
import ch.ivy.ws.addon.bo.CaseServiceResult;
import ch.ivy.ws.addon.bo.NoteServiceResult;
import ch.ivy.ws.addon.transformer.IvyCaseTransformer;
import ch.ivy.ws.addon.transformer.IvyDocumentTransformer;
import ch.ivy.ws.addon.transformer.IvyNoteTransformer;
import ch.ivy.ws.addon.types.IvyAdditionalProperty;
import ch.ivy.ws.addon.types.IvyCase;
import ch.ivy.ws.addon.util.SessionUtil;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.Binary;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.INote;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.document.IDocument;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseServiceImpl extends AbstractService implements ICaseService {

  @Override
  public CaseServiceResult setAdditionalProperties(final Long caseId,
      final List<IvyAdditionalProperty> additionalProperties) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<CaseServiceResult>() {
        @Override
        public CaseServiceResult call() throws Exception {
          CaseServiceResult result = new CaseServiceResult();
          List<WSException> errors = new ArrayList<WSException>();
          if (caseId != null) {
            CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);

            List<ICase> cases = null;
            try {
              cases = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getResults(query);
            } catch (Exception e) {
              // Wrong case id
              List<Object> userTextParams = new ArrayList<Object>();
              userTextParams.add(caseId);
              errors.add(new WSException(WSErrorType.WARNING, 10022, e, userTextParams, null));
            }

            if (cases.size() > 0 && cases.get(0) != null) {
              ICase c = cases.get(0);

              for (int i = 0; i < additionalProperties.size(); i++) {
                c.setAdditionalProperty(additionalProperties.get(i).getKey(), additionalProperties.get(i).getValue());
              }

            } else {
              // Wrong case id
              List<Object> userTextParams = new ArrayList<Object>();
              userTextParams.add(caseId);
              errors.add(new WSException(WSErrorType.WARNING, 10022, userTextParams, null));
            }
          } else {
            // No case id given
            List<Object> userTextParams = new ArrayList<Object>();
            userTextParams.add("");
            errors.add(new WSException(WSErrorType.WARNING, 10022, userTextParams, null));
          }
          result.setErrors(errors);

          return result;
        }
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
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<CaseServiceResult>() {
        @Override
        public CaseServiceResult call() throws Exception {
          CaseServiceResult result = new CaseServiceResult();
          List<WSException> errors = new ArrayList<WSException>();

          if (caseId != null) {
            CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);

            List<ICase> cases = null;
            try {
              cases = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getResults(query);
            } catch (Exception e) {
              // Wrong case id
              List<Object> userTextParams = new ArrayList<Object>();
              userTextParams.add(caseId);
              errors.add(new WSException(WSErrorType.WARNING, 10022, e, userTextParams, null));
            }

            if (cases.size() > 0 && cases.get(0) != null) {
              ICase c = cases.get(0);
              List<String> additionalPropertyNames = c.getAdditionalPropertyNames();

              List<IvyAdditionalProperty> properties = new ArrayList<IvyAdditionalProperty>();
              new ArrayList<IvyAdditionalProperty>();
              for (String property : additionalPropertyNames) {
                IvyAdditionalProperty p = new IvyAdditionalProperty();
                p.setKey(property);
                p.setValue(c.getAdditionalProperty(property));
                properties.add(p);
              }
              result.setAddtionalProperties(properties);

            } else {
              // Wrong case id
              List<Object> userTextParams = new ArrayList<Object>();
              userTextParams.add(caseId);
              errors.add(new WSException(WSErrorType.WARNING, 10022, userTextParams, null));
            }
          } else {
            // No case id given
            List<Object> userTextParams = new ArrayList<Object>();
            userTextParams.add("");
            errors.add(new WSException(WSErrorType.WARNING, 10022, userTextParams, null));
          }

          result.setErrors(errors);

          return result;
        }
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

    ICase ivyCase = ServerFactory.getServer().getSecurityManager().executeAsSystem(caseCallable);
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
            List<Object> userTextParams = new ArrayList<Object>();
            userTextParams.add(caseId);
            throw new WSException(WSErrorType.WARNING, 10022, userTextParams, null);
          }
          return caseServiceResult;
        }
      });
    } catch (Exception exception) {
      List<Object> userTextParams = new ArrayList<Object>();
      userTextParams.add(caseId);
      throw new WSException(WSErrorType.WARNING, 10022, exception, userTextParams, null);
    }
  }

  @Override
  public CaseServiceResult findAllCases(final String username, final List<String> apps) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<CaseServiceResult>() {
        @Override
        public CaseServiceResult call() throws Exception {

          CaseServiceResult result = new CaseServiceResult();
          List<WSException> errors = new ArrayList<WSException>();
          List<String> availableApps = new ArrayList<String>();

          if (username != null && !username.trim().equals("")) {

            // Check if there is any unavailable application
            AvailableAppsResult aaResult = findAvailableApplicationsAndUsers(apps, username);
            errors.addAll(aaResult.getErrors());
            availableApps.addAll(aaResult.getAvailableApps());

            List<IUser> users = aaResult.getUsers();

            if (users.size() > 0) {
              IvyCaseTransformer caseTransformer = new IvyCaseTransformer();
              CaseQuery query = CaseQuery.create();
              Boolean first = true;
              for (IUser i : users) {
                if (first) {
                  query.where().isInvolved(i);
                } else {
                  query.where().or().isInvolved(i);
                }
                first = false;
              }
              List<IvyCase> cases =
                  caseTransformer.transform(Ivy.wf().getGlobalContext().getCaseQueryExecutor().getResults(query));

              if (cases != null) {
                result.setCases(cases);
              }

            }

          } else {
            errors.add(new WSException(WSErrorType.WARNING, 10029));
          }

          result.setErrors(errors);

          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10001, e);
    }
  }

  @Override
  public CaseServiceResult findByFilter(String filter, CaseState state) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {

        CaseQuery finalQuery = CaseQuery.create();

        if (!Objects.toString(filter, "").isEmpty()) {
          finalQuery = finalQuery.where().and(queryForFilter(filter));
        }
        finalQuery.where().and(queryForState(state));

        return result(transformToIvyCases(executeCaseQuery(finalQuery)), new ArrayList<>());
      });
    } catch (Exception e) {
      throw new WSException(100002, e);
    }
  }

  @Override
  public CaseServiceResult findByFilter(final String username, final String filter, final CaseState state,
      final List<String> apps) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        boolean userNameGiven = Objects.toString(username, "").isEmpty() == false;

        if (!userNameGiven) {
          return result(errors(new WSException(WSErrorType.WARNING, 10029, Arrays.asList(username), null)));
        }

        AvailableAppsResult availableAppsResult = findAvailableApplicationsAndUsers(apps, username);
        List<WSException> errorsOccured = availableAppsResult.getErrors();

        List<IUser> users = availableAppsResult.getUsers();
        boolean userExists = users.size() > 0 && users.get(0) != null;

        if (!userExists) {
          return result(errorsOccured);
        }

        CaseQuery finalQuery = queryForUsers(users);
        if (!Objects.toString(filter, "").isEmpty()) {
          finalQuery = finalQuery.where().and(queryForFilter(filter));
        }
        finalQuery.where().and(queryForState(state));

        return result(transformToIvyCases(executeCaseQuery(finalQuery)), errorsOccured);
      });
    } catch (Exception e) {
      throw new WSException(10002, e);
    }
  }

  private static List<WSException> errors(WSException... errors) {
    return Arrays.asList(errors);
  }

  private static CaseServiceResult result(List<WSException> errors) {
    CaseServiceResult result = new CaseServiceResult();
    result.setErrors(errors);
    return result;
  }

  private static CaseServiceResult result(List<IvyCase> ivyCases, List<WSException> errors) {
    CaseServiceResult result = new CaseServiceResult();
    result.setCases(ivyCases);
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

  private static CaseQuery queryForFilter(String filter) {
    CaseQuery filterStringFieldQuery = filterStringFields(filter);
    Number filterAsNumber = NumberUtils.createNumber(filter);

    return filterAsNumber == null ? filterStringFieldQuery : filterStringFieldQuery.where().or(
        filterNumericFields(filterAsNumber));
  }

  private static CaseQuery queryForKeyword(String keyword) {

    String containingKeyword = String.format("%%%s%%", keyword);

    CaseQuery filterByKeywordQuery =
        CaseQuery.create().where().or().name().isLike(containingKeyword).or().description().isLike(containingKeyword)
            .or().customVarCharField1().isLike(containingKeyword).or().customVarCharField2().isLike(containingKeyword)
            .or().customVarCharField3().isLike(containingKeyword).or().customVarCharField4().isLike(containingKeyword)
            .or().customVarCharField5().isLike(containingKeyword);

    return filterByKeywordQuery;
  }

  private static CaseQuery filterStringFields(String filter) {
    return CaseQuery.create().where().businessMainContactDocDbCode().isLike(filter).or().businessMainContactFolderId()
        .isLike(filter).or().businessMainContactName().isLike(filter).or().businessMainContactType().isLike(filter)
        .or().businessObjectCode().isLike(filter).or().businessObjectDocDbCode().isLike(filter).or()
        .businessObjectFolderId().isLike(filter).or().businessObjectName().isLike(filter).or().customVarCharField1()
        .isLike(filter).or().customVarCharField2().isLike(filter).or().customVarCharField3().isLike(filter).or()
        .customVarCharField4().isLike(filter).or().customVarCharField5().isLike(filter).or().name().isLike(filter).or()
        .processCategoryCode().isLike(filter).or().processCategoryName().isLike(filter).or().processCode()
        .isLike(filter).or().processName().isLike(filter).or().subTypeCode().isLike(filter).or().subTypeName()
        .isLike(filter);
  }

  private static CaseQuery filterNumericFields(Number filter) {
    int intValue = filter.intValue();
    double doubleValue = filter.doubleValue();
    return CaseQuery.create().where().businessCorrespondentId().isEqual(intValue).or().businessMainContactId()
        .isEqual(intValue).or().caseId().isEqual(intValue).or().customDecimalField1().isEqual(doubleValue).or()
        .customDecimalField2().isEqual(doubleValue).or().customDecimalField3().isEqual(doubleValue).or()
        .customDecimalField4().isEqual(doubleValue).or().customDecimalField5().isEqual(doubleValue);
  }

  private static CaseQuery queryForState(CaseState state) {
    CaseQuery stateFieldQuery = CaseQuery.create();

    if (state == null) {
      stateFieldQuery.where().state().isNotEqual(CaseState.DONE).and().state().isNotEqual(CaseState.ZOMBIE).and()
          .state().isNotEqual(CaseState.DESTROYED);
    } else {
      stateFieldQuery.where().state().isEqual(state);
    }
    return stateFieldQuery;
  }

  private static CaseQuery queryForExcludedStates(List<CaseState> excludedStates) {
    CaseQuery stateFieldQuery = CaseQuery.create();

    if (excludedStates == null || excludedStates.isEmpty()) {
      return stateFieldQuery;
    }

    excludedStates.forEach(excludedState -> stateFieldQuery.where().and().state().isNotEqual(excludedState));

    return stateFieldQuery;
  }

  private List<ICase> executeCaseQuery(CaseQuery finalQuery) {
    List<ICase> cases = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getResults(finalQuery);
    return cases;
  }

  @Override
  public NoteServiceResult findNotes(final Integer caseId) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<NoteServiceResult>() {
        @Override
        public NoteServiceResult call() throws Exception {
          NoteServiceResult result = new NoteServiceResult();
          List<WSException> errors = new ArrayList<WSException>();

          if (caseId != null) {
            IvyNoteTransformer noteTransformer = new IvyNoteTransformer();
            CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);
            ICase c = null;
            try {
              c = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getResults(query).get(0);
              // ICase c = Ivy.wf().findCase(caseId);
            } catch (Exception e) {
              List<Object> userText = new ArrayList<Object>();
              userText.add(caseId.toString());
              errors.add(new WSException(WSErrorType.WARNING, 10022, e, userText, null));
            }

            if (c != null) {
              result.setNotes(noteTransformer.transform(c.getNotes()));
            }

          } else {
            List<Object> userText = new ArrayList<Object>();
            userText.add("");
            errors.add(new WSException(WSErrorType.WARNING, 10028, userText, null));
          }

          result.setErrors(errors);

          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10003, e);
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
          List<WSException> errors = new ArrayList<WSException>();

          if (username == null || username.trim().equals("")) {
            // No user specified
            List<Object> userText = new ArrayList<Object>();
            userText.add("");
            errors.add(new WSException(WSErrorType.WARNING, 10029));
          } else if (caseId == null) {
            // No CaseID specified
            List<Object> userText = new ArrayList<Object>();
            userText.add("");
            errors.add(new WSException(WSErrorType.WARNING, 10034, userText, null));
          } else {
            IvyNoteTransformer noteTransformer = new IvyNoteTransformer();

            CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);
            ICase c = null;

            try {
              c = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getResults(query).get(0);
            } catch (Exception e) {
              // Wrong CaseID
              List<Object> userText = new ArrayList<Object>();
              userText.add(caseId.toString());
              errors.add(new WSException(WSErrorType.WARNING, 10034, e, userText, null));
            }

            if (c != null) {
              IWorkflowSession session = null;
              try {
                session = findUserWorkflowSession(username, c.getApplication());
              } catch (Exception e) {
                // Wrong Username
                List<Object> userText = new ArrayList<Object>();
                userText.add(username);
                errors.add(new WSException(WSErrorType.WARNING, 10029, e, userText, null));
              }

              if (session != null) {
                INote note = c.createNote(session, message);
                result.setNewNote(noteTransformer.transform(note));
              }
            }
          }

          result.setErrors(errors);

          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10004, e);
    }
  }

  @Override
  public CaseServiceResult findDocuments(final Integer caseId) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<CaseServiceResult>() {
        @Override
        public CaseServiceResult call() throws Exception {
          CaseServiceResult result = new CaseServiceResult();
          List<WSException> errors = new ArrayList<WSException>();

          if (caseId != null) {

            CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);
            ICase iCase = null;
            try {
              iCase = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getFirstResult(query);
            } catch (Exception e) {
              // Wrong CaseId
              List<Object> userText = new ArrayList<Object>();
              userText.add(caseId);
              errors.add(new WSException(WSErrorType.WARNING, 10022, e, userText, null));
            }

            if (iCase != null) {
              try {
                IvyDocumentTransformer transformer = new IvyDocumentTransformer();
                result.setDocuments(transformer.transform(iCase.documents().getAll()));
              } catch (Exception e) {
                List<Object> userText = new ArrayList<Object>();
                userText.add(e.getMessage());
                errors.add(new WSException(WSErrorType.WARNING, 10043, e, userText, null));
              }
            }
          } else {
            // No caseId given
            List<Object> userText = new ArrayList<Object>();
            userText.add("");
            errors.add(new WSException(WSErrorType.WARNING, 10028, userText, null));
          }

          result.setErrors(errors);

          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10005, e);
    }
  }

  @Override
  public CaseServiceResult uploadDocument(Integer caseId, String documentName, Binary documentContent)
      throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<CaseServiceResult>() {
        @Override
        public CaseServiceResult call() throws Exception {
          CaseServiceResult result = new CaseServiceResult();
          List<WSException> errors = new ArrayList<WSException>();

          if (caseId != null) {
            CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);
            ICase iCase = null;
            try {
              iCase = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getFirstResult(query);
            } catch (Exception e) {
              // Wrong CaseId
              List<Object> userText = new ArrayList<Object>();
              userText.add(caseId);
              errors.add(new WSException(WSErrorType.WARNING, 10022, e, userText, null));
            }

            if (iCase != null) {
              try {
                IvyDocumentTransformer transformer = new IvyDocumentTransformer();
                IDocument document = iCase.documents().add(documentName).write().withContentFrom(documentContent);
                result.setDocument(transformer.transform(document));
              } catch (Exception e) {
                List<Object> userText = new ArrayList<Object>();
                userText.add(documentName);
                errors.add(new WSException(WSErrorType.WARNING, 10042, e, userText, null));
              }
            }
          } else {
            // No caseId given
            List<Object> userText = new ArrayList<Object>();
            userText.add("");
            errors.add(new WSException(WSErrorType.WARNING, 10028, userText, null));
          }

          result.setErrors(errors);

          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10005, e);
    }
  }

  @Override
  public CaseServiceResult downloadDocument(Integer caseId, Integer documentId) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<CaseServiceResult>() {
        @Override
        public CaseServiceResult call() throws Exception {
          CaseServiceResult result = new CaseServiceResult();
          List<WSException> errors = new ArrayList<WSException>();

          if (caseId != null) {
            CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);
            ICase iCase = null;
            try {
              iCase = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getFirstResult(query);
            } catch (Exception e) {
              // Wrong CaseId
              List<Object> userText = new ArrayList<Object>();
              userText.add(caseId);
              errors.add(new WSException(WSErrorType.WARNING, 10022, e, userText, null));
            }

            if (iCase != null) {
              try {
                result.setDocumentContent(iCase.documents().get(documentId).read().asBinary());
              } catch (Exception e) {
                List<Object> userText = new ArrayList<Object>();
                userText.add(e.getMessage());
                errors.add(new WSException(WSErrorType.WARNING, 10043, e, userText, null));
              }
            }
          } else {
            // No caseId given
            List<Object> userText = new ArrayList<Object>();
            userText.add("");
            errors.add(new WSException(WSErrorType.WARNING, 10028, userText, null));
          }

          result.setErrors(errors);

          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10005, e);
    }
  }

  @Override
  public CaseServiceResult removeDocument(Integer caseId, Integer documentId) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<CaseServiceResult>() {
        @Override
        public CaseServiceResult call() throws Exception {
          CaseServiceResult result = new CaseServiceResult();
          List<WSException> errors = new ArrayList<WSException>();

          if (caseId != null) {
            CaseQuery query = CaseQuery.create().where().caseId().isEqual(caseId);
            ICase iCase = null;
            try {
              iCase = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getFirstResult(query);
            } catch (Exception e) {
              // Wrong CaseId
              List<Object> userText = new ArrayList<Object>();
              userText.add(caseId);
              errors.add(new WSException(WSErrorType.WARNING, 10022, e, userText, null));
            }

            if (iCase != null) {
              try {
                iCase.documents().delete(documentId);
              } catch (Exception e) {
                List<Object> userText = new ArrayList<Object>();
                userText.add(e.getMessage());
                errors.add(new WSException(WSErrorType.WARNING, 10043, e, userText, null));
              }
            }
          } else {
            // No caseId given
            List<Object> userText = new ArrayList<Object>();
            userText.add("");
            errors.add(new WSException(WSErrorType.WARNING, 10028, userText, null));
          }

          result.setErrors(errors);

          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10005, e);
    }
  }

  @Override
  public CaseServiceResult findCasesByCriteria(CaseSearchCriteria caseSearchCriteria) throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return ServerFactory
          .getServer()
          .getSecurityManager()
          .executeAsSystem(
              () -> {

                if (caseSearchCriteria.isEmpty()) {
                  return result(noErrors());
                }

                CaseQuery finalQuery = CaseQuery.create();
                String involvedUsername = caseSearchCriteria.getInvolvedUsername();
                if (caseSearchCriteria.hasInvolvedUsername()) {
                  List<String> involvedApplications = caseSearchCriteria.getInvolvedApplications();

                  AvailableAppsResult availableAppsResult =
                      findAvailableApplicationsAndUsers(involvedApplications, involvedUsername);
                  finalQuery.where().and(queryForUsers(availableAppsResult.getUsers()));
                }

                if (caseSearchCriteria.hasKeyword()) {
                  finalQuery.where().and(queryForKeyword(caseSearchCriteria.getKeyword()));
                }

                if (caseSearchCriteria.hasExcludedStates()) {
                  finalQuery.where().and(queryForExcludedStates(caseSearchCriteria.getExcludedStates()));
                }

                return result(transformToIvyCases(executeCaseQuery(finalQuery)), errors);
              });
    } catch (Exception e) {
      throw new WSException(10002, e);
    }
  }

  public CaseServiceResult findCasesWithDestroyPermissionByCriteria(CaseSearchCriteria caseSearchCriteria,
      String username) throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return ServerFactory
          .getServer()
          .getSecurityManager()
          .executeAsSystem(
              () -> {

                if (caseSearchCriteria.isEmpty()) {
                  return result(noErrors());
                }

                CaseQuery finalQuery = CaseQuery.create();
                String involvedUsername = caseSearchCriteria.getInvolvedUsername();
                if (caseSearchCriteria.hasInvolvedUsername()) {
                  List<String> involvedApplications = caseSearchCriteria.getInvolvedApplications();

                  AvailableAppsResult availableAppsResult =
                      findAvailableApplicationsAndUsers(involvedApplications, involvedUsername);
                  finalQuery.where().and(queryForUsers(availableAppsResult.getUsers()));
                }

                if (caseSearchCriteria.hasKeyword()) {
                  finalQuery.where().and(queryForKeyword(caseSearchCriteria.getKeyword()));
                }

                if (caseSearchCriteria.hasExcludedStates()) {
                  finalQuery.where().and(queryForExcludedStates(caseSearchCriteria.getExcludedStates()));
                }

                List<IvyCase> ivyCases = new ArrayList<>();
                for (ICase iCase : executeCaseQuery(finalQuery)) {
                  IvyCase ivyCase = transformToIvyCase(iCase);
                  ivyCase.setCanDestroy(SessionUtil.doesUserHavePermission(iCase.getApplication(), username,
                      IPermission.CASE_DESTROY));
                  ivyCases.add(ivyCase);
                }

                return result(ivyCases, errors);
              });
    } catch (Exception e) {
      throw new WSException(10002, e);
    }
  }
}
