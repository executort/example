package selenideskeleton;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.ArrayList;
import java.util.List;

public class MailboxPage {
	
	@FindBy(name = "q_query")
	SelenideElement inputField;
	
	@FindBy(id = "PH_authMenu_button")
	SelenideElement user;
	
	@FindBy(name = "search")
	SelenideElement searchButton;
	
	@FindBy(id = "PH_logoutLink")
	SelenideElement logout;
	
	public List<MailboxItem> search(String text) {
		List<MailboxItem> result = new ArrayList<MailboxItem>();
		inputField.sendKeys(text);
		searchButton.click();
		$(By.xpath("//a[@href='https://e.mail.ru/messages/inbox/']")).shouldBe(Condition.visible);
		for (SelenideElement curItem : $$("div.b-datalist__item")) {
			result.add(new MailboxItem(curItem));
		}
		return result;
	}
	
	public void waitPageLoadedForUser(String login) {
		inputField.shouldBe(Condition.visible);
		user.shouldHave(exactText(login));
	}
}
