package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    
    @LocalServerPort
    private int serverPort;
    
    private ChromeDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
    }

    @Test
    void testCreateProduct() {
        driver.get("http://localhost:" + serverPort + "/product/create");
        
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        nameInput.sendKeys("New Product Testing");
        
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        quantityInput.sendKeys("10");
        
        WebElement submitButton = driver.findElement(By.tagName("button"));
        submitButton.click();
        driver.get("http://localhost:" + serverPort + "/product/list");
        
        WebElement productList = driver.findElement(By.tagName("table"));
        String productListText = productList.getText();
        
        assertEquals(true, productListText.contains("New Product Testing"));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
