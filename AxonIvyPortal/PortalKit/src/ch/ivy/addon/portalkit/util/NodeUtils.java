package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;

import ch.ivy.addon.portalkit.bo.CaseNode;
import ch.ivy.addon.portalkit.bo.TaskNode;

/**
 * Utilities for case tree.
 */
public class NodeUtils {

  private static final String ALL = "All";

  private NodeUtils() {}

  @SuppressWarnings("rawtypes")
  private static boolean isCaseNode(Class nodeClass) {
    return nodeClass.getName() == CaseNode.class.getName();
  }

  @SuppressWarnings("rawtypes")
  public static String getNodeValue(CheckboxTreeNode[] nodes, Class nodeClass) {
    if (nodes == null || nodes.length == 0) {
      return ALL;
    }

    List<String> values = new ArrayList<>();
    boolean isCaseNode = isCaseNode(nodeClass);

    for (CheckboxTreeNode node : nodes) {
      if (node.getParent() != null && !Arrays.asList(nodes).contains(node.getParent())) {
        if (isCaseNode) {
          values.add(((CaseNode) node.getData()).getCategory());
        } else {
          values.add(((TaskNode) node.getData()).getCategory());
        }
      }
    }
    return StringUtils.join(values, ", ");
  }

  @SuppressWarnings("rawtypes")
  public static List<String> getCategoryPaths(CheckboxTreeNode[] nodes, Class nodeClass) {
    List<String> nodePaths = new ArrayList<>();
    boolean isCaseNode = isCaseNode(nodeClass);

    for (CheckboxTreeNode node : nodes) {
      if (isCaseNode) {
        nodePaths.add(((CaseNode) node.getData()).getValue());
      } else {
        nodePaths.add(((TaskNode) node.getData()).getValue());
      }
    }
    return nodePaths;
  }
}
