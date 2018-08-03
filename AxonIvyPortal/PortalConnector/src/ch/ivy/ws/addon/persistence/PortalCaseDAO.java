package ch.ivy.ws.addon.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.data.persistence.IIvyEntityManager;

public class PortalCaseDAO extends AbstractDAO {


  public List<String> findCustomVarChar1Fields(String keyword, int limit, List<Long> applications) {
    return queryForCustomVarChar("customVarCharField1", keyword, limit, applications);
  }

  public List<String> findCustomVarChar2Fields(String keyword, int limit, List<Long> applications) {
    return queryForCustomVarChar("customVarCharField2", keyword, limit, applications);
  }

  public List<String> findCustomVarChar3Fields(String keyword, int limit, List<Long> applications) {
    return queryForCustomVarChar("customVarCharField3", keyword, limit, applications);
  }

  public List<String> findCustomVarChar4Fields(String keyword, int limit, List<Long> applications) {
    return queryForCustomVarChar("customVarCharField4", keyword, limit, applications);
  }

  public List<String> findCustomVarChar5Fields(String keyword, int limit, List<Long> applications) {
    return queryForCustomVarChar("customVarCharField5", keyword, limit, applications);
  }

  @SuppressWarnings("unchecked")
  private List<String> queryForCustomVarChar(String fieldName, String keyword, int limit, List<Long> applicationIds) {
    Query query = null;
    String queryString = null;
    IIvyEntityManager ivyEntityManager = Ivy.persistence().get(getPersistenceIdentifier());
    EntityManager em = ivyEntityManager.createEntityManager();
    try {

      if (StringUtils.isNoneBlank(keyword)) {
        queryString = String.format("Select distinct %1$s from PortalSystemIvyCase where lower(%1$s) like lower(concat('%%', :%1$s,'%%')) and applicationId in (:applicationId) order by %1$s", fieldName);
        query = em.createQuery(queryString);
        query.setParameter(fieldName, keyword);
        query.setParameter("applicationId", applicationIds);
      } else {
        queryString = String.format("Select distinct %1$s from PortalSystemIvyCase where %1$s is not null and applicationId in (:applicationId) order by %1$s", fieldName);
        query = em.createQuery(queryString);
        query.setParameter("applicationId", applicationIds);
      }
      if (limit > 0) {
        query.setMaxResults(limit);
      }
      em.getTransaction().begin();
      List<String> result = query.getResultList();
      em.getTransaction().commit();
      return result;
    } finally {
      em.close();
    }
  }
}
