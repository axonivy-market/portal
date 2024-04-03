package com.axonivy.portal.developerexamples.showcase;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean(name="treeBasicView")
@ViewScoped
public class TreeView implements Serializable {
	private static final long serialVersionUID = 7782960801078745575L;
	private TreeNode<Object> root;
     
    @PostConstruct
    public void init() {
        root = new DefaultTreeNode<Object>("Root", null);
        TreeNode<Object> node0 = new DefaultTreeNode<Object>("Node 0", root);
        TreeNode<Object> node1 = new DefaultTreeNode<Object>("Node 1", root);
         
        TreeNode<Object> node00 = new DefaultTreeNode<Object>("Node 0.0", node0);
        TreeNode<Object> node01 = new DefaultTreeNode<Object>("Node 0.1", node0);
         
        TreeNode<Object> node10 = new DefaultTreeNode<Object>("Node 1.0", node1);
         
        node1.getChildren().add(new DefaultTreeNode<Object>("Node 1.1"));
        node00.getChildren().add(new DefaultTreeNode<Object>("Node 0.0.0"));
        node00.getChildren().add(new DefaultTreeNode<Object>("Node 0.0.1"));
        node01.getChildren().add(new DefaultTreeNode<Object>("Node 0.1.0"));
        node10.getChildren().add(new DefaultTreeNode<Object>("Node 1.0.0"));
        root.getChildren().add(new DefaultTreeNode<Object>("Node 2"));
    }
 
    public TreeNode<Object> getRoot() {
        return root;
    }
}