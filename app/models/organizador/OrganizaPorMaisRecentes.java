package models.organizador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import models.Dica;

public class OrganizaPorMaisRecentes implements Organizador {

	@Override
	public List<Dica> sort(List<Dica> dicas) {
		Collections.reverse(dicas);
		return dicas;
	}
}
