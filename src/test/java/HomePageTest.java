import org.testng.annotations.Test;

import com.shaanstraining.ui.pages.HomePage;

public class HomePageTest{
	HomePage homePage = new HomePage();
	
	@Test
	void search(){
		homePage.search("Selenium WebDriver");
	}
}
