package tests;

import org.testng.annotations.Test;

import Base.BaseClass;
import Pages.HomePage;

public class Test1 extends BaseClass {

	@Test
	public void getAuthorDetails() {

		HomePage pg = new HomePage(driver);
		pg.getListOfAuthor();

	}

	public void getQuotesAndTags() {
		HomePage pg = new HomePage(driver);
		pg.getListOfAuthor();
		pg.getQuotesAndTags();
	}
}
