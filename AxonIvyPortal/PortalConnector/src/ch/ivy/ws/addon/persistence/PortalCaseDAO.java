package ch.ivy.ws.addon.persistence;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

public class PortalCaseDAO extends AbstractDAO{

  
  @SuppressWarnings("unchecked")
  public List<String> findCustomVarChar1Fields(String keyword,int limit) {
      Query query = null;
      if(StringUtils.isNoneBlank(keyword)) {
        query = getEM().createQuery("Select distinct customVarCharField1 from PortalSystemIvyCase where lower(customVarCharField1) like lower(concat('%', :customVarCharField1,'%')) order by customVarCharField1");
        query.setParameter("customVarCharField1", keyword);
      }
      else {
        query = getEM().createQuery("Select distinct customVarCharField1 from PortalSystemIvyCase where customVarCharField1 is not null order by customVarCharField1");
      }
      if(limit > 0) {
        query.setMaxResults(limit);
      }
      return query.getResultList();
  }
  
  @SuppressWarnings("unchecked")
  public List<String> findCustomVarChar2Fields(String keyword, int limit) {
      Query query = null;
      if(StringUtils.isNoneBlank(keyword)) {
        query = getEM().createQuery("Select distinct customVarCharField2 from PortalSystemIvyCase where lower(customVarCharField2) like lower(concat('%', :customVarCharField2,'%')) order by customVarCharField2");
        query.setParameter("customVarCharField2", keyword);
      }
      else {
        query = getEM().createQuery("Select distinct customVarCharField2 from PortalSystemIvyCase where customVarCharField2 is not null order by customVarCharField2");
      }
      if(limit > 0) {
        query.setMaxResults(limit);
      }
      return query.getResultList();
  }
  
  @SuppressWarnings("unchecked")
  public List<String> findCustomVarChar3Fields(String keyword, int limit) {
      Query query = null;
      if(StringUtils.isNoneBlank(keyword)) {
        query = getEM().createQuery("Select distinct customVarCharField3 from PortalSystemIvyCase where lower(customVarCharField3) like lower(concat('%', :customVarCharField3,'%') order by customVarCharField3");
        query.setParameter("customVarCharField3", keyword);
      }
      else {
        query = getEM().createQuery("Select distinct customVarCharField3 from PortalSystemIvyCase where customVarCharField3 is not null order by customVarCharField3");
      }
      if(limit > 0) {
        query.setMaxResults(limit);
      }
      return query.getResultList();
  }
  
  @SuppressWarnings("unchecked")
  public List<String> findCustomVarChar4Fields(String keyword, int limit) {
      Query query = null;
      if(StringUtils.isNoneBlank(keyword)) {
        query = getEM().createQuery("Select distinct customVarCharField4 from PortalSystemIvyCase where lower(customVarCharField4) like lower(concat('%', :customVarCharField4,'%') order by customVarCharField4");
        query.setParameter("customVarCharField4", keyword);
      }
      else {
        query = getEM().createQuery("Select distinct customVarCharField4 from PortalSystemIvyCase where customVarCharField4 is not null order by customVarCharField4");
      }
      if(limit > 0) {
        query.setMaxResults(limit);
      }
      return query.getResultList();
  }
  
  @SuppressWarnings("unchecked")
  public List<String> findCustomVarChar5Fields(String keyword, int limit) {
      Query query = null;
      if(StringUtils.isNoneBlank(keyword)) {
        query = getEM().createQuery("Select distinct customVarCharField5 from PortalSystemIvyCase where lower(customVarCharField5) like lower(concat('%', :customVarCharField5,'%') order by customVarCharField5");
        query.setParameter("customVarCharField5", keyword);
      }
      else {
        query = getEM().createQuery("Select distinct customVarCharField5 from PortalSystemIvyCase where customVarCharField5 is not null order by customVarCharField5");
      }
      if(limit > 0) {
        query.setMaxResults(limit);
      }
      return query.getResultList();
  }
}
