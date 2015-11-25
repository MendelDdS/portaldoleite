package models;

import java.util.ArrayList;
import java.util.List;

public class OrganizaPorMaisRecentes implements Organizador {

	@Override
	public List<Dica> sort(List<Dica> dicas) {
		List<Dica> ultimasDicas = new ArrayList<>();
		for (int i = 0; i < dicas.size(); i++) {
			ultimasDicas.add(dicas.get(dicas.size()-1-i));
		}
		dicas = ultimasDicas;
		return dicas;

	}
}
