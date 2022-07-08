package com.axonivy.portal.component.ivydata.service;

import com.axonivy.portal.component.ivydata.dto.IvyCaseResultDTO;
import com.axonivy.portal.component.ivydata.searchcriteria.CaseSearchCriteria;

public interface ICaseService {

  IvyCaseResultDTO findCasesByCriteria(CaseSearchCriteria criteria, int startIndex, int count);
  IvyCaseResultDTO countCasesByCriteria(CaseSearchCriteria criteria);
}
