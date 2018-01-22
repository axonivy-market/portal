package ch.ivy.addon.portalkit.service;

import java.util.List;

import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.support.CaseQueryCriteria;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.OrderByColumnQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CaseQueryService {

    private static CaseQueryService service = new CaseQueryService();

    private CaseQueryService() {
    }

    public static CaseQueryService service() {
        return service;
    }

    public CaseQuery createQuery(CaseQueryCriteria criteria) {
        CaseQuery finalQuery = CaseQuery.create();
        criteria.setNewQueryCreated(criteria.isNewQueryCreated()
                || criteria.getCaseQuery() == null || criteria.hasTaskId()
                || criteria.hasCaseId());

        if (!criteria.isNewQueryCreated()) {
            finalQuery = CaseQuery.fromJson(criteria.getCaseQuery().asJson());
        }

        if (criteria.hasIncludedStates()) {
            finalQuery.where().and(queryForStates(criteria.getIncludedStates()));
        }

        if (criteria.hasTaskId()) {
            finalQuery.where().and(queryForTaskId(criteria.getTaskId()));
        }

        if (criteria.hasCaseId()) {
            finalQuery.where().and().caseId().isEqual(criteria.getCaseId());
        }

        if (criteria.hasKeyword()) {
            finalQuery.where().and(queryForKeyword(criteria.getKeyword()));
        }
        
        if (criteria.hasCategory()) {
          finalQuery.where().and(queryForCategory(criteria.getCategory()));
        }
        
        CaseSortingQueryAppender appender = new CaseSortingQueryAppender(finalQuery);
        finalQuery = appender.appendSorting(criteria).toQuery();
        return finalQuery;
    }

    private CaseQuery queryForStates(List<CaseState> states) {
        CaseQuery stateFieldQuery = CaseQuery.create();
        IFilterQuery filterQuery = stateFieldQuery.where();
        for (CaseState state : states) {
          filterQuery.or().state().isEqual(state);
        }
        return stateFieldQuery;
    }
    
    private CaseQuery queryForTaskId(Long taskId) {
        CaseQuery query = CaseQuery.create();
        query.where().and().tasks(TaskQuery.create().where().taskId().isEqual(taskId));
        return query;
    }
    
    private CaseQuery queryForKeyword(String keyword) {
        String containingKeyword = String.format("%%%s%%", keyword);

        CaseQuery filterByKeywordQuery =
            CaseQuery.create().where().or().name().isLikeIgnoreCase(containingKeyword).or().description().isLikeIgnoreCase(containingKeyword)
                .or().customVarCharField1().isLikeIgnoreCase(containingKeyword).or().customVarCharField2().isLikeIgnoreCase(containingKeyword)
                .or().customVarCharField3().isLikeIgnoreCase(containingKeyword).or().customVarCharField4().isLikeIgnoreCase(containingKeyword)
                .or().customVarCharField5().isLikeIgnoreCase(containingKeyword);

        try {
          long idKeyword = Long.parseLong(keyword);
          filterByKeywordQuery.where().or().caseId().isEqual(idKeyword);
        } catch (NumberFormatException e) {
        }
        return filterByKeywordQuery;
    }
    
    private CaseQuery queryForCategory(String keyword) {
      String startingWithCategory = String.format("%s%%", keyword);
      CaseQuery filterByCategoryQuery = CaseQuery.create().where().category().isLike(startingWithCategory);

      return filterByCategoryQuery;
    }
    
    private static final class CaseSortingQueryAppender {

        private CaseQuery query;

        public CaseSortingQueryAppender(CaseQuery query) {
          this.query = query;
        }

        public CaseQuery toQuery() {
          return query;
        }

        public CaseSortingQueryAppender appendSorting(CaseQueryCriteria criteria) {
          appendSortByNameIfSet(criteria);
          appendSortByIdIfSet(criteria);
          appendSortByStartTimeIfSet(criteria);
          appendSortByEndTimeIfSet(criteria);
          appendSortByCreatorIfSet(criteria);
          appendSortByStateIfSet(criteria);
          return this;
        }

        private void appendSortByNameIfSet(CaseQueryCriteria criteria) {
          if (!CaseSortField.NAME.toString().equalsIgnoreCase(criteria.getSortField())) {
            return;
          }
          OrderByColumnQuery orderByName = query.orderBy().name();
          if (criteria.isSortDescending()) {
            orderByName.descending();
          }
        }

        private void appendSortByIdIfSet(CaseQueryCriteria criteria) {
          if (criteria.getSortField() == null) {
            query.orderBy().caseId().descending();
            return;
          }
          if (!CaseSortField.ID.toString().equalsIgnoreCase(criteria.getSortField())) {
            return;
          }
          OrderByColumnQuery orderByName = query.orderBy().caseId();
          if (criteria.isSortDescending()) {
            orderByName.descending();
          }
        }

        private void appendSortByStartTimeIfSet(CaseQueryCriteria criteria) {
          if (!CaseSortField.START_TIME.toString().equalsIgnoreCase(criteria.getSortField())) {
            return;
          }
          OrderByColumnQuery orderByName = query.orderBy().startTimestamp();
          if (criteria.isSortDescending()) {
            orderByName.descending();
          }
        }

        private void appendSortByEndTimeIfSet(CaseQueryCriteria criteria) {
          if (!CaseSortField.END_TIME.toString().equalsIgnoreCase(criteria.getSortField())) {
            return;
          }
          OrderByColumnQuery orderByName = query.orderBy().endTimestamp();
          if (criteria.isSortDescending()) {
            orderByName.descending();
          }
        }

        private void appendSortByCreatorIfSet(CaseQueryCriteria criteria) {
          if (!CaseSortField.CREATOR.toString().equalsIgnoreCase(criteria.getSortField())) {
            return;
          }
          OrderByColumnQuery orderByName = query.orderBy().creatorUserDisplayName();
          if (criteria.isSortDescending()) {
            orderByName.descending();
          }
        }

        private void appendSortByStateIfSet(CaseQueryCriteria criteria) {
          if (!CaseSortField.STATE.toString().equalsIgnoreCase(criteria.getSortField())) {
            return;
          }
          OrderByColumnQuery orderByName = query.orderBy().state();
          if (criteria.isSortDescending()) {
            orderByName.descending();
          }
        }
   }

}
