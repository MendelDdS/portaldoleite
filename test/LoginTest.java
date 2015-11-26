import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.callAction;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.status;
import static play.test.Helpers.flash;
import static play.test.Helpers.session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.User;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;

import org.apache.http.HttpStatus;
import org.junit.Test;

import play.mvc.Http;
import play.mvc.Result;
import play.test.FakeRequest;
import base.AbstractTest;


public class LoginTest extends AcessoAbstractTest {

	GenericDAO dao = new GenericDAOImpl();
	
	/**
	 * Testa se é possível se acessar a url de login.
	 */
	@Test
	public void callLogin() {
		result = callAction(controllers.routes.ref.Login.show(),
				fakeRequest());
		assertThat(status(result)).isEqualTo(Http.Status.OK);
	} 
	
	/**
	 * Testa se não é possível fazer login quando login ou senha são inválidos.
	 */
	@Test
	public void deveFalharParaUsuarioInvalido() {
		List<User> users = dao.findAllByClassName("User");
		assertThat(users.size()).isEqualTo(10);
		
		FakeRequest fakeRequest1 = new FakeRequest();
		tentaFazerLogin(fakeRequest1);
		
		result = callAction(controllers.routes.ref.Login.authenticate(), fakeRequest1);
		Map<String, String> flash = new HashMap<String, String>();
		flash.put("fail", "Login ou Senha Inválidos");
		
		assertThat(status(result)).isEqualTo(Http.Status.UNAUTHORIZED);
		assertThat(flash(result)).isEqualTo(flash);
	}
	
	/**
	 * Testa se é possível se fazer login com usuário válido.
	 */
	@Test
	public void deveFazerLogin() {
		cadastrarUsuario();
		
		assertThat(status(result)).isEqualTo(Http.Status.SEE_OTHER);

		List<User> users = dao.findAllByClassName("User");
		assertThat(users.size()).isEqualTo(11);
		
		FakeRequest fakeRequest2 = new FakeRequest();
		tentaFazerLogin(fakeRequest2);
		
		assertThat(session(result)).isEqualTo(new HashMap<String, String>());
		
		result = callAction(controllers.routes.ref.Login.authenticate(), fakeRequest2);
		
		Map<String, String> session = new HashMap<String, String>();
		session.put("username", "joao");
		session.put("login", "mimimimi");
		
		assertThat(status(result)).isEqualTo(Http.Status.SEE_OTHER);
		assertThat(session(result)).isEqualTo(session);
	}
	
}
