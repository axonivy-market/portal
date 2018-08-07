package ch.ivy.addon.portalkit.lazy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import ch.ivyteam.ivy.workflow.ICase;

/**
 * This class provide the service to work with case.
 */
public class CaseDataModel {

	private List<ICase> cases;


	/**
	 * Constructor
	 *
	 * @param cases list of {@link ICase}
	 */
	public CaseDataModel(List<ICase> cases){
		this.cases = cases;
	}
	
	/**
	 * Load the list case by create From and To,  list of properties
	 * 
	 * @param createdFrom created form of case. 
	 * @param createdTo created to of case.
	 * @param priorities priorities of case
	 * @return List<ICase> : list case after filter
	 */
	public List<ICase> load(Date createdFrom, Date createdTo, List<String> priorities){

		filter(createdFrom, createdTo, priorities);
		sortAndOrder("applicationName", "asc"); //PrimeFaces DataTable can do it buy itself
		//paginate(first, pageSize);

		return cases;
	}
	
	/**
	 * Get list Case
	 * 
	 * @return List<ICase> : List of case
	 */
	public List<ICase> getCases(){
		return this.cases;
	}
	
	/**
	 * Filter the list case by  application
	 * 
	 * @param apps : List application
	 */
	public void filterByApps(List<String> apps){
		List<ICase> l = new ArrayList<ICase>();
		for(ICase i : this.cases){
			if(apps.contains(i.getApplication().getName())){
				l.add(i);
			}
		}
		this.cases = l;
	}
	
	/**
	 * Filter and set the list Case by  create From and To,  list of properties
	 * 
	 * @param createdFrom
	 * @param createdTo
	 * @param priorities
	 */
	private void filter(Date createdFrom, Date createdTo, List<String> priorities){
		Calendar c = Calendar.getInstance();
		c.set(2012, 1, 1);
		
		Calendar ct = Calendar.getInstance();
		ct.setTime(createdTo);
		ct.add(Calendar.DAY_OF_MONTH, 1);
		
		if(createdFrom!=null&&!createdFrom.toString().equals("")&&createdFrom.compareTo(c.getTime())>0&&
				createdTo!=null&&!createdTo.toString().equals("")&&createdTo.compareTo(c.getTime())>0){
			List<ICase> l = new ArrayList<ICase>();
			for(ICase i : this.cases){
				if(i.getStartTimestamp().compareTo(createdFrom)>=0&&i.getStartTimestamp().compareTo(ct.getTime())<=0){
					l.add(i);
				}
			}
			this.cases = l;
		}		
	}

	/**
	 * Sort the list case by sortField and sortOrder
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
		Collections.sort(this.cases, new Comparator<ICase>(){

			@Override
			public int compare(ICase arg0, ICase arg1) {
				String a = arg0.getApplication().getName();
				String b = arg0.getApplication().getName();

				return order * a.compareTo(b);
			}

		});
	}
	
	/**
	 * Get list of application 
	 * 
	 * @return List<String> : List of application 
	 */
	public List<String> getApplications(){
		List<String> applicationNames = new ArrayList<String>();
		
		for(ICase i : this.cases){
			if(!applicationNames.contains(i.getApplication().getName())){
				applicationNames.add(i.getApplication().getName());
			}
		}
		
		return applicationNames;
	}
}
