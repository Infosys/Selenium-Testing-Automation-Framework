package main.java.actions;
/**
 * The FileDownloaderTest class is used to test the FileDownloader class
 *
 * @author Shilpashree_V
 * @version 1.0
 * @since February 2015
 * 
 */
import java.io.File;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class FileDownloadTest {

	@Test
	public void downloadAFile() throws Exception {
		System.setProperty(
				"webdriver.ie.driver",
				"D:\\Softwares\\Selenium_Materials\\Selenium_Materials\\IEDriverServer_Win32_2.33.0\\IEDriverServer.exe");
		WebDriver driver;
		driver = new InternetExplorerDriver();

		driver.get("http://iscls1apps/INFYDIR/");
		driver.findElement(By.id("_ctl0_ContentPlaceHolder1_txtSearch"))
		.sendKeys("shilpashree_v");
		driver.findElement(By.id("_ctl0_ContentPlaceHolder1_lnkSearch"))
		.click();
		Thread.sleep(20000);

		WebElement downloadLink = null;
		int count = 0;
		while (downloadLink == null) {
			downloadLink = driver.findElement(By
					.id("_ctl0_ContentPlaceHolder1_lnkDownload"));
			count++;
			if (count == 50) {
				
				break;
			}
		}
		// WebElement downloadLink = driver.findElement(By
		// .id("_ctl0_ContentPlaceHolder1_lnkDownload"));
		FileDownloader downloadTestFile = new FileDownloader(driver);
		String downloadedFileAbsoluteLocation = downloadTestFile
				.downloadFile(downloadLink);
		System.out.println("downloadTestFile.downloadFile(downloadLink);"
				+ downloadTestFile.downloadFile(downloadLink));

		Assert.assertTrue(new File(downloadedFileAbsoluteLocation).exists());
		// assertThat(downloadTestFile.getHTTPStatusOfLastDownloadAttempt(),
		// is(equalTo(200)));
	}

	@Test
	public void checkValidMD5Hash() throws Exception {
		CheckFileHash fileToCheck = new CheckFileHash();
		fileToCheck.fileToCheck(new File(System.getProperty("java.io.tmpdir")));
		
		fileToCheck.hashDetails("617bfc4b78b03a0f61c98188376d2a6d",
				HashType.MD5);
		Assert.assertTrue(fileToCheck.hasAValidHash());
	}
}
