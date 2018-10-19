package ch.ivyteam.ivy.project.portal.examples.component.customize;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
import ch.ivy.addon.portalkit.datamodel.SearchResultsDataModel;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.data.persistence.IIvyEntityManager;
import ch.ivyteam.ivy.process.data.persistence.IIvyQuery;
import ch.ivyteam.ivy.project.portal.examples.Employee;
import ch.ivyteam.ivy.project.portal.examples.component.customize.cases.CustomizedCaseLazyDataModel;

public class CustomizedSearchResultsDataModel extends SearchResultsDataModel {

  private List<Employee> employees;
  
  public CustomizedSearchResultsDataModel() {
    super();
    IIvyQuery query = persistence().createQuery("Delete from Employee");
    query.executeUpdate();
    employees = persistence().findAll(Employee.class);
    if (CollectionUtils.isEmpty(employees)) {
      initEmployees();
    }
  }

  @Override
  public void search() {
    employees =
        persistence().findAll(Employee.class).stream().filter(this::filterEmployeeByName)
            .collect(Collectors.toList());
  }
  
  @Override
  protected TaskLazyDataModel initTaskDataModel() {
    return new CustomizedTaskLazyDataModel(TASK_WIDGET_COMPONENT_ID);
  }
  
  @Override
  protected CaseLazyDataModel initCaseDataModel() {
    return new CustomizedCaseLazyDataModel(CASE_WIDGET_COMPONENT_ID);
  }
  
  private boolean filterEmployeeByName(Employee employee) {
    return StringUtils.containsIgnoreCase(employee.getFirstName(), keyword) || StringUtils.containsIgnoreCase(employee.getLastName(), keyword);
  }
  
  private void initEmployees() {
    Employee e1 = new Employee();
    e1.setFirstName("Long");
    e1.setLastName("Do");
    e1.setCountry("Vietnam");
    persistence().persist(e1);
    
    Employee e2 = new Employee();
    e2.setFirstName("Nam");
    e2.setLastName("Le");
    e2.setCountry("Cambodia");
    persistence().persist(e2);
    
    Employee e3 = new Employee();
    e3.setFirstName("Nam");
    e3.setLastName("Mai");
    e3.setCountry("Thailand");
    persistence().persist(e3);
  }
  
  private IIvyEntityManager persistence() {
    return Ivy.persistence().get("PersistenceSample");
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }
  
}
