package ch.ivy.ws.addon.persistence;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

public class PortalCaseDAO extends AbstractDAO{

  
  @SuppressWarnings("unchecked")
  public List<String> findCustomVarChar1Fields(String keyword,int limit, List<Long> applications) {
      Query query = generateQueryForField("customVarCharField1", keyword, limit, applications);
      return query.getResultList();
  }

  @SuppressWarnings("unchecked")
  public List<String> findCustomVarChar2Fields(String keyword, int limit, List<Long> applications) {
      Query query = generateQueryForField("customVarCharField2", keyword, limit, applications);
      return query.getResultList();
  }
  
  @SuppressWarnings("unchecked")
  public List<String> findCustomVarChar3Fields(String keyword, int limit, List<Long> applications) {
      Query query = generateQueryForField("customVarCharField3", keyword, limit, applications);
      return query.getResultList();
  }
  
  @SuppressWarnings("unchecked")
  public List<String> findCustomVarChar4Fields(String keyword, int limit, List<Long> applications) {
      Query query = generateQueryForField("customVarCharField4", keyword, limit, applications);
      return query.getResultList();
  }
  
  @SuppressWarnings("unchecked")
  public List<String> findCustomVarChar5Fields(String keyword, int limit, List<Long> applications) {
      Query query = generateQueryForField("customVarCharField5", keyword, limit, applications);
      return query.getResultList();
  }
  
  private Query generateQueryForField(String fieldName ,String keyword, int limit, List<Long> applicationIds) {
    Query query = null;
    String queryString = null;
    if(StringUtils.isNoneBlank(keyword)) {
      queryString = String.format("Select distinct %1$s from PortalSystemIvyCase where lower(%1$s) like lower(concat('%%', :%1$s,'%%')) and applicationId in (:applicationId) order by %1$s", fieldName);
      query = getEM().createQuery(queryString);
      query.setParameter(fieldName, keyword);
      query.setParameter("applicationId", applicationIds);
      //query.setParameter("applicationId", applications.stream().map(IvyApplication::getId).collect(Collectors.toList()));
    }
    else {
      queryString = String.format("Select distinct %1$s from PortalSystemIvyCase where %1$s is not null and applicationId in (:applicationId) order by %1$s", fieldName);
      query = getEM().createQuery(queryString);
      query.setParameter("applicationId", applicationIds);
      //query.setParameter("applicationId", applications.stream().map(IvyApplication::getId).collect(Collectors.toList()));
    }
    if(limit > 0) {
      query.setMaxResults(limit);
    }
    return query;
  }
}
