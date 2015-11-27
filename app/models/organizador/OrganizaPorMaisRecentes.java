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
		Collections.sort(dicas, new Comparator<Dica>() {
            @Override
            public int compare(Dica dica, Dica otherDica) {
                if (dica.getDataDeCriacao().before(otherDica.getDataDeCriacao())){
                    return 1;
                }else if (dica.getDataDeCriacao().after(otherDica.getDataDeCriacao())){
                    return -1;
                } else {
                	return 0;
                }
            }
        });

        return dicas;
	}
}
