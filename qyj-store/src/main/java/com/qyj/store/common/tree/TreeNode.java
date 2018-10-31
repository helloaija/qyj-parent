package com.qyj.store.common.tree;

import java.util.List;
import java.util.Map;

public class TreeNode {

	public enum TreeNodeState {
		open, closed
	}

	// 节点id
	private Long id;
	// 要显示的文本
	private String text;
	// 节点状态'closed'或'open'
	private TreeNodeState state;
	// 节点是否被选中
	private Boolean checked;
	// 绑定到节点的自定义属性
	private Map<String, String> attributes;
	// 子节点
	private List<TreeNode> children;

	public TreeNode() {
	}

	public TreeNode(Long id, String text) {
		this.id = id;
		this.text = text;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the state
	 */
	public TreeNodeState getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(TreeNodeState state) {
		this.state = state;
	}

	/**
	 * @return the checked
	 */
	public Boolean getChecked() {
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	/**
	 * @return the attributes
	 */
	public Map<String, String> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	/**
	 * @return the children
	 */
	public List<TreeNode> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

}
