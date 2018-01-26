package ch.ivy.ws.addon.service;

import java.util.List;

import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.CaseServiceResult;
import ch.ivy.ws.addon.bo.NoteServiceResult;
import ch.ivy.ws.addon.types.IvyAdditionalProperty;
import ch.ivy.ws.addon.types.IvyCase;
import ch.ivyteam.ivy.scripting.objects.Binary;

/**
 * Case service provides a set of service methods for Ivy case instances
 * 
 * @author mde
 *
 */
public interface ICaseService {

  /**
   * Find an ivy case by id
   * 
   * @param caseId to look for
   * @return case instance
   */
  public CaseServiceResult findCase(Integer caseId) throws WSException;

  /**
   * Find additional properties for case by case id
   * 
   * @param caseId
   * @return Collection of properties <key, value>
   * @throws WSException
   */
  public CaseServiceResult getAdditionalProperties(Long caseId) throws WSException;

  /**
   * Set additional properties for case by case id
   * 
   * @param caseId
   * @param additionalProperties
   * @return errors
   * @throws WSException
   */
  public CaseServiceResult setAdditionalProperties(Long caseId, List<IvyAdditionalProperty> additionalProperties)
      throws WSException;

  /**
   * Create note for case
   * 
   * @param note
   * @return
   * @throws WSException
   */
  public NoteServiceResult createNote(String username, Integer caseId, String message) throws WSException;


  /**
   * Find all notes for a case
   * 
   * @param caseId
   * @return list of case notes
   * @throws WSException
   */
  public NoteServiceResult findNotes(Integer caseId) throws WSException;

  /**
   * Find all documents for the case
   * 
   * @param caseId
   * @return
   * @throws WSException
   */
  public CaseServiceResult findDocuments(Integer caseId) throws WSException;

  /**
   * Upload the given document into the case
   * 
   * @param caseId
   * @param documentName
   * @param documentContent
   * @return
   * @throws WSException
   */
  public CaseServiceResult uploadDocument(Integer caseId, String documentName, Binary documentContent)
      throws WSException;

  /**
   * Download the given document
   * 
   * @param caseId
   * @param documentId
   * @return
   * @throws WSException
   */
  public CaseServiceResult downloadDocument(Integer caseId, Integer documentId) throws WSException;

  /**
   * Remove the given document
   * 
   * @param caseId
   * @param documentId
   * @return
   * @throws WSException
   */
  public CaseServiceResult removeDocument(Integer caseId, Integer documentId) throws WSException;

  public void destroyCase(final Integer caseId) throws WSException;

  public CaseServiceResult findCasesByCriteria(CaseSearchCriteria caseSearchCriteria, Integer startIndex, Integer count)
      throws WSException;

  public CaseServiceResult countCasesByCriteria(CaseSearchCriteria caseSearchCriteria) throws WSException;
  
  public CaseServiceResult findCategories(String jsonQuery, final String username, List<String> apps, String language) throws WSException;

  /**
   * Get statistic data by case state
   * 
   * @param caseSearchCriteria
   * @return statistic data
   * @throws WSException
   */
  public CaseServiceResult analyzeCaseStateStatistic(CaseSearchCriteria caseSearchCriteria) throws WSException;

  /**
   * Get statistic data by case category's elapsed time
   * 
   * @param caseSearchCriteria
   * @return statistic data
   * @throws WSException
   */
  public CaseServiceResult analyzeElapsedTimeByCaseCategory(CaseSearchCriteria caseSearchCriteria) throws WSException;

  void saveCase(IvyCase ivyCase) throws WSException;
}
