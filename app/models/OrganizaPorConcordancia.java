package models;

import java.util.List;
import java.util.Collections;

public class OrganizaPorConcordancia implements Organizador {

	@Override
	public List<Dica> sort(List<Dica> dicas) {
		List<Dica> resultado = dicas;
		if (!dicas.isEmpty()) {
			Collections.sort(resultado);
			return resultado;			
		}
		return dicas;		
	}
}
