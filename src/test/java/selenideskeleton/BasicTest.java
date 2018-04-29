package selenideskeleton;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.annotations.Report;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@Report
public class BasicTest {
	
	@BeforeSuite()
	public void suiteSetup() {
		Configuration.browser = "phantomjs";
		System.setProperty("phantomjs.binary.path", "./ext/phantomjs.exe");
		Configuration.timeout = 10000;
	}
	
	@Test()
	public void exampleTest() {
		String login = "executort@bk.ru";
		String pwd = "111Letters";
		String subject = "Узнайте о супервозможностях Почты Mail.Ru";
		String keyword = "супервозможностях";
		String fromName = "Команда Mail.ru";
		String fromEmail = "welcome@corp.mail.ru";
		String bodyPart = "Спасибо, что зарегистрировались в Почте Mail.Ru";
		
		MailBasePage basePage = open("https://mail.ru", MailBasePage.class);
		basePage.waitPageLoaded();
		MailboxPage mailbox = basePage.login(login, pwd);
		mailbox.waitPageLoadedForUser(login);
		MailboxItem target = null;
		for (MailboxItem curItem : mailbox.search(keyword)) {
			System.out.println(curItem.getSelf().innerHtml());
			System.out.println(curItem.subject.toString());
			if (curItem.subject.text().startsWith(subject)) {
				target = curItem;
				break;
			}
		}
		Assert.assertTrue(target != null, "Can't find target mail in search results");
		Mail mail = target.open();
		mail.waitMailLoaded();
		mail.subject.shouldHave(exactText(subject));
		Assert.assertEquals(fromName, mail.getSender().getName());
		Assert.assertEquals(fromEmail, mail.getSender().getEmail());
		mail.body.shouldHave(text(bodyPart));
		mailbox.logout.click();
		basePage.waitPageLoaded();
	}

}
