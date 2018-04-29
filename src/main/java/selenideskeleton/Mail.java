package selenideskeleton;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;

public class Mail extends ElementsContainer{
	
	SelenideElement subject;
	
	SelenideElement body;

	public Mail(SelenideElement element) {
		setSelf(element);
		subject = getSelf().$("div.b-letter__head__subj__text");
		body = getSelf().$("div.b-letter__body");
	}
	
	public void waitMailLoaded() {
		for (SelenideElement element : new SelenideElement[] { subject, body } ) {
			element.shouldBe(Condition.visible);
		}
	}
	
	public MailContact getSender() {
		return new MailContact(getSelf().$("div.b-letter__head__addrs__from"));
	}
	
	public class MailContact extends ElementsContainer {

		public MailContact(SelenideElement contact) {
			setSelf(contact);
		}
		
		public String getEmail() {
			return getSelf().$("span").getAttribute("data-contact-informer-email");
		}
		
		public String getName() {
			return getSelf().$("span").getAttribute("data-contact-informer-name");
		}
	}

}
