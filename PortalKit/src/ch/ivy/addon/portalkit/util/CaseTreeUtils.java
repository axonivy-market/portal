package ch.ivy.addon.portalkit.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import ch.ivy.addon.portalkit.bo.CaseNode;
import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivyteam.ivy.environment.Ivy;

/**
 * Utilities for case tree.
 *
 * @author maonguyen
 */
public class CaseTreeUtils {

  private final static String DELIMITER = "/";

  /**
   * Convert field CustomVarCharField5 of cases to TreeNode
   * 
   * @param cases : list of case
   * @return {@link TreeNode}
   */
  public static TreeNode convertToTreeNode(List<RemoteCase> cases) {
    TreeNode root = new DefaultTreeNode();

    CaseNode realRootData = new CaseNode();
    realRootData.setValue(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseTree/allCases"));
    TreeNode realRootNode = new DefaultTreeNode(realRootData, root);
    realRootNode.setExpanded(true);
    TreeNode current = realRootNode;

    for (RemoteCase aCase : cases) {
      if (!StringUtils.isEmpty(aCase.getCustomVarCharField5())) {
        // parse CustomVarCharField5, and build node
        for (String nodeLabel : aCase.getCustomVarCharField5().split(DELIMITER)) {
          current = buildCaseNode(current, nodeLabel, aCase);
        }
        current = realRootNode;
      } else {
        // path is empty , add case to node
        // realRootData.addCase(aCase);
      }
    };

    return root;
  }


  /**
   * Build case node for current case. If node name exist, return node. Else add new node.
   * 
   * @param currentNode
   * @param nodeName
   * @param currentCase
   * @return {@link TreeNode}
   */
  private static TreeNode buildCaseNode(TreeNode currentNode, String nodeName, RemoteCase currentCase) {
    for (TreeNode node : currentNode.getChildren()) {
      CaseNode nodeData = (CaseNode) node.getData();
      if (nodeData.getValue().equals(nodeName)) {
        // nodeData.addCase(currentCase);
        return node;
      }
    }
    CaseNode currentNodeData = (CaseNode) currentNode.getData();
    CaseNode caseNodeData = null;
    // FIXME: OLD IMPLEMENTATION IN 5.0 (CONSIDER)
    // if (currentNodeData.getNodePath() == null) {
    // caseNodeData = new CaseNode(nodeName, currentCase);
    // currentNode.setExpanded(true);
    // } else {
    // caseNodeData = new CaseNode(nodeName, currentCase,
    // currentNodeData.getNodePath());
    // }

    TreeNode newNode = new DefaultTreeNode(caseNodeData, currentNode);
    return newNode;
  }
}
