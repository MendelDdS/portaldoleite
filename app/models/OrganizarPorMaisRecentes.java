package models;

import java.util.ArrayList;
import java.util.List;

public class OrganizarPorMaisRecentes implements Organizador {

	@Override
	public List<Dica> sort(List<Dica> dicas) {
		return dicas;
//		List<Dica> top = new ArrayList<>();
//		List<Dica> temaGeral = new ArrayList<>();
//		List<Dica> todasAsDicas = dao.findAllByClassName(Dica.class.getName());
//		List<Dica> result = new ArrayList<>();
//
//		if (todasAsDicas.size() > 10) {
//			for (int i = 1; i < 11; i++) {
//				top.add(todasAsDicas.get(todasAsDicas.size() - i));
//			}
//		}
//
//		List<Tema> listaTema = dao.findAllByClassName(Tema.class.getName());
//		for (Tema t : listaTema) {
//			for (Dica d : t.getDicas()) {
//				temaGeral.add(d);
//			}
//		}
//
//		for (int i = 0; i < top.size(); i++) {
//			Dica dica_base = top.get(i);
//			for (Dica dica_completa : temaGeral) {
//				if (dica_completa.getId() == dica_base.getId()) {
//					result.add(dica_completa);
//				}
//			}
//
//		}
//		return result;
//	}
	}

}
