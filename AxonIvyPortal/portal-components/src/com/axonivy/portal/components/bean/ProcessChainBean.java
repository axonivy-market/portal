package com.axonivy.portal.components.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;

@ManagedBean
@ViewScoped
public class ProcessChainBean implements Serializable {

  private static final long serialVersionUID = 1L;

  public List<String> getProcessIndexes(List<String> processSteps) {
    List<String> stepIndexes = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(processSteps)) {
      for (int i = 0; i < processSteps.size(); i++) {
        stepIndexes.add(String.valueOf(i));
      }
    }
    return stepIndexes;
  }
}
