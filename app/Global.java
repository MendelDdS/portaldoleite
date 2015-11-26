import java.util.ArrayList;
import java.util.List;

import models.Dica;
import models.DicaAssunto;
import models.DicaConselho;
import models.DicaDisciplina;
import models.DicaMaterial;
import models.Disciplina;
import models.Tema;
import models.User;
import models.dao.GenericDAOImpl;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.db.jpa.JPA;


public class Global extends GlobalSettings {

	private static GenericDAOImpl dao = new GenericDAOImpl();
	private List<Disciplina> disciplinas = new ArrayList<>();
	private User user1, user2, user3, user4, user5, user6, user7, user8, user9, user10;
	private Dica dcConselho, dcMaterial, dcAssunto, dcDisciplina, dcConselho2;
	private Tema alloy, play, tableaux, caract;
	
	@Override
	public void onStart(Application app) {
		Logger.info("Aplicação inicializada...");

		JPA.withTransaction(new play.libs.F.Callback0() {
			@Override
			public void invoke() throws Throwable {
				if(dao.findAllByClassName(Disciplina.class.getName()).size() == 0){
					criaDisciplinas();
				}
				criaUsuarios();
				criaDicas();
				cadastraVotos();
			}
		});
	}
	
	@Override
	public void onStop(Application app){
	    JPA.withTransaction(new play.libs.F.Callback0() {
	    @Override
	    public void invoke() throws Throwable {
	        Logger.info("Aplicação finalizando...");
	        disciplinas = dao.findAllByClassName("Disciplina");

	        for (Disciplina disciplina: disciplinas) {
	        dao.removeById(Disciplina.class, disciplina.getId());
	       } 
	    }}); 
	}
	
	private void criaDisciplinas(){
		Disciplina si1 = new Disciplina("Sistemas de Informação 1");
		
		si1.addTema(new Tema("Análise x Design"));
		si1.addTema(new Tema("Orientação a objetos"));
		si1.addTema(new Tema("GRASP"));
		si1.addTema(new Tema("GoF"));
		si1.addTema(new Tema("Arquitetura"));
		play = new Tema("Play");
		si1.addTema(play);
		si1.addTema(new Tema("JavaScript"));
		si1.addTema(new Tema("HTML / CSS / Bootstrap"));
		si1.addTema(new Tema("Heroku"));
		si1.addTema(new Tema("Labs"));
		si1.addTema(new Tema("Minitestes"));
		si1.addTema(new Tema("Projeto"));
		
		dao.persist(si1);
		
		////////////////////////////////////////////////////////////////		
		Disciplina prob = new Disciplina("Probabilidade e Estatistica");
		prob.addTema(new Tema("Probabilidade"));
		prob.addTema(new Tema("Variáveis Aleatórias"));
		prob.addTema(new Tema("Variáveis Aleatórias Multidimensionais"));
		prob.addTema(new Tema("Modelos para Variáveis Aleatórias Discretas"));
		prob.addTema(new Tema("Modelos para Variáveis Aleatórias Contínuas"));
		caract = new Tema("Função Característica");
		prob.addTema(caract);
		prob.addTema(new Tema("Sequências de Variáveis"));
		
		dao.persist(prob);
		
		////////////////////////////////////////////////////////////////
		Disciplina logica = new Disciplina("Lógica Matemática");
		
		logica.addTema(new Tema("Lógica Proposicional"));
		tableaux = new Tema("Tableaux");
		logica.addTema(tableaux);
		logica.addTema(new Tema("Semântica na Lógica Proposicional"));
		logica.addTema(new Tema("Dedução na Lógica Proposicional"));
		logica.addTema(new Tema("Lógica de Predicados"));
		logica.addTema(new Tema("Lógica Temporal"));
		alloy = new Tema("Alloy");
		logica.addTema(alloy);
		
		dao.persist(logica);
		
		////////////////////////////////////////////////////////////////
		dao.flush();
	}
	
	private void criaDicas() {		
		dcConselho = new DicaConselho("Alloy é vida, não precisa nem estudar");
		dcConselho.setUser(user8.getNome());
		dcConselho.setTema(alloy);
		alloy.addDica(dcConselho);
		dao.persist(dcConselho);
		dao.persist(alloy);
		
		dcMaterial = new DicaMaterial("http://blog.caelum.com.br/comecando-"
				+ "um-projeto-no-play-framework-usando-java/");
		dcMaterial.setUser(user2.getNome());
		dcMaterial.setTema(play);
		play.addDica(dcMaterial);
		dao.persist(dcMaterial);
		dao.persist(play);
		
		dcAssunto = new DicaConselho("Isso não cai nhem na prova");
		dcAssunto.setUser(user4.getNome());
		dcAssunto.setTema(caract);
		caract.addDica(dcAssunto);
		dao.persist(dcAssunto);
		dao.persist(caract);
		
		dcDisciplina = new DicaDisciplina("Disciplinas como Matemática discreta ajudam muito.", "Estudam quase o mesmo assunto");
		dcDisciplina.setUser(user5.getNome());
		dcDisciplina.setTema(tableaux);
		tableaux.addDica(dcDisciplina);
		dao.persist(dcDisciplina);
		dao.persist(tableaux);
		
		dcConselho2 = new DicaConselho("E quando tudo estiver perdido e fora do seu controle,"
				+ " olhe para aquilo que você tem de importante em sua vida");
		dcConselho2.setUser(user10.getNome());
		dcConselho2.setTema(play);
		play.addDica(dcConselho2);
		dao.persist(dcConselho2);
		dao.persist(play);
	}
	
	private void cadastraVotos() {
		dcConselho.incrementaDiscordancias();
		dcConselho.addComentarioDeUsuario(user3.getLogin(), "Vida no inferno");
		dcConselho.addUsuarioQueVotou(user3.getNome());
		
		dcConselho.incrementaDiscordancias();
		dcConselho.addComentarioDeUsuario(user1.getLogin(), "Quer apanhar cara?");
		dcConselho.addUsuarioQueVotou(user1.getNome());
		
		dcConselho.incrementaDiscordancias();
		dcConselho.addComentarioDeUsuario(user9.getLogin(), "Mas alloy é bom mesmo");
		dcConselho.addUsuarioQueVotou(user9.getNome());
		
		dao.persist(dcConselho);
		
		dcMaterial.incrementaConcordancias();
		dcMaterial.addUsuarioQueVotou(user8.getNome());
		
		dcMaterial.incrementaConcordancias();
		dcMaterial.addUsuarioQueVotou(user3.getNome());
		
		dao.persist(dcMaterial);
		
		dcAssunto.incrementaConcordancias();
		dcAssunto.addUsuarioQueVotou(user1.getNome());
		
		dcAssunto.incrementaConcordancias();
		dcAssunto.addUsuarioQueVotou(user7.getNome());
		
		dcAssunto.incrementaConcordancias();
		dcAssunto.addUsuarioQueVotou(user9.getNome());
		
		dcAssunto.incrementaConcordancias();
		dcAssunto.addUsuarioQueVotou(user5.getNome());
		
		dao.persist(dcAssunto);
		
		dcDisciplina.incrementaDiscordancias();
		dcDisciplina.addComentarioDeUsuario(user10.getLogin(), "Não cara, esse assunto não tem em discreta");
		dcDisciplina.addUsuarioQueVotou(user10.getNome());
		
		dao.persist(dcDisciplina);
	}
	
	private void criaUsuarios() {
		user1 = new User("miketyson@leiteflix.com", "boxerboxer", "mikemikeTy");
		user1.setNome("Mike Tyson");
		user2 = new User("charliekauf@leiteflix.com", "anomalisa", "charliekauf");
		user2.setNome("Charlie Kaufman");
		user3 = new User("maxrock@leiteflix.com","justmax", "maxRocky");
		user3.setNome("Max Rockatansky");
		user4 = new User("maysanhem@leiteflix.com", "nhemnhem", "maysanhem");
		user4.setNome("Maysa Nhem");
		user5 = new User("amadeusMoza@leiteflix.com", "mozao123", "amadeusmoza");
		user5.setNome("Amadeus Mozart");
		user6 = new User("mendel_sa@leiteflix.com", "mendelmendel", "mendel_sa");
		user6.setNome("Mendelssohn Dantas");
		user7 = new User("lucascabral@leiteflix.com", "lucaslucas", "lucas_cabral");
		user7.setNome("Lucasl Cabral");
		user8 = new User("tommywiseau@leiteflix.com", "ohimark", "wiseauRoom");
		user8.setNome("Wiseau Tommy");
		user9 = new User("mrnobody@leiteflix.com", "nobody", "nobody");
		user9.setNome("Mr Nobody");
		user10 = new User("barish@leiteflix.com", "ohclementine", "joelyBari");
		user10.setNome("Joel Barish");
		
		dao.persist(user1);
		dao.persist(user2);
		dao.persist(user3);
		dao.persist(user4);
		dao.persist(user5);
		dao.persist(user6);
		dao.persist(user7);
		dao.persist(user8);
		dao.persist(user9);
		dao.persist(user10);
		
		dao.flush();
	}
}