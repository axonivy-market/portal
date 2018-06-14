package ch.ivy.ws.addon.persistence;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

public class PortalCaseDAO extends AbstractDAO{

  
  @SuppressWarnings("unchecked")
  public List<String> findCustomVarChar1Fields(String keyword,int limit) {
      Query query = generateQueryForCustomVarCharFields("customVarCharField1", keyword, limit);
      return query.getResultList();
  }

  @SuppressWarnings("unchecked")
  public List<String> findCustomVarChar2Fields(String keyword, int limit) {
      Query query = generateQueryForCustomVarCharFields("customVarCharField2", keyword, limit);
      return query.getResultList();
  }
  
  @SuppressWarnings("unchecked")
  public List<String> findCustomVarChar3Fields(String keyword, int limit) {
      Query query = generateQueryForCustomVarCharFields("customVarCharField3", keyword, limit);
      return query.getResultList();
  }
  
  @SuppressWarnings("unchecked")
  public List<String> findCustomVarChar4Fields(String keyword, int limit) {
      Query query = generateQueryForCustomVarCharFields("customVarCharField4", keyword, limit);
      return query.getResultList();
  }
  
  @SuppressWarnings("unchecked")
  public List<String> findCustomVarChar5Fields(String keyword, int limit) {
      Query query = generateQueryForCustomVarCharFields("customVarCharField5", keyword, limit);
      return query.getResultList();
  }
  
  private Query generateQueryForCustomVarCharFields(String customVarCharColumn ,String keyword, int limit) {
    Query query = null;
    String queryString = null;
    if(StringUtils.isNoneBlank(keyword)) {
      queryString = String.format("Select distinct %1$s from PortalSystemIvyCase where lower(%1$s) like lower(concat('%%', :%1$s,'%%')) order by %1$s", customVarCharColumn);
      query = getEM().createQuery(queryString);
      query.setParameter(customVarCharColumn, keyword);
    }
    else {
      queryString = String.format("Select distinct %1$s from PortalSystemIvyCase where %1$s is not null order by %1$s", customVarCharColumn);
      query = getEM().createQuery(queryString);
    }
    if(limit > 0) {
      query.setMaxResults(limit);
    }
    return query;
  }
}
