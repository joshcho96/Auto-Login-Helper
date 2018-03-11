import java.io.IOException;
import java.net.MalformedURLException;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * A simple crawler for the naver page.
 * @author Yeonwoo Sung
 */
public class NaverCrawler {

	private static final String LOG_IN_URL = "http://static.nid.naver.com/login.nhn?svc=wme&amp;amp;url=http%3A%2F%2Fwww.naver.com&amp;amp;t=20120425";
	private static final String URL_MAIL = "https://mail.naver.com/";
	private static final String URL_PAY = "https://pay.naver.com/";
	private static final String URL_WEBTOON = "http://comic.naver.com/";

	private final int ZERO = 0;
	private String id;
	private String pw;

	private WebClient webClient = null;
	private HtmlPage naver;
	private HtmlForm form;

	/**
	 * This constructor sets the id and password to log in the Naver page.
	 * @param id of the Naver.
	 * @param pw of the Naver.
	 */
	NaverCrawler(String id, String pw) {
		this.id = id;
		this.pw = pw;

		init();
	}

	/**
	 * The aim of this method is to initialise the basic settings.
	 */
	private void init() {
		webClient = new WebClient(BrowserVersion.CHROME); //Use the google chrome as a browser.

		webClient.setAjaxController(new NicelyResynchronizingAjaxController());

		// Set the program not to throw exception on error.
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);

		webClient.getOptions().setJavaScriptEnabled(true); //allow to use JavaScript.
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getCookieManager().setCookiesEnabled(true);
		webClient.getOptions().setCssEnabled(false);

		webClient.waitForBackgroundJavaScript(1000);

		try {
			naver = webClient.getPage(LOG_IN_URL);
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The aim of this method is to allow the user to log in the Naver page.
	 */
	public void loginNaver() {
		form = naver.getForms().get(ZERO);
		form.reset();
		
		form.getInputByName("id").setValueAttribute(id);
		form.getInputByName("pw").setValueAttribute(pw);

		HtmlImageInput button = (HtmlImageInput)form.getByXPath("//input[@alt='로그인']").get(ZERO);
		try {
			naver = (HtmlPage)button.click();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(naver.asText().contains("new device(browser)")) {
            ScriptResult result = naver.executeJavaScript("$('regyn').value ='Y';$('frmNIDLogin').submit();");
            result.getJavaScriptResult();
            naver = (HtmlPage)result.getNewPage();
        } else if(naver.asText().contains("Naver Sign in"))  {
        	System.out.println(naver.asXml());
        	System.out.println("Login Fail!!");
        } else {
        	System.out.println("Login success!!!!!");
        }
	}

	/**
	 * This method returns the naver email page.
	 * @return html page of your naver mail page.
	 * @throws FailingHttpStatusCodeException could be thrown.
	 * @throws MalformedURLException could be thrown.
	 * @throws IOException could be thrown.
	 */
	public HtmlPage goToEmailPage() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		return webClient.getPage(URL_MAIL);
	}

	/**
	 * This method returns the naver pay page.
	 * @return html page of your naver pay page.
	 * @throws FailingHttpStatusCodeException could be thrown.
	 * @throws MalformedURLException could be thrown.
	 * @throws IOException could be thrown.
	 */
	public HtmlPage goToPayPage() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		return webClient.getPage(URL_PAY);
	}

	/**
	 * This method returns the naver comics page.
	 * @return html page of the naver comic page.
	 * @throws FailingHttpStatusCodeException could be thrown.
	 * @throws MalformedURLException could be thrown.
	 * @throws IOException could be thrown.
	 */
	public HtmlPage goToWebtoonPage() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		return webClient.getPage(URL_WEBTOON);
	}

	/**
	 * The getter for id.
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * The getter for pw
	 * @return the password
	 */
	public String getPw() {
		return pw;
	}

	/**
	 * This method allows the user to login as an other user.
	 * @param id of the other user.
	 * @param pw of the other user.
	 */
	public void logInAsOtherUser(String id, String pw) {
		this.id = id;
		this.pw = pw;

		loginNaver();
	}
}
