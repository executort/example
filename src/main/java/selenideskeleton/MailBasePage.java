package selenideskeleton;

import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.page;

public class MailBasePage {
	
	@FindBy(id = "mailbox__login")
	SelenideElement loginField;
	
	@FindBy(id = "mailbox__password")
	SelenideElement pwdField;
	
	@FindBy(id = "mailbox__auth__button")
	SelenideElement loginButton;
	
	public void waitPageLoaded() {
		loginField.shouldBe(Condition.visible);
		pwdField.shouldBe(Condition.visible);
		loginButton.shouldBe(Condition.enabled);
	}
	
	public MailboxPage login(String login, String password) {
		loginField.sendKeys(login);
		pwdField.sendKeys(password);
		loginButton.click();
		return page(MailboxPage.class);
	}

}
