package com.axonivy.portal.components.bean;

import java.io.Serializable;

import jakarta.inject.Named;

import com.axonivy.portal.components.enums.ProcessType;

@Named
public class ProcessViewerBean implements Serializable {

  private static final long serialVersionUID = 3619473738758338192L;

  public String getProcessTypeDisplayName(String processType) {
    ProcessType type = ProcessType.typeOf(processType);
    return type != null ? type.getLabel() : processType;
  }
}