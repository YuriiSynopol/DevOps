package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CategoryPage extends AHeadComponent {

    private WebElement categoryName;

    public CategoryPage(WebDriver driver) {
        super(driver);
        categoryName = driver.findElement(By.cssSelector("#left-column > " +
                "div.block-categories.hidden-sm-down > ul > li:nth-child(1)"));
    }

    public String getCategoryName() {
        return categoryName.getText().trim().toUpperCase();
    }

}
