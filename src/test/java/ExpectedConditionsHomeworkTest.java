import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExpectedConditionsHomeworkTest extends BaseTest {

    @Test
    public void loadingCheckboxTest() {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        WebElement removeButton = driver.findElement(By.xpath("//button[text()='Remove']"));
        WebElement checkbox = driver.findElement(By.cssSelector("input[type='checkbox']"));
        removeButton.click();
        wait.until(ExpectedConditions.stalenessOf(checkbox));
        WebElement actualText = driver.findElement(By.cssSelector("p#message"));
        Assert.assertEquals(actualText.getText(), "It's gone!");
        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add']"));
        addButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[id='checkbox']")));
        WebElement actualTextForAdd = driver.findElement(By.cssSelector("p#message"));
        Assert.assertEquals(actualTextForAdd.getText(), "It's back!");

    }

    @Test
    public void loadingEnableDisableTest() {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        WebElement enableButton = driver.findElement(By.xpath("//button[text()='Enable']"));
        enableButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='text']")));
        WebElement actualEnableText = driver.findElement(By.id("message"));
        Assert.assertEquals(actualEnableText.getText(), "It's enabled!");
        WebElement disableButton = driver.findElement(By.xpath("//button[text()='Disable']"));
        disableButton.click();
        WebElement inputField = driver.findElement(By.cssSelector("input[type='text']"));
        wait.until(ExpectedConditions.attributeContains(inputField, "disabled", ""));
        WebElement actualDisableText = driver.findElement(By.id("message"));
        Assert.assertEquals(actualDisableText.getText(), "It's disabled!");
    }

}
