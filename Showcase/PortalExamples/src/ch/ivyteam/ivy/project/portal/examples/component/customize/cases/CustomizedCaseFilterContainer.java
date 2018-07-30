package ch.ivyteam.ivy.project.portal.examples.component.customize.cases;

import java.util.Collections;

import ch.ivy.addon.portalkit.casefilter.DefaultCaseFilterContainer;
import ch.ivy.addon.portalkit.comparator.CaseFilterComparator;

public class CustomizedCaseFilterContainer extends DefaultCaseFilterContainer {

  private CustomerNameFieldFilter customerNameFieldFilter = new CustomerNameFieldFilter();
  private ShipmentDateFieldFilter shipmentDateFieldFilter = new ShipmentDateFieldFilter();

  public CustomizedCaseFilterContainer() {
    super();
    filters.add(customerNameFieldFilter);
    filters.add(shipmentDateFieldFilter);
    Collections.sort(filters, new CaseFilterComparator());
  }

  public CustomerNameFieldFilter getCustomerNameFieldFilter() {
    return customerNameFieldFilter;
  }

  public void setCustomerNameFieldFilter(CustomerNameFieldFilter customerNameFieldFilter) {
    this.customerNameFieldFilter = customerNameFieldFilter;
  }

  public ShipmentDateFieldFilter getShipmentDateFieldFilter() {
    return shipmentDateFieldFilter;
  }

  public void setShipmentDateFieldFilter(ShipmentDateFieldFilter shipmentDateFieldFilter) {
    this.shipmentDateFieldFilter = shipmentDateFieldFilter;
  }
}
