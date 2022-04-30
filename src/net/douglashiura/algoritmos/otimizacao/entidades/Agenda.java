package net.douglashiura.algoritmos.otimizacao.entidades;

import java.util.Objects;

public class Agenda {

	private Pessoa pessoa;
	private Aeroporto origem;
	private Trecho ida;
	private Trecho volta;
	private Aeroporto destino;

	public Agenda(Pessoa pessoa, Aeroporto origem, Aeroporto destino) {
		this.pessoa = pessoa;
		this.origem = origem;
		this.destino = destino;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(destino, ida, origem, pessoa, volta);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agenda other = (Agenda) obj;
		return Objects.equals(destino, other.destino) && Objects.equals(ida, other.ida)
				&& Objects.equals(origem, other.origem) && Objects.equals(pessoa, other.pessoa)
				&& Objects.equals(volta, other.volta);
	}


	public Pessoa getPessoa() {
		return pessoa;
	}

	public Trecho getIda() {
		return ida;
	}

	public Trecho getVolta() {
		return volta;
	}

	public Aeroporto getOrigem() {
		return origem;
	}

	public void setIda(Trecho trecho) {
		this.ida = trecho;
	}

	public void setVolta(Trecho volta) {
		this.volta = volta;
	}

	public Aeroporto getDestino() {
		return destino;
	}

}
