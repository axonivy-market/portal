package ch.ivy.gawfs.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import gawfs.ApprovalTaskResult;

@ManagedBean
@ViewScoped
public class ApprovalResultBean implements Serializable {

  private static final long serialVersionUID = -5526872224029002403L;
  private List<List<ApprovalTaskResult>> approvalResultGroups;
  
  public void groupingApprovalResult(List<ApprovalTaskResult> approvalResults) {
    List<List<ApprovalTaskResult>> groups  = new ArrayList<>();
    Map<Integer, List<ApprovalTaskResult>> approvalResultMap = 
        approvalResults.stream().collect(Collectors.groupingBy(ApprovalTaskResult::getIndex));
    for(Map.Entry<Integer, List<ApprovalTaskResult>> entry : approvalResultMap.entrySet()) {
      groups.add(entry.getValue());
    }
    this.approvalResultGroups = groups;
  }

  public List<List<ApprovalTaskResult>> getApprovalResultGroups() {
    return approvalResultGroups;
  }

  public void setApprovalResultGroups(List<List<ApprovalTaskResult>> approvalResultGroups) {
    this.approvalResultGroups = approvalResultGroups;
  }
  
}
