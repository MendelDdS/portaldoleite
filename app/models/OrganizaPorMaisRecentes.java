package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class OrganizaPorMaisRecentes implements Organizador {

	@Override
	public List<Dica> sort(List<Dica> dicas) {
		Collections.reverse(dicas);
		return dicas;

	}
}
