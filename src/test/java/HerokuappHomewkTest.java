import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class HerokuappHomewkTest extends BaseTest {

    @Test
    public void AddRemoveTest() {
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add Element']"));
        addButton.click();
        addButton.click();
        List<WebElement> allDeleteButtons = driver.findElements(By.xpath("//button[text()='Delete']"));
        allDeleteButtons.get(1).click();//удалили вторую Delete button
        allDeleteButtons = driver.findElements(By.xpath("//button[text()='Delete']"));
        Assert.assertEquals(allDeleteButtons.size(), 1, "delete buttons count is incorrect");


    }
    @Test
    public void checkboxesTest() {
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        List <WebElement> checkboxes = driver.findElements(By.cssSelector("input[type=checkbox]"));
        Assert.assertFalse(checkboxes.get(0).isSelected(),
                "Checkbox 1 is selected");
        Assert.assertTrue(checkboxes.get(1).isSelected(),
                "Checkbox 2 is not selected");
        checkboxes.get(0).click();
        checkboxes.get(1).click();
        Assert.assertTrue(checkboxes.get(0).isSelected(),
                "Checkbox 1 is NOT selected, Expected result: checkbox selected");
        Assert.assertFalse(checkboxes.get(1).isSelected(),
                "Checkbox 2 is selected, Expected result: checkbox is NOT selected");
    }

    @Test
    public void dropdownTest() {
        driver.get("http://the-internet.herokuapp.com/dropdown");
        Select select = new Select(driver.findElement(By.id("dropdown")));
        List<WebElement> allOptions =  select.getOptions();
        List <String> allOptionNames = allOptions.stream().map(option -> option.getText()).toList();
        List <String> allOptionNames1 = new ArrayList<>();
        for (WebElement option: allOptions) {
            allOptionNames1.add(option.getText());
        }
        List<String> expectedOptionNames = new ArrayList<>();
        expectedOptionNames.add("Please select an option");
        expectedOptionNames.add("Option 1");
        expectedOptionNames.add("Option 2");
        Assert.assertEquals(allOptionNames, expectedOptionNames);

        }
    @Test
    public void inputsTest() {
        driver.get("http://the-internet.herokuapp.com/inputs");
        String inputValue = "12";
        WebElement input = driver.findElement(By.cssSelector("input[type='number']"));
        input.sendKeys(inputValue);
        String actualValue = input.getAttribute("value");
        Assert.assertEquals(actualValue, inputValue);
        input.clear();
        inputValue = "qq";
        input.sendKeys(inputValue);
        actualValue = input.getAttribute("value");
        Assert.assertEquals(actualValue, "");
        input.clear();
        Actions actions  = new Actions(driver);
        actions.sendKeys(Keys.ARROW_UP)
                .build().perform();
        actualValue = input.getAttribute("value");
        Assert.assertEquals(actualValue, "1");//1
        actions.sendKeys(Keys.ARROW_DOWN)
                .build().perform();
        actualValue = input.getAttribute("value");
        Assert.assertEquals(actualValue, "0");//0

    }

    @Test
    public void sortableDatatablesTest() {
        driver.get("http://the-internet.herokuapp.com/tables");
        WebElement table = driver.findElement(By.id("table1"));
        List<WebElement> tableRows = driver.findElements(By.xpath("//tbody/tr"));
        List<WebElement> firstRowCells = tableRows.get(0).findElements(By.tagName("td"));
        String expectedLastName = "Smith";
        Assert.assertEquals(firstRowCells.get(0).getText(), expectedLastName);
        String expectedFirstRowText = "Smith John jsmith@gmail.com $50.00 http://www.jsmith.com edit delete";
        Assert.assertEquals(tableRows.get(0).getText(), expectedFirstRowText,
                "first row text is not the same as expected");

    }

    @Test
    public void sortableDataTablesHeaderTest() {
        driver.get("http://the-internet.herokuapp.com/tables");
        WebElement table = driver.findElement(By.id("table1"));
        List<WebElement> headerRowCells = driver.findElements(By.xpath("//thead/tr/th"));
        List<String> actualHeaderNames = new ArrayList<>();
        actualHeaderNames.add(headerRowCells.get(0).getText());
        actualHeaderNames.add(headerRowCells.get(1).getText());
        actualHeaderNames.add(headerRowCells.get(2).getText());
        actualHeaderNames.add(headerRowCells.get(3).getText());
        actualHeaderNames.add(headerRowCells.get(4).getText());
        actualHeaderNames.add(headerRowCells.get(5).getText());
        List<String> expectedHeaderNames = new ArrayList<>();
        expectedHeaderNames.add("Last Name");
        expectedHeaderNames.add("First Name");
        expectedHeaderNames.add("Email");
        expectedHeaderNames.add("Due");
        expectedHeaderNames.add("Web Site");
        expectedHeaderNames.add("Action");
        Assert.assertEquals(actualHeaderNames, expectedHeaderNames,
                "header row cells text not correct");

    }
    @Test
    public void typosTest() {
        driver.get("http://the-internet.herokuapp.com/typos");
        WebElement content = driver.findElement(By.className("example"));
        String expectedContent = "Typos\n" +
                "This example demonstrates a typo being introduced. It does it randomly on each page load.\n" +
                "Sometimes you'll see a typo, other times you won't.";
        Assert.assertEquals(content.getText(), expectedContent, "there is a typo in the content");


    }
    }





