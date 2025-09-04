package com.axonivy.portal.developerexamples.bean;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.developerexamples.dto.SideStepExampleDTO;

import ch.ivyteam.ivy.environment.Ivy;

@ViewScoped
@ManagedBean
public class SideStepExampleBean implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  public boolean isVisible(List<SideStepExampleDTO> tasks) {
    if (CollectionUtils.isEmpty(tasks)) {
      return false;
    }
    for (SideStepExampleDTO dto : tasks) {
      if (dto.getOriginalTaskUuid().equals(Ivy.wfTask().uuid())) {
        return true;
      }
    }
    return false;
  }
  
  public List<SideStepExampleDTO> filterSideStepTaskList(List<SideStepExampleDTO> tasks){
    return tasks.stream()
        .filter(task -> task.getOriginalTaskUuid().equals(Ivy.wfTask().uuid()))
        .collect(Collectors.toList());
  }
  
}
