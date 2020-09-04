package project.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import project.utilities.SeleniumUtils;

public class ChangeInfo_Popup_Page extends PageObject{

	SeleniumUtils oSeleniumUtils;

	String lables = "//div[@class='modal-body']//tbody/tr/td[1]";
	
	
	public boolean verifyFields(String arg1) {
		boolean bln = false;
		String[] sections = arg1.split(",");
		for (int i = 0; i < sections.length; i++){
			switch(sections[i]){
			case "Client ID / Name":
				bln = true;
				break;
						
			}
		}
		return bln;
	}
	
	public String[] getLables(){
		return oSeleniumUtils.get_All_Text_from_Locator(lables);
	}
}
