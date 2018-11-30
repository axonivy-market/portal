package ch.ivyteam.ivy.project.portal.guitest.mobile.tester;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ElementDisplayedTester<T> implements ExpectedCondition<Boolean> {
  private static final Log log = LogFactory.getLog(ElementDisplayedTester.class);
  
  private SearchContext root;
    private final T locator;
    private final boolean visible;
    
    public boolean isElementDisplayed(SearchContext root, T locator) {
        By by;
        if ( locator instanceof String ) {
            by = By.xpath(String.class.cast(locator));
        } else if(locator instanceof WebElement) {
          return ((WebElement)locator).isDisplayed();
        } else {
            by = By.class.cast(locator);
        }
        return root.findElement(by).isDisplayed();
    }
    
    @Override
  public Boolean apply(WebDriver driver) {
        log.debug("Testing: " + locator);
        if ( root == null ) {
            root = driver;
        }
        return visible ? isElementDisplayed(root, locator) : !isElementDisplayed(root, locator);
    }
    
    public ElementDisplayedTester(SearchContext root_, T locator_, boolean visible_) {
        this.root = root_;
        this.locator = locator_;
        this.visible = visible_;
    }
    
    public ElementDisplayedTester(T locator_, boolean visible_) {
        this.root = null;
        this.locator = locator_;
        this.visible = visible_;
    }
  
}


