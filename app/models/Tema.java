package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

@Entity(name = "Tema")
public class Tema {
	@Id
	@GeneratedValue
	@Column
	private long id;

	@Column
	private String nome;

	@ManyToOne
	private Disciplina disciplina;

	@OneToMany(mappedBy = "tema", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Dica> dicas;

	@ElementCollection
	@MapKeyColumn(name = "user_tema")
	@Column(name = "dificuldade")
	@CollectionTable(name = "users_diff", joinColumns = @JoinColumn(name = "tema_id"))
	private Map<String, String> dificuldadeDeUsuario;

	@Column(name = "total_diff")
	private int dificuldade;

	public Tema() {
	}

	public Tema(String name) {
		this.nome = name;
		this.dicas = new ArrayList<Dica>();
		this.dificuldadeDeUsuario = new HashMap<String, String>();
	}

	public void incrementarDificuldade(String userLogin, int dificuldade) {
		if (dificuldadeDeUsuario.containsKey(userLogin)) {
			String previousDiff = dificuldadeDeUsuario.get(userLogin);

			this.dificuldade -= Integer.valueOf(previousDiff);
		}

		this.dificuldade += dificuldade;

		dificuldadeDeUsuario.put(userLogin, String.valueOf(dificuldade));
	}

	public String getNome() {
		return nome;
	}

	public void setName(String nome) {
		this.nome = nome;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public int getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(int dificuldade) {
		this.dificuldade = dificuldade;
	}

	public long getId() {
		return id;
	}

	public List<Dica> getDicas() {
		Collections.sort(dicas);

		for (Dica dica : dicas) {
			dica.checaTipoDica();
		}

		return dicas;
	}

	public void addDica(Dica dica) {
		this.dicas.add(dica);
	}

	public Map<String, String> getDificuldadeDeUsuario() {
		return dificuldadeDeUsuario;
	}

	public String getMediana() {
		List<String> votosDeDificuldade = new ArrayList<String>(
				this.dificuldadeDeUsuario.values());

		Collections.sort(votosDeDificuldade);
		return calcularMediana(votosDeDificuldade);
	}

	private String calcularMediana(List<String> votosDeDificuldade) {
		int totalVotes = votosDeDificuldade.size();
		if (totalVotes == 0) {
			return "0";
		} else if (totalVotes % 2 == 1) {
			String mediana = votosDeDificuldade.get(totalVotes / 2);

			return mediana;
		} else {
			String primeiraMediana = votosDeDificuldade.get(totalVotes / 2);
			String segundaMediana = votosDeDificuldade.get((totalVotes / 2) - 1);

			return String.format("%.2f",
					(Integer.parseInt(primeiraMediana) + Integer
							.parseInt(segundaMediana)) / 2.0);
		}
	}

	public String getMedia() {
		List<String> votosDeDificuldade = new ArrayList<String>(
				this.dificuldadeDeUsuario.values());

		return calcularMedia(votosDeDificuldade);
	}

	private String calcularMedia(List<String> votosDeDificuldade) {
		int totalVotes = votosDeDificuldade.size();
		if (totalVotes == 0) {
			return "0";
		}

		double votesCount = 0;

		for (String vote : votosDeDificuldade) {
			votesCount = votesCount + Double.parseDouble(vote);
		}

		return String.format("%.2f", votesCount / totalVotes);
	}
}
