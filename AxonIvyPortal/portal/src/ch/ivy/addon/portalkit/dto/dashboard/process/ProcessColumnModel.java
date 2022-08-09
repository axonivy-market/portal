package ch.ivy.addon.portalkit.dto.dashboard.process;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProcessColumnModel extends ColumnModel implements Serializable {

  private static final long serialVersionUID = 6064393561101193033L;

}
