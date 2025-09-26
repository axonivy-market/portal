package com.axonivy.portal.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.dto.StatisticDrillDownDto;
import com.axonivy.portal.service.StatisticService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ManagedBean
@ViewScoped
public class StatisticDrillDownBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private StatisticDrillDownDto drillDownData;
  private List<Object> drillDownResults;
  private int totalCount = 0;
  private boolean dialogVisible = false;

  public void openDrillDown(String drillDownDataJson) {
    try {
      if (StringUtils.isBlank(drillDownDataJson)) {
        return;
      }

      ObjectMapper mapper = new ObjectMapper();
      this.drillDownData = mapper.readValue(drillDownDataJson, StatisticDrillDownDto.class);
      
      // Fetch drill-down data
      loadDrillDownData();
      
      this.dialogVisible = true;
    } catch (Exception e) {
      e.printStackTrace();
      // Handle error - show message to user
    }
  }

  private void loadDrillDownData() {
    try {
      // Reset pagination
      if (drillDownData != null) {
        drillDownData.setPageNumber(0);
        drillDownData.setPageSize(10);
      }

      Object result = StatisticService.getInstance().getStatisticDrillDownData(drillDownData);
      
      if (result instanceof DrillDownResult) {
        DrillDownResult drillResult = (DrillDownResult) result;
        this.drillDownResults = drillResult.getItems();
        this.totalCount = drillResult.getTotalCount();
      } else if (result instanceof List) {
        this.drillDownResults = (List<Object>) result;
        this.totalCount = drillDownResults.size();
      }
      
    } catch (Exception e) {
      e.printStackTrace();
      // Handle error
    }
  }

  public void onPageChange() {
    loadDrillDownData();
  }

  public void onSort() {
    loadDrillDownData();
  }

  public void viewCaseDetails(String caseId) {
    // Navigate to case details page
    String url = "/ch.ivy.addon.portalkit.ui.jsf/case/" + caseId;
    // Implementation depends on your navigation strategy
  }

  public void startTask(String taskId) {
    // Start task logic
    String url = "/ch.ivy.addon.portalkit.ui.jsf/task/" + taskId + "/start";
    // Implementation depends on your task execution strategy
  }

  public void viewTaskDetails(String taskId) {
    // Navigate to task details page
    String url = "/ch.ivy.addon.portalkit.ui.jsf/task/" + taskId;
    // Implementation depends on your navigation strategy
  }

  public void closeDrillDown() {
    this.dialogVisible = false;
    this.drillDownData = null;
    this.drillDownResults = null;
    this.totalCount = 0;
  }

  // Getters and setters
  public StatisticDrillDownDto getDrillDownData() {
    return drillDownData;
  }

  public void setDrillDownData(StatisticDrillDownDto drillDownData) {
    this.drillDownData = drillDownData;
  }

  public List<Object> getDrillDownResults() {
    return drillDownResults;
  }

  public void setDrillDownResults(List<Object> drillDownResults) {
    this.drillDownResults = drillDownResults;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public boolean isDialogVisible() {
    return dialogVisible;
  }

  public void setDialogVisible(boolean dialogVisible) {
    this.dialogVisible = dialogVisible;
  }

  // Helper class for drill-down results
  public static class DrillDownResult {
    private List<Object> items;
    private int totalCount;
    private int pageNumber;
    private int pageSize;

    // Constructors, getters, and setters
    public DrillDownResult() {}

    public DrillDownResult(List<Object> items, int totalCount, int pageNumber, int pageSize) {
      this.items = items;
      this.totalCount = totalCount;
      this.pageNumber = pageNumber;
      this.pageSize = pageSize;
    }

    public List<Object> getItems() {
      return items;
    }

    public void setItems(List<Object> items) {
      this.items = items;
    }

    public int getTotalCount() {
      return totalCount;
    }

    public void setTotalCount(int totalCount) {
      this.totalCount = totalCount;
    }

    public int getPageNumber() {
      return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
      this.pageNumber = pageNumber;
    }

    public int getPageSize() {
      return pageSize;
    }

    public void setPageSize(int pageSize) {
      this.pageSize = pageSize;
    }
  }
}
