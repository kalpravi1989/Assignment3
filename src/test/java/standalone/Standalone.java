package standalone;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Standalone {
	public WebDriver driver;
	static String excelFilePath = "/Users/balajivalveriveeraperumal/Desktop/SeleniumDataDemo.xlsx";
	@BeforeTest
	public void setup() {
		// ChromeOptions options = new ChromeOptions();
		// options.addArguments("--incognito");
		// options.addArguments("ignore-certificate-errors");
		// options.addArguments("--ignore-ssl-errors=yes");
		// options.setAcceptInsecureCerts(true);
		driver = new EdgeDriver();

		driver.get("https://www.brainyquote.com/authors");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}

	@Test
	public void test() {
		List<String> ans = new ArrayList<String>();
		List<WebElement> content = driver.findElements(By.xpath("//div[@class='bq_fl indexContent authorContent']"));
		for (int i = 1; i < content.size(); i++) {
			List<WebElement> authorNameList = driver
					.findElements(By
							.xpath("(//div[@class='bq_fl indexContent authorContent'])[" + i + "]/child::div/a/span"));
			for (WebElement authorname : authorNameList) {
				if (authorname.getText().equals("Albert Einstein")) {

				}
				System.out.println(authorname.getText());
				ans.add(authorname.getText());
			}
		}
		for (String s : ans) {
			System.out.println("arraylist" + s);
		}
		writeDatatoExcel(ans);
	}

	@Test
	public void test2() {
		Map<String, ArrayList<String>> ans = new HashMap<String, ArrayList<String>>();
		List<WebElement> quotesList = driver.findElements(By.xpath("//div[@class='grid-item qb clearfix bqQt']"));
		for (int i = 1; i < quotesList.size(); i++) {
			driver.findElements(By.xpath("//div[@class='grid-item qb clearfix bqQt'][" + i + "]"));
			WebElement quotes = driver.findElement(By.xpath("//p[@class='b-qt qt_125368']"));
			String qu = quotes.getText();
			List<WebElement> tagsList = driver.findElements(By.xpath("//a[@class='qkw-btn btn btn-sm oncl_klc']"));
			ArrayList<String> tag = new ArrayList<String>();
			for (WebElement tagName : tagsList) {
				tag.add(tagName.getText());
			}
			ans.put(qu, tag);
			driver.navigate().back();

		}
		System.out.println(ans);
	}

	@Test
	public void writeDatatoExcel(List<String> data) {

		try {
			Workbook workbook = new XSSFWorkbook();
			FileOutputStream fileOut = new FileOutputStream(excelFilePath);

			Sheet sheet = workbook.createSheet("Sheet2");





				for (int i = 0; i < data.size(); i++) {
					Row row = sheet.createRow(i);
					Cell cell = row.createCell(0);
					cell.setCellValue(data.get(i));
	            }

				workbook.write(fileOut);

	            // Print a success message
	            System.out.println("Data written to Excel successfully!");

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }



	}
