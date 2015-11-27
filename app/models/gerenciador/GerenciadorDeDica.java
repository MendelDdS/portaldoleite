package models.gerenciador;

import models.Dica;
import models.Tema;
import models.dao.GenericDAOImpl;

public class GerenciadorDeDica {
	private Dica dica;
	
	public void setDica(Dica dica) {
		this.dica = dica;
	}
	
	public void setInformacoesDaDica(String nomeDeUsuario, Tema tema, GenericDAOImpl dao) {
		tema.addDica(dica);
		dica.setTema(tema);
		dica.setUser(nomeDeUsuario);
		dao.persist(dica);	
	}
	
}
