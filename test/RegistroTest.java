import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.callAction;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.status;
import static play.test.Helpers.flash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.User;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;

import org.junit.Test;

import play.mvc.Http;
import play.mvc.Result;
import play.test.FakeRequest;
import base.AbstractTest;


public class RegistroTest extends AcessoAbstractTest{

	GenericDAO dao = new GenericDAOImpl();
	
	/**
	 * Testa se é possível se acessar a url de registro de usuário.
	 */
	@Test
	public void callLogin() {
		result = callAction(controllers.routes.ref.Register.show(),
				fakeRequest());
		assertThat(status(result)).isEqualTo(Http.Status.OK);
	}
	
	/**
	 * Testa se é possível registrar um usuário.
	 */
	@Test
	public void deveRegistrarUser() {
		List<User> users = dao.findAllByClassName("User");
		
		assertThat(users.size()).isEqualTo(10);

		cadastrarUsuario();
		
		assertThat(status(result)).isEqualTo(Http.Status.SEE_OTHER);
		
		users = dao.findAllByClassName("User");
		
		assertThat(users.size()).isEqualTo(11);
	}
	
	/**
	 * Testa se realmente não se pode registrar um usuário com login já usado.
	 */
	@Test
	public void deveNaoPermitirCadastroDeUsuariosComMesmoLogin() {
		cadastrarUsuario();
		assertThat(status(result)).isEqualTo(Http.Status.SEE_OTHER);

		List<User> users = dao.findAllByClassName("User");
		
		assertThat(users.size()).isEqualTo(11);
		
		FakeRequest fakeRequest2 = new FakeRequest();
		Map<String, String> form2 = new HashMap<String, String>();
		form2.put("nome", "joao");
		form2.put("email", "a@b.com");
		form2.put("login", "mimimimi");
		form2.put("pass", "tchutchu");
		fakeRequest2.withFormUrlEncodedBody(form2);
		
		result = callAction(controllers.routes.ref.Register.register(), fakeRequest2);
		
		assertThat(status(result)).isEqualTo(Http.Status.BAD_REQUEST);
		
		users = dao.findAllByClassName("User");
		
		assertThat(users.size()).isEqualTo(11);
		Map<String, String> flash = new HashMap<String, String>();
		flash.put("fail", "Login em uso");
		assertThat(flash(result)).isEqualTo(flash);
	}
	
	/**
	 * Testa se realmente não se pode registrar um usuário com e-mail já usado.
	 */
	@Test
	public void deveNaoPermitirCadastroDeUsuariosComMesmoEmail() {
		cadastrarUsuario();
		
		List<User> users = dao.findAllByClassName("User");
		
		assertThat(users.size()).isEqualTo(11);
		
		FakeRequest fakeRequest2 = new FakeRequest();
		Map<String, String> form2 = new HashMap<String, String>();
		form2.put("nome", "joao");
		form2.put("email", "abc@bbc.com");
		form2.put("login", "herbert");
		form2.put("pass", "tchutchu");
		fakeRequest2.withFormUrlEncodedBody(form2);
		
		result = callAction(controllers.routes.ref.Register.register(), fakeRequest2);
		
		assertThat(status(result)).isEqualTo(Http.Status.BAD_REQUEST);
		
		users = dao.findAllByClassName("User");
		
		assertThat(users.size()).isEqualTo(11);
		Map<String, String> flash = new HashMap<String, String>();
		flash.put("fail", "E-mail em uso");
		assertThat(flash(result)).isEqualTo(flash);
	}
}