package ch.ivy.addon.portalkit.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Data class for TreeNode of Case Tree.
 */
public class CaseNode extends MainMenuNode implements Serializable {

  private static final long serialVersionUID = -7458740651177437962L;

  private List<RemoteCase> cases;

  /**
   * Constructor
   *
   */
  public CaseNode() {
    this.cases = new ArrayList<>();
  }

  /**
   * Constructor
   *
   * @param iCase case to add to node.
   */
  public CaseNode(RemoteCase iCase) {
    this.cases = new ArrayList<>();
    this.cases.add(iCase);
  }

  /**
   * Add a case to list cases.
   *
   * @param aCase case to add.
   */
  public void addCase(RemoteCase aCase) {
    this.cases.add(aCase);
  }

  /**
   * Gets the cases
   *
   * @return Returns the cases
   */
  public List<RemoteCase> getCases() {
    return cases;
  }

  /**
   * Sets the cases
   *
   * @param cases The cases to set
   */
  public void setCases(List<RemoteCase> cases) {
    this.cases = cases;
  }
}
