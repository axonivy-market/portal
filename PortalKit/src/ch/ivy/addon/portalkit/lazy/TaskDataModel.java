package ch.ivy.addon.portalkit.lazy;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import ch.ivyteam.ivy.workflow.ITask;

/**
 * This class provide the service to work with task
 */
public class TaskDataModel {
	
	private List<ITask> tasks;
	
	
	/**
	 * Constructor
	 *
	 * @param tasks list of task.
	 */
	public TaskDataModel(List<ITask> tasks){
		this.tasks = tasks;
	}
	
	/**
	 * Load the list task by create From and To list of priorities
	 * 
	 * @param createdFrom start rank to get task
	 * @param createdTo end rank to get task
	 * @param priorities list of priority to get task
	 * @return List<ITask> : list task after filter
	 */
	public List<ITask> load(Date createdFrom, Date createdTo, List<String> priorities){
		
				
		filter(createdFrom, createdTo, priorities);
		
		sortAndOrder("applicationName", "asc"); //Must be solved in JSF by dataTable
		//paginate(first, pageSize);
		
		return tasks;
		
	}
	
	/**
	 * Get list Task
	 * 
	 * @return List<ITask>
	 */
	public List<ITask> getTasks(){
		return this.tasks;
	}
	
	/**
	 * Filter the list task by  application
	 * 
	 * @param apps : List application
	 */
	public void filterByApps(List<String> apps){
		List<ITask> l = new ArrayList<ITask>();
		for(ITask i : this.tasks){
			if(apps.contains(i.getApplication().getName())){
				l.add(i);
			}
		}
		this.tasks = l;
	}
	
	/**
	 * Filter and set the list task by  create From and To,  list of properties
	 * 
	 * @param createdFrom
	 * @param createdTo
	 * @param priorities
	 */
	private void filter(Date createdFrom, Date createdTo, List<String> priorities){
		Calendar c = Calendar.getInstance();
		c.set(2012, 1, 1);
		
		//To get tasks of include createdTo
		Calendar ct = Calendar.getInstance();
		ct.setTime(createdTo);
		ct.add(Calendar.DAY_OF_MONTH, 1);
		
		if(createdFrom!=null&&!createdFrom.toString().equals("")&&createdFrom.compareTo(c.getTime())>0&&
			createdTo!=null&&!createdTo.toString().equals("")&&createdTo.compareTo(c.getTime())>0){
			List<ITask> l = new ArrayList<ITask>();
			for(ITask i : this.tasks){
				if(priorities.contains(i.getPriority().toString())){
					if(i.getStartTimestamp().compareTo(createdFrom)>=0&&i.getStartTimestamp().compareTo(ct.getTime())<=0)
					l.add(i);
				}
			}
			this.tasks = l;
		}
	}
	
	/**
	 * Sort the list task by sortField and sortOrder
	 * 
	 * @param sortField : Choose the field to sort
	 * @param sortOrder : Order to sort (ascending or  descending)
	 */
	private void sortAndOrder(final String sortField, String sortOrder){
		
		final int order; 
		
		if(sortOrder.toLowerCase().equals("ascending")||sortOrder.toLowerCase().equals("asc")){
			order = 1;
		} else if(sortOrder.toLowerCase().equals("descending")||sortOrder.toLowerCase().equals("desc")){
			order = -1;
		} else{
			order = 0;
		}
		
		if(sortField.equals("")){}
		else if(sortField.equals("taskId")){
			Collections.sort(this.tasks, new Comparator<ITask>(){

				@Override
				public int compare(ITask arg0, ITask arg1) {
					Number a = arg0.getId();
					Number b = arg1.getId();
					
					return order * a.toString().compareTo(b.toString());
				}
				
			});	
		}
		else if(sortField.equals("taskPriority")){
			Collections.sort(this.tasks, new Comparator<ITask>(){

				@Override
				public int compare(ITask arg0, ITask arg1) {
					String a = arg0.getPriority().toString();
					String b = arg1.getPriority().toString();
					
					return order * a.toString().compareTo(b.toString());
				}
				
			});		
		}
		else if(sortField.equals("taskStatus")){
			Collections.sort(this.tasks, new Comparator<ITask>(){

				@Override
				public int compare(ITask arg0, ITask arg1) {
					String a = arg0.getState().toString();
					String b = arg1.getState().toString();
					
					return order * a.toString().compareTo(b.toString());
				}
				
			});
		}
		else if(sortField.equals("taskName")){
			Collections.sort(this.tasks, new Comparator<ITask>(){

				@Override
				public int compare(ITask arg0, ITask arg1) {
					String a = arg0.getName().toString();
					String b = arg1.getName().toString();
					
					return order * a.toString().compareTo(b.toString());
				}
				
			});
		}
		else if(sortField.equals("responsible")){
			Collections.sort(this.tasks, new Comparator<ITask>(){

				@Override
				public int compare(ITask arg0, ITask arg1) {
					String a = arg0.getActivator().getDisplayName().toString();
					String b = arg1.getActivator().getDisplayName().toString();
					
					return order * a.toString().compareTo(b.toString());
				}
				
			});
		}
		else if(sortField.equals("created")){
			Collections.sort(this.tasks, new Comparator<ITask>(){

				@Override
				public int compare(ITask arg0, ITask arg1) {
					Date a = arg0.getStartTimestamp();
					Date b = arg1.getStartTimestamp();
					
					return order * a.toString().compareTo(b.toString());
				}
				
			});
		}
		else if(sortField.equals("applicationName")){
			Collections.sort(this.tasks, new Comparator<ITask>(){
				
				@Override
				public int compare(ITask arg0, ITask arg1) {
					String a = arg0.getApplication().getName();
					String b = arg1.getApplication().getName();
					
					return order * a.compareTo(b);
				}
			});
		}
		
	}
	
	/**
	 * Get list of application name.
	 * 
	 * @return List<String> : List of application name
	 */
	public List<String> getApplications(){
		List<String> applicationNames = new ArrayList<String>();
		
		for(ITask i : this.tasks){
			if(!applicationNames.contains(i.getApplication().getName())){
				applicationNames.add(i.getApplication().getName());
			}
		}
		
		return applicationNames;
	}
	
	
	/**
	 * Paging the task list
	 * 
	 * @param first : index to get the first record
	 * @param pageSize : 
	 */
	@SuppressWarnings("unused")
	private void paginate(int first, int pageSize){
		if(pageSize>this.tasks.size()){
			pageSize=this.tasks.size();
		}
		this.tasks = this.tasks.subList(first, pageSize);
		
	}
}
