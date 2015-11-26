import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.callAction;
import static play.test.Helpers.status;

import java.util.HashMap;
import java.util.Map;

import base.AbstractTest;
import play.mvc.Http;
import play.mvc.Result;
import play.test.FakeRequest;


public abstract class AcessoAbstractTest extends AbstractTest {
	public Result result;

	public void cadastrarUsuario() {
		FakeRequest fakeRequest1 = new FakeRequest();
		Map<String, String> form1 = new HashMap<String, String>();
		form1.put("nome", "joao");
		form1.put("email", "abc@bbc.com");
		form1.put("login", "mimimimi");
		form1.put("pass", "tchutchu");
		fakeRequest1.withFormUrlEncodedBody(form1);
		
		result = callAction(controllers.routes.ref.Register.register(), fakeRequest1);
		}
	
	public void tentaFazerLogin(FakeRequest fakeRequest) {
		Map<String, String> form2 = new HashMap<String, String>();
		
		form2.put("login", "mimimimi");
		form2.put("pass", "tchutchu");
		fakeRequest.withFormUrlEncodedBody(form2);
	}
}
