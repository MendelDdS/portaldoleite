package models;

import java.util.List;

public class OrganizaPorDiscordancia implements Organizador {

	@Override
	public List<Dica> sort(List<Dica> dicas) {
		List<Dica> resultado = dicas;
		boolean trocou = true;
		Dica temp;
		while(trocou){
			trocou = false;
			for(int i = 0; i < resultado.size() -1; i++){
				if(resultado.get(i).getDiscordancias() < (resultado.get(i+1).getDiscordancias())){
					temp = resultado.get(i);
					resultado.set(i,resultado.get(i+1) );
					resultado.set(i+1, temp);
					trocou = true;
				}
			}
		}
		return resultado;
	}		
}
