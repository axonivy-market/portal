package ch.ivy.addon.portalkit.vo;

import java.io.Serializable;
import java.util.Date;

import ch.ivy.addon.portalkit.bo.Contact;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

/**
 * A Task ViewObject. Or a Note ViewObject when type is Note. 
 * 
 * @author ptanh
 *
 */
public class TaskVO implements Serializable {
	
	private static final long serialVersionUID = -5579144231963936306L;
	
	/** TYPE_TASK */
	public static final String TYPE_TASK = "Task";
	/** TYPE_NOTE */
	public static final String TYPE_NOTE = "Note";
	
	private long id;
	private WorkflowPriority priority;
	private TaskState state;
	private String name;
	private Date deadline;
	private ISecurityMember editor;
	private Date lastEdit;
	private String type;
	private String typeName;
	private Date edited;
	private String description;
	private Contact contact;
	private String editorName;
	private Number customDecimalField1;
	private Date delayTimestamp;
	
	
	/**
	 * Get the Type of task 
	 * 
	 * @return String : Type of the task 
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Set the Type of task 
	 * 
	 * @param type : Type of the task 
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Get the edited date of the task
	 * 
	 * @return Date : Edited date of the task
	 */
	public Date getEdited() {
		return edited;
	}
	
	/**
	 * Set the edited date of the task
	 * 
	 * @param edited : Edited date of the task
	 */
	public void setEdited(Date edited) {
		this.edited = edited;
	}
	
	/**
	 * Get the description of the task
	 * 
	 * @return String : description of the task
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set the description of the task
	 * 
	 * @param description : description of the task
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Get the id of the task
	 * 
	 * @return long : id of the task
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Set the id of the task
	 * 
	 * @param id : id of the task
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Get the priority of the task
	 * 
	 * @return WorkflowPriority : priority of the task
	 */
	public WorkflowPriority getPriority() {
		return priority;
	}
	
	/**
	 * Set the priority of the task
	 * 
	 * @param priority : priority of the task
	 */
	public void setPriority(WorkflowPriority priority) {
		this.priority = priority;
	}
	
	/**
	 * Get the state of the task
	 * 
	 * @return TaskState : state of the task
	 */
	public TaskState getState() {
		return state;
	}
	
	/**
	 * Set the state of the task
	 * 
	 * @param state : state of the task
	 */
	public void setState(TaskState state) {
		this.state = state;
	}
	
	/**
	 * Get the name of the task
	 * 
	 * @return String : name of the task
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name of the task
	 * 
	 * @param name : name of the task
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the deadline of the task
	 * 
	 * @return Date : deadline of the task
	 */
	public Date getDeadline() {
		return deadline;
	}
	
	/**
	 * Set the deadline of the task
	 * 
	 * @param deadline : deadline of the task
	 */
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	/**
	 * Get the editor of the task
	 * 
	 * @return ISecurityMember : editor of the task
	 */
	public ISecurityMember getEditor() {
		return editor;
	}
	
	/**
	 * Set the editor of the task
	 * 
	 * @param editor : editor of the task
	 */
	public void setEditor(ISecurityMember editor) {
		this.editor = editor;
	}
	
	/**
	 * Get the last edited date of the task
	 * 
	 * @return Date : the last edited date of the task
	 */
	public Date getLastEdit() {
		return lastEdit;
	}
	
	/**
	 * Set the last edited date of the task
	 * 
	 * @param lastEdit : the last edited date of the task
	 */
	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}
	
	/**
	 * Get the typeName date of the task
	 * 
	 * @return String : the typeName date of the task
	 */
	public String getTypeName() {
		return typeName;
	}
	
	/**
	 * Set the typeName date of the task
	 * 
	 * @param typeName : the typeName date of the task
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	/**
	 * Get the editor's Contact date 
	 * 
	 * @return Contact : the editor's Contact  
	 */
	public Contact getContact() {
		return contact;
	}
	
	/**
	 * Set the editor's Contact 
	 * 
	 * @param contact : the editor's Contact  
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	/**
	 * Get the editor's Name  
	 * 
	 * @return String : the editor's Name
	 */
	public String getEditorName() {
		return editorName;
	}
	
	/**
	 * Get the editor's Name  
	 * 
	 * @param editorName : the editor's Name 
	 */
	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}

	/**
	 * @return the value of number
	 */
	public Number getCustomDecimalField1() {
		return customDecimalField1;
	}

	/**
	 * setCustomDecimalField1
	 * @param customDecimalField1  customDecimalField1
	 */
	public void setCustomDecimalField1(Number customDecimalField1) {
		this.customDecimalField1 = customDecimalField1;
	}

	/**
	 * @return Date : the delay timestamp
	 */
	public Date getDelayTimestamp() {
		return delayTimestamp;
	}

	/**
	 * set value for DelayTimestamp
	 * @param delayTimestamp
	 */
	public void setDelayTimestamp(Date delayTimestamp) {
		this.delayTimestamp = delayTimestamp;
	}	
}
