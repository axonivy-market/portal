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
	private TreeNode<String> root;
     
    @PostConstruct
    public void init() {
        root = new DefaultTreeNode<String>("Root", null);
        TreeNode<String> node0 = new DefaultTreeNode<String>("Node 0", root);
        TreeNode<String> node1 = new DefaultTreeNode<String>("Node 1", root);
         
        TreeNode<String> node00 = new DefaultTreeNode<String>("Node 0.0", node0);
        TreeNode<String> node01 = new DefaultTreeNode<String>("Node 0.1", node0);
         
        TreeNode<String> node10 = new DefaultTreeNode<String>("Node 1.0", node1);
         
        node1.getChildren().add(new DefaultTreeNode<String>("Node 1.1"));
        node00.getChildren().add(new DefaultTreeNode<String>("Node 0.0.0"));
        node00.getChildren().add(new DefaultTreeNode<String>("Node 0.0.1"));
        node01.getChildren().add(new DefaultTreeNode<String>("Node 0.1.0"));
        node10.getChildren().add(new DefaultTreeNode<String>("Node 1.0.0"));
        root.getChildren().add(new DefaultTreeNode<String>("Node 2"));
    }
 
    public TreeNode<String> getRoot() {
        return root;
    }
}