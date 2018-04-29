package selenideskeleton;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MailboxItem extends ElementsContainer {

	SelenideElement subject;
		
	public MailboxItem(SelenideElement item) {
		setSelf(item);
		subject = getSelf().$("div.b-datalist__item__subj");
	}
	
	public Mail open() {
		subject.click();
		return new Mail($("div.b-letter"));
	}

}
