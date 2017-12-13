package portal.guitest.page;

import org.openqa.selenium.By;

public class CaseHistoryPage extends TemplatePage {
    
    public int countNotes() {
        waitForElementDisplayed(By.cssSelector("div[id*='notes-table']"), true);
        return driver.findElements(By.cssSelector("div[id*='notes-table'] table>tbody>tr")).size();
    }

}
