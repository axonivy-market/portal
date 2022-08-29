package com.axonivy.portal.components.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import com.axonivy.portal.components.enums.ProcessType;

@ManagedBean
public class ProcessViewerBean implements Serializable {

  private static final long serialVersionUID = 3619473738758338192L;

  public static String getProcessTypeDisplayName(String processType) {
    ProcessType type = ProcessType.typeOf(processType);
    return type != null ? type.getLabel() : processType;
  }
}