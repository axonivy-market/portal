package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NoteHistoryPage extends TemplatePage {
    
    private static final String TABLE_ROWS_PATH = "div[id*='notes-table'] table>tbody>tr";
    
    public int countNotes() {
        waitForElementDisplayed(By.cssSelector("div[id*='notes-table']"), true);
        return driver.findElements(By.cssSelector(TABLE_ROWS_PATH)).size();
    }
    
    public String getNoteContentOfRow(int index) {
        WebElement firstRow = driver.findElements(By.cssSelector(TABLE_ROWS_PATH)).get(index);
        return firstRow.findElements(By.xpath("td")).get(2).getText();
    }

}
