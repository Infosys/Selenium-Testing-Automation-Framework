package actions;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TestFileupload {

 public static void main(String[] args) throws InterruptedException, AWTException {
  
  
   System.setProperty("webdriver.ie.driver","D:\\Selenium Framework development\\IEDriverServer.exe");
       WebDriver driver =  new InternetExplorerDriver();
// WebDriver driver = new FirefoxDriver();
 driver.get("http://api.checklist.com/login");
  
  
  driver.findElement(By.xpath("//*[@id='email']")).sendKeys("rgulati883@gmail.com");
  driver.findElement(By.xpath("//*[@id='loginForm']/div[2]/input")).sendKeys("jaiguruji");
  driver.findElement(By.xpath("//button[@class='btn btn btn-primary pull-right']")).click();
  Thread.sleep(8000);
  driver.findElement(By.xpath("//*[@id='userChecklists']/li[1]/a/span")).click();
  
   WebElement UploadImg = driver.findElement(By.xpath("//*[@id='taskUploadFile']"));
    UploadImg.click();
    
                  //  StringSelection ss = new StringSelection("Your file path ");
    StringSelection ss = new StringSelection("D:\\Selenium Framework development\\FileuploadTest.txt");
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
 
    Robot r = new Robot();
    
    
    // Calling key press event to press Enter Key from keyboard to place cursor in window textbox
    r.keyPress(KeyEvent.VK_ENTER);
    //Releaseing the Enter Key
    r.keyRelease(KeyEvent.VK_ENTER);
  
    /*
     * Now we are going to trigger CTRL+V action so first we will press CTRL and then V and finally will
     * release these key.
     */
    r.keyPress(KeyEvent.VK_CONTROL);    
    r.keyPress(KeyEvent.VK_V);
    
    r.keyRelease(KeyEvent.VK_V);    
    r.keyRelease(KeyEvent.VK_CONTROL);
    
    // After pasting path now we are going to click on Open and that can be 
    //triggered by pressing enter key from Keyboard.
    r.keyPress(KeyEvent.VK_ENTER);
    r.keyRelease(KeyEvent.VK_ENTER);
    //Release open 
    
 }

}
