package com.axonivy.portal.components.ivydata.service;

import com.axonivy.portal.components.ivydata.dto.IvyCaseResultDTO;
import com.axonivy.portal.components.ivydata.searchcriteria.CaseSearchCriteria;

public interface ICaseService {

  IvyCaseResultDTO findCasesByCriteria(CaseSearchCriteria criteria, int startIndex, int count);
  IvyCaseResultDTO countCasesByCriteria(CaseSearchCriteria criteria);
}
