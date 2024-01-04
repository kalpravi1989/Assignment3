package Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ExcelWriter;

public class HomePage {
	WebDriver mydriver;
	List<String> listOfAuthor = new ArrayList<String>();
	public HomePage(WebDriver driver) {
		this.mydriver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='bq_fl indexContent authorContent']")
	List<WebElement> content;

	@FindBy(xpath = "//div[@class='grid-item qb clearfix bqQt']")
	List<WebElement> QuotesList;

	@FindBy(xpath = "//div[@class='bq_fl indexContent authorContent']")
	List<WebElement> tagList;

	public void getListOfAuthor() {
		outerLoop:
		for (WebElement con : content) {
			List<WebElement> authorNameList = con.findElements(By.xpath("//div/a/span"));
			for (WebElement authorNm : authorNameList) {
				if (authorNm.getText().equals("Albert Einstein")) {
					authorNm.click();
					break outerLoop;
				}
                listOfAuthor.add(authorNm.getText());
				System.out.println(authorNm.getText());
			}
			
     }
		ExcelWriter ew = new ExcelWriter();
		ew.writeDatatoExcel(listOfAuthor, "Sheet2");
	}

	public void getQuotesAndTags() {

	}

}
